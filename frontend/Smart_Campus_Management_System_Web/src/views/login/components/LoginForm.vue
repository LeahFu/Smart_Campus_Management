<script setup lang="ts">

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

</style>