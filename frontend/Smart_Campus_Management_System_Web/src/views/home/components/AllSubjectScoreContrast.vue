<script setup lang="ts">
import * as echarts from 'echarts'
import { EChartsType } from 'echarts/core'
import { ref, onMounted} from 'vue'

let props = defineProps({
    legendData: {
        type:Array,
        default: []
    },
    categoryData: {
        type:Array,
        default: []
    },
    seriesData: {
        type:Array,
        default: []
    },
    className: {
        type: String,
        default: 'chart',
    },
    id: {
        type: String,
        default: 'chart',
    },
    width: {
        type: String,
        default: '200px',
    },
    height: {
        type: String,
        default: '200px',
    },
})
</script>

<template>
    <div :id="id" :class="className" :style="{ height: height, width: width }" />
</template>

<style scoped>

</style>