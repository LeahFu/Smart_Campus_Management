<script setup lang="ts">

</script>

<template>
 <el-card class="box-card">
  <!--head start-->
  <template #header>
   <div class="card-header">
    <h3>
     <el-icon style="margin-right: 10px;"><UserFilled /></el-icon>user management
    </h3>

    <!--search area start-->
    <div class="card-search">
     <el-row :gutter="10">
      <el-col :span="8">
       <el-input :prefix-icon="Search" v-model="searchValue" @keyup.enter.native="search"
                 placeholder="Key word search（return）"/>
      </el-col>
      <el-col :span="6">
       <el-select v-model="status" placeholder="Please choose status">
        <el-option label="All" value="-1"/>
        <el-option label="Banned" value="0"/>
        <el-option label="Normal" value="1"/>
       </el-select>
      </el-col>
      <el-col :span="8">
       <div class="my-button">
        <el-button plain style="width: 50%;" color="#2fa7b9" @click="addUser">add user</el-button>
        <el-button @click="exportExcelAction" type="primary">
         <el-icon style="margin-right: 6px"><Download /></el-icon>导出 Excel
        </el-button>
       </div>

      </el-col>
      <el-col :span="2" style="display: inline-flex;justify-content: center;align-items: center; cursor: pointer;">
       <el-icon style="font-size: 20px;color: #b3b6bc;" @click="refresh">
        <Refresh />
       </el-icon>
      </el-col>
     </el-row>
    </div>
    <!--search area end-->
   </div>
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
 .el-card {
  border-radius: 0px;
  border: none;
 }
</style>