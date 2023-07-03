<script setup lang="ts">

</script>

<template>
    <el-row :gutter="20">
        <!--Information on the left start-->
        <el-col :span="18">
            <div class="left_box">
                <h3 class="title">
                    <el-icon style="margin-right: 10px;"><User /></el-icon>
                    Personal Information Settings
                </h3>

                <!--Basic settings start-->
                <div class="set">
                    <h4>Basic settings</h4>
                    <el-form ref="basicFormRef" :rules="basicRules" status-icon :model="basic">
                        <el-row :gutter="20">
                            <!--User real name-->
                            <el-col :span="8">
                                <el-form-item prop="realname" label="Real name" style="width: 100%;">
                                    <el-input v-model="basic.realname" placeholder="Please enter your real name"/>
                                </el-form-item>
                            </el-col>
                            <!--User gender-->
                            <el-col :span="8">
                                <el-form-item prop="gender" label="Gender" style="width: 100%;">
                                    <el-radio v-model="basic.gender" label="Male" size="large">Male</el-radio>
                                    <el-radio v-model="basic.gender" label="Female" size="large">Female</el-radio>
                                </el-form-item>
                            </el-col>
                            <!--Avatar-->
                            <el-col :span="5">
                                <el-form-item  label="Avatar：" style="margin: auto;">
                                    <el-upload class="avatar-uploader" >
                                        <img src="../../../assets/default_avatar.png"
                                             style="width: 50px;border-radius: 50px;" />
                                        <span style="margin-left: 10px;
                                        font-size: 10px;
                                        text-decoration: underline;line-height: 20px;">Click to replace</span>
                                    </el-upload>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3">
                                <el-form-item>
                                    <el-button  plain color="#2fa7b9"  style="margin-left: 50px;" >Submit</el-button>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </div>
                <!--Basic settings end-->
                <el-divider border-style="dashed" />
                <!--Bind email start-->

                <!--Bind email end-->
                <el-divider border-style="dashed" />
                <!--Change Password start-->

                <!--Change Password end-->
            </div>
        </el-col>
        <!--Information on the left end-->
        <!--Information on the right start-->

        <!--Information on the right end-->
    </el-row>
</template>
<style scoped>

</style>