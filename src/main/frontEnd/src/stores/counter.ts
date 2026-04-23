import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useCounterStore = defineStore('user', {
  state: () => ({
    userName: '',
    userNo: '',
    isLogin: false,
    ready: false,
  }),
  actions: {
    async bootstrap() {
      try {
        const { data } = await axios.post('/hyeon/refresh')
        this.userName = data.principal.user.user_name
        this.userNo = data.principal.user.user_no
        this.isLogin = true
      } catch {
        this.isLogin = false
      } finally {
        this.ready = true
      }
    },
    login(name: string, no: string) {
      this.userName = name
      this.userNo = no
      this.isLogin = true
    },
    logout() {
      this.userName = ''
      this.userNo = ''
      this.isLogin = false
    }
  },
})