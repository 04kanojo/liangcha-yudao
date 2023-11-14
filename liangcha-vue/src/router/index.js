import Vue from 'vue'
import VueRouter from 'vue-router'
import index from '@/views/index.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'index',
    component: index
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

/**
 * 全局前置守卫
 * @param to 将要访问的路由的信息对象
 * @param from 将要离开的路由的信息对象
 * @param next next是一个函数,使用next()表示放行
 */
// router.beforeEach((to, from, next) => {
//   if (to.path.concat('/t3')) {
//     next(false)
//   }
//   next()
// })

export default router
