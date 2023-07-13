<script setup lang="ts">
import { ref, reactive } from 'vue'
//import editRole from "./EditRole.vue";
import { FormInstance, FormRules } from 'element-plus'
import {ElMessage} from 'element-plus'
import {editRoleApi} from "../../../api/role/role.ts";
const subLoading = ref(false)
const ruleFormRef = ref<FormInstance>()
// Define form constraint rule objects
const rules = reactive<FormRules>({
    name: [{ required: true, message: 'Role name can not be empty', trigger: 'blur' }],
    code: [{ required: true, message: 'Role code can not be empty', trigger: 'blur' }],
})
const formRole = reactive({
    id: 0,
    code: '',
    name: '',
    remarks: ''
})
const props = defineProps(['roleInfo'])
const roleInfo = ref(props.roleInfo)
const emit = defineEmits(['closeEditRoleForm','success'])
// Fill the form with data
for (const key in formRole) {
    formRole[key] = roleInfo.value[key]
}
// Update role information
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
// Cancel form
const close = ()=> {
    emit('closeEditRoleForm')
}
</script>

<template>
    <el-form ref="ruleFormRef" :rules="rules" :model="formRole" label-width="80px">
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

    <div class="dialog__button--wrap">
        <el-button @click="close">Cancel</el-button>
        <el-button color="#178557" :loading="subLoading" type="success" @click="editRole(ruleFormRef)">Save</el-button>
    </div>
</template>

<style scoped>
.dialog__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>