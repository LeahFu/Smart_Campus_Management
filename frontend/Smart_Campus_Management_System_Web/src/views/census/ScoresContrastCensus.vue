<script setup lang="ts">
import { ref,onMounted } from 'vue'
import {getAllCourseListApi} from "../../api/teacher/teacher.ts";
import ScoreContrastCensusBar from "./components/ScoreContrastCensusBar.vue";
import {getScoresContrastCensusApi} from "../../api/census/census.ts";
import {Notification} from "@element-plus/icons-vue";

// Define course ID
const courseId = ref()
// Define Course Dropdown Selections
const courseOptions = ref<object[]>([])
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
const legendData = ref(['total people','overall score','average score', 'highest score', 'minimum score'])
const seriesData = ref([])
const categoryData = ref([])
// Class course grades comparison
const getScoresContrastCensus = async ()=> {
    const { data } = await getScoresContrastCensusApi(courseId.value)
    if(data.status===200){
        seriesData.value = data.result.barEchartsSeriesList
        categoryData.value = data.result.categoryList
    }
}
// Monitor course drop-down selection box
const changeCourse = async ()=> {
    if(courseId.value!==null&&courseId.value!==""){
        await getScoresContrastCensus()
    }
}
//Load data after mount
onMounted(() => {
    getAllCourseList()
})
</script>

<template>
    <el-card>
        <!--Header start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><Notification/></el-icon>Class course grades comparison statistics
                </h3>

                <!--Search area start-->
                <div class="card-search">
                    <el-row :gutter="8">
                        <el-col :span="24">
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
        <ScoreContrastCensusBar :seriesData="seriesData" :legendData="legendData" :categoryData="categoryData" height="400px" width="100%" id="pie"/>
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
    color:#178557;
}

/*:deep(.el-card__header) {
    border-bottom: 1px solid rgb(238 238 238);
    color: #178557;
}*/
</style>