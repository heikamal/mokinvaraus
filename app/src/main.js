import { createApp } from 'vue'
import { createRouter, createWebHashHistory } from 'vue-router'
import './style.css'
import App from './App.vue'
import Home from './components/Home.vue'
import Mokit from './components/Mokit.vue'
import Mokki from './components/Mokki.vue'

const routes = [
  { path: '/', component: Home},
  { path: '/cabins', component: Mokit},
  { path: '/cabins/:id', component: Mokki}
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})


const app = createApp(App)
app.use(router)
app.mount('#app')
