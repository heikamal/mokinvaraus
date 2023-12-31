import { createApp } from 'vue'
import { createRouter, createWebHashHistory } from 'vue-router'
import './style.css'
import App from './App.vue'
import HelloWorld from './components/HelloWorld.vue'
import Mokit from './components/Mokit.vue'

const routes = [
  { path: '/', component: HelloWorld},
  { path: '/mokit', component: Mokit}
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})


const app = createApp(App)
app.use(router)
app.mount('#app')
