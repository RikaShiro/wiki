import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import './assets/main.css'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'

import axios from 'axios'
axios.defaults.baseURL = import.meta.env.VITE_SERVER
axios.interceptors.request.use(
  (config) => {
    console.log('request', config)
    return config
  },
  (err) => {
    return Promise.reject(err)
  }
)
axios.interceptors.response.use(
  (res) => {
    console.log('response', res)
    return res
  },
  (err) => {
    return Promise.reject(err)
  }
)

const app = createApp(App)
app.use(Antd).use(router)
app.mount('#app')
