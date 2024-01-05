import { createApp } from 'vue'
import { createRouter, createWebHashHistory } from 'vue-router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import './style.css'
import App from './App.vue'
import Home from './components/Home.vue'
import Mokit from './components/cabins/Mokit.vue'
import Mokki from './components/cabins/Mokki.vue'
import MokkiForm from './components/cabins/MokkiForm.vue'
import Alueet from './components/locations/Alueet.vue'
import AlueForm from './components/locations/AlueForm.vue'
import Postit from './components/postal/Postit.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/cabins', component: Mokit },
  { path: '/cabins/:id', component: Mokki },
  { path: '/cabins/new', component: MokkiForm },
  { path: '/locations', component: Alueet },
  { path: '/locations/new', component: AlueForm },
  { path: '/postal', component: Postit },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})


const app = createApp(App)
app.use(VueAxios, axios)
app.use(router)
app.mount('#app')
