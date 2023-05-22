<script setup lang="ts">
import { reactive,ref } from 'vue'
import {ElMessage, FormInstance, FormRules} from 'element-plus'
import {addUserApi} from "../../../api/user/user.ts";
const emit = defineEmits(['closeAddUserForm','success'])
const subLoading = ref(false)
const ruleFormRef = ref<FormInstance>()
const formUser = reactive({
    username: '',
    password: '123456',
    status:1,
    realname: '',
    email: '',
    sex: 'female',
    remarks: '',
    sysRole: {
        id: ''
    }
})
// define Form Constraint Rules
const rules = reactive<FormRules>({
    username: [{ required: true, message: 'Username can not be empty', trigger: 'blur' }],
    password: [{ required: true, message: 'Password can not be empty', trigger: 'blur' }],
    realname: [{ required: true, message: 'Real name can not be empty', trigger: 'blur' }],
    sysRole: [{ required: true, message: 'Role can not be empty', trigger: 'blur' }],
    email: [{ required: true, message: 'Email can not be empty', trigger: 'blur' }]
})
// add user information
const addUser = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        subLoading.value = true
        if (valid) {
            const { data } =  await addUserApi(formUser)
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
// cancel form
const close = ()=> {
    emit('closeAddUserForm')
}
</script>

<template>
    <el-form ref="ruleFormRef" :rules="rules"  :model="formUser"  label-width="80px">
        <el-row>
            <el-col :span="24">
                <el-form-item prop="role" label="role">
                    <el-select v-model="formUser.sysRole.id" placeholder="please select a role" style="width: 100%;">
                        <el-option v-for="item in roleOptions" :key="item.id" :label="item.name" :value="item.id" />
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="username" prop="username">
                    <el-input v-model="formUser.username" placeholder="please enter user name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="password" prop="password">
                    <el-input v-model="formUser.password" placeholder="please enter password" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="realname" prop="realname">
                    <el-input v-model="formUser.realname" placeholder="please enter real name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="email" prop="email">
                    <el-input v-model="formUser.email" placeholder="please enter email" />
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
                            placeholder="please enter remark"
                    />
                </el-form-item>
            </el-col>

        </el-row>
    </el-form>

    <div class="dialong__button--wrap">
        <el-button @click="close">cancel</el-button>
        <el-button color="#178557" :loading="subLoading" type="success" @click="addUser(ruleFormRef)">save</el-button>
    </div>
</template>

<style scoped>
.dialong__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>