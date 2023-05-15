<script setup lang="ts">

</script>

<template>
        <div class="main">
            <div class="tool-left">
                <CollapseIcon/>
                <Hamburger/>
            </div>
            <div class="linkBox">
                <!--Login user information start-->
                <el-popover
                        :width="320"
                >
                    <!--User avatar start-->
                    <template #reference>
                        <el-link :underline="false">
                            <!-- If the user does not set an avatar, the system default avatar will be used -->
                            <img v-if="userInfo.userIcon" :src="url+'uploadFile/'+userInfo.userIcon"
                                 style="width: 50px;border-radius: 50px;" />
                            <img v-else src="../../../assets/default_avatar.png"
                                 style="width: 40px;border-radius: 50px;">
                            <span>{{userInfo.realname}}</span>
                        </el-link>
                    </template>
                    <!--User avatar end-->
                    <!--User information start-->
                    <template #default>
                        <div class="demo-rich-conent" style="display: flex; gap: 16px; flex-direction: column">
                            <div class="demo-rich-conent-custom">
                                <!-- If the user does not set information, the system default information will be used -->
                                <img v-if="userInfo.userIcon" :src="url+'uploadFile/'+userInfo.userIcon"/>
                                <img v-else  src="../../../assets/default_avatar.png">
                                <p class="demo-rich-content__name">
                                    User Name：{{userInfo.username}}
                                </p>
                                <p class="demo-rich-content__mention">
                                    Email：{{userInfo.email}}
                                </p>
                                <p class="demo-rich-content__mention">
                                    Register Time：{{formatTime(userInfo.createTime,'yyyy-MM-dd HH:mm:ss')}}
                                </p>
                            </div>
                            <p class="demo-rich-content__desc" style="margin: 0">
                                <div
                                        style="float:left;width: 75px;padding:10px;border-right: 1px solid rgb(205, 205, 205);text-align: center;">
                                    <p>性别</p>
                                    <p style="font-size: 25px;font-weight:600;">
                                        {{ userInfo.sex }}
                                        <span style="font-size: 10px;font-weight:100;margin-left:5px;">孩</span>
                                    </p>
                                </div>
                                <div
                                        style="float:left;width: 80px;padding:10px;border-right: 1px solid rgb(205, 205, 205);text-align: center;">
                                    <p>Register</p>
                                    <p style="font-size: 25px;font-weight:600;">
                                        {{calculateDays(userInfo.createTime)}}
                                        <span style="font-size: 10px;font-weight:100;margin-left:5px;">Day</span>
                                    </p>
                                </div>
                                <div style="float:left;width: 75px;padding:10px;text-align: center;">
                                    <p>Stryle</p>
                                    <p style="font-size: 15px;font-weight:600;line-height: 40px;">
                                        <span >{{userInfo.role.name}}</span>
                                    </p>
                                </div>
                            </p>
                        </div>
                    </template>
                    <!--User information end-->
                </el-popover>
                <!--Login user information end-->

            </div>
        </div>
        <TabsView/>
</template>

<style scoped>

</style>