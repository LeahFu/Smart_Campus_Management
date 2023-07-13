<script setup lang="ts">
import {onMounted, ref} from 'vue'
import {gradeClassListApi} from "../../api/student/student";
import {getAllCourseListApi} from "../../api/teacher/teacher.ts";
import ScoreCensusPie from "./components/ScoreCensusPie.vue";
import {getScoreCensusApi} from "../../api/census/census.ts";

// Define the class ID
const gradeClassId = ref()
// Class dropdown selection
const gradeClassOptions = ref<object[]>([])
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
// Define the course ID
const courseId = ref()
// Course dropdown selection
const courseOptions = ref<object[]>([])
// Get a list of all courses
const getAllCourseList = async ()=>{
    const {data} = await getAllCourseListApi()
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
// Statistical class subject grades
const legendData = ref(["A","B","C","D","F"])
const seriesData = ref([])
const getScoreCensus = async ()=> {
    const { data } = await getScoreCensusApi(courseId.value,gradeClassId.value)
   // if(data.status===200) {
        seriesData.value = data.result
    //    console.log(`data.result is: ${data.result}`)}
}
// Monitor class changes
const changeCourse = async ()=> {
    if(gradeClassId.value!==null&&gradeClassId.value!==""&&courseId.value!==null&&courseId.value!==""){
        await getScoreCensus()
    }
}
onMounted(()=>{
    gradeClassList()
    getAllCourseList()
   // getScoreCensus()
})
</script>

<template>
    <el-card>
        <!--Header start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><Histogram/></el-icon>Class subject performance statistics
                </h3>
                <!--Search area start-->
                <div class="card-search">
                    <el-row :gutter="8">
                        <el-col :span="12">
                            <el-select v-model="gradeClassId" placeholder="Please select a class" style="width: 100%;" >
                                <el-option v-for="item in gradeClassOptions" :key="item.id" :label="item.name" :value="item.id" />
                            </el-select>
                        </el-col>
                        <el-col :span="12">
                            <el-select v-model="courseId" placeholder="Please select a course" style="width: 100%;" @change="changeCourse">
                                <el-option v-for="item in courseOptions" :key="item.id" :label="item.name" :value="item.id" />
                            </el-select>
                        </el-col>
                    </el-row>
                </div>
                <!--Search area end-->
            </div>
        </template>
        <!--Header end-->

        <!--echarts start-->
        <ScoreCensusPie :seriesData="seriesData" :legendData="legendData" height="400px" width="100%" id="pie"/>
        <!--echarts end-->
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
</style>