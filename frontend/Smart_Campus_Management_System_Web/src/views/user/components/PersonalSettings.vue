<script setup lang="ts">
import {reactive,toRefs,onMounted,ref} from 'vue'
import { useUserStore } from '../../../store/modules/user'
import {ElMessage, FormInstance, FormRules} from 'element-plus'
import {updateInfoApi} from "../../../api/user/user.ts";
import BindEmail from "./BindEmail.vue";
import UpdatePwd from "./UpdatePwd.vue";
import UserInfo from "./UserInfo.vue"

const state = reactive({
    // Basic information
    basic: {
        realname: '',
        gender: '',
        userIcon: ''
    }
})
// Verify basic information
const basicRules = reactive<FormRules>({
    realname: [{ required: true, message: "Please enter your real name", trigger: "blur" }],
    gender: [{ required: true, message: "Please enter your gender", trigger: "blur" }],
    userIcon: [{ required: true, message: "Please upload your icon", trigger: "blur" }],
})
const basicFormRef = ref<FormInstance>()
const loading = ref(false)
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
// Submit basic information
const userStore = useUserStore()
const onBasicSubmit = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.validate(async(valid) => {
        if (valid) {
            loading.value = true
            // Loading
            const { data } = await updateInfoApi({ ...state.basic });
            if(data.status===200){
                // Set login user information
                userStore.setUserPartInfo({
                    realname: state.basic.realname,
                    gender: state.basic.gender,
                    userIcon: state.basic.userIcon
                })
                ElMessage({
                    message: 'Basic information modified successfully',
                    type: 'success',
                })
                loading.value = false
            }else {
                ElMessage({
                    message: 'Failed to modify basic information',
                    type: 'error',
                })
                loading.value = false
            }
        } else {
            console.log('error submit!')
            return false
        }
    })
}
const { userInfo } = userStore
// Load data after mount
onMounted(() => {
    state.basic.realname = userInfo.realname
    state.basic.gender = userInfo.gender
    state.basic.userIcon = userInfo.userIcon
})
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
                                    <el-button :loading="loading"  plain color="#2fa7b9"  style="margin-left: 50px;" @click="onBasicSubmit(basicFormRef)">Submit</el-button>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </div>
                <!--Basic settings end-->
                <el-divider border-style="dashed" />
                <!--Bind email start-->
                <BindEmail/>
                <!--Bind email end-->
                <el-divider border-style="dashed" />
                <!--Change Password start-->
                <UpdatePwd/>
                <!--Change Password end-->
            </div>
        </el-col>
        <!--Information on the left end-->
        <!--Information on the right start-->
        <UserInfo/>
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