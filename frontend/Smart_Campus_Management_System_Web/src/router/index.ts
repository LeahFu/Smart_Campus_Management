//1. import Vue Router module
import { createRouter, createWebHashHistory } from 'vue-router'
import Nprogress from "../config/nprogress.ts";
//2. Define some routes, each route needs to be mapped to a component
const routes = [{
    path:'/',
    name:'Login',
    meta:{title:'smart campus management system login'},
    component:()=>import('../views/login/Login.vue')
},
    {path:'/index',
     name:'index',
     component:()=>import('../views/layout/Index.vue'),
     redirect:'/home',
     children:[{
        path:'/home',
        name:'home',
        meta:{title:'home page',icon:'House',affix:true},
        component:()=>import('../views/home/index.vue')
     }]
    },
    {
        path:'/system',
        name:'system',
        meta:{title:'system management'},
        redirect:'/system/user',
        component:()=>import('../views/layout/Index.vue'),
        children: [{
            path: 'user',
            name: 'user',
            meta: {title:'user management'},
            component:()=>import('../views/user/UserList.vue')
        },
            {
                path: 'role',
                name: 'Role',
                meta: {
                    title: 'role management',
                    icon: 'Stamp',
                    role: ['ROLE_ADMIN']
                },
                component: ()=> import('../views/role/RoleList.vue')
            }
        ]
    },
    {
        path:'/base',
        name:'base',
        meta:{ title:'Data management'},
        redirect: '/base/gradeclass',
        component:()=> import('../views/layout/Index.vue'),
        children: [
            {
                path:'gradeclass',
                name:'gradeclass',
                meta:{title: 'Class management'},
                component:()=>import('../views/gradeclass/GradeClassList.vue')
            },
            {
                path:'student',
                name:'student',
                meta:{title: 'Student management'},
                component:()=>import('../views/student/StudentList.vue')
            },
            {
                path:'course',
                name:'course',
                meta:{ title: 'Course management'},
                component: ()=> import('../views/course/CourseList.vue')
            },
            {
                path:'teacher',
                name:'teacher',
                meta:{ title: 'teacher management'},
                component: ()=> import('../views/teacher/TeacherList.vue')
            }
        ]
    }
]
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