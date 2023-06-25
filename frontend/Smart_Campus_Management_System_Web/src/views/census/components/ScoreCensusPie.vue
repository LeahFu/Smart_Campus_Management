<script setup lang="ts">
import * as echarts from 'echarts'
import { EChartsType } from 'echarts/core'
import {onMounted} from 'vue'

let props = defineProps({
    legendData: {
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
    config: {
        type: Object,
        default: () => {},
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
const options = {
    title: {
        text: 'Class course grades analysis pie chart',
        subtext: '0~59:F；60~69:D；70~79:C；80~89:B；90~100：A',
        x: 'center'
    },
    grid: {
        top: 10,
        left: '2%',
        right: '2%',
        bottom: '2%',
        containLabel: true,
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: props.legendData
    },
    series: [
        {
            name: 'quantity',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            label: {
                show: false,
                position: 'center',
            },
            emphasis: {
                label: {
                    show: true,
                    fontSize: '40',
                    fontWeight: 'bold',
                },
            },
            labelLine: {
                show: false,
            },
            data: props.seriesData,
        },
    ],
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel'],
                option: {
                    funnel: {
                        x: '25%',
                        width: '50%',
                        funnelAlign: 'left',
                        max: 1548
                    }
                }
            },
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
}
let chart: EChartsType

const initChart = () => {
    let chart = echarts.init(document.getElementById(props.id))
    chart.setOption(options,true)
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
    <div :id="id"  :class="className" :style="{ height: height, width: width }" />
</template>

<style scoped>

</style>