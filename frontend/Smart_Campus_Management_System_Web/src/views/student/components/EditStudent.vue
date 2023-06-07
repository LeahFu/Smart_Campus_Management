<script setup lang="ts">

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

</style>