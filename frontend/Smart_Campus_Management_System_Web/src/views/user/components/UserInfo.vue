<script setup lang="ts">

import userInfo from "./UserInfo.vue";
import {calculateDays} from "../../../utils/date.ts";
import {useUserStore} from "../../../store/modules/user.ts";
// Server path
const url = import.meta.env.VITE_APP_BASE_API;
// Login user information
const {userInfo} = useUserStore()
</script>

<template>
    <el-col class="hidden-md-and-down" :lg="6" :xl="6">
        <div class="right_box">
            <h3 class="title">
                <el-icon style="margin-right: 10px;">
                    <Grid />
                </el-icon>
                Information Display
            </h3>
            <div class="info">
                <div class="right_photo">
                    <img v-if="userInfo.userIcon === null || userInfo.userIcon === ''" src="../../../assets/default_avatar.png">
                    <img v-else :src="url+'uploadFile/'+userInfo.userIcon">
                </div>
                <h2>Hi，{{userInfo.realname}}</h2>
                <br>
                <p class="email">{{userInfo.email}}</p>
            </div>
            <el-divider />
            <h3 class="title">
                <el-icon style="margin-right: 10px;">
                    <Sunrise />
                </el-icon>
                Account Type
            </h3>
            <div class="type">
                <br>
                <p>Account types are divided into: ordinary users, administrators</p>
                <p>The administrator can manage the data, grades, system, etc. of this site</p>
                <p>Ordinary users can manage grades, etc.</p>
                <br>
                <p>Technical Support</p>
                <p>
                    Email: <a href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&amp;email=11184629@qq.com"
                                target="_blank" data-v-634963c2="">11184629 @qq.com</a>
                </p>
            </div>
            <el-divider />
            <h3 class="title">
                <el-icon style="margin-right: 10px;">
                    <Watch />
                </el-icon>
                The number of days this site is running: （{{calculateDays('2022-12-20 00:00:00')}}）
            </h3>
        </div>
    </el-col>
</template>

<style scoped>
.right_box {
    width: 100%;
    height: auto;
    background: white;
    padding: 20px;
    box-sizing: border-box;
}
.right_photo {
    position: relative;
    text-align: center;
    height: 100px;
    width: 100px;
    margin-left: -50px;
    left: 50%;
}
.info {
    text-align: center;
}
.right_photo img {
    width: 100%;
    border-radius: 50%;
}
.right_box h2 {
    padding-top: 10px;
    letter-spacing: 0;
    font-size: 30px;
}
.right_box .title {
    color: #178557;
}
</style>