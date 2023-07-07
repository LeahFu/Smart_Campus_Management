import {defineStore} from 'pinia'
import {asyncRoutes, staticRouter} from "../../router";
export const useMenuStore = defineStore({
    id:'menuState',
    state: ()=>({
        // menu static route
        routers:[],
        // dynamic route
        addRouters: [],
        // user role
        roles: []
    }),
    getters: {},
    actions: {
        // set role
        generateRoutes: function ({roles}: { roles: any }) {
            let accessedRoutes = filterAsyncRoutes({routes: asyncRoutes, roles: roles})
            this.addRouters = accessedRoutes
            this.routers = staticRouter.concat(accessedRoutes)
            return accessedRoutes
        },
        // Dynamically generate access routes
        setRoles({roles}: { roles: any }) {
            this.roles = roles
        }
    }
})