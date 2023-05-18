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

</style>