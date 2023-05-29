<script setup lang="ts">

import editRole from "./EditRole.vue";
</script>

<template>
    <el-form :model="formRole" label-width="80px">
        <el-row>
            <el-col :span="12">
                <el-form-item label="role name" prop="name">
                    <el-input v-model="formRole.name" placeholder="Please enter role name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="role code" prop="code">
                    <el-input v-model="formRole.name" placeholder="Please enter role code" />
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="remarks">
                    <el-input
                            v-model="formRole.remarks"
                            :rows="2"
                            type="textarea"
                            placeholder="Please enter remarks"
                    />
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>

    <div class="dialong__button--wrap">
        <el-button @click="close">Cancel</el-button>
        <el-button color="#178557" :loading="subLoading" type="success" @click="editRole(ruleFormRef)">Save</el-button>
    </div>
</template>

<style scoped>
.dialong__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>