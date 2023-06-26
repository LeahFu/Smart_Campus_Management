<script setup lang="ts">
import { ref,onMounted } from 'vue'
import {getAllCourseListApi} from "../../api/teacher/teacher.ts";
// Define course ID
const courseId = ref()
// Define Course Dropdown Selections
const courseOptions = ref<object[]>([])
// Get a list of all courses
const getAllCourseList() = async()=>{
        const { data } = await getAllCourseListApi()
            courseOptions.value = data.result
}
//Load data after mount
onMounted(() => {
    getAllCourseList()
})
</script>

<template>
    <el-card class="box-card">
        <!--Header start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><Histogram /></el-icon>Class course grades comparison statistics
                </h3>

                <!--Search area start-->
                <div class="card-search">
                    <el-row :gutter="8">
                        <el-col :span="24">
                            <el-select v-model="courseId" placeholder="Please select a course" style="width: 100%;" >
                                <el-option v-for="item in courseOptions" :key="item.id" :label="item.name" :value="item.id" />
                            </el-select>
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
    color:#178557;
}

:deep(.el-card__header) {
    border-bottom: 1px solid rgb(238 238 238);
    color: #178557;
}
</style>