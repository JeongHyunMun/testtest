import './assets/main.css'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { installRouterGuards } from './router/guards'
import { useCounterStore } from '@/stores/counter'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import App from './App.vue'
import router from './router'

// Pinia/persis 플러그인
const app = createApp(App)
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)   
app.use(pinia)

// 새로고침 시 인증 복원
const store = useCounterStore()
await store.bootstrap?.()   

installRouterGuards(router)

app.use(router)

app.mount('#app')
