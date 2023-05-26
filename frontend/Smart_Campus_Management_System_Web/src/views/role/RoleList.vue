<script setup lang="ts">

</script>

<template>
    <el-card class="box-card">
        <!--head start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><UserFilled /></el-icon>Role Management
                </h3>

                <!--Search area start-->
                <div class="card-search">
                    <el-row :gutter="10">
                        <el-col :span="10">
                            <el-input :prefix-icon="Search" v-model="searchValue" @keyup.enter.native="search"
                                      placeholder="Keyword search (Enter)"/>
                        </el-col>
                        <el-col :span="11">
                            <div class="my-button">
                                <el-button plain style="width: 100%;" color="#2fa7b9" @click="addRole">Add Role</el-button>
                                <el-button @click="exportExcelAction" type="primary">
                                    <el-icon style="margin-right: 6px"><Download /></el-icon>Export Excel
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
                <!--Search area end-->
            </div>
        </template>

    </el-card>
</template>
<!--head end-->
<!--table-box start-->
<div class="table-box">
<el-table element-loading-text="Loading..." v-loading="loading" :data="tableData"
          style="width: 100%;text-align: center" :cell-style="{textAlign: 'center'}"
          :row-class-name="rowClassName"
          :header-cell-style="{fontSize: '15px', background: '#178557',color: 'white',textAlign: 'center'}">

    <el-table-column label="serial number" width="100" type="index" :index="Nindex"/>
    <el-table-column label="role name">
        <template #default="scope">
            <el-tooltip :content="scope.row.name" palacement="top" effect="light">
                <span class="highlight">{{scope.row.name}}</span>
            </el-tooltip>
        </template>
    </el-table-column>
    <el-table-column label="role code">
        <template #default="scope">
            <el-tooltip :content="scope.row.code" palacement="top" effect="light">
                <span class="highlight">{{scope.row.code}}</span>
            </el-tooltip>
        </template>
    </el-table-column>
    <el-table-column label="created time">
        <template #default="scope">
            <el-tooltip :content="scope.row.createTime" placement="top" effect="light">
                <span class="highlight">{{formatTime(scope.row.createTime, 'yyyy-MM-dd')}}</span>
            </el-tooltip>
        </template>
    </el-table-column>
    <el-table-column label="operate">
        <template #default="scope">
            <el-button size="small" @click="editRole(scope.row.id)"
                       style="margin: 0 0 10px 10px;">Edit</el-button>
            <el-popconfirm width="200px" confirm-button-text="Submit" cancel-button-text="Cancel" :icon="Delete"
                           icon-color="#626AEF" :title="'Are you sure you want to delete “'+scope.row.name+'” ？'"
                           @confirm="delRole( scope.row.id)">
                <template #reference>
                    <el-button size="small" type="danger" style="margin-bottom: 10px;">Delete</el-button>
                </template>
            </el-popconfirm>
        </template>
    </el-table-column>

</el-table>
</div>
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