<script setup lang="ts">
import {ref, reactive} from 'vue'
import {ElMessage} from 'element-plus'
import {addTeacherApi, getAllCourseListApi} from "../../../api/teacher/teacher.ts";
// Button status
const subLoading = ref(false)
// Form data object
const formTeacher = reactive({
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
// Add teacher information
const addTeacher = async () => {
        subLoading.value = true
            const { data } =  await addTeacherApi(formTeacher)
            if(data.status===200){
                ElMessage.success(data.message)
            }else {
                ElMessage.error(data.message)
            }
        subLoading.value = false
}
// Define course drop-down box selection items
const courseOptions = ref<object[]>([])
// Get a list of all courses
const getAllCourseList() = async ()=>{
        const { data } = await getAllCourseListApi()
        if (data.status === 200) {
            courseOptions.value = data.result
        }
}
getAllCourseList()
// Define event
const emit = defineEmits(['closeAddTeacherForm'])
// Cancel form
const close = ()=> {
    emit('closeAddTeacherForm')
}
</script>

<template>
    <el-form  :model="formTeacher"  label-width="80px">
        <el-row>
            <el-col :span="12">
                <el-form-item prop="gradeClass" label="Course">
                    <el-select v-model="formTeacher.course.id" placeholder="Please select a course" style="width: 100%;">
                        <el-option v-for="item in courseOptions" :key="item.id" :label="item.name" :value="item.id" />
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Teacher number" prop="teachno">
                    <el-input v-model="formTeacher.teachno" placeholder="Please enter teacher number" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Teacher name" prop="name">
                    <el-input v-model="formTeacher.name" placeholder="Please enter teacher name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Gender" prop="gender">
                    <el-input v-model="formTeacher.gender" placeholder="Please enter gender" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Phone" prop="phone">
                    <el-input v-model="formTeacher.phone" placeholder="Please enter phone number" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Email" prop="email">
                    <el-input v-model="formTeacher.email" placeholder="Please enter email" />
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="Remarks">
                    <el-input
                            v-model="formTeacher.remarks"
                            :rows="2"
                            type="textarea"
                            placeholder="Please enter remarks"/>
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>

    <div class="dialong__button--wrap">
        <el-button @click="close">Cancel</el-button>
        <el-button color="#178557" :loading="subLoading" type="success" @click="addTeacher(ruleFormRef)">Save</el-button>
    </div>
</template>

<style scoped>
.dialong__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>