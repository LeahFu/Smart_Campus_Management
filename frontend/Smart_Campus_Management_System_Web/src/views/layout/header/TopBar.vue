<script setup lang="ts">
import { SwitchButton } from '@element-plus/icons-vue'
import { useUserStore } from '../../../store/modules/user'
import {loginOutApi} from '../../../api/login/login'
import CollapseIcon  from './CollapseIcon.vue'
import Hamburger from './Hamburger.vue'
import TabsView from '../tags/Index.vue'
import {calculateDays, formatTime} from "../../../utils/date";
const { userInfo } = useUserStore()
// logout
const exit = async () => {

    const { data } = await loginOutApi();
    if(data.status===200){
        // clear login information
        window.localStorage.removeItem("userStore");
        // return login page
        window.location.href = "/";
    }

}
// server path
const url = import.meta.env.VITE_APP_BASE_API
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
                                    <p>Gender</p>
                                    <p style="font-size: 25px;font-weight:600;">
                                        {{ userInfo.sex }}
                                        <span style="font-size: 10px;font-weight:100;margin-left:5px;"></span>
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

                <!--logout start-->
                <el-popconfirm confirm-button-text="Submit" cancel-button-text="Cansel" :icon="SwitchButton" icon-color="#30bcd7"
                      title="Are you sure you want to log out of the system?" @confirm="exit">
                   <template #reference>
                      <el-link :underline="false">
                        <el-icon>
                            <SwitchButton />
                        </el-icon>
                        <span>Logout</span>
                      </el-link>
                   </template>
                </el-popconfirm>
                <!--logout end-->
        </div>
      </div>
         <TabsView/>
</template>

<style scoped>
.main {
    display: flex;
    justify-content: space-between;
    height: 70px;
    box-shadow: rgb(0 0 0 / 10%) 0px 0px 10px;
    background: white;
}

.linkBox {
    height: 100%;
    display: flex;
    align-items: center;
    text-align: center;
    float: right;
}
.linkBox .el-link {
    margin-right: 25px;
    color: #8c8c8c;
}
.linkBox .el-link:hover {
    color: #30bcd7;
}
.linkBox .el-link span {
    margin-left: 8px;
}

/* The style of the mouse over the avatar */
.demo-rich-conent-custom img {
    width: 55px;
    margin: 0px 15px 0px 0px;
    border-radius: 50px;
    float: left;
}

.demo-rich-conent-custom p {
    margin: 0;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
.tool-left{
    display: flex;
    align-items: center;
    height: 100%;
}
</style>