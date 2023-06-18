<script setup lang="ts">

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

</style>