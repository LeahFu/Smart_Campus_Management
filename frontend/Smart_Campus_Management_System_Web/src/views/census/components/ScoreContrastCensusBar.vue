<script setup lang="ts">
import * as echarts from 'echarts'
import { EChartsType } from 'echarts/core'
import { onMounted,watch } from 'vue'

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
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    toolbox: {
        show: true,
        feature: {
            saveAsImage: {
                show: true
            }
        }
    },
    legend: {
        data: props.legendData
    },
    grid: {
        top:10,
        left: '2%',
        right: '2%',
        bottom: '2%',
        containLabel: true,
    },
    xAxis: {
        type: 'category',
        data: props.categoryData
    },
    yAxis: {
        type: 'value'
    },
    series: props.categoryData
}
let chart: EChartsType
const initChart = () => {
    let chart = echarts.init(document.getElementById(props.id))
    chart.setOption(options)
    return chart
}
watch([()=>props.categoryData, () => props.seriesData], ([newCategoryData, newSeriesData]) => {
  //  console.log(`x is ${newCategoryData} and y is `,newSeriesData)
    options.series = newSeriesData
    options.xAxis.data = newCategoryData
    initChart()
})
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