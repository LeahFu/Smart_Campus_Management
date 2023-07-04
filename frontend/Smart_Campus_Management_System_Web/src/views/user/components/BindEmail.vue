<script setup lang="ts">

</script>

<template>
    <div class="set">
        <h4>Bind email</h4>
        <p>
            <span>Email has been bound：</span><span style="font-size: 25px;">{{userEmail}}</span>
        </p>
        <p>Email is an important way of your identity verification, please be careful!</p>
        <p>
            <el-form ref="toBindFormRef" :rules="toBindRules" status-icon :model="toBind"
                     class="demo-form-inline">
                <el-row :gutter="20">
                    <el-col :span="12" v-show="showGetCode && !showNewEmail">
                        <el-form-item prop="code" style="width: 100%;">
                            <el-input v-model="toBind.code" placeholder="Please enter the email verification code">
                                <template #append>
                                    <input type="button" :plain="true" @click="getCode(1)" :disabled="!show"
                                           style="width: 100%;height: 100%;border: 0px;background: none;width: 70px;color: #ababab;cursor:pointer;"
                                           :value="codeText" />
                                </template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8"
                            v-show="showNewEmail && !showGetCode">
                        <el-form-item prop="email" style="width: 100%;">
                            <el-input v-model="toBind.email" placeholder="Please enter a new email address" />
                        </el-form-item>
                    </el-col>
                    <el-col :span="8"
                            v-show="showNewEmail && !showGetCode">
                        <el-form-item prop="code2" style="width: 100%;">
                            <el-input v-model="toBind.code2" placeholder="Please enter the new email verification code">
                                <template #append>
                                    <input type="button" :plain="true" @click="getCode(2)" :disabled="!show"
                                           style="width: 100%;height: 100%;border: 0px;background: none;width: 70px;color: #ababab;;cursor:pointer;"
                                           :value="codeText" />
                                </template>
                            </el-input>
                        </el-form-item>
                    </el-col>

                    <el-col  :span="6"
                             v-show="showNewEmail && !showGetCode">
                        <el-form-item style="width: 100%;">
                            <el-button plain color="#2fa7b9" @click="toBindSubmit">Submit
                            </el-button>
                        </el-form-item>
                    </el-col>

                    <el-col :span="12"
                            v-show="showGetCode && !showNewEmail">
                        <el-form-item style="width: 100%;">
                            <el-button plain color="#2fa7b9" @click="confirmCode">
                                Confirm replacement
                            </el-button>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24"
                            v-show="!showGetCode && !showNewEmail">
                        <el-button plain color="#2fa7b9" @click="showGetCode = true"
                                   style="margin-left: 0px;">Replace bound email
                        </el-button>
                    </el-col>
                </el-row>
            </el-form>
        </p>
    </div>
</template>

<style scoped>

</style>