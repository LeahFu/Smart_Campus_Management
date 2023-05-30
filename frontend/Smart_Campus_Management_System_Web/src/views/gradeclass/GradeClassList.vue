<script setup lang="ts">
import { reactive,toRefs, ref  } from 'vue'
import {Search} from "@element-plus/icons-vue";
import {formatTime} from "../../utils/date.ts";

const state = reactive({
    // Search form content
    searchValue: "",
    // All information on the form
    tableData: [],
    total: 0, // total number of items
    pageSize: 10, // rows per page
    pageIndex: 1, // current page number
    loading: false, // data loading
})
const {tableData,pageIndex,pageSize,loading,total,searchValue} = toRefs(state)
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
        <!--table-box area start-->
        <div class="table-box">
            <el-table element-loading-text="Loading..." v-loading="loading" :data="tableData"
                      style="width: 100%;text-align: center" :cell-style="{textAlign: 'center'}"
                      :row-class-name="rowClassName"
                      :header-cell-style="{fontSize: '15px', background: '#178557',color: 'white',textAlign: 'center'}">

                <el-table-column label="serial number" width="100" type="index" :index="Nindex"/>

                <el-table-column label="class code">
                    <template #default="scope">
                        <span>{{scope.row.code}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="class name">
                    <template #default="scope">
                        <span>{{scope.row.name}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="grade">
                    <template #default="scope">
                        <span>{{scope.row.grade}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="class">
                    <template #default="scope">
                        <span>{{scope.row.clazz}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="class size">
                    <template #default="scope">
                        <span>{{scope.row.students.length}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="created time">
                    <template #default="scope">
                        <el-tooltip :content="formatTime(scope.row.createTime, 'yyyy-MM-dd')" placement="top" effect="light">
                            <span class="highlight">{{formatTime(scope.row.createTime, 'yyyy-MM-dd')}}</span>
                        </el-tooltip>
                    </template>
                </el-table-column>

                <el-table-column label="operate">
                    <template #default="scope">
                        <el-button size="small" @click="editGradeClass(scope.row.id)"
                                   style="margin: 0 0 10px 10px;">Edit</el-button>
                        <el-popconfirm confirm-button-text="Submit" cancel-button-text="Cancel"
                                       icon-color="#626AEF" :title="'Are you sure you want to delete class “'+scope.row.name+'” ？'"
                                       @confirm="delGradeClass(scope.row.id)">
                            <template #reference>
                                <el-button size="small" type="danger" style="margin-bottom: 10px;">Delete</el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <!--table-box area end-->

        <!--page start-->
        <el-pagination background layout="total, sizes, prev, pager, next, jumper" :total="total"
                       v-model:page-size="pageSize"
                       @current-change="changePage"
                       :page-sizes="[10, 20, 30, 40]"/>
        <!--page end-->
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
/*Pagination style*/
:deep(.el-pagination.is-background .el-pager li:not(.is-disabled).is-active) {
    background-color: #178557;
}
.el-pagination {
    margin-top: 20px;
    justify-content: center;
}
</style>