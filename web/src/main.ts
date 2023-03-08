import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import './assets/main.css'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'

import axios from 'axios'
axios.defaults.baseURL = import.meta.env.VITE_SERVER

const app = createApp(App)
app.use(Antd).use(router)
app.mount('#app')
