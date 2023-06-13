<script setup lang="ts">

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

</style>