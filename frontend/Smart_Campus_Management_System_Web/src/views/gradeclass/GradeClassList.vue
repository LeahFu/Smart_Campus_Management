<script setup lang="ts">

import {Search} from "@element-plus/icons-vue";
</script>

<template>
    <el-card class="box-card">
        <!--head start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><UserFilled /></el-icon>Class Management
                </h3>

                <!--search area start-->
                <div class="card-search">
                    <el-row :gutter="10">
                        <el-col :span="10">
                            <el-input :prefix-icon="Search" v-model="searchValue" @keyup.enter.native="search"
                                      placeholder="Keywords search（Enter）"/>
                        </el-col>
                        <el-col :span="11">
                            <div class="my-button">
                                <el-button plain style="width: 100%;" color="#2fa7b9" @click="addGradeClass">Add Class</el-button>
                                <el-button @click="exportExcelAction" type="primary">
                                    <el-icon style="margin-right: 1px"><Download /></el-icon>Export Excel
                                </el-button>
                            </div>
                        </el-col>
                        <el-col :span="3" style="display: inline-flex;justify-content: center;align-items: center; cursor: pointer;">
                            <el-icon style="font-size: 20px;color: #b3b6bc;" @click="refresh">
                                <Refresh />
                            </el-icon>
                        </el-col>
                    </el-row>
                </div>
                <!--search area end-->
            </div>
        </template>
        <!--head end-->
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