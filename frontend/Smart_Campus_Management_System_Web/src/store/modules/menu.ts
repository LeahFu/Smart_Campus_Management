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
// Filter asyncRoutes by recursion
export function filterAsyncRoutes ({routes, roles}: { routes: any, roles: any }) {
    const res = []
    routes.forEach(route => {
        const tmp = { ...route }
        if (hasPermission(roles, tmp)) {
            if (tmp.children) {
                tmp.children = filterAsyncRoutes({routes: tmp.children, roles: roles})
            }
            res.push(tmp)
        }
    })
    return res
}
function hasPermission (roles, route) {
    if (route.meta && route.meta.role) {
        // The some() method is used to check whether
        // the elements in the array meet the specified conditions (provided by the function)
        return roles.some(role => route.meta.role.indexOf(role) >= 0)
    } else {
        return true
    }
}