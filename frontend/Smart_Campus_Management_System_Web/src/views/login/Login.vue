<script setup lang="ts">

import LoginForm from "./components/LoginForm.vue";
</script>

<template>
    <div class="login-container">
        <div class="login-box">
            <!--left background start-->
            <div class="login-left">
                <img src="../../assets/login/side-logo.png">
            </div>
            <!--left background end-->
            <!--login form start-->
            <div class="login-form">
                <!--title and logo start-->
                <div class="login-title">
                    <img class="icon" src="../../assets/logo2.png">
                    <h2 class="title">Smart Campus Management System</h2>
                </div>
                <!--title and logo end-->
                <LoginForm/>
            </div>
            <!--login form end-->
        </div>
    </div>
</template>

<style scoped>
.login-container {
    background-color: #f0f2f5;
    height: 100%;
    width: 100%;
    overflow: hidden;
    display: flex;
    background-image: url("../../assets/login/login_bg.svg");
    justify-content: center;
    align-items: center;
    padding: 25px 25px;
    box-sizing: border-box;
}
.login-box {
    position: relative;
    width: 100%;
    height: 100%;
    background-color: #fffc;
    border-radius: 8px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.login-left{
    width: 50%;
}
.login-left img{
    width: 100%;
    max-width: 900px;
}

.login-form{
    max-width: 480px;
    width: 50%;
    padding: 40px;
    border-radius: 10px;
    box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
    box-sizing: border-box;
}

.login-title{
    display: flex;
    align-items: center;
    justify-content: space-around;
    margin-bottom: 30px;
}

.login-title .title{
    margin: 0;
    font-size: 30px;
    white-space: nowrap;
}
.login-title .icon{
    width: 60px;
}

::v-deep(.el-input__inner){
    height: 40px;
}
</style>