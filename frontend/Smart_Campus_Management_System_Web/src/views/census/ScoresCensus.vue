<script setup lang="ts">

</script>

<template>
    <el-card class="box-card">
        <!--Header start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><Histogram /></el-icon>Class subject performance statistics
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
                            <el-select v-model="courseId" placeholder="Please select a subject" style="width: 100%;" >
                                <el-option v-for="item in courseOptions" :key="item.id" :label="item.name" :value="item.id" />
                            </el-select>
                        </el-col>
                    </el-row>
                </div>
                <!--Search area end-->
            </div>
        </template>
        <!--Header end-->
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