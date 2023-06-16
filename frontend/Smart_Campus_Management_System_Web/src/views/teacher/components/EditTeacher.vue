<script setup lang="ts">
import { ref, reactive } from 'vue'
import {ElMessage} from 'element-plus'
import {editTeacherApi, getAllCourseListApi} from "../../../api/teacher/teacher.ts";
// Button status
const subLoading = ref(false)
// Form data object
const formTeacher = reactive({
    id: 0,
    name: '',
    teachno: '',
    gender: '',
    phone: '',
    course: {
        id: ''
    },
    email: '',
    remarks: ''
})
// Get the (teacherInfo) object passed by the parent component
const props = defineProps(['teacherInfo'])
const teacherInfo = ref(props.teacherInfo)
// Fill the form with data
for (const key in formTeacher) {
    formTeacher[key] = teacherInfo.value[key]
}
// Edit teacher information
const editTeacher = async () => {
        subLoading.value = true
            const { data } =  await editTeacherApi(formTeacher)
            if(data.status===200){
                ElMessage.success(data.message)
                emit('success')
            }else {
                ElMessage.error(data.message)
            }
        subLoading.value = false
}
// Define Course Dropdown Selections
const courseOptions = ref<object[]>([])
// Get a list of all courses
async function getAllCourseList() {
        const { data } = await getAllCourseListApi()
        courseOptions.value = data.result
}
getAllCourseList()
// Define event
const emit = defineEmits(['closeEditTeacherForm'])
// Cancel form
const close = ()=> {
    emit('closeEditTeacherForm')
}
</script>

<template>
    <el-form  :model="formTeacher"  label-width="80px">
        <el-row>
            <el-col :span="12">
                <el-form-item label="Course">
                    <el-select v-model="formTeacher.course.id" placeholder="Please select a course" style="width: 100%;">
                        <el-option v-for="item in courseOptions" :key="item.id" :label="item.name" :value="item.id" />
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Teacher number" >
                    <el-input v-model="formTeacher.teachno" placeholder="Please enter teacher number" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Teacher name" >
                    <el-input v-model="formTeacher.name" placeholder="Please enter teacher name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Gender" >
                    <el-input v-model="formTeacher.gender" placeholder="Please enter gender" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Phone" >
                    <el-input v-model="formTeacher.phone" placeholder="Please enter phone number" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Email" >
                    <el-input v-model="formTeacher.email" placeholder="Please enter email" />
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="remarks">
                    <el-input
                            v-model="formTeacher.remarks"
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
        <el-button color="#178557" :loading="subLoading" type="success" @click="editTeacher">Save</el-button>
    </div>
</template>

<style scoped>
.dialong__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>