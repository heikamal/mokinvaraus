import { createApp } from 'vue'
import { createRouter, createWebHashHistory } from 'vue-router'
import './style.css'
import App from './App.vue'
import Home from './components/Home.vue'
import Mokit from './components/Mokit.vue'
import Mokki from './components/Mokki.vue'
import MokkiForm from './components/MokkiForm.vue'
import Alueet from './components/Alueet.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/cabins', component: Mokit },
  { path: '/cabins/:id', component: Mokki },
  { path: '/cabins/new', component: MokkiForm },
  { path: '/locations', component: Alueet }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})


const app = createApp(App)
app.use(router)
app.mount('#app')
