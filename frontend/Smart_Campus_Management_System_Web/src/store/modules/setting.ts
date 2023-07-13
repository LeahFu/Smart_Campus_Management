import {defineStore} from 'pinia'
export const useSettingStore = defineStore({
    id:'settingState',
    state: ()=>({
        isCollapse:true,
        // tagsView default display
       // showTag:true,
    }),
    getters: {},
    actions:{
        setCollapse(value: boolean){
            this.isCollapse = value
        }
    }
})
