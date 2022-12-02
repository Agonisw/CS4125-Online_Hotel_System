<template>
    <div class="container">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> statistical management
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div style="display: inline-flex">
            <div class="echart" id="echart-line" :style="{float:'left',width: '1300px', height: '530px'}"></div>
        <!-- 添加弹出框 -->
        </div>
    </div>
</template>

<script>

    import echarts from  "echarts";
    import { getEchartData } from '../../api/cake';
    export default {
        name: 'user',

        data() {
            return {
                tableData:[],
                addVisible: false,
                data1:[],
                data2:[],
                data3:[],
                data4:[],
                data5:[]
            };
        },
        mounted() {
            this.getEchartData();
            setTimeout(() =>{
                this.getData();
                // this.getData2();
                this.getData3()
            },200)

        },
        methods: {

            getEchartData(){
                getEchartData().then(res =>{
                    this.data1 = res.data.s1;
                    this.data2 = res.data.s2;
                    // this.data3 = res.data.s3;
                    // this.data4 = res.data.s4;
                    this.data5 = res.data.s5;
                })
            },

            // 获取所有数组(支持分页和关键字查询)
            getData() {
                let getchart = echarts.init(document.getElementById('echart-line'));
                const option = {
                    backgroundColor: '#031245',
                    color: ["#63caff", "#3608FE"],
                    grid: {
                        containLabel: true,
                        left: 20,
                        right: 20,
                        bottom: 10,
                        top: 40
                    },
                    xAxis: {
                        axisLabel: {
                            color: "#c0c3cd",
                            fontSize: 10,
                            interval: 0
                        },
                        splitLine: {
                            show: false
                        },
                        axisLine: {
                            lineStyle: {
                                color: "#384267",
                                width: 1,
                                type: "dashed"
                            },
                            show: true
                        },
                        data:this.data1,
                        type: "category"
                    },
                    yAxis: {
                        axisLabel: {
                            color: "#c0c3cd",
                            fontSize: 14
                        },

                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: "#384267",
                                type: "dashed"
                            }
                        },
                        axisLine: {
                            lineStyle: {
                                color: "#384267",
                                width: 1,
                                type: "dashed"
                            },
                            show: true
                        },
                        name: ""
                    },
                    series: [{
                        data: this.data2,
                        type: "bar",
                        barWidth: 30,
                        itemStyle: {
                            color: {
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                type: "linear",
                                global: false,
                                colorStops: [{
                                    offset: 0,
                                    color: "#3A1BFD"
                                }, {
                                    offset: 1,
                                    color: "#3BEFFD"
                                }]
                            }
                        },
                        label: {
                            show: true,
                            position: "top",
                            distance: 10,
                            color: "#fff"
                        }
                    }, {
                        data: [1, 1, 1, 1, 1, 1, 1, 1],
                        type: "pictorialBar",
                        barMaxWidth: "20",
                        symbol: "diamond",
                        symbolOffset: [0, "50%"],
                        symbolSize: [30, 15]
                    }, {
                        type: "pictorialBar",
                        barMaxWidth: "20",
                        symbolPosition: "end",
                        symbol: "diamond",
                        symbolOffset: [0, "-50%"],
                        symbolSize: [30, 12],
                        zlevel: 2
                    }],
                    tooltip: {
                        trigger: "item",
                        show: true
                    }
                }

                getchart.setOption(option);
                //随着屏幕大小调节图表
                window.addEventListener("resize", () => {
                    getchart.resize();
                });
            },
            getData2() {
                let getchart = echarts.init(document.getElementById('echart-line2'));
                const option = {
                    backgroundColor: '#031245',
                    color: ["#63caff", "#3608FE"],
                    title:{
                        text:'ddd'
                    },
                    grid: {
                        containLabel: true,
                        left: 20,
                        right: 20,
                        bottom: 10,
                        top: 40
                    },
                    xAxis: {
                        axisLabel: {
                            color: "#c0c3cd",
                            fontSize: 10,
                            interval: 0
                        },
                        splitLine: {
                            show: false
                        },
                        axisLine: {
                            lineStyle: {
                                color: "#384267",
                                width: 1,
                                type: "dashed"
                            },
                            show: true
                        },
                        data:this.data3,
                        type: "category"
                    },
                    yAxis: {
                        axisLabel: {
                            color: "#c0c3cd",
                            fontSize: 14
                        },

                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: "#384267",
                                type: "dashed"
                            }
                        },
                        axisLine: {
                            lineStyle: {
                                color: "#384267",
                                width: 1,
                                type: "dashed"
                            },
                            show: true
                        },
                        name: ""
                    },
                    series: [{
                        data: this.data4,
                        type: "bar",
                        barWidth: 30,
                        itemStyle: {
                            color: {
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                type: "linear",
                                global: false,
                                colorStops: [{
                                    offset: 0,
                                    color: "#3A1BFD"
                                }, {
                                    offset: 1,
                                    color: "#3BEFFD"
                                }]
                            }
                        },
                        label: {
                            show: true,
                            position: "top",
                            distance: 10,
                            color: "#fff"
                        }
                    }, {
                        data: [1, 1, 1, 1, 1, 1, 1, 1],
                        type: "pictorialBar",
                        barMaxWidth: "20",
                        symbol: "diamond",
                        symbolOffset: [0, "50%"],
                        symbolSize: [30, 15]
                    }, {
                        type: "pictorialBar",
                        barMaxWidth: "20",
                        symbolPosition: "end",
                        symbol: "diamond",
                        symbolOffset: [0, "-50%"],
                        symbolSize: [30, 12],
                        zlevel: 2
                    }],
                    tooltip: {
                        trigger: "item",
                        show: true
                    }
                }

                getchart.setOption(option);
                //随着屏幕大小调节图表
                window.addEventListener("resize", () => {
                    getchart.resize();
                });
            },

            getData3() {
                let getchart = echarts.init(document.getElementById('echart-line3'));
                const option = {
                    title: {
                        text: '性别情况',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                    },
                    series: [
                        {
                            name: '患者性别',
                            type: 'pie',
                            radius: '70%',
                            data:this.data5,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };

                getchart.setOption(option);
                //随着屏幕大小调节图表
                window.addEventListener("resize", () => {
                    getchart.resize();
                });
            },

        }
    };
</script>

<style scoped>
    .handle-box {
        margin-top: 20px;
        margin-bottom: 10px;
    }
    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .table {
        width: 100%;
        font-size: 14px;
    }
    .crop-demo-btn{
        position: relative;
        width: 98px;
        height: 32px;
        line-height: 32px;
        padding: 0 15px;
        background-color: #409eff;
        color: #fff;
        font-size: 12px;
        border-radius: 4px;
        box-sizing: border-box;
    }
    .crop-input{
        position: absolute;
        width: 98px;
        height: 32px;
        left: 0;
        top: 0;
        opacity: 0;
        cursor: pointer;
    }
</style>
