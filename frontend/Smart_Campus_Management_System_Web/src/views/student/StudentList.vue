<script setup lang="ts">

</script>

<template>
    <el-card class="box-card">
        <!--Header start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><UserFilled /></el-icon>Student Management
                </h3>

                <!--Search area start-->
                <div class="card-search">
                    <el-row :gutter="10">
                        <el-col :span="10">
                            <el-input :prefix-icon="Search" v-model="searchValue" @keyup.enter.native="search"
                                      placeholder="Keywords search（Enter）"/>
                        </el-col>
                        <el-col :span="11">
                            <div class="my-button">
                                <el-button plain style="width: 100%;" color="#2fa7b9" @click="addStudent">Add student</el-button>

                            </div>
                        </el-col>

                    </el-row>
                </div>
                <!--Search area end-->
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