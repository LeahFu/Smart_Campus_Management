<script setup lang="ts">
import { ref,reactive,toRefs,onMounted } from 'vue'
import {getTeacherListApi} from "../../api/teacher/teacher.ts";
import { formatTime } from "../../utils/date"
import {ElMessage} from 'element-plus'
import AddTeacher from "./components/AddTeacher.vue";
import EditTeacher from "./components/EditTeacher.vue";
import {exportExcel} from "../../utils/exportExcel.ts";

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
// Get teacher list data
const loadData = async (state: any)=> {
    state.loading = true
    // Clear the data
    state.tableData=[]
    const params = {
        'pageIndex':state.pageIndex,
        'pageSize': state.pageSize,
        'searchValue': state.searchValue
    }
    const { data } = await getTeacherListApi(params)
    state.tableData = data.content
    state.total = data.totalElements
    state.loading = false
}
//Load data after mount
onMounted(() => {
    loadData(state);
})
// Refresh button
const refresh = () => {
    // Search keywords
    state.searchValue = ""
    // Load data
    loadData(state);
}
// Search
const search = () => {
    if (state.searchValue !== null) {
        ElMessage({
            type: 'success',
            message: `Keywords : “${state.searchValue}”. The search content is as follows : `,
        })
        loadData(state)
    }
}
// The execution event of switching pages ( val: the current page number)
const changePage = (val) => {
    state.pageIndex = val;
    loadData(state);
}
// Process list number
const Nindex = (index) => {
    // Current page number - 1 * number of data items per page + 1
    const page = state.pageIndex // Current page number
    const pagesize = state.pageSize // Number of data items per page
    return index + 1 + (page - 1) * pagesize
}
// Add teacher pop-up box status
const addTeacherDialogFormVisible = ref(false)
const addTitle = ref('Add teacher')
//Add teacher
const addTeacher = ()=> {
    addTeacherDialogFormVisible.value = true
}
// Close (add teacher) pop-up box
const closeAddTeacherForm = ()=> {
    addTeacherDialogFormVisible.value = false
}
// Edit teacher pop-up box status
const editTeacherDialogFormVisible = ref(false)
const editTitle = ref('Edit teacher')
// Edit teacher information
const teacherInfo = ref()
const editTeacher = async (id:number)=> {
    const { data } = await getTeacherApi(id)
    teacherInfo.value = data.result
    editTeacherDialogFormVisible.value = true
}
// Close edit teacher pop-up box
const closeEditTeacherForm = ()=> {
    editTeacherDialogFormVisible.value = false
}
// Submit form callback function
const success = ()=> {
    loadData(state);
    closeAddTeacherForm();
    closeEditTeacherForm();
}
// Delete teacher information
const delTeacher = async (id:number)=> {
    const { data } = await deleteTeacherApi(id)
    if(data.status===200){
        ElMessage.success('Successfully deleted')
        await loadData(state);
    }else {
        ElMessage.error('Failed to delete')
    }
}
// Export list
const column = [
    {name: 'id',label: 'Teacher id'},
    {name: 'name',label: 'Teacher name'},
    {name: 'teachno',label: 'Teacher number'},
    {name: 'gender',label: 'Gender'},
    {name: 'phone',label: 'Phone number'},
    {name: 'email',label: 'Email'}
]
// Export teacher information
const exportExcelAction = () => {
    exportExcel({
        column,
        data:state.tableData,
        filename: 'Teacher information data',
        format: 'xlsx',
        autoWidth: true,
    })
}
</script>

<template>
    <el-card class="box-card">
        <!--Header start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><UserFilled /></el-icon>Teacher Management
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
                                <el-button plain style="width: 100%;" color="#2fa7b9" @click="addTeacher">Add teacher</el-button>
                                <el-button @click="exportExcelAction" type="primary">
                                    <el-icon style="margin-right: 1px"><Download /></el-icon>Export Excel
                                </el-button>
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

        <!--Table-box area start-->
        <div class="table-box">
            <el-table element-loading-text="Loading..." v-loading="loading" :data="tableData"
                      style="width: 100%;text-align: center" :cell-style="{textAlign: 'center'}"
                      :row-class-name="rowClassName"
                      :header-cell-style="{fontSize: '15px', background: '#178557',color: 'white',textAlign: 'center'}">

                <el-table-column label="Serial number" width="100" type="index" :index="Nindex"/>
                <el-table-column label="Teacher ID">
                    <template #default="scope">
                        <span>{{scope.row.teachno}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Teacher name">
                    <template #default="scope">
                        <span>{{scope.row.name}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Course name">
                    <template #default="scope">
                        <span>{{scope.row.course.coursename}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Gender">
                    <template #default="scope">
                        <span>{{scope.row.gender}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Phone">
                    <template #default="scope">
                        <span>{{scope.row.phone}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Email">
                    <template #default="scope">
                        <span>{{scope.row.email}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Created time">
                    <template #default="scope">
                            <span>{{formatTime(scope.row.createTime, 'yyyy-MM-dd')}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Operate">
                    <template #default="scope">
                        <el-button size="small" @click="editTeacher(scope.row.id)"
                                   style="margin: 0 0 10px 10px;">Edit</el-button>
                        <el-popconfirm confirm-button-text="Submit" cancel-button-text="Cancel" :icon="Delete"
                                       icon-color="#626AEF" :title="'Are you sure you want to delete “'+scope.row.name+'” ?'"
                                       @confirm="delTeacher(scope.row.id)">
                            <template #reference>
                                <el-button size="small" type="danger" style="margin-bottom: 10px;">Delete</el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <!--Table-box area end-->

        <!--Pagination start-->
        <el-pagination background layout="total, sizes, prev, pager, next, jumper" :total="total"
                       v-model:page-size="pageSize"
                       @current-change="changePage"
                       :page-sizes="[10, 20, 30, 40]"/>
        <!--Pagination end-->
    </el-card>

    <!--Add teacher pop-up box start-->
    <el-dialog  align-center v-model="addTeacherDialogFormVisible"  width="42%" destroy-on-close>
        <template #header="{ close, titleId, titleClass }">
            <div class="my-header">
                <el-icon size="26px"><EditPen /></el-icon>
                <h1 id="titleId">{{addTitle}}</h1>
            </div>

        </template>
        <!--Add teacher component start-->
        <AddTeacher @closeAddTeacherForm="closeAddTeacherForm" @success="success"/>
        <!--Add teacher component end-->
    </el-dialog>
    <!--Add teacher pop-up box end-->

    <!--Edit teacher pop-up box start-->
    <el-dialog  align-center v-model="editTeacherDialogFormVisible"  width="42%" destroy-on-close>
        <template #header="{ close, titleId, titleClass }">
            <div class="my-header">
                <el-icon size="26px"><EditPen /></el-icon>
                <h1 id="titleId">{{editTitle}}</h1>
            </div>
        </template>
        <!--Edit teacher component start-->
        <EditTeacher :teacherInfo="teacherInfo" @closeEditTeacherForm="closeEditTeacherForm" @success="success"/>
        <!--Edit teacher component end-->
    </el-dialog>
    <!--Edit teacher pop-up box end-->
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
.el-pagination {
    margin-top: 20px;
    justify-content: center;
}
:deep(.el-pagination.is-background .el-pager li:not(.is-disabled).is-active) {
    background-color: #178557;
}
/*Custom header style - add teacher pop-up box*/
.my-header {
    display: flex;
    justify-content: flex-start;
    color: #178557;
}
/*Custom button style*/
.my-button {
    display: flex;
    justify-content:space-between;
}
</style>