<script setup lang="ts">
import * as echarts from 'echarts'
import { EChartsType } from 'echarts/core'
import { onMounted } from 'vue'

let props = defineProps({
    className: {
        type:String,
        default: 'chart'
    },
    id: {
        type:String,
        default: 'chart'
    },
    height: {
        type:String,
        default: '200px'
    },
    width: {
        type: String,
        default: '200px',
    }
})
const options = {
    grid: {
        top:10,
        left: '2%',
        right: '2%',
        bottom: '2%',
        containLabel: true,
    },
    xAxis: {
        type: 'category',
        data: []
    },
    yAxis: {
        type: 'value'
    },
    series: []
}
let chart: EChartsType
const initChart = () => {
    let chart = echarts.init(document.getElementById(props.id))
    chart.setOption(options)
    return chart
}
onMounted(() => {
    chart = initChart()
    window.addEventListener('resize', function () {
        chart && chart.resize()
    })
})
</script>

<template>
    <div :id="id" :class="className" :style="{ height: height, width: width }" />
</template>

<style scoped>

</style>