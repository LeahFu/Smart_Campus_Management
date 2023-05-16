<script setup lang="ts">

</script>

<template>
    <div class="home">
        <!--top background picture and content start-->
        <div class="top_bg">
            <h1>Welcome to ACCESS</h1>
            <p>smart campus management system，admin</p>
        </div>
        <!--top background picture and content start end-->
        <!--Data Statistics start-->
        <p style="margin-bottom:15px;color: #144b9f;">
            <div style="width: 12px;height:12px;background-color:#f9a332;border-radius: 50%;float: left;margin-top: 5px;
            margin-right: 8px;"></div>Data Statistics
        </p>
        <el-row :gutter="40" class="data_row">
            <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
                <div style="background: linear-gradient(to right, #6D80FE, #23D2FD);">
                    <div class="data_left">
                        <el-icon>
                            <Avatar />
                        </el-icon>
                    </div>
                    <div class="data_right">
                        <h1>{{studentNums}}<span></span></h1>
                        <p>Student number</p>
                    </div>
                </div>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
                <div style="background: linear-gradient(to right, #FF988B, #FF6B88);">
                    <div class="data_left">
                        <el-icon>
                            <Reading />
                        </el-icon>
                    </div>
                    <div class="data_right">
                        <h1>{{classNums}}<span>个</span></h1>
                        <p>Class number</p>
                    </div>
                </div>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
                <div style="background: linear-gradient(to right, #09B0E8, #29F49A);">
                    <div class="data_left">
                        <el-icon>
                            <ChatDotSquare />
                        </el-icon>
                    </div>
                    <div class="data_right">
                        <h1>{{ teacherNums }}<span>人</span></h1>
                        <p>Teacher number</p>
                    </div>
                </div>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
                <div style="background: linear-gradient(to right, #717CFE, #FC83EC);">
                    <div class="data_left">
                        <el-icon>
                            <Clock />
                        </el-icon>
                    </div>
                    <div class="data_right">
                        <h1>{{courseNums}}<span>门</span></h1>
                        <p>Course number</p>
                    </div>
                </div>
            </el-col>
        </el-row>
        <!--Data Statistics end-->
        <!--Subject score comparison statistics start-->
        <p style="margin-bottom:15px;color: #144b9f;">
            <div style="width: 12px;height:12px;background-color:#f9a332;border-radius: 50%;float: left;margin-top: 5px;
            margin-right: 8px;"></div>Subject score comparison statistics
        </p>
        <el-row>
            <el-col>
                <AllSubjectScoreContrast :legendData="legendData" :seriesData="seriesData" :categoryData="categoryData"  height="400px" width="100%" id="bar"/>
            </el-col>
        </el-row>
        <!--Subject score comparison statistics end-->
    </div>
</template>

<style scoped>

</style>