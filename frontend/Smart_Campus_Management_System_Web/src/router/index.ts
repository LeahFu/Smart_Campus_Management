//1. import Vue Router module
import { createRouter, createWebHashHistory } from 'vue-router'
import Nprogress from "../config/nprogress.ts";
//2. Define some routes, each route needs to be mapped to a component
const routes = [{
    path:'/',
    name:'Login',
    meta:{title:'smart campus management system login'},
    component:()=>import('../views/Login.vue')
}]
//3. Create routing instance and pass routes configuration
const router = createRouter({
    history: createWebHashHistory(),
    routes:routes
})
//Route interception guard
router.beforeEach(async(to,from,next)=>{
    //1. start Nprogress
    Nprogress.start()
    next()
})

// Routing jump ends
router.afterEach(()=>{
    Nprogress.done()
})

// Routing jump fail
router.onError(error=>{
    Nprogress.done()
    console.warn("Routing error",error.message)
})
export default router