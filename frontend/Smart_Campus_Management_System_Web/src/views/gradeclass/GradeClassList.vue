<script setup lang="ts">
import { reactive,toRefs, ref, onMounted } from 'vue'
import {Search} from "@element-plus/icons-vue";
import {formatTime} from "../../utils/date.ts";
import {ElMessage} from 'element-plus'
import {getGradeClassApi, getGradeClassListApi} from "../../api/gradeclass/gradeclass.ts";
import AddGradeClass from "./components/AddGradeClass.vue";
import EditGradeClass from "./components/EditGradeClass.vue";
import {exportExcel} from "../../utils/exportExcel.ts";

const state = reactive({
    // Search form content
    searchValue: "",
    // All information on the form
    tableData: [],
    total: 0, // total number of items
    pageSize: 10, // rows per page
    pageIndex: 1, // current page number
    loading: false, // data loading
})
// Get class list data
const loadData = async (state: any)=> {
    state.loading = true
    // Clear the data first
    state.tableData=[]
    const params = {
        'pageIndex':state.pageIndex,
        'pageSize': state.pageSize,
        'searchValue': state.searchValue
    }
    const { data } = await getGradeClassListApi(params)
    state.tableData = data.content
    state.total = data.totalElements
    state.loading = false
}
// Refresh button
const refresh = () => {
    // Search form content
    state.searchValue = ""
    // Refresh data
    loadData(state);
}
// Search
const search = () => {
    if (state.searchValue !== null) {
        ElMessage({
            type: 'success',
            message: `Keywords“${state.searchValue}”The search content is as follows`,
        })
        loadData(state)
    }
}
// Execution event for switching pages (val : current page number)
const changePage = (val) => {
    state.pageIndex = val;
    loadData(state);
}
// Process the data sequence number of the list
const Nindex = (index) => {
    // Current page number - 1 * number of data items per page + 1
    const page = state.pageIndex // current page number
    const pagesize = state.pageSize // number of data items per page
    return index + 1 + (page - 1) * pagesize
}
// Add class
const addGradeClass = ()=> {
    addGradeClassDialogFormVisible.value = true
}
// Add class pop-up box status
const addGradeClassDialogFormVisible = ref(false)
// Close add class pop-up box
const closeAddGradeClassForm = ()=> {
    addGradeClassDialogFormVisible.value = false
}
// Edit class popup status
const editGradeClassDialogFormVisible = ref(false)
// Edit class information
const gradeClassInfo = ref()
const editGradeClass = async (id:number)=> {
    const { data } = await getGradeClassApi(id)
    gradeClassInfo.value = data.result
    editGradeClassDialogFormVisible.value = true
}
// Close edit class popup
const closeEditGradeClassForm = ()=> {
    editGradeClassDialogFormVisible.value = false
}
// Submit form callback function
const success = ()=> {
    loadData(state);
    closeAddGradeClassForm()
    closeEditGradeClassForm()
}
// Delete class information
const delGradeClass = async (id:number)=> {
    const { data } = await deleteGradeClassApi(id)
    if(data.status===200){
        ElMessage.success('Successfully deleted')
        await loadData(state);
    }else {
        ElMessage.error('Failed to delete')
    }
}
// Export list
const column = [
    {name: 'id',label: 'class id'},
    {name: 'name',label: 'class name'},
    {name: 'code',label: 'class code'},
    {name: 'grade',label: 'grade'},
    {name: 'clazz',label: 'class'},
    {name: 'remarks',label: 'remarks'}
]
const exportExcelAction = () => {
    exportExcel({
        column,
        data:state.tableData,
        filename: 'class information data',
        format: 'xlsx',
        autoWidth: true,
    })
}
const {tableData,pageIndex,pageSize,loading,total,searchValue} = toRefs(state)
//Load data after mount
onMounted(() => {
    loadData(state);
})
</script>

<template>
    <el-card class="box-card">
        <!--Head start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><UserFilled /></el-icon>Class Management
                </h3>

                <!--Search area start-->
                <div class="card-search">
                    <el-row :gutter="10">
                        <el-col :span="10">
                            <el-input :prefix-icon="Search" v-model="searchValue" @keyup.enter.native="search"
                                      placeholder="Keywords search（Enter）"/>
                        </el-col>
                        <el-col :span="11">
                            <div class="my-button">
                                <el-button plain style="width: 100%;" color="#2fa7b9" @click="addGradeClass">Add Class</el-button>
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
        <!--Head end-->
        <!--Table-box area start-->
        <div class="table-box">
            <el-table element-loading-text="Loading..." v-loading="loading" :data="tableData"
                      style="width: 100%;text-align: center" :cell-style="{textAlign: 'center'}"
                      :row-class-name="rowClassName"
                      :header-cell-style="{fontSize: '15px', background: '#178557',color: 'white',textAlign: 'center'}">

                <el-table-column label="serial number" width="100" type="index" :index="Nindex"/>

                <el-table-column label="class code">
                    <template #default="scope">
                        <span>{{scope.row.code}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="class name">
                    <template #default="scope">
                        <span>{{scope.row.name}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="grade">
                    <template #default="scope">
                        <span>{{scope.row.grade}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="class">
                    <template #default="scope">
                        <span>{{scope.row.clazz}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="class size">
                    <template #default="scope">
                        <span>{{scope.row.students.length}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="created time">
                    <template #default="scope">
                        <el-tooltip :content="formatTime(scope.row.createTime, 'yyyy-MM-dd')" placement="top" effect="light">
                            <span class="highlight">{{formatTime(scope.row.createTime, 'yyyy-MM-dd')}}</span>
                        </el-tooltip>
                    </template>
                </el-table-column>

                <el-table-column label="operate">
                    <template #default="scope">
                        <el-button size="small" @click="editGradeClass(scope.row.id)"
                                   style="margin: 0 0 10px 10px;">Edit</el-button>
                        <el-popconfirm confirm-button-text="Submit" cancel-button-text="Cancel"
                                       icon-color="#626AEF" :title="'Are you sure you want to delete class “'+scope.row.name+'” ？'"
                                       @confirm="delGradeClass(scope.row.id)">
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
    <!--Add class pop-up box start-->
    <el-dialog  align-center v-model="addGradeClassDialogFormVisible"  width="42%" destroy-on-close>
        <template #header="{ close, titleId, titleClass }">
            <div class="my-header">
                <el-icon size="26px"><EditPen /></el-icon>
                <h1 id="titleId">{{addTitle}}</h1>
            </div>

        </template>
        <!--Add class components start-->
        <AddGradeClass @closeAddGradeClassForm="closeAddGradeClassForm" @success="success"/>
        <!--Add class components end-->
    </el-dialog>
    <!--Add class pop-up box end-->

    <!--Edit class pop-up box start-->
    <el-dialog  align-center v-model="editGradeClassDialogFormVisible"  width="42%" destroy-on-close>
        <template #header="{ close, titleId, titleClass }">
            <div class="my-header">
                <el-icon size="26px"><EditPen /></el-icon>
                <h1 id="titleId">{{editTitle}}</h1>
            </div>

        </template>
        <!--Edit class components start-->
        <EditGradeClass :gradeClassInfo="gradeClassInfo" @closeEditGradeClassForm="closeEditGradeClassForm" @success="success"/>
        <!--Edit class components end-->
    </el-dialog>
    <!--Edit class pop-up box end-->
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
/*Add class pop-up box -- customize header style*/
.my-header {
    display: flex;
    justify-content: flex-start;
}

/*Custom button style*/
.my-button {
    display: flex;
    justify-content:space-between;
}
</style>