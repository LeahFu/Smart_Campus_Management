<script setup lang="ts">
import {reactive,toRefs} from 'vue'
const state = reactive({
    // Basic information
    basic: {
        realname: '',
        gender: '',
        userIcon: ''
    }
})
// Server path
const url = import.meta.env.VITE_APP_BASE_API
// The path to upload the image to the server
const uploadURL = url + "user/userIcon"
// The function to be executed after the image is uploaded successfully
const handleAvatarSuccess = (res: { status: number; result: { userIcon: string } }) => {
    if(res.status === 200){
        state.basic.userIcon = res.result.userIcon;
    }
}
const {basic} = toRefs(state)
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
                                    <el-upload class="avatar-uploader" :action="uploadURL" name="fileResource" :show-file-list="false" :on-success="handleAvatarSuccess">
                                        <img v-if="basic.userIcon" :src="url+'uploadFile/'+basic.userIcon"
                                             style="width: 50px;border-radius: 50px;" />
                                        <img v-else src="../../../assets/default_avatar.png"
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
.left_box {
    width: 100%;
    height: auto;
    background: white;
    padding: 20px;
    box-sizing: border-box;
}
.left_box .title {
    color: #178557;
    margin-bottom: 10px;
    padding: 20px 20px;
    display: inline-flex;
    justify-content: center;
    align-items: center;
}
.left_box .set {
    text-align: left;
    padding: 0px 20px;
    margin-bottom: 10px;
    color: #8f8f8f;
    line-height: 35px;
}
.left_box .set h4 {
    line-height: 45px;
    color: #8f8f8f;
}
</style>