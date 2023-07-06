<script setup lang="ts">

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