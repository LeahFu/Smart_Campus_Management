<script setup lang="ts">
import { ref, reactive, onMounted, toRefs } from 'vue'
import {gradeClassListApi} from "../../api/student/student.ts";
import {getAllCourseListApi} from "../../api/teacher/teacher.ts";
import {editScoresApi, getScoresListApi, registerScoresApi, deleteScoresApi} from "../../api/scores/scores.ts";
import { formatTime } from "../../utils/date"
import {ElMessage} from 'element-plus'
import {exportExcel} from "../../utils/exportExcel.ts";
import {Delete, Download, Money, Search} from "@element-plus/icons-vue";

// Define class dropdown selections
const gradeClassOptions = ref<object[]>([])
// Define class id
const gradeClassId = ref()
// Get a list of all classes
const gradeClassList = async ()=>{
    const { data } = await gradeClassListApi()
    gradeClassOptions.value = data.result
}
/*async function gradeClassList() {
    try {
        const { data } = await gradeClassListApi()
        if (data.status === 200) {
            gradeClassOptions.value = data.result
        }
    } catch (e) {
        console.log(e)
    }
}*/
// Define course dropdown selections
const courseOptions = ref<object[]>([])
// Define course id
const courseId = ref()
// Get a list of all courses
const getAllCourseList = async ()=>{
    const { data } = await getAllCourseListApi()
    courseOptions.value = data.result
}
/*async function getAllCourseList() {
    try {
        const { data } = await getAllCourseListApi()
        if (data.status === 200) {
            courseOptions.value = data.result
        }
    } catch (e) {
        console.log(e)
    }
}*/
//Load data after mount
onMounted(() => {
    loadData(state)
    getAllCourseList()
    gradeClassList()
})
const state = reactive({
    stuno:'',
    name:'',
    tableData: [],
    // Total items
    total: 0,
    // Number of items displayed per page
    pageSize: 10,
    // Current page number
    pageIndex: 1,
    // Data loading
    loading: false,
})
const {tableData,pageIndex,pageSize,loading,total,stuno,name} = toRefs(state)
// Get grade list data
const loadData = async (state: any)=> {
    state.loading = true
    // Clear the data first
    state.tableData=[]
    const params = {
        'pageIndex':state.pageIndex,
        'pageSize': state.pageSize,
        'name': state.name,
        'stuno': state.stuno,
        'courseId': courseId.value,
        'gradeClassId': gradeClassId.value
    }
    const { data } = await getScoresListApi(params)
    state.tableData = data.content
    state.total = data.totalElements
    state.loading = false
}
// Refresh list data
const refresh = () => {
    courseId.value = ''
    gradeClassId.value = ''
    state.name = ''
    state.stuno = ''
    loadData(state);
}
const search = () => {
    if (state.name !== null&&state.name !== "") {
        ElMessage({
            type: 'success',
            message: `Student name: “${state.name}”. The search content is as follows:`,
        })
        loadData(state)
    }

    if (state.stuno !== null&&state.stuno !== "") {
        ElMessage({
            type: 'success',
            message: `Student number: “${state.stuno}”. The search content is as follows:`,
        })
        loadData(state)
    }
}
// Change page event
const changePage = (val:number) => {
    state.pageIndex = val
    loadData(state)
}
// Process serial number
const Nindex = (index:number) => {
    // Current page number - 1 * number of data items per page + 1
    const page = state.pageIndex // Current page number
    const pagesize = state.pageSize // Number of data items per page
    return index + 1 + (page - 1) * pagesize
}
// Register class course grades
const registerScores = async () => {
    if(gradeClassId.value < 1){
        ElMessage.error('Please select a class')
        return false
    }
    if(courseId.value < 1){
        ElMessage.error('Please select a course')
        return false
    }
    const { data } =  await registerScoresApi(gradeClassId.value,courseId.value)
    if(data.status===200){
        await loadData(state)
        ElMessage.success(data.message)
    }else {
        ElMessage.error(data.message)
    }
}
// Defines whether the cell is editable
const edit = ref(false)
// Double click cell function
const celldblclick = (row, column, cell, event)=> {
    const scoreTarget: any = state.tableData.find(item => {
        return item.id === row.id
    })
    if (scoreTarget !== undefined) {
        scoreTarget._originalData = { ...scoreTarget }
        scoreTarget.edit = !scoreTarget.edit
    }
}
// Cancel edit
const cancel = (key: any) => {
    key._originalData = { ...key }
    key.edit = !key.edit
}
// Save edit scores
const editScores = async (record: { id: any; score: any; })=> {
    loading.value = true
    const { id,score } = record
    if(!score){
        loading.value = false
        ElMessage.error('Submission failed, please change grades!')
        return
    }
    const { data } = await editScoresApi(id,score)
    if(data.status===200){
        ElMessage.success(data.message)
        await loadData(state)
    }else {
        ElMessage.error(data.message)
    }
    loading.value = false
}
// 删除成绩信息
const delScores = async (id:number)=> {
    const { data } = await deleteScoresApi(id)
    if(data.status===200){
        ElMessage.success('Successfully deleted')
        await loadData(state)
    }else {
        ElMessage.error('Failed to delete')
    }
}
// Export list
const column = [
    {name: 'id',label: 'Grades id'},
    {name: 'stuno',label: 'Student number'},
    {name: 'name',label: 'Student name'},
    {name: 'coursename',label: 'Course name'},
    {name: 'score',label: 'Score'},
    {name: 'type',label: 'Type'}
]
const exportExcelAction = () => {
    const newTableData = state.tableData.flatMap((item: any)=> {
        return {...item,...item.course,...item.student}
    })
    exportExcel({
        column,
        data:newTableData,
        filename: 'Class Subject Grade Data',
        format: 'xlsx',
        autoWidth: true,
    })
}
</script>

<template>
    <el-card>
        <!--Header start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><Money /></el-icon>Grades
                </h3>

                <!--Search area start-->
                <div class="card-search">
                    <el-row :gutter="12">
                        <el-col :span="5">
                            <el-select v-model="gradeClassId" placeholder="Please select a class" style="width: 100%;">
                                <el-option v-for="item in gradeClassOptions" :key="item.id" :label="item.name" :value="item.id" />
                            </el-select>
                        </el-col>
                        <el-col :span="5">
                            <el-select v-model="courseId" placeholder="Please select a course" style="width: 100%;">
                                <el-option v-for="item in courseOptions" :key="item.id" :label="item.name" :value="item.id" />
                            </el-select>
                        </el-col>
                        <el-col :span="6">
                            <el-button plain  color="#2fa7b9" @click="registerScores">Registration score</el-button>
                            <el-button plain @click="exportExcelAction" type="primary">
                                <el-icon style="margin-right: 1px"><Download /></el-icon>Export Excel
                            </el-button>
                        </el-col>
                        <el-col :span="3">
                            <el-input :prefix-icon="Search" v-model="stuno" @keyup.enter.native="search"
                                      placeholder="Student number search (Enter)"/>
                        </el-col>
                        <el-col :span="3">
                            <el-input :prefix-icon="Search" v-model="name" @keyup.enter.native="search"
                                      placeholder="Student name search (Enter)"/>
                        </el-col>
                        <el-col :span="2" style="display: inline-flex;justify-content: center;align-items: center; cursor: pointer;">
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
            <el-table row-key="id"  element-loading-text="Loading..." v-loading="loading" :data="tableData"
                      style="width: 100%;text-align: center" :cell-style="{textAlign: 'center'}"
                      :header-cell-style="{fontSize: '15px', background: '#178557',color: 'white',textAlign: 'center'}"
                      @cell-dblclick="celldblclick">

                <el-table-column label="Serial number" width="100" type="index" :index="Nindex"/>
                <el-table-column label="Student number" >
                    <template #default="scope">
                        <span>{{scope.row.student.stuno}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Student name" >
                    <template #default="scope">
                        <span>{{scope.row.student.name}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Course">
                    <template #default="scope">
                        <span>{{scope.row.course.coursename}}</span>
                    </template>
                </el-table-column>

                <el-table-column>
                    <!-- Custom header -->
                    <template #header>
                        <span>Grades</span>
                        <el-icon><Edit /></el-icon>
                    </template>
                    <!-- Customize cell content-->
                    <template #default="scope">
                        <div class="edit-score" v-if="scope.row.edit">
                            <el-input
                                clearable
                                placeholder="Please enter"
                                v-model="scope.row.score"
                            ></el-input>
                            <el-button type="success" size="small" @click="editScores(scope.row)"
                                       style="margin: 0 0 10px 10px;">Save</el-button>
                            <el-button type="warning" size="small" @click="cancel(scope.row)"
                                       style="margin: 0 0 10px 10px;">Cancel</el-button>
                        </div>
                        <span  v-else>
                            {{scope.row.score}}
                            <el-tooltip content="Double click to edit" placement="top" effect="light">
                                <el-icon style="cursor:pointer;"><Edit /></el-icon>
                            </el-tooltip>
                        </span>
                    </template>
                </el-table-column>

                <el-table-column label="Type" prop="type">
                    <template #default="scope">
                        <span>{{scope.row.type}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Created time">
                    <template #default="scope">
                            <span>{{formatTime(scope.row.createTime, 'yyyy-MM-dd')}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Operate">
                    <template #default="scope">
                        <el-popconfirm confirm-button-text="Submit" cancel-button-text="Cancel" :icon="Delete"
                                       icon-color="#626AEF" :title="'Are you sure you want to delete “'+scope.row.student.name+'”\'s grades?'"
                                       @confirm="delScores(scope.row.id)">
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
/*修改v-loading样式*/
:deep(.el-loading-spinner .el-loading-text){
    color: #178557;
}
:deep(.el-loading-spinner .path){
    stroke: #178557;
}
/*Pagination style*/
:deep(.el-pagination.is-background .el-pager li:not(.is-disabled).is-active) {
    background-color: #178557;
}
.el-pagination {
    margin-top: 20px;
    justify-content: center;
}
/*Custom edit cell style*/
.edit-score {
    display: flex;
    justify-content: space-between;
    align-items: baseline;
}
</style>