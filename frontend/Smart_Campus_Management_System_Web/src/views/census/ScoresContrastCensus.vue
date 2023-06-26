<script setup lang="ts">

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

</style>