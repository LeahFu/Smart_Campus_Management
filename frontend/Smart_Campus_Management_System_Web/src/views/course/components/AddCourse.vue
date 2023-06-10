<script setup lang="ts">

import addCourse from "./AddCourse.vue";
</script>

<template>
    <el-form  :model="formCourse"  label-width="80px">
        <el-row>
            <el-col :span="12">
                <el-form-item label="Course NO.">
                    <el-input v-model="formCourse.courseno" placeholder="Please enter course NO." />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Course name">
                    <el-input v-model="formCourse.coursename" placeholder="Please enter course name" />
                </el-form-item>
            </el-col>

            <el-col :span="24">
                <el-form-item label="Remarks">
                    <el-input
                            v-model="formCourse.remarks"
                            :rows="2"
                            type="textarea"
                            placeholder="Please enter remarks"
                    />
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>

    <div class="dialong__button--wrap">
        <el-button @click="close">Cancel</el-button>
        <el-button color="#178557" :loading="subLoading" type="success" @click="addCourse">Save</el-button>
    </div>
</template>

<style scoped>

</style>