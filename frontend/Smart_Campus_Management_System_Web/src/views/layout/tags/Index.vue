<script setup lang="ts">
import {computed, watch, ref, onMounted} from "vue"
import { useRoute, useRouter } from "vue-router";
import { TabsPaneContext } from "element-plus";
import {useTagsViewStore} from "../../../store/modules/tagsView"
import MoreButton from './components/MoreButton.vue'
const TagsViewStore = useTagsViewStore()
const route = useRoute()
const router = useRouter()
const visitedViews = computed(() => TagsViewStore.visitedViews)
const activeTabsValue = computed({
    get: () => {
        return TagsViewStore.activeTabsValue;
    },
    set: val => {
        TagsViewStore.setTabsMenuValue(val);
    }
})
// Show previous or next tab label
function toLastView(activeTabPath) {
    // the index of the current tab
    let index = visitedViews.value.findIndex(item=>item.path===activeTabPath)
    // get the previous or next index
    const nextTab = visitedViews.value[index + 1] || visitedViews.value[index - 1];
    if (!nextTab) return;
    router.push(nextTab.path);
    // add function of tabs label
    TagsViewStore.addVisitedView(nextTab)
}
// Click tabs
const tabClick = (tabItem: TabsPaneContext) => {
    let path = tabItem.props.name as string;
    router.push(path);
}
// Add tabs
const addTabs = ()=> {
    const { name } = route
    if(name==='Login')return
    if(name){
        TagsViewStore.addView(route)
    }
    return false
}

// Determine whether it is activated
const isActive = (path) => {
    return path === route.path
}
// Remove tabs
const removeTab = async (activeTabPath: string)=> {
    if (isActive(activeTabPath)) {
        toLastView(activeTabPath)
    }
    await TagsViewStore.delView(activeTabPath)
}
onMounted(()=>{
    addTabs()
})
watch(route, () => {
    addTabs()
})

</script>

<template>
    <div class="main-tabs-view">
        <div class="tabs-view">
            <el-tabs
                    v-model="activeTabsValue"
                    type="card"
                    @tab-click="tabClick"
                    @tab-remove="removeTab"
            >
                <el-tab-pane
                        v-for="item in visitedViews"
                        :key="item.path"
                        :path="item.path"
                        :label="item.title"
                        :name="item.path"
                        :closable="!(item.meta&&item.meta.affix)">
                >
                    <template #label>
                        <el-icon class="tabs-icon" v-if="item.icon">
                            <component :is="item.icon"></component>
                        </el-icon>
                        {{ item.title }}
                    </template>
                </el-tab-pane>
            </el-tabs>
        </div>
        <div class="right-btn">
            <MoreButton/>
        </div>
    </div>
</template>

<style scoped>
.main-tabs-view {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-left: 10px;
    padding-right: 10px;
    background: white;
}
.tabs-view {
    flex: 1;
    overflow: hidden;
    box-sizing: border-box;
}
:deep(.el-tabs){
    border-top: 1px solid #178557;
}
:deep(.el-tabs .el-tabs__nav){
    border: none;
}
:deep(.el-tabs .el-tabs__header .el-tabs__item ){
    border: none;
    color: #cccccc;
}

:deep(.el-tabs .el-tabs__header .el-tabs__item.is-active ) {
    color: #178557;
    border-bottom:2px solid #178557;
}
</style>
