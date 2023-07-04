import {defineStore} from 'pinia'
export const useUserStore = defineStore({
    id:'userStore',
    state: ()=>{
        return {
            // login token
            token: '',
            // user information
            userInfo:{
                userIcon:'',
                realname:'',
                gender:''
            },
            // user roles
            roles:[]
        }
    },
    getters: {},
    // synchronous or asynchronous
    actions:{
        // set login token
        setToken(token:string){
            this.token = token;
        },
        // set user information
        setUserInfo(userInfo:any){
            this.userInfo = userInfo
        },
        // Update part of information
        setUserPartInfo(userInfo:any){
            this.userInfo.userIcon = userInfo.userIcon
            this.userInfo.gender = userInfo.gender
            this.userInfo.realname = userInfo.realname
        }
    },
    persist: true
})
