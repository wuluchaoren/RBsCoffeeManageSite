import Vue from 'vue'
import Router from 'vue-router'
import Recommand from '../components/Recommand'
import Menu from '../components/Menu'
import SelfInformation from '../components/SelfInformation'
import Order from '../components/Order'
import Register from  '../components/Register'
import Login from '../components/Login'
import Home from '../components/Home'
import Admin from '../components/Admin'
import coffeeMenu from '../components/coffeeMenu'
import AdminOrder from '../components/AdminOrder'
import Graph from '../components/graph'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path:'/Menu',
      name:'Menu',
      component:Menu
    },
    {
      path:'/Recommand',
      name:'Recommand',
      component:Recommand
    },
    {
      path: '/SelfInformation',
      name: 'SelfInformation',
      component: SelfInformation
    },
    {
      path: '/Order',
      name: 'Order',
      component: Order
    },
    {
      path:'/Login',
      name:'Login',
      component:Login
    },
    {
      path:'/Register',
      name:'Register',
      component:Register
    },
    {
      path:'/Home',
      name:'Home',
      component:Home
    },
    {
      path:'/Admin',
      name:'Admin',
      component:Admin
    },
    {
      path:'/coffeeMenu',
      name:'coffeeMenu',
      component:coffeeMenu
    },
    {
      path:'/AdminOrder',
      name:'AdminOrder',
      component:AdminOrder
    },
    {
      path:'/Graph',
      name:'Graph',
      component:Graph
    }
  ]
})


