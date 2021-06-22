import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/views/layout/Layout'

Vue.use(Router)

const routerMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },
  { path: '/401', component: () => import('@/views/401'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    children: [
      {
        path: 'dashboard', component: () => import('@/views/dashboard/index'),
        meta: { title: '仪表盘', icon: 'dashboard' }
      }
    ]
  },

  {
    path: '/authority',
    component: Layout,
    redirect: '/authority/admin',
    name: '权限管理',
    meta: { title: '权限管理', icon: 'authority' },
    children: [
      {
        path: 'admin',
        name: '管理员管理',
        component: () => import('@/views/authority/admin'),
        meta: { title: '管理员管理', icon: 'user' }
      },
      {
        path: 'role',
        name: '角色管理',
        component: () => import('@/views/authority/role'),
        meta: { title: '角色管理', icon: 'peoples' }
      },
      {
        path: 'menu',
        name: '菜单管理',
        component: () => import('@/views/authority/menu'),
        meta: { title: '菜单管理', icon: 'authority' }
      }
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/personalCenter',
    name: '系统管理',
    meta: { title: '系统管理', icon: 'system' },
    children: [
      {
        path: 'personalCenter',
        name: '个人中心',
        component: () => import('@/views/system/personalCenter'),
        meta: { title: '个人中心', icon: 'personalCenter' }
      }
    ]
  },

];

const router = new Router({
  routes: routerMap
})

export default router;