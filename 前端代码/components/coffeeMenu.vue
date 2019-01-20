<template>
  <div class="admin">
    <el-container>
      <el-aside style="width: 20%;  text-align:left;">
        <div style="display: block; position: fixed; left: 0px;width: 20%; height:100%">
          <div>
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
            <el-menu default-active="2" class="el-menu-vertical-demo">
              <router-link to="/Admin" style="text-decoration: none">
                <el-menu-item index="1">
                  <i class="el-icon-menu"></i>
                  <span slot="title" style="align:left">用户</span>
                </el-menu-item>
              </router-link>
              <el-menu-item index="2">
                <i class="el-icon-setting"></i>
                <span slot="title">菜单</span>
              </el-menu-item>
              <router-link to="/AdminOrder" style="text-decoration: none">
                <el-menu-item index="3">
                  <i class="el-icon-document"></i>
                  <span slot="title">订单</span>
                </el-menu-item>
              </router-link>
              <router-link to="/graph" style="text-decoration: none">
                <el-menu-item index="4">
                  <i class="el-icon-star-on"></i>
                  <span slot="title">店铺资讯</span>
                </el-menu-item>
              </router-link>
            </el-menu>

          </div>
        </div>
      </el-aside>

      <el-main>
        <div>
          <div style="margin-top:60px;">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">管理员</el-breadcrumb-item>
              <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
            </el-breadcrumb>
          </div>

          <div style="margin-top: 60px; padding:16px; background-color: #E9EEF3; text-align: left;">
            <el-form :inline="true" :model="formInline" label-width="60px" style="height:45px" >
              <el-form-item label="商品名:" style="width:280px;margin-bottom:0px">
                <el-input v-model="formInline.name" size="small"></el-input>
              </el-form-item>
              <el-form-item style="width:100px">
                <el-button type="primary" size="small" @click="searchByName(formInline.name)">搜索</el-button>
              </el-form-item>
              <el-form-item style="width:100px;float: right" >
                <el-button type="success"  size="small" icon="el-icon-plus" @click="newItem">添加菜品</el-button>
              </el-form-item>
            </el-form>
          </div>

          <div style="margin-top: 20px">
            <el-table :data="tableData" max-height="500" fit border stripe style="width: 100%">
              <el-table-column prop="itemName" label="商品名" ></el-table-column>
              <el-table-column prop="picture" label="图片" >
                <template slot-scope="scope" >
                  <img :src="scope.row.picture" alt="" style="height: 80px;width: 100px"/>
                </template>
              </el-table-column>
              <el-table-column prop="itemType" label="类型" sortable ></el-table-column>
              <el-table-column prop="itemContent" label="购买频率" ></el-table-column>
              <el-table-column prop="stock" label="库存" ></el-table-column>
              <el-table-column prop="itemPrice" label="价格" sortable ></el-table-column>
              <el-table-column label="操作" >
                <template slot-scope="scope">
                  <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-button type="danger" size="small" @click="confirmDelete(scope.$index, scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>

          <el-dialog title="编辑菜单信息" :visible.sync="dialogFormVisible" width="30%" style="text-align: left;">
            <el-form :model="form">
              <el-form-item label="商品名" :label-width="formLabelWidth">
                <el-input v-model="form.itemName" auto-complete="off" style="width: 300px"></el-input>
              </el-form-item>
              <!--<el-form-item label="图片" :label-width="formLabelWidth">-->
                <!--<el-input v-model="form.picture" auto-complete="off"></el-input>-->
              <!--</el-form-item>-->
              <el-form-item label="图片" :label-width="formLabelWidth">
                <template>
                  <el-upload
                    class="upload-demo"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :on-progress="handleChange"
                    :on-success="successChange"
                    :on-remove="backChange"
                    :on-error="errorChange"
                    list-type="picture-card"
                    limit=1>
                    <el-button size="small" type="primary">点击上传</el-button>
                  </el-upload>
                </template>
              </el-form-item>
              <el-form-item label="类型" :label-width="formLabelWidth">
                <el-autocomplete class="inline-input" v-model="form.itemType" :fetch-suggestions="querySearch"></el-autocomplete>
              </el-form-item>
              <el-form-item label="购买频率" :label-width="formLabelWidth">
                <el-input v-model="form.itemContent" auto-complete="off" disabled="true" style="width: 300px"></el-input>
              </el-form-item>
              <el-form-item label="价格" :label-width="formLabelWidth">
                <el-input v-model="form.itemPrice" auto-complete="off" style="width: 300px"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="confirmEdit">确 定</el-button>
              <el-button @click="dialogFormVisible = false">取 消</el-button>
            </div>
          </el-dialog>

          <el-dialog title="新增菜单" :visible.sync="newDialogVisible" width="30%" style="text-align: left;">
            <el-form :model="newform">
              <el-form-item label="商品名" :label-width="formLabelWidth">
                <el-input v-model="newform.itemName" auto-complete="off" style="width: 300px"></el-input>
              </el-form-item>
              <el-form-item label="图片" :label-width="formLabelWidth">
                <template>
                  <el-upload
                    class="upload-demo"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :on-progress="handleChange"
                    :on-success="successChange"
                    :on-remove="backChange"
                    :on-error="errorChange"
                    list-type="picture-card"
                    limit=1>
                    <el-button size="small" type="primary">点击上传</el-button>
                  </el-upload>
                </template>
              </el-form-item>
              <el-form-item label="类型" :label-width="formLabelWidth">
                <el-autocomplete class="inline-input" v-model="newform.itemType" :fetch-suggestions="querySearch"></el-autocomplete>
              </el-form-item>
              <el-form-item label="库存" :label-width="formLabelWidth">
                <el-input v-model="newform.itemStock" auto-complete="off" style="width: 300px"></el-input>
              </el-form-item>
              <el-form-item label="价格" :label-width="formLabelWidth">
                <el-input v-model="newform.itemPrice" auto-complete="off" style="width: 300px"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="confirmNew">确 定</el-button>
              <el-button @click="newDialogVisible = false">取 消</el-button>
            </div>
          </el-dialog>

        </div>

      </el-main>
    </el-container>


  </div>
</template>

<script>
  import app from "../App"
  export default {
    name: "coffeeMenu",
    data() {
      return {
        //表格数据
        tableData:[{
          itemName:'',
          picture:'',
          itemType:'',
          stock:'',
          itemContent:'',
          itemPrice:''
        }],

        //查找框内数据
        formInline: {
          name: '',
        },

        //编辑框是否可见
        dialogFormVisible: false,
        //新建框是否可见
        newDialogVisible: false,

        //编辑表单内数据
        form: {
          itemName:'',
          picture:'',
          itemType:'',
          itemContent:'',
          itemPrice:''
        },
        formLabelWidth: '120px',

        //新建表单内数据
        newform: {
          itemName:'',
          itemType:'',
          itemContent:'',
          itemStock:'',
          itemPrice:''
        },

        //当前正在编辑的Index
        currentIndex:'',
        //当前上传的图片名字
        currentPicture:'',

        //输入建议
        restaurants:[
          {value:'浓情蜜意'},
          {value:'甜在心扉'},
          {value:'栈语茗茶'},
          {value:'莱茵河畔'},
        ]
      }
    },

    created(){
      //cookie
      if(app.methods.getCookie("IsAdminister")==="")
        window.location.assign('http://localhost:8081/#/Admin');

      this.loadAllMenu();
    },

    methods: {

      //装载所有菜品
      loadAllMenu:function() {
        let _this=this;

        this.$axios({
          method:'get',
          url:'/allMenu',

        }).then(function (response) {

          _this.$data.tableData=response.data;
          for(var i=0;i<response.data.length;i++){
            _this.$data.tableData[i].itemName=response.data[i].mealsname;
            _this.$data.tableData[i].picture=require("../assets/Menu/"+_this.$data.tableData[i].itemName+".jpg");
            _this.$data.tableData[i].itemType=response.data[i].mealstype;
            _this.$data.tableData[i].itemContent=parseFloat(response.data[i].purchasefrequency);
            _this.$data.tableData[i].itemPrice=parseFloat(response.data[i].mealsprice);
            _this.$data.tableData[i].stock=parseInt(response.data[i].mealsstock);
          }

        }).catch(function (error) {
          console.log(error.response.data)
        })
      },

      //删除菜单时弹框确认
      confirmDelete:function(index, row){
        this.$confirm('此操作将永久删除该菜品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.handleDelete(index,row);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },

      //删除一个菜品
      handleDelete:function (index, row) {
        var mealsname=row.itemName.toString();
        let _this=this;

        this.$axios({
          method:'get',
          url:'/deleteMenu?mealsname='+mealsname,
          contentType:'application/json;charset=UTF-8',

        }).then(function (response) {
          console.log(response.data);
          _this.tableData.splice(index,1);
        }).catch(function (error) {
          console.log(error.response.data)
        })
      },

      //编辑一个菜品
      handleEdit:function (index, row){
        let _this=this;
        _this.$data.form=row;
        _this.$data.dialogFormVisible = true;
        _this.$data.currentIndex=index;
        _this.$data.currentPicture=_this.$data.tableData[_this.$data.currentIndex].itemName+'.jpg';
      },

      //编辑后的确定
      confirmEdit:function (){
        this.$confirm('确定要修改该菜品吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let _this=this;
          let currentIndex=_this.$data.currentIndex;
          _this.$data.dialogFormVisible = false;
          _this.$data.tableData[currentIndex].itemName=_this.$data.form.itemName;
          _this.$data.tableData[currentIndex].picture=require("../assets/Menu/"+_this.$data.currentPicture);
          //_this.$data.tableData[currentIndex].itemType=_this.$data.form.itemType;
          _this.$data.tableData[currentIndex].itemContent=parseFloat(_this.$data.form.itemContent);
          _this.$data.tableData[currentIndex].itemPrice=parseFloat(_this.$data.form.itemPrice);

          var name=_this.$data.form.itemName;
          var type=_this.$data.form.itemType;
          var price=_this.$data.form.itemPrice;

          this.$axios({
            method:'get',
            url:'/alterMenu?mealsname='+name+'&mealstype='+type+'&mealsprice='+price,

          }).then(function (response) {
            console.log(response.data);

          }).catch(function (error) {
            console.log(error.response.data)
          })

          this.$message({
            type: 'success',
            message: '修改成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消修改'
          });
        });
      },

      //根据菜品名字搜索
      searchByName:function(name){
        let _this=this;

        this.$axios({
          method:'get',
          url:'/keywordSelect?keyword='+name,

        }).then(function (response) {

          _this.$data.tableData=response.data;
          for(var i=0;i<response.data.length;i++){
            _this.$data.tableData[i].itemName=response.data[i].mealsname;
            _this.$data.tableData[i].picture=require("../assets/Menu/"+_this.$data.tableData[i].itemName+".jpg");
            _this.$data.tableData[i].itemType=response.data[i].mealstype;
            _this.$data.tableData[i].itemContent=parseFloat(response.data[i].purchasefrequency);
            _this.$data.tableData[i].itemPrice=parseFloat(response.data[i].mealsprice);
          }

        }).catch(function (error) {
          console.log(error.response.data)
        })
      },

      //新增一个菜品
      newItem:function(){
        let _this=this;
        _this.$data.newDialogVisible = true;
      },

      //新增后的确定
      confirmNew:function(){
        this.$confirm('确定创建吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let _this=this;
          _this.$data.newDialogVisible = false;

          var itemName=_this.$data.newform.itemName;
          var itemType=_this.$data.newform.itemType;
          //var itemContent=parseFloat(_this.$data.newform.itemContent);
          var itemPrice=parseFloat(_this.$data.newform.itemPrice);
          var itemStock=parseInt(_this.$data.newform.itemStock);

          var newItem={itemName:itemName,picture:require("../assets/Menu/"+_this.$data.currentPicture),itemType:itemType,itemContent:0,stock:itemStock,itemPrice:itemPrice};
          _this.$data.tableData.unshift(newItem);

          this.$axios({
            method:'get',
            url:'/addMenu?mealsname='+itemName+'&mealsprice='+itemPrice+'&mealstype='+itemType+'&mealsstork='+itemContent,

          }).then(function (response) {
            console.log(response.data);

          }).catch(function (error) {
            console.log(error.response.data)
          });

          this.$message({
            type: 'success',
            message: '创建成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消创建'
          });
        });
      },

      //上传文件后隐藏上传框
      handleChange(event,file, fileList) {
        var divset = document.getElementsByClassName("el-upload el-upload--picture-card");
        for (var i = 0; i<divset.length;i++) {
          divset[i].style.display="none";
        };
      },

      //上传文件成功
      successChange(event,file, fileList) {
        let _this=this;
        var divset = document.getElementsByClassName("el-upload el-upload--picture-card");
        for (var i = 0; i<divset.length;i++) {
          divset[i].style.display="none";
        };
        _this.$data.currentPicture=file.name;
      },

      //删除文件后显示上传框
      backChange(file, fileList) {
        var divset = document.getElementsByClassName("el-upload el-upload--picture-card");
        for (var i = 0; i<divset.length;i++) {
          divset[i].style.display="block";
        };
      },

      //上传失败后显示上传框
      errorChange(err,file, fileList) {
        var divset = document.getElementsByClassName("el-upload el-upload--picture-card");
        for (var i = 0; i<divset.length;i++) {
          divset[i].style.display="block";
        };
      },

      //编辑框的输入建议
      querySearch(queryString, cb) {
        var restaurants = this.restaurants;
        var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },

    }
  }
</script>

<style scoped>
  .admin{
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
</style>
