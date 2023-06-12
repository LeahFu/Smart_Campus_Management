<script setup lang="ts">

</script>

<template>
    <el-card class="box-card">
        <!--Header start-->
        <template #header>
            <div class="card-header">
                <h3>
                    <el-icon style="margin-right: 10px;"><UserFilled /></el-icon>Teacher Management
                </h3>

                <!--Search area start-->
                <div class="card-search">
                    <el-row :gutter="10">
                        <el-col :span="10">
                            <el-input :prefix-icon="Search" v-model="searchValue" @keyup.enter.native="search"
                                      placeholder="Keywords search (Enter)"/>
                        </el-col>
                        <el-col :span="11">
                            <div class="my-button">
                                <el-button plain style="width: 100%;" color="#2fa7b9" @click="addTeacher">Add teacher</el-button>
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
        <!--Header end-->

        <!--Table-box area start-->
        <div class="table-box">
            <el-table element-loading-text="Loading..." v-loading="loading" :data="tableData"
                      style="width: 100%;text-align: center" :cell-style="{textAlign: 'center'}"
                      :row-class-name="rowClassName"
                      :header-cell-style="{fontSize: '15px', background: '#178557',color: 'white',textAlign: 'center'}">

                <el-table-column label="Serial number" width="100" type="index" :index="Nindex"/>
                <el-table-column label="Teacher ID">
                    <template #default="scope">
                        <span>{{scope.row.teachno}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Teacher name">
                    <template #default="scope">
                        <span>{{scope.row.name}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Course name">
                    <template #default="scope">
                        <span>{{scope.row.course.coursename}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Gender">
                    <template #default="scope">
                        <span>{{scope.row.gender}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Phone">
                    <template #default="scope">
                        <span>{{scope.row.phone}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Email">
                    <template #default="scope">
                        <span>{{scope.row.email}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Created time">
                    <template #default="scope">
                            <span>{{scope.row.createTime}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Operate">
                    <template #default="scope">
                        <el-button size="small" @click="editTeacher(scope.row.id)"
                                   style="margin: 0 0 10px 10px;">Edit</el-button>
                        <el-popconfirm confirm-button-text="Submit" cancel-button-text="Cancel" :icon="Delete"
                                       icon-color="#626AEF" :title="'Are you sure you want to delete “'+scope.row.name+'” ?'"
                                       @confirm="delTeacher(scope.row.id)">
                            <template #reference>
                                <el-button size="small" type="danger" style="margin-bottom: 10px;">Delete</el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <!--Table-box area end-->
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