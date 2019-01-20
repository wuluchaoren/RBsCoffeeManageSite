<template>
  <div class="recommand">
    <el-container>
      <!--Left-->
      <el-aside width="30%" >
        <div style="display: block; position: fixed; left: 0px; width:30%;background-attachment: fixed;background-color: #fffaf0;height: 100%">
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
              <div style="float: left"><el-button round style="margin-top: 250px" type="info">季度精选</el-button></div>
              <router-link to="/Menu"> <div style="float: left"><el-button round style="margin-top: 50px" type="info" plain >菜单</el-button></div></router-link>
            </div>
          </el-col>
        </div>
      </el-aside>

      <!--Right-->
      <el-main width="70%">
        <div class="animated bounceInRight">
          <div style="margin:10px auto;width: 90%">
            <img src="../assets/recommand/玩出夏天2.jpg" width="100%"/>
          </div>

          <div style="margin-top: 60px;font-family: PingFang SC;font-size: 23px;">够热才好玩</div>
          <div style="margin-top: 20px;margin-bottom:60px;font-family: PingFang SC">
            全新的抹茶提拉米苏星冰乐，新鲜馥郁的提拉米苏层层融入浓郁奶香，顶部覆以焦香松脆的华夫风味饼<br>
            干碎，像品尝一份流动的甜品，口感层层丰富。马达加斯加香草风味星冰乐清新回归，来自马达加斯加<br>
            的优质香草的芬芳与奶香星冰乐交融相拥，黑白互衬，层层美味，层层入心。<br>
          </div>

          <div style="margin:10px auto;width: 90%">
            <img src="../assets/recommand/冷萃2.jpg" width="100%"/>
          </div>

          <div style="margin-top: 60px;font-family: PingFang SC;font-size: 23px;">集卡套玩出别样夏日乐趣</div>
          <div style="margin-top: 20px;margin-bottom:60px;font-family: PingFang SC">
            热带丛林卡套装设计灵感源自浅雅烘焙咖啡豆包装，如同夏日轻快跳跃的阳光点缀于星享<br>
            卡上。随卡附赠趣味扭蛋，三款咖啡冲煮器皿随机出现，带来玩趣小惊喜。<br>
          </div>
          <div style="margin:10px auto;width: 90%">
            <img src="../assets/recommand/卡套装2.jpg" width="100%"/>
          </div>

          <div style="margin-top: 60px;font-family: PingFang SC;font-size: 23px;">闲庭综合咖啡豆，感受这杯芬芳醇香</div>
          <div style="margin-top: 20px;margin-bottom:60px;font-family: PingFang SC">
            浅雅烘焙系列——闲庭综合咖啡豆轻柔回归，让咖啡风味之旅更加完整。<br>
            现在就来RBS门店，找到你喜爱的那一杯吧。<br>
          </div>
          <div style="margin:10px auto;width: 90%">
            <img src="../assets/recommand/咖啡豆2.jpg" width="100%"/>
          </div>

        </div>
      </el-main>
    </el-container>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        input10: ''
      }
    },
    methods:{

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  p{
    font-size: 120%;
  }

  /*Main里的css*/

</style>
