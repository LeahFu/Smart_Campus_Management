<script setup lang="ts">
import {ref, reactive} from 'vue'
import {ElMessage} from 'element-plus'
import { FormInstance, FormRules } from 'element-plus'
import {addGradeClassApi} from "../../../api/gradeclass/gradeclass.ts";

const emit = defineEmits(['closeAddGradeClassForm','success'])
const subLoading = ref(false)
const formGradeClass = reactive({
    name: '',
    code: '',
    clazz: '',
    grade: 2023,
    remarks: ''
})
const ruleFormRef = ref<FormInstance>()
// Define form constraint rule object
const rules = reactive<FormRules>({
    name: [{ required: true, message: 'Class name can not be empty', trigger: 'blur' }],
    code: [{ required: true, message: 'Class code can not be empty', trigger: 'blur' }],
    grade: [{ required: true, message: 'Grade can not be empty', trigger: 'blur' }],
    clazz: [{ required: true, message: 'Class can not be empty', trigger: 'blur' }]
})
// Add class information
const addGradeClass = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        subLoading.value = true
        if (valid) {
            const { data } =  await addGradeClassApi(formGradeClass)
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
    emit('closeAddGradeClassForm')
}
</script>

<template>
    <el-form ref="ruleFormRef" :rules="rules"  :model="formGradeClass"  label-width="80px">
        <el-row>
            <el-col :span="12">
                <el-form-item label="class code" prop="code">
                    <el-input v-model="formGradeClass.code" placeholder="Please enter class code" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="class name" prop="name">
                    <el-input v-model="formGradeClass.name" placeholder="Please enter class name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="grade" prop="grade">
                    <el-input v-model="formGradeClass.grade" placeholder="Please enter grade" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="class" prop="clazz">
                    <el-input v-model="formGradeClass.clazz" placeholder="Please enter class" />
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="remarks">
                    <el-input
                            v-model="formGradeClass.remarks"
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
        <el-button color="#178557" :loading="subLoading" type="success" @click="addGradeClass(ruleFormRef)">Save</el-button>
    </div>
</template>

<style scoped>
.dialog__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>