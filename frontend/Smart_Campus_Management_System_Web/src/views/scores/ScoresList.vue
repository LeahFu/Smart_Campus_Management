<script setup lang="ts">
import { ref, reactive, onMounted, toRefs } from 'vue'
import {gradeClassListApi} from "../../api/student/student.ts";
import {getAllCourseListApi} from "../../api/teacher/teacher.ts";
import {getScoresListApi} from "../../api/scores/scores.ts";
import { formatTime } from "../../utils/date"
import {ElMessage} from 'element-plus'

// Define class dropdown selections
const gradeClassOptions = ref<object[]>([])
// Define class id
const gradeClassId = ref(null)
// Get a list of all classes
const gradeClassList() = async ()=>{
    const {data} = await gradeClassListApi()
    gradeClassOptions.value = data.result;
}
// Define course dropdown selections
const courseOptions = ref<object[]>([])
// Define course id
const courseId = ref(null)
// Get a list of all classes
const getAllCourseList() = async ()=>{
    const {data} = await getAllCourseListApi()
    courseOptions.value = data.result;
}
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
    // Course ID
    courseId.value = ''
    // Class ID
    gradeClassId.value = ''
    // Search keywords
    state.searchValue = ''
    // Refresh data
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
</script>

<template>
    <el-card class="box-card">
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

                <el-table-column label="Grades">
                    <template #default="scope">
                        <span>{{scope.row.score}}</span>
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
</style>