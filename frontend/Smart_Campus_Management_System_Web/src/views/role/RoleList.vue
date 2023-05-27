<script setup lang="ts">
import { ref, reactive,toRefs,onMounted } from 'vue'
import { formatTime } from "../../utils/date"
import {getRoleListApi} from "../../api/role/role.ts";
import {ElMessage} from 'element-plus'
import AddRole from "./components/AddRole.vue";
const addTitle = ref('Add role')
const addRoleDialogFormVisible = ref(false)
const state = reactive({
    // Search content
    searchValue: "",
    // All information
    tableData: [],
    // Total items
    total: 0,
    // Number of items displayed per page
    pageSize: 10,
    // Current page number
    pageIndex: 1,
    // Data loading
    loading: false,
})
// Get role list data
const loadData = async (state: any)=> {
    state.loading = true
    // Clear the data first
    state.tableData=[]
    const params = {
        'pageIndex':state.pageIndex,
        'pageSize': state.pageSize,
        'searchValue': state.searchValue
    }
    const { data } = await getRoleListApi(params)
    state.tableData = data.content
    state.total = data.totalElements
    state.loading = false
}
// Refresh list data
const refresh = () => {
    // Search data
    state.searchValue = ""
    // Refresh data
    loadData(state);
}
// Search
const search = () => {
    if (state.searchValue !== null) {
        ElMessage({
            type: 'success',
            message: `Key words“${state.searchValue}”The search content is as follows: `,
        })
        loadData(state)
    }
}
// The execution event of switching pages
// val : the current page number
const changePage = (val:number) => {
    state.pageIndex = val;
    loadData(state);
}
// Deal with the serial number problem after page turning
const Nindex = (index:number) => {
    // Current page number - 1 * number of data items per page + 1
    const page = state.pageIndex // current page number
    const pagesize = state.pageSize // Items per page
    return index + 1 + (page - 1) * pagesize
}
// Add role
const addRole = ()=> {
    addRoleDialogFormVisible.value = true
}
//Load data after mount
onMounted(() => {
    loadData(state);
})
const{tableData,pageIndex,pageSize,loading,total,searchValue} = toRefs(state)
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
<!--table-box end-->
<!--page start-->
<el-pagination background layout="total, sizes, prev, pager, next, jumper" :total="total"
               v-model:page-size="pageSize"
               @current-change="changePage"
               :page-sizes="[10, 20, 30, 40]"/>
<!--page end-->
</el-card>

<!--Add role pop-up box start-->
<el-dialog  align-center v-model="addRoleDialogFormVisible"   width="42%" destroy-on-close>
<template #header="{ close, titleId, titleClass }">
    <div class="my-header">
        <el-icon size="26px"><EditPen /></el-icon>
        <h1 id="titleId">{{addTitle}}</h1>
    </div>

</template>
<!--Add role component start-->
<AddRole @closeAddRoleForm="closeAddRoleForm" @success="success"/>
<!--Add role component end-->
</el-dialog>
<!--Add role pop-up box end-->

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
/*page style*/
:deep(.el-pagination.is-background .el-pager li:not(.is-disabled).is-active) {
    background-color: #178557;
}
.el-pagination {
    margin-top: 20px;
    justify-content: center;
}
/*Customize the header style of the user pop-up box*/
.my-header {
    display: flex;
    justify-content: flex-start;
}
</style>