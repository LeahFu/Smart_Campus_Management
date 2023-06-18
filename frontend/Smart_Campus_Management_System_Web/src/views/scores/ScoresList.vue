<script setup lang="ts">
import { ref, reactive, onMounted  } from 'vue'
import {gradeClassListApi} from "../../api/student/student.ts";
import {getAllCourseListApi} from "../../api/teacher/teacher.ts";

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
    getAllCourseList()
    gradeClassList()
})
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