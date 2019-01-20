<template>
  <div class="SelfInformation">
    <el-container>
      <!--Left-->
      <el-aside width="30%" >
        <div style="display: block; position: fixed; left: 0px; width:30%;background-attachment: fixed;background-color: #fffaf0;height: 100%">
          <div style="width: 100%;position: relative">
            <el-menu class="el-menu-demo" mode="horizontal" menu-trigger="click">
              <el-menu-item index="1" style="margin-left: 20px"><router-link to="/"><img src="../assets/logo1.png" width="50px" height="50px" /></router-link></el-menu-item>
              <el-submenu index="2" style="margin-left: 20px">
                <template slot="title" >我的账户</template>
                <router-link to="/SelfInformation" style="text-decoration: none"><el-menu-item index="2-1">个人中心</el-menu-item></router-link>
                <router-link to="/Order" style="text-decoration: none"><el-menu-item index="2-2">我的订单</el-menu-item></router-link>
                <router-link to="/Admin" style="text-decoration: none"><el-menu-item index="2-3">管理员</el-menu-item></router-link>
              </el-submenu>
              <el-menu-item index="3" disabled>消息中心</el-menu-item>
              <el-menu-item index="4"><router-link to="/Recommand" style="text-decoration: none">菜单</router-link></el-menu-item>
            </el-menu>
          </div>
          <div class="line"></div>

          <el-col :span="1" :offset="8">
            <div>
              <router-link to="/SelfInformation"><div style="float: left"><el-button round style="margin-top: 350px;" type="info" plain>个人中心</el-button></div></router-link>
              <div style="float: left"><el-button round style="margin-top: 50px" type="info" >我的订单</el-button></div>
            </div>
          </el-col>

          <!--<div id="Rocket1">-->
          <!--<img src="../assets/Rocket.jpg" width="100px" height="100px"/>-->
          <!--</div>-->
        </div>

      </el-aside>

      <!--Right-->
      <el-main width="70%" style="text-align: center;">
        <div class="animated bounceInRight" style="display:inline-block;width:60%;">
            <el-card style="padding-top:30px;height:900px;width:100%" >
            <div slot="header" class="clearfix" style="font-size:30px">
              用户订单
            </div>
            <div style="text-align:center">
              <img src="../assets/fox.png" width="50px" height="50px" style="padding-right: 0%" />
              <el-tabs v-model="activeName" @tab-click="handleClick" style="width: 100%; height:500px" stretch>
                <el-tab-pane label="全部" name="all">
                  <el-table :data="tableData1" stripe border  style="width: 100% " max-height="700" :default-sort = "{prop: 'paytime', order: 'descending'}">
                    <el-table-column type="expand">
                      <template slot-scope="props" >
                        <el-form  label-position="left" inline class="demo-table-expand" style="background-color: #fffaf0">
                          <el-form-item label="菜品名称">
                            <div>{{ props.row.meal }}</div>
                          </el-form-item>
                          <div>
                            <img height="150px" :src="props.row.imgURL"/>
                          </div>
                          <el-form-item label="菜品单价">
                            <div>{{ props.row.price }}</div>
                          </el-form-item>

                        </el-form>
                      </template>
                    </el-table-column>
                    <el-table-column prop="user" label="用户" align="center"></el-table-column>
                    <el-table-column prop="meal" label="菜品" align="center"></el-table-column>
                    <el-table-column prop="amount" label="数量" align="center " width="60"></el-table-column>
                    <el-table-column prop="paytime" label="支付时间" align="center" sortable width="170">
                      <template slot-scope="scope">
                        <i class="el-icon-time"></i>
                        <span style="margin-left: 10px">{{ scope.row.paytime }}</span>
                      </template>
                    </el-table-column>
                    <el-table-column prop="state" label="状态" align="center" :filters="[{ text: '未上餐', value: '未上餐' }, { text: '已上餐', value: '已上餐' }]" :filter-method="filterTag" filter-placement="bottom-end">
                      <template slot-scope="scope">
                        <el-tag :type="scope.row.state === '未上餐' ? 'danger' : 'success'" disable-transitions>{{scope.row.state}}</el-tag>
                      </template>
                    </el-table-column>
                    <el-table-column prop="user" label="评价" align="center">
                      <template slot-scope="scope">
                        <el-button size="mini" type="warning" :disabled="true">123</el-button>
                      </template>

                    </el-table-column>
                  </el-table>
                </el-tab-pane>

                <el-tab-pane label="待上餐" name="paid">
                  <el-table :data="tableData2" stripe border  style="width: 100%" max-height="700" :default-sort = "{prop: 'paytime', order: 'descending'}">
                    <el-table-column type="expand">
                      <template slot-scope="props" >
                        <el-form  label-position="left" inline class="demo-table-expand" style="background-color: #fffaf0">
                          <el-form-item label="菜品名称">
                            <div>{{ props.row.meal }}</div>
                          </el-form-item>
                          <div>
                            <img height="150px" :src="props.row.imgURL"/>
                          </div>
                          <el-form-item label="菜品单价">
                            <div>{{ props.row.price }}</div>
                          </el-form-item>
                        </el-form>
                      </template>
                    </el-table-column>
                    <el-table-column prop="user" label="用户" align="center"></el-table-column>
                    <el-table-column prop="meal" label="菜品" align="center"></el-table-column>
                    <el-table-column prop="amount" label="数量" align="center" width="100"></el-table-column>
                    <el-table-column prop="paytime" label="支付时间" align="center" sortable width="180">
                      <template slot-scope="scope">
                        <i class="el-icon-time"></i>
                        <span style="margin-left: 10px">{{ scope.row.paytime }}</span>
                      </template>
                    </el-table-column>
                  </el-table>
                </el-tab-pane>

                <el-tab-pane label="已完成" name="done">
                  <el-table :data="tableData3" stripe border style="width: 100%" max-height="700" :default-sort = "{prop: 'paytime', order: 'descending'}">
                    <el-table-column type="expand">
                      <template slot-scope="props" >
                        <el-form  label-position="left" inline class="demo-table-expand" style="background-color: #fffaf0">
                          <el-form-item label="菜品名称">
                            <div>{{ props.row.meal }}</div>
                          </el-form-item>
                          <div>
                            <img height="150px" :src="props.row.imgURL"/>
                          </div>
                          <el-form-item label="菜品单价">
                            <span>{{ props.row.price }}</span>
                          </el-form-item>
                        </el-form>
                      </template>
                    </el-table-column>
                    <el-table-column prop="user" label="用户" align="center"></el-table-column>
                    <el-table-column prop="meal" label="菜品" align="center"></el-table-column>
                    <el-table-column prop="amount" label="数量" align="center" width="100"></el-table-column>
                    <el-table-column prop="paytime" label="支付时间" align="center" sortable width="180">
                      <template slot-scope="scope">
                        <i class="el-icon-time"></i>
                        <span style="margin-left: 10px">{{ scope.row.paytime }}</span>
                      </template>
                    </el-table-column>
                  </el-table>
                </el-tab-pane>

              </el-tabs>
            </div>
          </el-card>
        </div>
        <div class="animated bounceInRight" style="display:inline-block;width:38%">
          <el-card style="height:900px;width:100%;padding-top:30px;">
            <div>
              <div style="font-size: 30px;font-weight:bolder;font-family: '微软雅黑'">
                <span ><img height="35px" width="25px" src="../assets/热销榜.jpg" align="absmiddle" style="vertical-align:middle"/></span>
                <span>热销榜</span>
              </div>
              <div>
                <el-row >
                  <el-col  :span="7" style="margin-top:30px" v-for="(item,index) in hot" :key="index" :offset="1">
                    <div style="height:120px; width:120px">
                      <router-link to="/Menu"><el-card :body-style="{ padding: '0px' }" style=" border-radius: 50%;box-shadow:5px 5px 5px #aaaa;">
                        <img height="120px" width="120px" :src="item.imgURL1" />
                      </el-card></router-link>
                    </div>
                    <div style="margin-top:20px;text-align: center" >
                      <span><img height="30px" width="35px" :src="item.imgURL2" align="absmiddle" ></span>
                      <span style="font-size: 13px;font-weight: normal;font-family: '微软雅黑';">{{item.mealsname}}</span>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </div>
            <div>
              <br/><br/><br/>
              <HR width="60%" color=#909399 SIZE=1 />
              <br/>
              <div style="font-size: 30px;font-weight:bolder;font-family: '微软雅黑'">
                <span ><img height="34px" width="33px" src="../assets/猜你喜欢.jpg" style="vertical-align:middle" align="absmiddle"/></span>
                <span >猜你喜欢</span>
                <div>
                  <el-row >
                    <el-col  :span="7" style="margin-top:30px" v-for="(item,index) in like" :key="index" :offset="1">
                      <div style="height:120px; width:120px">
                        <router-link to="/Menu"><el-card :body-style="{ padding: '0px' }" style="box-shadow:5px 5px 5px #aaaa;">
                          <img height="120px" width="120px" :src="item.imgURL" />
                        </el-card></router-link>
                      </div>
                      <div style="margin-top:0px;text-align: center" >
                        <span style="font-size: 13px;font-weight: normal;font-family: '微软雅黑';">{{item.mealsname}}</span>
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </div>

            </div>
          </el-card>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<style>
  body {
    margin: 0;
  }

  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }


  /*#Rocket{*/
    /*width:100px;*/
    /*height:100px;*/
    /*position: absolute;*/
    /*left:50px;*/
    /*-webkit-animation:RocketAnimation 10s infinite; !* Safari and Chrome *!*/
  /*}*/
  /*@-webkit-keyframes RocketAnimation !* Safari 和 Chrome *!*/
  /*{*/
    /*0%   { left:50px; top:0px; transform: rotate(0deg)}*/
    /*5%   { left:50px; top:0px; transform: rotate(90deg)}*/
    /*20%  { left:1100px; top:0px;transform: rotate(90deg)}*/
    /*25%  { left:1100px; top:0px;transform: rotate(180deg)}*/
    /*45%  { left:1100px; top:700px;transform: rotate(180deg)}*/
    /*50%  { left:1100px; top:700px;transform: rotate(270deg)}*/
    /*70% { left:50px; top:700px;transform: rotate(270deg)}*/
    /*75%  { left:50px; top:700px;transform: rotate(360deg)}*/
    /*100% { left:50px; top:0px;transform: rotate(360deg)}*/
  /*}*/


</style>

<script>
  import app from "../App"
  export default {
    data() {
      return {
        Usersaccountnumber:'',
        activeName:"all",

        tableData1: [{
          paytime: '',
          user: '',
          meal: '',
          state: '',
          amount:'',
          imgURL:'',
          price:''
        }],
        tableData2:[{
          paytime: '',
          user: '',
          meal: '',
          state: '',
          amount:'',
          imgURL:'',
        }],
        tableData3:[{
         paytime: '',
         user: '',
         meal: '',
         amount:'',
         imgURL:'',
        }],
          hot:[{
          mealsname:'',
          imgURL1:'',
          imgURL2:''
        }],
          like:[{
          mealsname:'',
          imgURL:''
        }]
      }
    },

    created(){
      //处理cookie
      app.methods.delCookie("IsAdminister");
      if(app.methods.getCookie("Customer")==="")
      {
        this.$message({
          message: '当前尚未登录，请登陆后查看',
          type: 'warning'
        });
        window.location.href="http://localhost:8081/#/Login";
      }
      else
      {
        this.Usersaccountnumber=app.methods.getCookie("Customer");
      }

      this.loadAllOrder()
      this.loadHot()
    },

    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      },

      //加载用户所有历史订单
      loadAllOrder:function() {
        let _this=this;

        this.$axios({
          method:'get',
          url:'/userOrderHistory2?usersaccountnumber='+_this.Usersaccountnumber
        }).then(function (response) {
           _this.$data.tableData1=response.data;
          for(var i=0;i<response.data.length;i++){
            _this.$data.tableData1[i].user=response.data[i].usersaccountnumber;
            _this.$data.tableData1[i].meal=response.data[i].mealsname;
            _this.$data.tableData1[i].paytime=response.data[i].paytime;
            _this.$data.tableData1[i].amount=response.data[i].amount;
            _this.$data.tableData1[i].imgURL=require('../assets/Menu/'+_this.$data.tableData1[i].meal+'.jpg');
            _this.$data.tableData1[i].price=response.data[i].mealsprice;
            if(response.data[i].state=='N') _this.$data.tableData1[i].state='未上餐';
            else _this.$data.tableData1[i].state='已上餐';
          }
          _this.loadNOrder();
        }).catch(function (error) {
          console.log(error.response.data)
        },)
      },

      //加载用户未上餐的历史订单
      loadNOrder:function() {
        let _this = this;

        this.$axios({
          method: 'get',
          url: '/userOrderHistoryN?usersaccountnumber=' + _this.Usersaccountnumber
        }).then(function (response) {
          _this.$data.tableData2 = response.data;
          for (var i = 0; i < response.data.length; i++) {
            _this.$data.tableData2[i].user = response.data[i].usersaccountnumber;
            _this.$data.tableData2[i].meal = response.data[i].mealsname;
            _this.$data.tableData2[i].paytime = response.data[i].paytime;
            _this.$data.tableData2[i].amount=response.data[i].amount;
            _this.$data.tableData2[i].imgURL=require('../assets/Menu/'+_this.$data.tableData2[i].meal+'.jpg');
          }
          _this.loadYOrder();
        }).catch(function (error) {
          console.log(error.response.data)
        })
      },

      //加载用户已上餐的历史订单
      loadYOrder:function() {
        let _this = this;

        this.$axios({
          method: 'get',
          url: '/userOrderHistoryY?usersaccountnumber=' + _this.Usersaccountnumber
        }).then(function (response) {
          _this.$data.tableData3 = response.data;
          for (var i = 0; i < response.data.length; i++) {
            _this.$data.tableData3[i].user = response.data[i].usersaccountnumber;
            _this.$data.tableData3[i].meal = response.data[i].mealsname;
            _this.$data.tableData3[i].paytime = response.data[i].paytime;
            _this.$data.tableData3[i].amount=response.data[i].amount;
            _this.$data.tableData3[i].imgURL=require('../assets/Menu/'+_this.$data.tableData3[i].meal+'.jpg');
          }

        }).catch(function (error) {
          console.log(error.response.data)
        })
        return this;
      },

      //加载热销榜
      loadHot:function() {
        let _this=this;
        this.$axios({
          method:'get',
          url:'/hot',
        }).then(function (response) {
          _this.$data.hot=response.data;
          for(var i = 0; i < response.data.length; i++)
          {
            _this.$data.hot[i].mealsname=response.data[i].mealsname;
            _this.$data.hot[i].imgURL1=require('../assets/Menu/'+_this.$data.hot[i].mealsname+'.jpg');
            _this.$data.hot[i].imgURL2=require('../assets/'+(i+1)+'.jpg');
          }
          _this.loadLike()
        }).catch(function (error) {
          console.log(error)
        })
      },

      //加载猜你喜欢
      loadLike:function(){
        let _this=this;
        this.$axios({
          method:'get',
          url:'/like',
        }).then(function (response) {
          //_this.number=;
          _this.$data.like=response.data;
          for(var i = 0; i < response.data.length; i++)
          {
            _this.$data.like[i].mealsname=response.data[i].mealsname;
            _this.$data.like[i].imgURL=require('../assets/Menu/'+_this.$data.like[i].mealsname+'.jpg');
          }
        }).catch(function (error) {
          console.log(error)
        })
      },

      filterTag(value, row) {
       return row.tag === value;
      },


    }
  };
</script>

<style scoped>

</style>
