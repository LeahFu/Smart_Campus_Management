<script setup lang="ts">
import { ref, reactive } from 'vue'
import {editCourseApi} from "../../../api/course/course.ts";
import {ElMessage} from 'element-plus'
import type { FormInstance, FormRules } from 'element-plus'
// Button status
const subLoading = ref(false)
// Form data object
const formCourse = reactive({
    id: 0,
    courseno: '',
    coursename: '',
    remarks: ''
})
// Get the courseInfo object passed from the parent component
const props = defineProps(['courseInfo'])
const courseInfo = ref(props.courseInfo)
// Fill the form with data
for (const key in formCourse) {
    formCourse[key] = courseInfo.value[key]
}
// Edit course information
const editCourse = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        subLoading.value = true
        if (valid) {
            const {data} = await editCourseApi(formCourse)
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
const emit = defineEmits(['closeEditCourseForm','success'])
// Cancel form
const close = ()=> {
    emit('closeEditCourseForm')
}
// Define the form instance object
const ruleFormRef = ref<FormInstance>()
// Define Form Constraint Rule Objects
const rules = reactive<FormRules>({
    courseno: [{ required: true, message: 'Course number cannot be empty', trigger: 'blur' }],
    coursename: [{ required: true, message: 'Course name cannot be empty', trigger: 'blur' }]
})
</script>

<template>
    <el-form ref="ruleFormRef" :rules="rules" :model="formCourse"  label-width="80px">
        <el-row>
            <el-col :span="12">
                <el-form-item label="Course number" prop="courseno">
                    <el-input v-model="formCourse.courseno" placeholder="Please enter course number" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Course name" prop="coursename">
                    <el-input v-model="formCourse.coursename" placeholder="Please enter course name" />
                </el-form-item>
            </el-col>

            <el-col :span="24">
                <el-form-item label="Remarks">
                    <el-input
                            v-model="formCourse.remarks"
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
        <el-button color="#178557" :loading="subLoading" type="success" @click="editCourse(ruleFormRef)">Save</el-button>
    </div>
</template>

<style scoped>
.dialong__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>