<script setup lang="ts">
import { ref, reactive} from 'vue'
import { FormInstance, FormRules, ElMessage } from 'element-plus'
import {updatePwdApi} from "../../../api/user/user.ts";
import {CircleCheck, Warning} from "@element-plus/icons-vue";

// Define the form instance object
const modifyFormRef = ref<FormInstance>()
const subLoading = ref(false)
// define form data object
const modifyPwd = ref({
    usedPass: "",
    newPass: ""
})
// Define form constraint rule object
const modifyRules = reactive<FormRules>({
    usedPass: [{ required: true, message: 'Old password cannot be empty', trigger: 'blur' }],
    newPass: [{ required: true, message: 'New password cannot be empty', trigger: 'blur' }]
})
// 提交修改密码
const modifySubmit = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        subLoading.value = true
        if (valid) {
            const { data } =  await updatePwdApi(modifyPwd.value)
            if(data.status===200){
                ElMessage.success(data.message)
            }else {
                ElMessage.error(data.message)
            }
        }else {
            ElMessage.error('Submission failed, you still have unfilled items!')
        }
        subLoading.value = false
    })
}
</script>

<template>
    <div class="set">
        <h4>Change Password</h4>
        <p style="display: inline-flex;
                    justify-content: center;
                    align-items: center;">
            <el-icon style="margin-right: 5px;">
                <CircleCheck />
            </el-icon>The password consists of 6~18 letters, numbers and special characters
        </p>
        <br>
        <p style="display: inline-flex;
                    justify-content: center;
                    align-items: center;">
            <el-icon style="margin-right: 5px;">
                <Warning />
            </el-icon>Security Tip: Do not make the new password too similar to the old password
        </p>
        <el-form ref="modifyFormRef" :rules="modifyRules" :model="modifyPwd" class="demo-form-inline">
            <el-row :gutter="20">
                <el-col  :span="10">
                    <el-form-item prop="usedPass" label="Old password" style="width: 100%;">
                        <el-input v-model="modifyPwd.usedPass" show-password placeholder="Please enter old password" />
                    </el-form-item>
                </el-col>
                <el-col :span="10">
                    <el-form-item prop="newPass" label="New password" style="width: 100%;">
                        <el-input v-model="modifyPwd.newPass" show-password placeholder="Please enter new password" />
                    </el-form-item>
                </el-col>
                <el-col  :span="4" style="text-align: center;">
                    <el-form-item style="margin-right: 0px;">
                        <el-button plain color="#2fa7b9" :loading="subLoading" @click="modifySubmit(modifyFormRef)">Submit
                        </el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
    </div>
</template>

<style scoped>

</style>