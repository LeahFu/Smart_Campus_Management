<script setup lang="ts">
import { ref, reactive } from 'vue'
import {ElMessage} from 'element-plus'
import {editStudentApi, gradeClassListApi} from "../../../api/student/student.ts";
const subLoading = ref(false)
// Form data object
const formStudent = reactive({
    id: 0,
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
// Student information
const props = defineProps(['studentInfo'])
const studentInfo = ref(props.studentInfo)
// Fill the form with data
for (const key in formStudent) {
    formStudent[key] = studentInfo.value[key]
}
// Edit student information
const editStudent = async () => {
        subLoading.value = true
            const { data } =  await editStudentApi(formStudent)
            if(data.status===200){
                ElMessage.success(data.message)
            }else {
                ElMessage.error(data.message)
            }
        subLoading.value = false
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
</script>

<template>
    <el-form  :model="formStudent"  label-width="80px">
        <el-row>
            <el-col :span="12">
                <el-form-item  label="class" prop="gradeClass">
                    <el-select v-model="formStudent.gradeClass.id" placeholder="Please select a class" style="width: 100%;">
                        <el-option v-for="item in gradeClassOptions" :key="item.id" :label="item.name" :value="item.id" />
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="student number" prop="stuno">
                    <el-input v-model="formStudent.stuno" placeholder="Please enter student number" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="student name" prop="name">
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
                    <el-input v-model="formStudent.email" placeholder="Please enter email" />
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
        <el-button color="#178557" :loading="subLoading" type="success" @click="editStudent(ruleFormRef)">Save</el-button>
    </div>
</template>

<style scoped>
.dialong__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>