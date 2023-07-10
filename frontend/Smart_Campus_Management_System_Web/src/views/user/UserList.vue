<script setup lang="ts">
import { reactive,toRefs,onMounted,watch,ref } from 'vue'
import {Search, Delete} from '@element-plus/icons-vue'
import {deleteUserApi, getUserApi, getUserListApi} from "../../api/user/user.ts";
import { formatTime } from "../../utils/date"
import AddUser from './components/AddUser.vue'
import EditUser from "./components/EditUser.vue";
import {ElMessage, ElNotification, ElMessageBox} from 'element-plus'
import {exportExcel} from "../../utils/exportExcel.ts";

// add user popup status
const userDialogFormVisible = ref(false)
const title = ref('add user')
// edit user popup status
const editUserDialogFormVisible = ref(false)
const editTitle = ref('edit user')

const state = reactive({
    // search form content
    searchValue: "",
    // all content
    tableData: [],
    // user information
    userInfo:null,
    status: null,
    total: 0,
    pageSize: 10, // rows per page
    pageIndex: 1, // current page number
    loading: false,
})
// Get user list data
const loadData = async (state: any)=> {
    state.loading = true
    // Clear the data
    state.tableData=[]
    const params = {
        'pageIndex':state.pageIndex,
        'pageSize': state.pageSize,
        'status': state.status ==-1 ? '':state.status,
        'searchValue': state.searchValue
    }
    const { data } = await getUserListApi(params)
    state.tableData = data.content
    state.total = data.totalElements
    state.loading = false
}

// the execution event of switching pages
// val current page
const changePage = (val) => {
    state.pageIndex = val;
    loadData(state);
}
// dealing with user serial number issues after pagination
const Nindex = (index) => {
    // current page number - 1 * number of data items per page + 1
    const page = state.pageIndex // current page
    const pagesize = state.pageSize // number of data items per page
    return index + 1 + (page - 1) * pagesize
}
// refresh
const refresh = () => {
    // search value
    state.searchValue = ""
    // filter drop-down box content
    state.status = null
    // refresh data
    loadData(state);
}
// search
const search = () => {
    if (state.searchValue !== null) {
        ElMessage({
            type: 'success',
            message: `keywords“${state.searchValue}”Search results: `,
        })
        loadData(state)
    }
}
// Monitor the change of the content of the drop-down box
watch(() => state.status, (val, preVal) => {
    if (val) {
        state.searchValue = ""
        if (state.status === -1) {
            // Search all
            loadData(null);
        } else {
            loadData(state)
        }
    }
})
// add user
const addUser = ()=> {
    userDialogFormVisible.value = true
}
// close the new user popup
const closeAddUserForm = ()=> {
    userDialogFormVisible.value = false
}
// submit form callback function
const success = ()=> {
    loadData(state);
    userDialogFormVisible.value = false
}
// edit user information
const userInfo = ref()
const editUser = async (id:number)=> {
    const { data } = await getUserApi(id)
    userInfo.value = data.result
    editUserDialogFormVisible.value = true
}
// close edit user popup
const closeEditUserForm = ()=> {
    editUserDialogFormVisible.value = false
}
// Delete user information
const delUser = async (id:number)=> {
    const { data } = await deleteUserApi(id)
    if(data.status===200){
        ElMessage.success('Successfully deleted')
        await loadData(state);
    }else {
        ElMessage.error('Failed to delete')
    }
}
// Define the column name object that needs to be exported
const column = [
    {name: 'id',label: 'id'},
    {name: 'username',label: 'username'},
    {name: 'realname',label: 'realname'},
    {name: 'gender',label: 'gender'},
    {name: 'status',label: 'status'},
    {name: 'email',label: 'email'},
    {name: 'remark',label: 'remark'}
]
// Export excel function
const exportExcelAction = () => {
    exportExcel({
        column,
        data:state.tableData,
        filename: 'user info data',
        format: 'xlsx',
        autoWidth: true,
    })
}
onMounted(() => {
    loadData(state);
})
const {tableData,pageIndex,pageSize,loading,total,status,searchValue} = toRefs(state)
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
               <el-button plain style="width: 50%;" color="#2fa7b9" @click="addUser">Add User</el-button>
               <el-button @click="exportExcelAction" type="primary">
               <el-icon style="margin-right: 6px"><Download/></el-icon>Export Excel
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
    <!--head end-->
    <!--form area start-->
    <div class="table-box">
      <el-table element-loading-text="loading..." v-loading="loading" :data="tableData"
             style="width: 100%;text-align: center" :cell-style="{textAlign: 'center'}"
             :header-cell-style="{fontSize: '15px', background: '#178557',color: 'white',textAlign: 'center'}">

        <el-table-column label="serial number" width="100" type="index" :index="Nindex"/>
        <el-table-column label="user name">
           <template #default="scope">
              <el-tooltip :content="scope.row.username" palacement="top" effect="light">
                 <span class="highlight">{{scope.row.username}}</span>
              </el-tooltip>
           </template>
        </el-table-column>
        <el-table-column label="real name">
           <template #default="scope">
              <el-tooltip :content="scope.row.realname" palacement="top" effect="light">
                 <span class="highlight">{{scope.row.realname}}</span>
              </el-tooltip>
           </template>
        </el-table-column>
        <el-table-column label="gender">
           <template #default="scope">
              <el-tooltip :content="scope.row.gender" palacement="top" effect="light">
                 <span class="highlight">{{scope.row.gender}}</span>
              </el-tooltip>
           </template>
        </el-table-column>
          <el-table-column label="role name">
              <template #default="scope">
                  <span class="highlight">{{scope.row.sysRole.name}}</span>
              </template>
          </el-table-column>

          <el-table-column label="role code">
              <template #default="scope">
                  <span class="highlight">{{scope.row.sysRole.code}}</span>
              </template>
          </el-table-column>
        <el-table-column label="status">
           <template #default="scope">
              <div v-if="scope.row.status == 1" style="color: #67C23A;">normal</div>
              <div v-if="scope.row.status == 0" style="color: #F56C6C;">banned</div>
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
              <el-button size="small"
                 style="margin: 0 0 10px 10px;" @click="editUser(scope.row.id)">edit</el-button>
              <el-popconfirm confirm-button-text="submit" cancel-button-text="cancel" :icon="Delete"
                     icon-color="#626AEF" :title="'Are you sure you want to delete :“'+scope.row.username+'” ?'"
                     @confirm="delUser(scope.row.id)">
                 <template #reference>
                    <el-button size="small" type="danger" style="margin-bottom: 10px;">delete</el-button>
                 </template>
              </el-popconfirm>
           </template>
        </el-table-column>
      </el-table>

      <!--paging start-->
      <el-pagination background layout="total, sizes, prev, pager, next, jumper" :total="total"
                   v-model:page-size="pageSize"
                   @current-change="changePage"
                   :page-sizes="[10, 20, 30, 40]"/>
      <!--paging end-->
    </div>
    <!--form area end-->
  </el-card>

  <!--User Dialog FormVisible start-->
  <el-dialog  align-center  v-model="userDialogFormVisible"  width="42%" destroy-on-close>
    <template #header="{ close, titleId, titleClass }">
      <div class="my-header">
        <el-icon size="26px"><EditPen /></el-icon>
        <h1 id="titleId">{{title}}</h1>
      </div>
    </template>
    <!--Add user component start-->
    <AddUser @closeAddUserForm="closeAddUserForm" @success="success"/>
    <!--Add user component end-->
  </el-dialog>
  <!--User Dialog FormVisible end-->

 <!--Edit user popup start-->
 <el-dialog  align-center v-model="editUserDialogFormVisible"  width="42%" destroy-on-close>
    <template #header="{ close, titleId, titleClass }">
      <div class="my-header">
        <el-icon size="26px"><EditPen /></el-icon>
        <h1 id="titleId">{{editTitle}}</h1>
      </div>
    </template>
    <!--Edit user component start-->
    <EditUser :userInfo="userInfo" @closeEditUserForm="closeEditUserForm" @success="success"/>
    <!--Edit user component end-->
 </el-dialog>
 <!--Edit user popup end-->

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
 :deep(.el-pagination.is-background .el-pager li:not(.is-disabled).is-active) {
     background-color: #178557;
 }
 .el-pagination {
     margin-top: 20px;
     justify-content: center;
 }
 /*customize the header style of the user pop-up box*/
 .my-header {
     display: flex;
     justify-content: flex-start;
 }
 /*custom button style*/
 .my-button {
     display: flex;
     justify-content:space-between;
 }
</style>