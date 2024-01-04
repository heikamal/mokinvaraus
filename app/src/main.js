import { createApp } from 'vue'
import { createRouter, createWebHashHistory } from 'vue-router'
import './style.css'
import App from './App.vue'
import Home from './components/Home.vue'
import Mokit from './components/Mokit.vue'

const routes = [
  { path: '/', component: Home},
  { path: '/mokit', component: Mokit}
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})


const app = createApp(App)
app.use(router)
app.mount('#app')
