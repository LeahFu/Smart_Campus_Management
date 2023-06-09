<script setup lang="ts">
import { reactive, toRefs, ref, onMounted } from 'vue'
import {deleteStudentApi, getStudentApi, getStudentListApi} from "../../api/student/student"
import { formatTime } from "../../utils/date"
import {ElMessage} from 'element-plus'
import AddStudent from "./components/AddStudent.vue"
import EditStudent from "./components/EditStudent.vue"
import {exportExcel} from "../../utils/exportExcel.ts";
import {Delete, Download, EditPen, Search, User} from "@element-plus/icons-vue";
const state = reactive({
    // Search form content
    searchValue: "",
    // All information on the form
    tableData: [],
    total: 0,  // total number of items
    pageSize: 10,  // rows per page
    pageIndex: 1,  // current page number
    loading: false, // data loading
})
const {tableData,pageIndex,pageSize,loading,total,searchValue} = toRefs(state)
// Get student list data
const loadData = async (state: any)=> {
    state.loading = true
    // Clear the data first
    state.tableData=[]
    const params = {
        'pageIndex':state.pageIndex,
        'pageSize': state.pageSize,
        'searchValue': state.searchValue
    }
    const { data } = await getStudentListApi(params)
    state.tableData = data.content
    state.total = data.totalElements
    state.loading = false
}
// Refresh student list data
const refresh = () => {
    // Empty keywords
    state.searchValue = ""
    // Refresh data
    loadData(state);
}
// Search
const search = () => {
    if (state.searchValue !== null) {
        ElMessage({
            type: 'success',
            message: `Keywords: “${state.searchValue}” The search content is as follows`,
        })
        loadData(state)
    }
}
// The execution event of switching pages (val : the current page number)
const changePage = (val:number) => {
    state.pageIndex = val;
    loadData(state);
}
// Process the data sequence number of the list
const Nindex = (index) => {
    //  (Current page number - 1) * number of data items per page + 1
    const page = state.pageIndex // Current page number
    const pageSize = state.pageSize // Number of data items per page
    return index + 1 + (page - 1) * pageSize
}
// Add student pop-up box status
const addStudentDialogFormVisible = ref(false)
// Add student
const addStudent = ()=> {
    addStudentDialogFormVisible.value = true
}
// Close (add student) pop-up box
const closeAddStudentForm = ()=> {
    addStudentDialogFormVisible.value = false
}
// Submit form callback function
const success = ()=> {
    loadData(state)
    closeAddStudentForm()
    closeEditStudentForm()
}
// Edit student pop-up box status
const editStudentDialogFormVisible = ref(false)
// Edit student information
const studentInfo = ref()
const editStudent = async (id:number)=> {
    const { data } = await getStudentApi(id)
    studentInfo.value = data.result
    editStudentDialogFormVisible.value = true
}
// Close edit student pop-up box
const closeEditStudentForm = ()=> {
    editStudentDialogFormVisible.value = false
}
// Delete student information
const delStudent = async (id:number)=> {
    const { data } = await deleteStudentApi(id)
    if(data.status===200){
        ElMessage.success('Successfully deleted')
        await loadData(state);
    }else {
        ElMessage.error('Failed to delete')
    }
}
// Export list
const column = [
    {name: 'id',label: 'student id'},
    {name: 'name',label: 'student name'},
    {name: 'stuno',label: 'student number'},
    {name: 'gender',label: 'gender'},
    {name: 'phone',label: 'phone'},
    {name: 'email',label: 'email'}
]
// Export data
const exportExcelAction = () => {
    exportExcel({
        column,
        data:state.tableData,
        filename: 'student information',
        format: 'xlsx',
        autoWidth: true,
    })
}
// Load data after mount
onMounted(() => {
    loadData(state);
})
</script>

<template>
    <el-card class="box-card">
        <!--Header start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><User/></el-icon>Student Management
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
                                <el-button plain style="width: 100%;" color="#2fa7b9" @click="addStudent">Add student</el-button>
                                <el-button @click="exportExcelAction" type="primary">
                                    <el-icon style="margin-right: 1px"><Download/></el-icon>Export Excel
                                </el-button>
                            </div>
                        </el-col>
                        <el-col :span="3" style="display: inline-flex;justify-content: center;align-items: center;cursor: pointer;">
                            <el-icon style="font-size: 20px;color: #b3b6bc;" @click="refresh"><Refresh/></el-icon>
                        </el-col>
                    </el-row>
                </div>
                <!--Search area end-->
            </div>
        </template>
        <!--Header end-->
        <!--Table-box start-->
        <div class="table-box">
            <el-table element-loading-text="Loading..." v-loading="loading" :data="tableData"
                      style="width: 100%;text-align: center" :cell-style="{textAlign: 'center'}"
                      :row-class-name="rowClassName"
                      :header-cell-style="{fontSize: '15px', background: '#178557',color: 'white',textAlign: 'center'}">

                <el-table-column label="Serial num" width="100" type="index" :index="Nindex"/>
                <el-table-column label="Student NO.">
                    <template #default="scope">
                        <span>{{scope.row.stuno}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Name">
                    <template #default="scope">
                        <span>{{scope.row.name}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Class">
                    <template #default="scope">
                        <span>{{scope.row.gradeClass.code}}</span>
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
                        <el-tooltip :content="scope.row.createdTime" placement="top" effect="light">
                            <span class="highlight">{{formatTime(scope.row.createdTime, 'yyyy-MM-dd')}}</span>
                        </el-tooltip>
                    </template>
                </el-table-column>

                <el-table-column label="Operate">
                    <template #default="scope">
                        <el-button size="small" @click="editStudent(scope.row.id)"
                                   style="margin: 0 0 10px 10px;">Edit</el-button>
                        <el-popconfirm confirm-button-text="Submit" cancel-button-text="Cancel" :icon="Delete"
                                       icon-color="#626AEF" :title="'Are you sure to delete student “'+scope.row.name+'” ？'"
                                       @confirm="delStudent(scope.row.id)">
                            <template #reference>
                                <el-button size="small" type="danger" style="margin-bottom: 10px;">Delete</el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>

            </el-table>
        </div>
        <!--Table-box  end-->
        <!--Pagination start-->
        <el-pagination background layout="total, sizes, prev, pager, next, jumper" :total="total"
                       v-model:page-size="pageSize"
                       @current-change="changePage"
                       :page-sizes="[10, 20, 30, 40]"/>
        <!--Pagination end-->
    </el-card>

    <!--Add student pop-up box start-->
    <el-dialog  align-center v-model="addStudentDialogFormVisible"  width="42%" destroy-on-close>
        <template #header="{ close, titleId, titleClass }">
            <div class="my-header">
                <el-icon size="26px"><EditPen /></el-icon>
                <h1 id="titleId">Add Student</h1>
            </div>
        </template>
        <!--Add student component start-->
        <AddStudent  @closeAddStudentForm="closeAddStudentForm" @success="success"/>
        <!--Add student component end-->
    </el-dialog>
    <!--Add student pop-up box end-->

    <!--Edit student pop-up box start-->
    <el-dialog  align-center v-model="editStudentDialogFormVisible"  width="42%" destroy-on-close>
        <template #header="{ close, titleId, titleClass }">
            <div class="my-header">
                <el-icon size="26px"><EditPen /></el-icon>
                <h1 id="titleId">Edit Student</h1>
            </div>

        </template>
        <!--Edit student component start-->
        <EditStudent :studentInfo="studentInfo" @closeEditStudentForm="closeEditStudentForm" @success="success" />
        <!--Edit student component end-->
    </el-dialog>
    <!--Edit student pop-up box end-->
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
    /*Add student pop-up box -- custom header styles*/
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