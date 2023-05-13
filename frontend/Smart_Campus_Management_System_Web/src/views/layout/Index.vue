<script setup lang="ts">

</script>

<template>
  <!-- background image -->
    <div id="ebg">

    </div>
    <div class="common-layout">
        <el-container>
            <!-- left menu -->
            <el-aside width="auto">
                <AsideMenu></AsideMenu>
            </el-aside>
            <el-container>
                <!-- top navigation bar -->
                <el-header>
                    <TopBar/>
                </el-header>
                <!-- main content -->
                <el-main>
                    <router-view>main content</router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<style scoped>
/* top navigation bar */
.el-header {
    --el-header-padding: 0px;
    --el-header-height: auto;
    height: 111px;
    background: #F3F3F3;
}
.el-main {
    background: #F3F3F3;
}
.el-container {
    height: 100%;
}
.common-layout {
    width: 99vw;
    height: 98vh;
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    background-color: white;
    border-radius: 15px;
    overflow: hidden;
}
/* design background image */
#ebg {
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
    padding: 10px;
    background-image: url(../../assets/system-bg.jpg);
    background-size: cover;
    background-position: center center;
    background-repeat: no-repeat;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
}
</style>