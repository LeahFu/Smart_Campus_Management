import {defineStore} from 'pinia'
import router from "../../router"
export const useTagsViewStore = defineStore({
    id: 'tagsViewState',
    state: () => ({
        activeTabsValue: '',
        visitedViews: [{path: '/home', name: 'home', meta: {title: 'home page', affix: true}, title: 'home page'}],
        cachedViews: [],

    }),
    getters: {},
    actions: {
        setTabsMenuValue(val:string){
            this.activeTabsValue = val
        }
    }
})