<script setup lang="ts">
import {ref, reactive} from 'vue'
import {ElMessage} from 'element-plus'
import {addStudentApi, gradeClassListApi} from "../../../api/student/student.ts";
import type { FormInstance, FormRules } from 'element-plus'
const emit = defineEmits(['closeAddStudentForm','success'])
const subLoading = ref(false)
// Form object
const formStudent = reactive({
    name: '',
    stuno: '',
    gender: '',
    phone: '',
    gradeClass: {
        id: ''
    },
    email: '',
    remarks: ''
})
// Define the form instance object
const ruleFormRef = ref<FormInstance>()
// Define Form Constraint Rule Objects
const rules = reactive<FormRules>({
    name: [{ required: true, message: 'Student name cannot be empty', trigger: 'blur' }],
    gender: [{ required: true, message: 'Gender cannot be empty', trigger: 'blur' }],
    phone: [{ required: true, message: 'Phone number cannot be empty', trigger: 'blur' }],
    email: [{ required: true, message: 'Email cannot be empty', trigger: 'blur' }],
    gradeClass: [{ required: true, message: 'Class cannot be empty', trigger: 'blur' }],
    stuno: [{ required: true, message: 'Student number cannot be empty', trigger: 'blur' }],
})
// Add student information
const addStudent = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        subLoading.value = true
        if (valid) {
            const {data} = await addStudentApi(formStudent)
            if (data.status === 200) {
                ElMessage.success(data.message)
                emit('success')
            } else {
                ElMessage.error(data.message)
            }
        } else {
            ElMessage.error('Submission failed, you still have unfilled items!')
            console.log('error submit!', fields)
        }
        subLoading.value = false
    })
}

const gradeClassOptions = ref<object[]>([])
// Get a list of all classes
async function gradeClassList() {
    try {
        const { data } = await gradeClassListApi()
        if (data.status === 200) {
            gradeClassOptions.value = data.result
        }
    } catch (e) {
        console.log(e)
    }
}
gradeClassList()
// Cancel form
const close = ()=> {
    emit('closeAddStudentForm')
}
</script>

<template>
    <el-form ref="ruleFormRef" :rules="rules"  :model="formStudent"  label-width="80px">
        <el-row>
            <el-col :span="12">
                <el-form-item prop="gradeClass" label="class">
                    <el-select v-model="formStudent.gradeClass.id" placeholder="Please select a class" style="width: 100%;">
                        <el-option v-for="item in gradeClassOptions" :key="item.id" :label="item.name" :value="item.id" />
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="studentNumber" prop="stuno">
                    <el-input v-model="formStudent.stuno" placeholder="Please enter student number" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="studentName" prop="name">
                    <el-input v-model="formStudent.name" placeholder="Please enter student name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="gender" prop="gender">
                    <el-input v-model="formStudent.gender" placeholder="Please enter gender" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="phone" prop="phone">
                    <el-input v-model="formStudent.phone" placeholder="Please enter phone number" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="email" prop="email">
                    <el-input v-model="formStudent.email" placeholder="Please enter your email" />
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="remarks">
                    <el-input
                            v-model="formStudent.remarks"
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
        <el-button color="#178557" :loading="subLoading" type="success" @click="addStudent(ruleFormRef)">Save</el-button>
    </div>
</template>

<style scoped>
.dialong__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>