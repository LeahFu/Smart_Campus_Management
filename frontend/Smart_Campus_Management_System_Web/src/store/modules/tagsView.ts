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
        },
        addVisitedView(view){
            this.setTabsMenuValue(view.path)
            if(this.visitedViews.some(v=>v.path===view.path))return
            this.visitedViews.push(
                Object.assign({},view,{
                    title:view.meta.title || 'no-name'
            })
            )
        },
        addView(view:any){
            this.addVisitedView(view)
        }
    }
})