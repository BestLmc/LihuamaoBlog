import Vue from 'vue'
import Router from 'vue-router'
import HomeIndex from '@/views/home'

Vue.use(Router)

const routerMap = [
  {
    path: '/',
    component: HomeIndex,
    children: [
      { path: '/', component: () => import('@/views/index') }
    ]
  }
];

const router = new Router({
  routes: routerMap
})

export default router;