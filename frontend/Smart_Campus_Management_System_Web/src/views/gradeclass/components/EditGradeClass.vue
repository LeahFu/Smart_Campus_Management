<script setup lang="ts">
import { ref, reactive } from 'vue'
const subLoading = ref(false)
const formGradeClass = reactive({
    id: 0,
    name: '',
    code: '',
    clazz: 1,
    grade: 2023,
    remarks: ''
})
// Get the class details passed by the parent component
const props = defineProps(['gradeClassInfo'])
const gradeClassInfo = ref(props.gradeClassInfo)
// Fill the form with data
for (const key in formGradeClass) {
    formGradeClass[key] = gradeClassInfo.value[key]
}
</script>

<template>
    <el-form  :model="formGradeClass"  label-width="80px">
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

    <div class="dialong__button--wrap">
        <el-button @click="close">Cancel</el-button>
        <el-button color="#178557" :loading="subLoading" type="success" @click="editGradeClass(ruleFormRef)">Save</el-button>
    </div>
</template>

<style scoped>
.dialong__button--wrap {
    text-align: center;
    margin-top: 20px;
}
</style>