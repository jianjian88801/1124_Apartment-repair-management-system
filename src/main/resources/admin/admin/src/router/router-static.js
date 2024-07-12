import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import weixiurenyuan from '@/views/modules/weixiurenyuan/list'
    import weixiushenqing from '@/views/modules/weixiushenqing/list'
    import gaipaishenqing from '@/views/modules/gaipaishenqing/list'
    import qingjiaxinxi from '@/views/modules/qingjiaxinxi/list'
    import weixiujilu from '@/views/modules/weixiujilu/list'
    import weixiufenpei from '@/views/modules/weixiufenpei/list'
    import fangjianxinxi from '@/views/modules/fangjianxinxi/list'
    import wupinxinxi from '@/views/modules/wupinxinxi/list'
    import zhuhu from '@/views/modules/zhuhu/list'
    import weixiufenlei from '@/views/modules/weixiufenlei/list'
    import weixiupingjia from '@/views/modules/weixiupingjia/list'


//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/weixiurenyuan',
        name: '维修人员',
        component: weixiurenyuan
      }
      ,{
	path: '/weixiushenqing',
        name: '维修申请',
        component: weixiushenqing
      }
      ,{
	path: '/gaipaishenqing',
        name: '改派申请',
        component: gaipaishenqing
      }
      ,{
	path: '/qingjiaxinxi',
        name: '请假信息',
        component: qingjiaxinxi
      }
      ,{
	path: '/weixiujilu',
        name: '维修记录',
        component: weixiujilu
      }
      ,{
	path: '/weixiufenpei',
        name: '维修分配',
        component: weixiufenpei
      }
      ,{
	path: '/fangjianxinxi',
        name: '房间信息',
        component: fangjianxinxi
      }
      ,{
	path: '/wupinxinxi',
        name: '物品信息',
        component: wupinxinxi
      }
      ,{
	path: '/zhuhu',
        name: '住户',
        component: zhuhu
      }
      ,{
	path: '/weixiufenlei',
        name: '维修分类',
        component: weixiufenlei
      }
      ,{
	path: '/weixiupingjia',
        name: '维修评价',
        component: weixiupingjia
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
