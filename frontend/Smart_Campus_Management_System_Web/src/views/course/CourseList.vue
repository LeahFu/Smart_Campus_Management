<script setup lang="ts">
import { ref,reactive,toRefs,onMounted} from 'vue'
import {getCourseListApi} from "../../api/course/course.ts";
import { formatTime } from "../../utils/date"
import {ElMessage} from 'element-plus'
import AddCourse from "./components/AddCourse.vue";
const state = reactive({
    // Search keywords
    searchValue: "",
    // Table data
    tableData: [],
    total: 0, // Total items
    pageSize: 10, // Items per page
    pageIndex: 1, // Current page number
    loading: false, // Data loading
})
const {tableData,pageIndex,pageSize,loading,total,searchValue} = toRefs(state)
// Get course list data
const loadData = async (state: any)=> {
    state.loading = true
    // Clear the table data first
    state.tableData=[]
    const params = {
        'pageIndex':state.pageIndex,
        'pageSize': state.pageSize,
        'searchValue': state.searchValue
    }
    const { data } = await getCourseListApi(params)
    state.tableData = data.content
    state.total = data.totalElements
    state.loading = false
}
// Load data after mount
onMounted(() => {
    loadData(state);
})
// Refresh button
const refresh = () => {
    // Search content
    state.searchValue = ""
    // Update data
    loadData(state);
}
// Search
const search = () => {
    if (state.searchValue !== null) {
        loadData(state)
        ElMessage({
            type: 'success',
            message: `Keywords: “${state.searchValue}”. The search content is as follows: `,
        })
    }
}
// The execution event of switching pages (val : the current page number)
const changePage = (val:number) => {
    state.pageIndex = val;
    loadData(state);
}
// Process list data serial number
const Nindex = (index:number) => {
    // current page number - 1 * number of data items per page + 1
    const page = state.pageIndex // current page number
    const pagesize = state.pageSize // number of data items per page
    return index + 1 + (page - 1) * pagesize
}
// Add course pop-up box status
const addCourseDialogFormVisible = ref(false)
// Add course
const addCourse = ()=> {
    addCourseDialogFormVisible.value = true
}
// Close the Add Course popup
const closeAddCourseForm = ()=> {
    addCourseDialogFormVisible.value = false
}
// Submit form callback function
const success = ()=> {
    loadData(state);
    closeAddCourseForm()
}
</script>

<template>
    <el-card class="box-card">
        <!--Header start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon><Tickets /></el-icon>Course Management
                </h3>

                <!--Search area start-->
                <div class="card-search">
                    <el-row :gutter="10">
                        <el-col :span="10">
                            <el-input :prefix-icon="Search" v-model="searchValue" @keyup.enter.native="search"
                                      placeholder="Keywords search (Enter)"/>
                        </el-col>
                        <el-col :span="11">
                            <div class="my-button">
                                <el-button plain style="width: 100%;" color="#2fa7b9" @click="addCourse">Add course</el-button>
                            </div>
                        </el-col>
                        <el-col :span="3" style="display: inline-flex;justify-content: center;align-items: center; cursor: pointer;">
                            <el-icon style="font-size: 20px;color: #b3b6bc;" @click="refresh">
                                <Refresh />
                            </el-icon>
                        </el-col>
                    </el-row>
                </div>
                <!--Search area end-->
            </div>
        </template>
        <!--Header end-->

        <!--Form area start-->
        <div class="table-box">
            <el-table element-loading-text="Loading..." v-loading="loading" :data="tableData"
                      style="width: 100%;text-align: center" :cell-style="{textAlign: 'center'}"
                      :row-class-name="rowClassName"
                      :header-cell-style="{fontSize: '15px', background: '#178557',color: 'white',textAlign: 'center'}">

                <el-table-column label="serial number" width="100" type="index" :index="Nindex"/>
                <el-table-column label="Course No.">
                    <template #default="scope">
                        <span>{{scope.row.courseno}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Course name">
                    <template #default="scope">
                        <span>{{scope.row.coursename}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="created time">
                    <template #default="scope">
                        <span class="highlight">{{formatTime(scope.row.createTime, 'yyyy-MM-dd')}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="opreate">
                    <template #default="scope">
                        <el-button size="small" @click="editCourse(scope.row.id)"
                                   style="margin: 0 0 10px 10px;">Edit</el-button>
                        <el-popconfirm confirm-button-text="Submit" cancel-button-text="Cancel" :icon="Delete"
                                       icon-color="#626AEF" :title="'Are you sure you want to delete “'+scope.row.coursename+'” ?'"
                                       @confirm="delCourse(scope.row.id)">
                            <template #reference>
                                <el-button size="small" type="danger" style="margin-bottom: 10px;">Delete</el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>

            </el-table>
        </div>
        <!--Form area end-->

        <!--Pagination start-->
        <el-pagination background layout="total, sizes, prev, pager, next, jumper" :total="total"
                       v-model:page-size="pageSize"
                       @current-change="changePage"
                       :page-sizes="[10, 20, 30, 40]"/>
        <!--Pagination end-->
    </el-card>

    <!--Add course pop-up box start-->
    <el-dialog  align-center v-model="addCourseDialogFormVisible"  width="42%" destroy-on-close>
        <template #header="{ close, titleId, titleClass }">
            <div class="my-header">
                <el-icon size="26px"><EditPen /></el-icon>
                <h1 id="titleId">{{addTitle}}</h1>
            </div>
        </template>
        <!--Add course components start-->
        <AddCourse @closeAddCourseForm="closeAddCourseForm" @success="success"/>
        <!--Add course components end-->
    </el-dialog>
    <!--Add course pop-up box end-->
</template>

<style scoped>
.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.card-header h3 {
    display: inline-flex;
    justify-content: center;
    align-items: center;
}
:deep(.el-card__header) {
    border-bottom: 1px solid rgb(238 238 238);
    color: #178557;
}
/*Pagination style*/
:deep(.el-pagination.is-background .el-pager li:not(.is-disabled).is-active) {
    background-color: #178557;
}

.el-pagination {
    margin-top: 20px;
    justify-content: center;
}
/*Customize the header style of the course pop-up box*/
.my-header {
    display: flex;
    justify-content: flex-start;
    color: #178557;
}
</style>