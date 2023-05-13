import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from "./router";
import pinia from "./store";
import ElementPlus from "element-plus"
import "element-plus/dist/index.css"
//Import all element-plus icon
import * as ElementPlusIconsVue from "@element-plus/icons-vue"

const app = createApp(App)
app.use(router)
app.use(pinia)
//Register all icons globally
for(const [key, component] of Object.entries(ElementPlusIconsVue)){
    app.component(key, component)
}
app.use(ElementPlus)
app.mount('#app')
