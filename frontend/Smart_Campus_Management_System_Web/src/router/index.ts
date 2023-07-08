//1. import Vue Router module
import { createRouter, createWebHashHistory } from 'vue-router'
import NProgress from "../config/nprogress.ts";
import {useUserStore} from "../store/modules/user.ts";
import {useMenuStore} from "../store/modules/menu.ts";
//2. Define some routes, each route needs to be mapped to a component
// Define static routes
// @ts-ignore
export const staticRouter = [
    {
        path: '/',
        redirect: "/login",
        isMenu: false
    },
    {
        path: '/login',
        name: 'Login',
        meta: { title: 'Smart Campus Management System - Login'},
        component: ()=> import('../views/login/Login.vue'),
        isMenu: false

    },
    {
        path: '/index',
        name: 'index',
        component: ()=> import('../views/layout/Index.vue'),
        redirect: "/home",
        isMenu: true,
        funcNode:1,
        children: [{
            path: '/home',
            name: 'home',
            meta: { title: 'Home', icon: 'House',affix: true },
            component: ()=> import('../views/home/Index.vue')
        }]
    },
    {
        path: '/user',
        name: 'UserSetting',
        redirect: '/user/setting',
        component: ()=> import('../views/layout/Index.vue'),
        isMenu: true,
        funcNode:1,
        children: [
            {
                path: 'setting',
                name: 'PersonalSettings',
                meta: { title: 'Personal setting', icon: 'Basketball'},
                component: ()=> import('../views/user/components/PersonalSettings.vue')
            }
        ]
    }
]
// Define dynamic route
export const asyncRoutes = [
    {
        path: '/system',
        name: 'system',
        meta: {
            title: 'System Management',
            icon: 'GoldMedal',
            role: ['ROLE_ADMIN']
        },
        redirect: '/system/user',
        component: ()=> import('../views/layout/Index.vue'),
        isMenu: true,
        funcNode:2,
        children: [
            {
                path: 'user',
                name: 'User',
                meta: {
                    title: 'User Management',
                    icon: 'UserFilled',
                    role: ['ROLE_ADMIN']
                },
                component: ()=> import('../views/user/UserList.vue')
            },
            {
                path: 'role',
                name: 'Role',
                meta: {
                    title: 'Role Management',
                    icon: 'Stamp',
                    role: ['ROLE_ADMIN']
                },
                component: ()=> import('../views/role/RoleList.vue')
            }
        ]
    },
    {
        path: '/base',
        name: 'base',
        meta: {
            title: 'Data Management',
            icon: 'DataAnalysis',
            role: ['ROLE_ADMIN']
        },
        redirect: '/base/gradeclass',
        component: ()=> import('../views/layout/Index.vue'),
        isMenu: true,
        funcNode:2,
        children: [
            {
                path: 'gradeclass',
                name: 'gradeclass',
                meta: {
                    title: 'Class Management',
                    icon: 'Box',
                    role: ['ROLE_ADMIN']
                },
                component: ()=> import('../views/gradeclass/GradeClassList.vue')
            },
            {
                path: 'student',
                name: 'student',
                meta: {
                    title: 'Student Management',
                    icon: 'User',
                    role: ['ROLE_ADMIN']
                },
                component: ()=> import('../views/student/StudentList.vue')
            },
            {
                path: 'course',
                name: 'course',
                meta: {
                    title: 'Course Management',
                    icon: 'Tickets',
                    role: ['ROLE_ADMIN']
                },
                component: ()=> import('../views/course/CourseList.vue')
            },
            {
                path: 'teacher',
                name: 'teacher',
                meta: {
                    title: 'Teacher Management',
                    icon: 'Avatar',
                    role: ['ROLE_ADMIN']
                },
                component: ()=> import('../views/teacher/TeacherList.vue')
            }
        ]
    },
    {
        path: '/scores',
        name: 'scores',
        meta: {
            title: 'Scores Management',
            icon: 'GoldMedal',
            role: ['ROLE_USER','ROLE_ADMIN']
        },
        redirect: '/scores/index',
        component: ()=> import('../views/layout/Index.vue'),
        isMenu: true,
        funcNode:2,
        children: [
            {
                path: 'index',
                name: 'scoresIndex',
                meta: {
                    title: 'Class Courses Grades',
                    icon: 'Money',
                    role: ['ROLE_USER','ROLE_ADMIN']
                },
                component: ()=> import('../views/scores/ScoresList.vue')
            }
        ]
    },
    {
        path: '/census',
        name: 'census',
        meta: {
            title: 'Data Statistics',
            icon: 'Medal',
            role: ['ROLE_USER','ROLE_ADMIN']
        },
        redirect: '/census/index',
        component: ()=> import('../views/layout/Index.vue'),
        isMenu: true,
        funcNode:2,
        children: [
            {
                path: 'index',
                name: 'scoresCensusIndex',
                meta: {
                    title: 'Class Courses Grades Statistics',
                    icon: 'Histogram',
                    role: ['ROLE_USER','ROLE_ADMIN']
                },
                component: ()=> import('../views/census/ScoresCensus.vue')
            },
            {
                path: 'contrast',
                name: 'scoresContrastCensusIndex',
                meta: {
                    title: 'Comparative Statistics of Class Courses',
                    icon: 'Notification',
                    role: ['ROLE_USER','ROLE_ADMIN']
                },
                component: ()=> import('../views/census/ScoresContrastCensus.vue')
            }
        ]
    }
]

//3. Create routing instance and pass routes configuration
const router = createRouter({
    history: createWebHashHistory(),
    routes:staticRouter
})
//Route interception guard
router.beforeEach(async (to, from, next) => {
// 1.NProgress start
    NProgress.start();

    //2.If you are visiting the login page, let it go directly
    if(to.path==='/login')return next()

    //3.Determine whether there is a Token, not redirected to login
    const userStore = useUserStore()
    if(!userStore.token)return next({path:`/login?redirect=${to.path}`,replace:true})

    // Get the role of the logged in user
    const { userInfo } = userStore
    const roles = []
    roles.push(userInfo.role.code)

    // Dynamically generate routing and access mappings based on roles
    const menuStore = useMenuStore()
    if (!menuStore.routers.length) {
        const accessRoutes =  menuStore.generateRoutes({roles: roles})
        accessRoutes.forEach(item => router.addRoute(item)) // Dynamically add access routing table
        next({ ...to, replace: true }) // This is equivalent to pushing to a page instead of entering route interception
    }else {
        // Normal page
        next();
    }
});

// Routing jump ends
router.afterEach(()=>{
    NProgress.done()
})

// Routing jump fail
router.onError(error=>{
    NProgress.done()
    console.warn("Routing error",error.message)
})
export default router