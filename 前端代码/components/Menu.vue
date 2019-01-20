<template>
  <div class="menu">
    <el-container>
      <!--Left-->
      <el-aside width="30%" >

        <div style="display: block; position: fixed; left: 0px; width:30%;background-attachment: fixed;background-color: #fffaf0;height: 100%" >
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
          <div class="line"></div>
          <el-col :span="1" :offset="4">
            <div>
              <router-link to="/Recommand"><div style="float: left"><el-button round style="margin-top: 250px" type="info" plain>季度精选</el-button></div></router-link>
              <div style="float: left"><el-button round style="margin-top: 50px" type="info" >菜单</el-button></div>
            </div>
          </el-col>
        </div>


      </el-aside>

      <!--Right-->
      <el-main width="70%">
        <div :class="myAnimation">
          <el-collapse v-model="activeNames">

            <el-collapse-item name="1">
              <template slot="title">
                <div style="text-align: left;font-size: large;font-weight: bold;padding-left: 0%;color: #603811">莱茵河畔</div>
              </template>
              <div>
                <el-row >
                  <el-col  :span="3" style="margin-top:50px" v-for="(item,index) in cardCoffee" :key="index" :offset="(index%4)>0?3:1">
                    <div style="width: 150px;height: 150px;">
                      <el-card :body-style="{ padding: '0px' }" style=" border-radius: 50%;box-shadow:5px 5px 5px #aaaa;">
                        <img height="150px" :src="item.imgURL" />
                      </el-card>
                    </div>
                    <div style="margin-top: 20px;" >
                      <span style="font-size: 15px;font-weight: normal;font-family: '微软雅黑'">{{item.mealsname}}</span>
                      <br>
                      <el-button circle class="button" icon="el-icon-plus" style="width: 25px;height: 25px;box-shadow:5px 5px 5px #aaaa" @click="ClickToBuyCoffee(index)"></el-button>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </el-collapse-item>

            <el-collapse-item name="2">
              <template slot="title">
                <div style="text-align: left;font-size: large;font-weight: bold;padding-left: 3%;color: #603811">栈语茗茶</div>
              </template>
              <div>
                <el-row >
                  <el-col  :span="3" style="margin-top:50px" v-for="(item,index) in cardTea" :key="index" :offset="(index%4)>0?3:1">
                    <div style="width: 150px;height: 150px;">
                      <el-card :body-style="{ padding: '0px' }" style=" border-radius: 50%;box-shadow:5px 5px 5px #aaaa;">
                        <img height="150px" :src="item.imgURL" />
                      </el-card>
                    </div>
                    <div style="margin-top: 20px;" >
                      <span style="font-size: 15px;font-weight: normal;font-family: '微软雅黑'">{{item.mealsname}}</span>
                      <br>
                      <el-button circle class="button" icon="el-icon-plus" style="width: 25px;height: 25px;box-shadow:5px 5px 5px #aaaa" @click="ClickToBuyTea(index)"></el-button>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </el-collapse-item>

            <el-collapse-item name="3">
              <template slot="title">
                <div style="text-align: left;font-size: large;font-weight: bold;padding-left: 6%;color: #603811">浓情蜜意</div>
              </template>
              <div>
                <el-row >
                  <el-col  :span="3" style="margin-top:50px" v-for="(item,index) in cardSoup" :key="index" :offset="(index%4)>0?3:1">
                    <div style="width: 150px;height: 150px;">
                      <el-card :body-style="{ padding: '0px' }" style=" border-radius: 50%;box-shadow:5px 5px 5px #aaaa;">
                        <img height="150px" :src="item.imgURL" />
                      </el-card>
                    </div>
                    <div style="margin-top: 20px;" >
                      <span style="font-size: 15px;font-weight: normal;font-family: '微软雅黑'">{{item.mealsname}}</span>
                      <br>
                      <el-button circle class="button" icon="el-icon-plus" style="width: 25px;height: 25px;box-shadow:5px 5px 5px #aaaa" @click="ClickToBuySoup(index)"></el-button>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </el-collapse-item>

            <el-collapse-item  name="4">
              <template slot="title">
                <div style="text-align: left;font-size: large;font-weight: bold;padding-left: 9%;color: #603811">甜在心扉</div>
              </template>
              <div>
                <el-row >
                  <el-col  :span="3" style="margin-top:50px" v-for="(item,index) in cardIce" :key="index" :offset="(index%4)>0?3:1">
                    <div style="width: 150px;height: 150px;">
                      <el-card :body-style="{ padding: '0px' }" style=" border-radius: 50%;box-shadow:5px 5px 5px #aaaa;">
                        <img height="150px" :src="item.imgURL" />
                      </el-card>
                    </div>
                    <div style="margin-top: 20px;" >
                      <span style="font-size: 15px;font-weight: normal;font-family: '微软雅黑'">{{item.mealsname}}</span>
                      <br>
                      <el-button circle class="button" icon="el-icon-plus" style="width: 25px;height: 25px;box-shadow:5px 5px 5px #aaaa" @click="ClickToBuyIce(index)"></el-button>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </el-collapse-item>
          </el-collapse>
        </div>
      </el-main>

    </el-container>

    <!--对话框-->
    <el-dialog  title="购买" :visible.sync="dialogFormVisible" width="30%" >
      <div style="background: url(../assets/backflower.jpg)">
        <el-form :model="currentBuy">
          <el-form-item>
            <img width="400px" :src="currentBuy.imgInBuy">
          </el-form-item>
          <el-form-item label="餐品名称：" :label-width="formLabelWidth">
            <span style="float: left;font-family: '微软雅黑 Light';font-size: 20px">{{currentBuy.name}}</span>
          </el-form-item>
          <el-form-item label="餐品介绍：" :label-width="formLabelWidth">
            <span style="float:left;font-family: '微软雅黑 Light';font-size: 20px">{{currentBuy.intro}}</span>
          </el-form-item>
          <el-form-item label="餐品单价：" :label-width="formLabelWidth">
            <span style="float:left;font-family: '微软雅黑 Light';font-size: 20px">{{currentBuy.price}}元</span>
          </el-form-item>
          <el-form-item label="餐品库存：" :label-width="formLabelWidth">
            <span style="float:left;font-family: '微软雅黑 Light';font-size: 20px">{{currentBuy.Stock}}个</span>
          </el-form-item>
          <el-form-item label="购买数量：" :label-width="formLabelWidth">
            <el-input-number v-model="currentBuy.BuyCount" :min="1" :max="10" label="购买的数量" style="float:left"></el-input-number>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addInBuyDialog">加入购物车</el-button>
          <el-button @click="cancelBuyDialog" >取 消</el-button>

        </div>
      </div>
    </el-dialog>

    <!--悬浮-->
    <div class="key">
      <el-card :body-style="{ padding: '0px' }" style="background-color: rgba(255,255,255,0)">
        <el-container>
          <!--左边-->
          <el-aside width="15%">
            <div style="display: block; position: center;font-family: '微软雅黑 Light'">
              <p>购</p>
              <p>物</p>
              <p>车</p>
            </div>
          </el-aside>

          <!--右边-->
          <el-container width="85%" style="background-color: rgba(248,243,209,0.68)">
            <el-main style="height: 500px">
              <div>

                <div v-for="(item,index) in Commodity" :key="index">
                  <el-card :body-style="{ padding: '10px' }" style="margin-bottom: 10px;font-family: Microsoft YaHei">
                    <div style="float: left;margin-left: 10px">
                      <el-checkbox v-model="item.check" style="float: left" @change="test123" ></el-checkbox>
                      <span style="margin-left: 5px">{{item.mealsname}}</span>
                    </div>
                    <div style="float: right;margin-top: 20px;">
                      <el-input-number v-model="item.amount" :min="1" :max="100" label="购物车数量" size="small" @change="handleChange"></el-input-number>
                      <el-button type="danger" icon="el-icon-delete" size="small" circle @click="clickToDelete(index)"></el-button><br/>
                      <div style="float:right">单品总价:{{item.price}}元</div>
                    </div>
                  </el-card>
                </div>

              </div>
            </el-main>

            <el-footer>
              <div style="float:left;margin-right: 20px; margin-top: 10px">
                <span style="margin-left: 10px">
                  <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
                  <el-button type="danger" icon="el-icon-delete" round size="small" @click="clickToClear" style="margin-left: 10px">清空</el-button>
                </span>

              </div>
              <div style="float: right;margin-top: 20px">
                <span>总计:{{BuyTotalPrice}}/{{totalPrice}}</span>
                <el-button type="success" icon="el-icon-check" round size="small" style="margin-left: 5px" @click="clickToGiveMoney">购买</el-button>
              </div>
            </el-footer>
          </el-container>

        </el-container>
      </el-card>
    </div>

    <!--付款界面-->
    <el-dialog title="付款" :visible.sync="dialogBuyVisible" width="30%">
      <el-tabs v-model="activePay" style="width: 500px;ipadding-left: 50px" tab-position="left">
        <el-tab-pane label="餐点列表" name="first" >
          <el-main style="height:293px;">
            <div v-for="(item,index) in BuyCommodity" :key="index" >
              <el-card :body-style="{ padding: '10px' }" style="margin-top: 10px;font-family: Microsoft YaHei">
                <div style="float: left;margin-left: 10px">{{item.mealsname}}</div>
                <div style="float: right;margin-top: 20px;">
                  <el-input-number v-model="item.amount" :min="1" :max="100" label="购物车数量" size="small" @change="handleChange"></el-input-number>
                  <el-button type="danger" icon="el-icon-delete" size="small" circle @click="clickToDelete(index)"></el-button><br/>
                  <div style="float:right">单品总价:{{item.price}}元</div>
                </div>
              </el-card>
            </div>
          </el-main>
        </el-tab-pane>
        <el-tab-pane label="微信支付" name="second" style="float: left;">
          <img src="../assets/Pay/Wechat.png" width="300" height="300"/>
        </el-tab-pane>
        <el-tab-pane label="支付宝" name="third" style="float: left;">
          <img src="../assets/Pay/Ali.jpg" width="300" height="300"/>
        </el-tab-pane>
      </el-tabs>
      <span style="color: #df0a1e;">ps:请选择使用微信或者支付宝付款</span><br/>
      <span>总计:{{BuyTotalPrice}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogBuyVisible = false">取 消</el-button>
        <el-button type="primary" @click="hasPayed" >已付款</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import app from "../App"
  export default {
    inject:['reload'],
    data() {
      return {
        myAnimation:"animated bounceInRight",
        shoppingCart:1,

        cardCoffee:[{
          mealsname:'',
          mealsprice:-1.0,
          imgURL:'',
          stock:0,
        }],
        cardTea:[{
          mealsname:'',
          mealsprice:-1.0,
          imgURL:'',
          stock:0,
        }],
        cardSoup:[{
          mealsname:'',
          mealsprice:-1.0,
          imgURL:'',
          stock:0,
        }],
        cardIce:[{
          mealsname:'',
          mealsprice:-1.0,
          imgURL:'',
          stock:0,
        }],
        number:8,
        activeNames: ['1'],

        //对话框的Data
        dialogFormVisible: false,
        currentBuy: {
          name: '',
          intro:'',
          BuyCount: 1,
          imgInBuy:'',
          price:0,
          Number:'',//用户账号,暂时使用
          Stock:0,
        },
        formLabelWidth: '200px',

        //购物车内容
        Commodity:[{
          mealsname:'',
          amount:0,
          price:0,
          check:true,
        }],
        checkAll:false,
        isIndeterminate:true,
        totalPrice:0,

        //购买的购物车内容
        BuyCommodity:[{
          mealsname:'',
          amount:0,
          price:0,
          check:true,
        }],
        BuyTotalPrice:0,
        BuyIndex:[0],

        //付款界面
        dialogBuyVisible:false,
        activePay:'first',
        loading:true,
      }
    },
    created(){
      //处理cookie
      app.methods.delCookie("IsAdminister");
      this.currentBuy.Number=app.methods.getCookie("Customer");

      this.loadAllCoffees();
    },
    methods: {
      loadAllCoffees:function() {
        let _this=this;

        this.$axios({
          method:'get',
          url:'http://localhost:8080/allCoffee',
        }).then(function (response) {
          _this.$data.cardCoffee=response.data;
          for(let index=0;index<_this.$data.cardCoffee.length;index++)
          {
            _this.$data.cardCoffee[index].imgURL=require('../assets/Menu/'+_this.$data.cardCoffee[index].mealsname+'.jpg');
            _this.$data.cardCoffee[index].stock=parseInt(response.data[index].mealsstock);
            console.log(response.data[index].mealsstock);
          }
          _this.loadAllTeas();
        }).catch(function (error) {
          console.log(1)
        })
      },
      loadAllTeas:function() {
        let _this=this;

        this.$axios({
          method:'get',
          url:'http://localhost:8080/allTea',
        }).then(function (response) {
          //_this.number=;
          _this.$data.cardTea=response.data;
          for(let index=0;index<_this.$data.cardTea.length;index++)
          {
            _this.$data.cardTea[index].imgURL=require('../assets/Menu/'+_this.$data.cardTea[index].mealsname+'.jpg');
            _this.$data.cardTea[index].stock=parseInt(response.data[index].mealsstock);
          }
          _this.loadAllSoups();
        }).catch(function (error) {
          console.log("Tea Error")
        })
      },
      loadAllSoups:function() {
        let _this=this;

        this.$axios({
          method:'get',
          url:'http://localhost:8080/allSoup',
        }).then(function (response) {
          //_this.number=;
          _this.$data.cardSoup=response.data;
          for(let index=0;index<_this.$data.cardSoup.length;index++)
          {
            _this.$data.cardSoup[index].imgURL=require('../assets/Menu/'+_this.$data.cardSoup[index].mealsname+'.jpg');
            _this.$data.cardSoup[index].stock=parseInt(response.data[index].mealsstock);
          }
          _this.loadAllIces();
        }).catch(function (error) {
          console.log(1)
        })
      },
      loadAllIces:function() {
        let _this=this;

        this.$axios({
          method:'get',
          url:'http://localhost:8080/allIce',
        }).then(function (response) {
          //_this.number=;
          _this.$data.cardIce=response.data;
          for(let index=0;index<_this.$data.cardIce.length;index++)
          {
            _this.$data.cardIce[index].imgURL=require('../assets/Menu/'+_this.$data.cardIce[index].mealsname+'.jpg');
            _this.$data.cardIce[index].stock=parseInt(response.data[index].mealsstock);
          }

          _this.loadAllCommodity()
        }).catch(function (error) {
          console.log(1)
        })
      },

      ClickToBuyCoffee(_index) {
        this.CheckCookie();

        this.currentBuy.name=this.cardCoffee[_index].mealsname.toString();
        this.currentBuy.intro="我是"+this.cardCoffee[_index].mealsname+"么么";
        this.currentBuy.imgInBuy=this.cardCoffee[_index].imgURL;
        this.currentBuy.BuyCount=1;
        this.currentBuy.price=this.findMealPrice(this.currentBuy.name);
        this.currentBuy.Stock=this.cardCoffee[_index].stock;
        this.dialogFormVisible=true;

      },
      ClickToBuyTea(_index) {
        this.CheckCookie();

        this.currentBuy.name=this.cardTea[_index].mealsname.toString();
        this.currentBuy.intro="我是"+this.cardTea[_index].mealsname+"么么";
        this.currentBuy.imgInBuy=this.cardTea[_index].imgURL;
        this.currentBuy.BuyCount=1;
        this.currentBuy.price=this.findMealPrice(this.currentBuy.name);
        this.currentBuy.Stock=this.cardTea[_index].stock;
        this.dialogFormVisible=true;
      },
      ClickToBuySoup(_index) {
        this.CheckCookie();

        this.currentBuy.name=this.cardSoup[_index].mealsname.toString();
        this.currentBuy.intro="我是"+this.cardSoup[_index].mealsname+"么么";
        this.currentBuy.imgInBuy=this.cardSoup[_index].imgURL;
        this.currentBuy.BuyCount=1;
        this.currentBuy.price=this.findMealPrice(this.currentBuy.name);
        this.currentBuy.Stock=this.cardSoup[_index].stock;
        this.dialogFormVisible=true;
      },
      ClickToBuyIce(_index) {
        this.CheckCookie();

        this.currentBuy.name=this.cardIce[_index].mealsname.toString();
        this.currentBuy.intro="我是"+this.cardIce[_index].mealsname+"么么";
        this.currentBuy.imgInBuy=this.cardIce[_index].imgURL;
        this.currentBuy.BuyCount=1;
        this.currentBuy.price=this.findMealPrice(this.currentBuy.name);
        this.currentBuy.Stock=this.cardIce[_index].stock;
        this.dialogFormVisible=true;
      },

      CheckCookie(){
        if(app.methods.getCookie("Customer")==="")
        {
          this.$message({
            message: '当前尚未登录，请登陆后购买',
            type: 'warning'
          });
          window.location.href="http://localhost:8081/#/Login";
        }
      },

      //购买界面
      cancelBuyDialog(){

        this.dialogFormVisible=false;
      },//取消购买
      addInBuyDialog(){
        let _this=this;

        this.$axios({
          method:'get',
          url:'http://localhost:8080/addShoppingTrolley?'+'usersaccountnumber='+_this.currentBuy.Number+'&mealsname='+_this.currentBuy.name+'&amount='+_this.currentBuy.BuyCount,
        }).then(function (response) {
          //存储当前的数量
          var tempAmount=0;
          //删除旧的
          for(let i=0;i<_this.$data.Commodity.length;i++){
            if(_this.currentBuy.name===_this.$data.Commodity[i].mealsname)
            {
              tempAmount=_this.$data.Commodity[i].amount;
              _this.$data.Commodity.splice(i,1);
              break;
            }
          }

          //创建新的
          var newItem= {mealsname:_this.currentBuy.name     ,amount:_this.currentBuy.BuyCount+tempAmount    ,price:_this.findMealPrice(_this.currentBuy.name)*(_this.currentBuy.BuyCount+tempAmount)}
          _this.$data.Commodity.push(newItem);

          //加入购物车的时候刷新总价
          _this.$data.totalPrice=0;
          for(let i=0;i<_this.$data.Commodity.length;i++)
          {
            _this.$data.totalPrice+=_this.$data.Commodity[i].price;
          }

          _this.$notify({
            title: '已加入购物车',
            message: '餐名:'+_this.currentBuy.name+"\n"+"数量:"+_this.currentBuy.BuyCount,
            type: 'success'
          });


        })

        this.dialogFormVisible=false;
      },//加入购物车

      //购物车界面
      loadAllCommodity(){
        let _this=this;

        this.$axios({
            method:'get',
              url:'http://localhost:8080/allShoppingTrolley?usersaccountnumber='+_this.currentBuy.Number,
          }).then(function (response) {
          _this.$data.Commodity=response.data;

          _this.$data.totalPrice=0;
          for(let i=0;i<_this.$data.Commodity.length;i++)
          {
            _this.$data.Commodity[i].price=_this.findMealPrice(_this.$data.Commodity[i].mealsname) * _this.$data.Commodity[i].amount;
            _this.$data.totalPrice+=_this.$data.Commodity[i].price;
            _this.$data.Commodity[i].check=false;
          }
        })
      },
      handleCheckAllChange(val) {
        if(val)
        {
          for(let i=0;i<this.$data.Commodity.length;i++)
          {
            this.$data.Commodity[i].check=true;
          }
          console.log("checkall"+this.checkAll);
        }
        else
        {
          for(let i=0;i<this.$data.Commodity.length;i++)
            this.$data.Commodity[i].check=false;
          console.log("checkall"+this.checkAll);
        }
        this.isIndeterminate=false;

        this.calculatePrice();
      },
      clickToDelete(index){
        this.$confirm('从购物车删除该商品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          let _this=this;

          this.$axios({
            method:'get',
            url:'http://localhost:8080/dropShoppingTrolley?'+'usersaccountnumber='+_this.currentBuy.Number+'&mealsname='+_this.Commodity[index].mealsname,
          }).then(function (response) {
            _this.$data.Commodity.splice(index,1);
            _this.$data.BuyCommodity.splice(_this.$data.BuyIndex[index],1);//改动123

            //删除购物车商品的时候刷新总价
            _this.$data.totalPrice=0;
            _this.$data.BuyTotalPrice=0;
            for(let i=0;i<_this.$data.Commodity.length;i++)
            {
              _this.$data.totalPrice+=_this.$data.Commodity[i].price;
              if(_this.$data.Commodity[i].check)
                _this.$data.BuyTotalPrice+=_this.$data.Commodity[i].price;
            }
          })
          _this.$notify({
            title: '已删除该商品',
            message: '餐名:'+_this.currentBuy.name,
            type: 'error'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      clickToClear(){
        let _this=this;
        this.$confirm('该操作将清空购物车, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          _this.ClearCart();

          _this.$notify({
            title: '已清空购物车',
            message: '请随意挑选',
            type: 'error'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      clickToGiveMoney(){
        this.CheckCookie();

        this.loadAllBuyCommodity();
        this.dialogBuyVisible=true;
      },
      test123(val){
        let Allflag=true;
        let Noflag=true;
        this.isIndeterminate=false;

        for(let i=0;i<this.$data.Commodity.length;i++)
        {
          if(!this.$data.Commodity[i].check)
          {
            Allflag=false;
            break;
          }
        }
        for(let i=0;i<this.$data.Commodity.length;i++)
        {
          if(this.$data.Commodity[i].check)
          {
            Noflag=false;
            break;
          }
        }

        this.checkAll=Allflag;
        this.isIndeterminate=!Allflag && !Noflag;

        this.calculatePrice();
      },
      handleChange(){
        this.calculatePrice();
      },
      calculatePrice(){
        this.totalPrice=0;
        for(let i=0;i<this.Commodity.length;i++)
        {
          this.Commodity[i].price=this.findMealPrice(this.$data.Commodity[i].mealsname) * this.Commodity[i].amount;
          this.totalPrice+=this.Commodity[i].price;
        }

        this.BuyTotalPrice=0;
        for(let i=0;i<this.Commodity.length;i++)
        {
          if(this.Commodity[i].check)
            this.BuyTotalPrice+=this.Commodity[i].price;
        }
      },

      //付款界面
      loadAllBuyCommodity(){
        this.clearAllBuyCommodity();
        for(let i=0;i<this.$data.Commodity.length;i++)
          if(this.Commodity[i].check)
          {
            this.BuyCommodity.push(this.Commodity[i]);
            this.BuyIndex.push(i);
          }
      },
      clearAllBuyCommodity(){
        this.BuyCommodity.length=0;
        this.BuyIndex.length=0;
      },
      hasPayed(){
        const loading = this.$loading({
          lock: true,
          text: '请等待系统确认',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        setTimeout(() => {
          //取消loading
          loading.close();
          //弹框
          this.$message({
            message: '付款成功',
            type: 'success'
          });
          //清除购物车
          //加入订单列表
          this.SubmitToOrder(this.BuyCommodity.length-1);
          this.BuyTotalPrice=0;
          this.totalPrice=0;
        }, 3000);//三秒
        this.dialogBuyVisible=false;
      },

      ClearCart(){
        let _this=this;
        this.$axios({
          method:'get',
          url:'http://localhost:8080/emptyShoppingTrolley?usersaccountnumber='+_this.currentBuy.Number
        }).then(function (response) {
          console.log(response.data);

          _this.$data.Commodity.length=0;

          //删除购物车商品的时候刷新总价
          _this.$data.totalPrice=0;
        })
      },
      SubmitToOrder(index){
        if(index>=0){
          let _this=this;

          this.$axios({
            method:'get',
            url:'http://localhost:8080/submitToOrder?usersaccountnumber='+_this.currentBuy.Number+"&mealsname="+_this.BuyCommodity[index].mealsname+"&amount="+_this.BuyCommodity[index].amount,
          }).then(function (response) {
            console.log(response);
            console.log(_this.currentBuy.Number+" "+_this.BuyCommodity[index].mealsname+" "+_this.BuyCommodity[index].amount);

            _this.SubmitToOrder(index-1);
            _this.Commodity.splice(_this.BuyIndex[index],1);
          })
        }
      },

      //查询
      findMealPrice:function (name) {
        for(let i=0;i<this.$data.cardCoffee.length;i++){
          if(this.$data.cardCoffee[i].mealsname===name)
            return this.$data.cardCoffee[i].mealsprice;
        }

        for(let i=0;i<this.$data.cardSoup.length;i++){
          if(this.$data.cardSoup[i].mealsname===name)
            return this.$data.cardSoup[i].mealsprice;
        }

        for(let i=0;i<this.$data.cardTea.length;i++){
          if(this.$data.cardTea[i].mealsname===name)
            return this.$data.cardTea[i].mealsprice;
        }

        for(let i=0;i<this.$data.cardIce.length;i++){
          if(this.$data.cardIce[i].mealsname===name)
            return this.$data.cardIce[i].mealsprice;
        }

        return -2;
      },
    }


  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  p{
    font-size: 120%;
  }

  /*Main里的css*/
  .button {
    padding: 0;
    float: right;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .key{
    position:fixed;right:-440px;top:200px;width: 500px;height: 600px;
    transition: all 0.7s;
  }

  .key:hover{
    right: 0px;
  }
</style>
