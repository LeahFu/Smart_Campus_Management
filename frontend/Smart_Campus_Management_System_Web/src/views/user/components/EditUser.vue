<script setup lang="ts">

</script>

<template>
    <el-form ref="ruleFormRef" :rules="rules"  :model="formUser"  label-width="80px">
        <el-row>
            <el-col :span="12">
                <el-form-item label="user name" prop="username">
                    <el-input v-model="formUser.username" placeholder="Please enter user name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="password" prop="password">
                    <el-input v-model="formUser.password" placeholder="Please enter password" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="real name" prop="realname">
                    <el-input v-model="formUser.realname" placeholder="Please enter real name" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="email" prop="email">
                    <el-input v-model="formUser.email" placeholder="Please enter email" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="gender" prop="sex">
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
                            placeholder="Please enter remark"
                    />
                </el-form-item>
            </el-col>

        </el-row>
    </el-form>
    <div class="dialong__button--wrap">
        <el-button @click="close">cancel</el-button>
        <el-button color="#178557" :loading="subLoading" type="success" @click="editUser(ruleFormRef)">save</el-button>
    </div>
</template>

<style scoped>

</style>