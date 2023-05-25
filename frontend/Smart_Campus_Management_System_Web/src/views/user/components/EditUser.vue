<script setup lang="ts">
import { reactive,ref } from 'vue'
import {ElMessage, FormRules} from 'element-plus'
import {FormInstance} from "element-plus";
import {editUserApi} from "../../../api/user/user.ts";
const emit = defineEmits(['closeEditUserForm','success'])
const props = defineProps(['userInfo'])
const userInfo = ref(props.userInfo)
const subLoading = ref(false)
const formUser = reactive({
    id: 0,
    username: '',
    password: '',
    status:1,
    realname: '',
    email: '',
    sex: 'female',
    remarks: '',
    sysRole: {
        id: ''
    }
})
// Fill the form with data
for (const key in formUser) {
    formUser[key] = userInfo.value[key]
}
// Define Form Constraint Rule Objects
const rules = reactive<FormRules>({
    username: [{ required: true, message: 'Username can not be empty', trigger: 'blur' }],
    password: [{ required: true, message: 'Password can not be empty', trigger: 'blur' }],
    realname: [{ required: true, message: 'Real name can not be empty', trigger: 'blur' }],
    sysRole: [{ required: true, message: 'Role can not be empty', trigger: 'blur' }],
    email: [{ required: true, message: 'Email can not be empty', trigger: 'blur' }]
})
// Edit user information
const editUser = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        subLoading.value = true
        if (valid) {
            const { data } =  await editUserApi(formUser)
            if(data.status===200){
                ElMessage.success(data.message)
                emit('success')
            }else {
                ElMessage.error(data.message)
            }
        } else {
            ElMessage.error('Submission failed, you still have unfilled items')
            console.log('error submit!', fields)
        }
        subLoading.value = false
    })
}
// cancel form
const close = ()=> {
    emit('closeEditUserForm')
}
</script>

<template>
    <el-form ref="ruleFormRef" :rules="rules"  :model="formUser"  label-width="80px">
        <el-row>
            <el-col :span="12">
                <el-form-item label="user name" prop="username">
                    <el-input v-model="formUser.username" placeholder="Please enter user name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="password" prop="password">
                    <el-input v-model="formUser.password" placeholder="Please enter password" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="real name" prop="realname">
                    <el-input v-model="formUser.realname" placeholder="Please enter real name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="email" prop="email">
                    <el-input v-model="formUser.email" placeholder="Please enter email" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="gender" prop="gender">
                    <el-radio-group v-model="formUser.sex" fill="#178557">
                        <el-radio-button label="male" />
                        <el-radio-button label="female" />
                    </el-radio-group>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="status" prop="status">
                    <el-radio-group v-model="formUser.status" fill="#178557">
                        <el-radio-button :label="1">normal</el-radio-button>
                        <el-radio-button :label="0">banned</el-radio-button>

                    </el-radio-group>
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="remark">
                    <el-input
                            v-model="formUser.remarks"
                            :rows="2"
                            type="textarea"
                            placeholder="Please enter remark"
                    />
                </el-form-item>
            </el-col>

        </el-row>
    </el-form>
    <div class="dialong__button--wrap">
        <el-button @click="close">cancel</el-button>
        <el-button color="#178557" :loading="subLoading" type="success" @click="editUser(ruleFormRef)">save</el-button>
    </div>
</template>

<style scoped>
.dialong__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>