import {defineStore} from 'pinia'
export const useUserStore = defineStore({
    id:'userStore',
    state: ()=>{
        return {
            // login token
            token: '',
            // user information
            userInfo:{},
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
        }
    },
    persist: true
})
