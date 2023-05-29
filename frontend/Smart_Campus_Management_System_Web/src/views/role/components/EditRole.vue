<script setup lang="ts">
import { ref, reactive } from 'vue'
import editRole from "./EditRole.vue";
import type { FormInstance, FormRules } from 'element-plus'
import {ElMessage} from 'element-plus'
const subLoading = ref(false)
const formRole = reactive({
    id: 0,
    code: '',
    name: '',
    remarks: ''
})
const props = defineProps(['roleInfo'])
const roleInfo = ref(props.roleInfo)
// Fill the form with data
for (const key in formRole) {
    formRole[key] = roleInfo.value[key]
}
// Edit role information
const editRole = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        subLoading.value = true
        if (valid) {
            const { data } =  await editRoleApi(formRole)
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