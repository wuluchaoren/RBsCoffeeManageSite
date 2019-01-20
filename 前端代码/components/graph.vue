<template>
  <div class="graph">
    <el-container>
      <el-aside style="width: 20%;  text-align:left;">
        <div style="display: block; position: fixed; left: 0px;width: 20%; height:100%">
          <div style="">
            <el-menu
              class="el-menu-demo"
              mode="horizontal"
              text-color="#FFFFFF"
              background-color="#409EFF"
              collapse-transition
              active-text-color="#ffd04b">
              <el-menu-item index="1">Logo</el-menu-item>
              <el-menu-item index="2">管理界面</el-menu-item>
              <el-menu-item index="3"><router-link to="/" style="text-decoration: none">返回</router-link></el-menu-item>
            </el-menu>
          </div>

          <div style="height:100%">
            <el-menu default-active="4" class="el-menu-vertical-demo" >
              <router-link to="/Admin" style="text-decoration: none">
                <el-menu-item index="1">
                  <i class="el-icon-menu"></i>
                  <span slot="title" style="align:left">用户</span>
                </el-menu-item>
              </router-link>
              <router-link to="/coffeeMenu" style="text-decoration: none">
                <el-menu-item index="2">
                  <i class="el-icon-setting"></i>
                  <span slot="title">菜单</span>
                </el-menu-item>
              </router-link>
              <router-link to="/AdminOrder" style="text-decoration: none">
                <el-menu-item index="3">
                  <i class="el-icon-document"></i>
                  <span slot="title">订单</span>
                </el-menu-item>
              </router-link>
              <el-menu-item index="4">
                <i class="el-icon-star-on"></i>
                <span slot="title">店铺资讯</span>
              </el-menu-item>

            </el-menu>

          </div>
        </div>
      </el-aside>

      <el-main>
        <div>
          <div style="margin-top:60px;">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">管理员</el-breadcrumb-item>
              <el-breadcrumb-item>店铺咨询</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
        </div>
        <div style="margin-top: 60px"><p>本店热销 - Top 8</p></div>
        <div id="graph2" style="width: 80%;height:550px;margin: 50px"></div>
        <div id="graph3" style="width: 80%;height:550px;margin:20px auto"></div>
      </el-main>
    </el-container>
  </div>

</template>

<script>
  import app from "../App"
    export default {
        name: "graph",

      created(){
        //cookie
        if(app.methods.getCookie("IsAdminister")==="")
          window.location.assign('http://localhost:8081/#/Admin');
      },
      data(){
        return {
          //表格数据
          topData:[{
            itemName:'',
            sellNum:0,
          }],
          myData:[{
            itemName:'',
            sellNum:0,
          }],
          xData:[],
          yData:[],
        }
      },

      mounted(){
        this.loadData();
        this.loadData2();
        //this.drawLineGraph();
        //this.drawGraph();
      },
      methods: {
        // drawLine(){
        //   // 基于准备好的dom，初始化echarts实例
        //   let myChart = this.$echarts.init(document.getElementById('main'))
        //   // 绘制图表
        //
        //   myChart.setOption({
        //     backgroundColor: '#fff',
        //
        //     title: {
        //       text: '最受欢迎 - Top 6 ',
        //       left: 'center',
        //       top: 20,
        //       textStyle: {
        //         color: 'black'
        //       }
        //     },
        //
        //     tooltip : {
        //       trigger: 'item',
        //       formatter: "{a} <br/>{b} : {c} ({d}%)"
        //     },
        //
        //     visualMap: {
        //       show: false,
        //       min: 80,
        //       max: 600,
        //       inRange: {
        //         colorLightness: [0, 1]
        //       }
        //     },
        //     series : [
        //       {
        //         name:'访问来源',
        //         type:'pie',
        //         radius : '55%',
        //         center: ['50%', '50%'],
        //         data:[
        //           {value:335, name:'直接访问'},
        //           {value:310, name:'邮件营销'},
        //           {value:274, name:'联盟广告'},
        //           {value:235, name:'视频广告'},
        //           {value:400, name:'搜索引擎'}
        //         ].sort(function (a, b) { return a.value - b.value; }),
        //         roseType: 'radius',
        //         label: {
        //           normal: {
        //             textStyle: {
        //               color: 'rgba(0, 0, 0, 1)',
        //             }
        //           }
        //         },
        //         labelLine: {
        //           normal: {
        //             lineStyle: {
        //               color: 'rgba(0, 0, 0, 1)'
        //             },
        //             smooth: 0.2,
        //             length: 10,
        //             length2: 20
        //           }
        //         },
        //         itemStyle: {
        //           normal: {
        //             color: '#409EFF',
        //             shadowBlur: 100,
        //             shadowColor: 'rgba(0, 0, 0, 0.2)'
        //           }
        //         },
        //
        //         animationType: 'scale',
        //         animationEasing: 'elasticOut',
        //         animationDelay: function (idx) {
        //           return Math.random() * 200;
        //         }
        //       }
        //     ]
        //   });
        //
        // },

        drawGraph(){
          // 基于准备好的dom，初始化echarts实例
          let myChart = this.$echarts.init(document.getElementById('graph2'))
          // 绘制图表
          let _this=this;

          myChart.setOption({
            title : {
              text: '',
              subtext: '',
              x:'center'
            },
            tooltip : {
              trigger: 'item',
              formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
              x : 'center',
              y : 'bottom',
              data:['rose1','rose2','rose3','rose4','rose5','rose6','rose7','rose8']
            },
            toolbox: {
              show : true,
              feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                  show: true,
                  type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
              }
            },
            calculable : true,
            series : [
              {
                name:'销售情况',
                type:'pie',
                radius : [20, 180],
                center : ['50%', 200],
                roseType : 'radius',
                width: '40%',       // for funnel
                itemStyle : {
                  emphasis : {
                    label : {
                      show : true
                    },
                    labelLine : {
                      show : true
                    }
                  }
                },
                data:[
                  {value:_this.$data.topData[1].sellNum, name:_this.$data.topData[1].itemName},
                  {value:_this.$data.topData[2].sellNum, name:_this.$data.topData[2].itemName},
                  {value:_this.$data.topData[3].sellNum, name:_this.$data.topData[3].itemName},
                  {value:_this.$data.topData[4].sellNum, name:_this.$data.topData[4].itemName},
                  {value:_this.$data.topData[5].sellNum, name:_this.$data.topData[5].itemName},
                  {value:_this.$data.topData[6].sellNum, name:_this.$data.topData[6].itemName},
                  {value:_this.$data.topData[7].sellNum, name:_this.$data.topData[7].itemName},
                  {value:_this.$data.topData[8].sellNum, name:_this.$data.topData[8].itemName}
                ]
              },
            ]
          });
        },

        drawLineGraph(){
          // 基于准备好的dom，初始化echarts实例
          let myChart = this.$echarts.init(document.getElementById('graph3'))
          // 绘制图表
          let _this=this;
          var x=_this.$data.xData;
          var y=_this.$data.yData;

          myChart.setOption({
            title : {
              text: '最近七天营业额',
              subtext: '',
              x: 'center',
              align: 'right'
            },
            xAxis: {
              type: 'category',
              data: [x[6], x[5],x[4], x[3],x[2],x[1],x[0]]
            },
            yAxis: {
              type: 'value'
            },
            series: [{
              data: [y[6], y[5],y[4], y[3],y[2],y[1],y[0]],
              type: 'line'
            }]
            });

        },

        loadData(){
          let _this=this;

          this.$axios({
            method:'get',
            url:'/allMenu',

          }).then(function (response) {
            for(var i=0;i<response.data.length;i++){
              var name=response.data[i].mealsname;
              var num=parseInt(response.data[i].purchasefrequency);
              var newItem={itemName:name,sellNum:num};
              _this.$data.myData.push(newItem);
            };

            var compare = function (prop) {
              return function (obj1, obj2) {
                var val1 = obj1[prop];
                var val2 = obj2[prop];if (val1 < val2) {
                  return 1;
                } else if (val1 > val2) {
                  return -1;
                } else {
                  return 0;
                }
              }
            };

            _this.$data.myData.sort(compare("sellNum"));

            for(var i=0;i<8;i++){
              var sortName=_this.$data.myData[i].itemName;
              var sortNum=parseInt(_this.$data.myData[i].sellNum);
              var sortItem={itemName:sortName,sellNum:sortNum};
              _this.$data.topData.push(sortItem);
            };
            _this.drawGraph();

          }).catch(function (error) {
            console.log(error.response.data)
          })
        },

        loadData2(){
          let _this=this;

          this.$axios({
            method:'get',
            url:'/Turnover',

          }).then(function (response) {
            console.log(response.data);
            for(var i=0;i<response.data.length;i++){

              var sum=response.data[i].sum;
              _this.$data.yData.push(sum);

              var date=response.data[i].day;
              _this.$data.xData.push(date);

            };

            console.log(_this.$data.xData);
            console.log(_this.$data.yData);
            _this.drawLineGraph();
          }).catch(function (error) {
            console.log(error.response.data)
          })
        }
      }
    }

</script>

<style scoped>

</style>
