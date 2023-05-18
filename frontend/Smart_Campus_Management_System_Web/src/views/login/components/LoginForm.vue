<script setup lang="ts">
import SvgIcon from "../../../components/SvgIcon/index.vue";
import { ref, reactive } from 'vue'
import type { FormInstance } from 'element-plus'
import { ElNotification } from "element-plus";
import { useRouter } from 'vue-router'
import { useUserStore } from '../../../store/modules/user'
const router = useRouter()
const ruleFormRef = ref<FormInstance>()
const passwordType = ref('password')
const loading = ref(false)

// form rules
const rules = reactive({
    password: [{ required: true, message: "Please enter usernama", trigger: "blur" }],
    username: [{ required: true, message: "Please enter password", trigger: "blur" }],
})
// form data
const ruleForm = reactive({
    username: 'admin',
    password: '123456',
})
// show password
const showPwd = () => {
    passwordType.value = passwordType.value === 'password'?'':'password'
}
// submit form
const userStore = useUserStore()
const submitForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.validate(async(valid) => {
        if (valid) {
            loading.value = true
            // login
            const { data } = await loginApi({ ...ruleForm });
            if(data.status===200){
                // set token
                userStore.setToken(data.result.token)
                userStore.setUserInfo({
                    username: data.result.username,
                    realname: data.result.realname,
                    email: data.result.email,
                    sex: data.result.sex,
                    userIcon: data.result.userIcon,
                    createTime: data.result.createTime,
                    role: data.result.role
                })
                await router.push({
                    path: '/index',
                })
                ElNotification({
                    title: 'Login successful',
                    message: "Welcome Login Smart Campus Management System",
                    type: "success",
                    duration: 3000
                })
            }else {
                ElNotification({
                    title: 'kind tips',
                    message: data.message,
                    type: "error",
                    duration: 3000
                });
                loading.value = false
            }
        } else {
            console.log('error submit!')
            loading.value = false
            return false
        }
    })
}
</script>

<template>
    <el-form
            ref="ruleFormRef"
            :model="ruleForm"
            :rules="rules"
    >
        <el-form-item label="" prop="username">
            <el-input placeholder="Please enter username" autoComplete="on" style="position: relative" v-model="ruleForm.username">
                <template #prefix>
                    <el-icon class="el-input__icon"><UserFilled /></el-icon>
                </template>
            </el-input>
        </el-form-item>

        <el-form-item label="" prop="password">
            <el-input
                    placeholder="Please enter password"
                    autoComplete="on"
                    v-model="ruleForm.password"
                    :type="passwordType"
            >
                <template #prefix>
                    <el-icon class="el-input__icon"><GoodsFilled /></el-icon>
                </template>
                <template #suffix>
                    <div class="show-pwd" @click="showPwd">
                        <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"/>
                    </div>
                </template>
            </el-input>
        </el-form-item>

        <el-form-item style="width: 100%">
            <el-button
                    :loading="loading"
                    class="login-btn"
                    type="success"
                    @click="submitForm(ruleFormRef)"
            >Login</el-button
            >
        </el-form-item>

    </el-form>
</template>

<style scoped>
.login-btn{
    margin-top: 20px;
    width: 100%; height: 47px
}
.show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    cursor: pointer;
    user-select: none;
}
::v-deep(.svg-icon){
    vertical-align: 0;
}
</style>