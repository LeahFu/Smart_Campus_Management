<script setup lang="ts">
import {computed} from "vue";
import {useSettingStore} from "../../../../store/modules/setting"
import {useTagsViewStore} from "../../../../store/modules/tagsView"
import { useRouter,useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()
const SettingStore = useSettingStore()
const TagsViewStore = useTagsViewStore()
const visitedViews = computed(() => TagsViewStore.visitedViews)

const closeCurrentTab = (event)=> {
    TagsViewStore.toLastView(route.path)
    TagsViewStore.delView(route.path)
}
const closeOtherTab= async ()=>{
    TagsViewStore.delOtherViews(route.path)
}

const closeAllTab = async ()=>{
    await TagsViewStore.delAllViews()
    TagsViewStore.goHome()
}


</script>

<template>
    <el-dropdown trigger="hover">
        <el-button size="small" color="#178557">
            <span>more</span>
            <el-icon class="el-icon--right"><arrow-down /></el-icon>
        </el-button>
        <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item @click="closeCurrentTab"><el-icon :size="14"><FolderRemove/></el-icon> Close Current Tab</el-dropdown-item>
                <el-dropdown-item @click="closeOtherTab"><el-icon :size="14"><Close /></el-icon>Close Other Tab</el-dropdown-item>
                <el-dropdown-item @click="closeAllTab"><el-icon :size="14"><FolderDelete /></el-icon>Close All Tab</el-dropdown-item>
            </el-dropdown-menu>
        </template>
    </el-dropdown>
</template>

<style scoped>

</style>