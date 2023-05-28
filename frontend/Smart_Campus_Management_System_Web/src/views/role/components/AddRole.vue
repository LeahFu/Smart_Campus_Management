<script setup lang="ts">
import {ref, reactive} from 'vue'
import {ElMessage, FormInstance} from "element-plus";
import {addRoleApi} from "../../../api/role/role.ts";

const subLoading = ref(false)
const emit = defineEmits(['closeAddRoleForm','success'])
const formRole = reactive({
    name: '',
    code: '',
    remarks: ''
})
// Add role
const addRole = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        subLoading.value = true
        if (valid) {
            const { data } =  await addRoleApi(formRole)
            if(data.status===200){
                ElMessage.success(data.message)
                emit('success')
            }else {
                ElMessage.error(data.message)
            }
        } else {
            ElMessage.error('Submission failed, you still have unfilled items!')
            console.log('error submit!', fields)
        }
        subLoading.value = false
    })
}
// Cancel form
const close = ()=> {
    emit('closeAddRoleForm')
}
</script>

<template>
    <el-form :model="formRole"  label-width="80px">
        <el-row>
            <el-col :span="12">
                <el-form-item label="role name" prop="name">
                    <el-input v-model="formRole.name" placeholder="Please enter role name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="role code" prop="code">
                    <el-input v-model="formRole.code" placeholder="Please enter role code" />
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="remark">
                    <el-input
                            v-model="formRole.remarks"
                            :rows="2"
                            type="textarea"
                            placeholder="Please enter a remark"
                    />
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>

    <div class="dialong__button--wrap">
        <el-button @click="close">Cancel</el-button>
        <el-button color="#178557" :loading="subLoading" type="success" @click="addRole(ruleFormRef)">Save</el-button>
    </div>
</template>

<style scoped>
.dialong__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>