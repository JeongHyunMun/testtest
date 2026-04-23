import type { Router } from 'vue-router'
import { useCounterStore } from '@/stores/counter'

export function installRouterGuards(router: Router) {
  router.beforeEach((to, from, next) => {
    const store = useCounterStore()
    if (!store.ready) store.bootstrap()
    const requiresAuth = to.matched.some(r => r.meta?.requiresAuth)
    const isPublic     = to.matched.some(r => r.meta?.public)

    if (requiresAuth && !store.isLogin) {
      // 로그인 안됨 → 로그인 페이지로, 원래 경로를 next 쿼리로 보냄
      if (to.path !== '/hyeon') {
        return next({ path: '/hyeon', query: { next: to.fullPath } })
      }
    }
    // 로그인 페이지에 이미 로그인 상태로 접근하면 홈으로 보내고 싶다면:
    if (isPublic && store.isLogin && to.path === '/hyeon') {
      return next({ path: '/home' })
    }
    next()
  })
}
