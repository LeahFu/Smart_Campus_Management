<script setup lang="ts">
import {ref,reactive,toRefs,onMounted,computed } from 'vue'
import {useUserStore} from "../../../store/modules/user.ts";
import {sendEmailApi, updateEmailApi, verifyCodeApi} from "../../../api/user/user.ts";
import {ElMessage} from "element-plus";
const state = reactive({
    toBind: {
        // Randomly generate verification codes
        verificationCode: '',
        // The verification code entered by the old email address
        code: '',
        email: '',
        // The verification code entered by the new email address
        code2: ''
    },
})

// Get login user information
const { userInfo } = useUserStore()
// Convert the middle of the mailbox to an asterisk
const userEmail = computed(() => {
    const { email } = userInfo
    return email.substr(0,3)+'****'+email.substr(7)
})
// Time timer
const timer = ref(null)
// Get the verification code 60 seconds countdown
const TIME_COUNT = 60
// Current seconds
const count = ref(0)
// Get the verification code, Click to disable
const show = ref(true)
// Get verification code text
const codeText = ref('Get verification code')
// Click to change the binding email
// Show get verification code box
const showGetCode = ref(false)

// Get email verification code
const getCode = async (type: number) => {
    // 60 seconds countdown after click
    if(!timer.value){
        count.value = TIME_COUNT
        show.value = false
        timer.value  = setInterval(() => {
            if (count.value > 0 && count.value <= TIME_COUNT) {
                count.value--;
                codeText.value = count.value + 's';
            } else {
                show.value = true;
                window.clearInterval(timer.value);
                timer.value = null;
                codeText.value = "Reacquire";
            }
        }, 1000)
    }
    const { data } = await sendEmailApi(state.toBind.email)
    if(data.status===200){
        ElMessage({
            message: 'The verification code has been sent to your mailbox. ' +
                'This verification code is used to replace the email binding.' +
                ' Please do not tell others the verification code. It is valid for 3 minutes, please keep it safe.',
            type: 'success',
        })
    }
}
const { toBind } = toRefs(state)

const showNewEmail = ref(false)
const confirmCode = async () => {

    if(state.toBind.code!=''){
        // Verify that the verification code entered by the user is correct
        const { data } = await verifyCodeApi(state.toBind.code)
        if(data.status===200) {
            // Show input box for new email address
            showNewEmail.value = true
            // Close the old email verification code input box
            showGetCode.value = false
            // Clear timer
            show.value = true
            window.clearInterval(timer.value)
            timer.value = null
            codeText.value = "Get verification code"
        }
    }else {
            ElMessage.error('Perform the replacement mailbox binding operation as required.')
            return false;
    }
}
// Submit email to change binding
const toBindSubmit = async () => {
    if(state.toBind.code2!=''){
        // Clear timer
        show.value = true
        window.clearInterval(timer.value);
        timer.value = null;
        codeText.value = "Get verification code";
        const { data } = await updateEmailApi(state.toBind.email,state.toBind.code2)
        if(data.status===200){
            ElMessage({
                message: 'Successfully changed the bound email address.',
                type: 'success',
            })
        }else {
            ElMessage.error('Perform the replacement email binding operation as required.')
            return false;
        }
    }else {
        ElMessage.error('Perform the replacement email binding operation as required.')
        return false;
    }
}
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