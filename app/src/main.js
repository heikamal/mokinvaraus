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
import PostiForm from './components/postal/PostiForm.vue'
import Asiakkaat from './components/customer/Asiakkaat.vue'
import Asiakas from './components/customer/Asiakas.vue'
import AsiakasForm from './components/customer/AsiakasForm.vue'
import Laskut from './components/invoices/Laskut.vue'
import LaskuForm from './components/invoices/LaskuForm.vue'
import Varaukset from './components/reservations/Varaukset.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/cabins', component: Mokit },
  { path: '/cabins/:id', component: Mokki },
  { path: '/cabins/new', component: MokkiForm },
  { path: '/locations', component: Alueet },
  { path: '/locations/new', component: AlueForm },
  { path: '/postal', component: Postit },
  { path: '/postal/new', component: PostiForm },
  { path: '/customers', component: Asiakkaat },
  { path: '/customers/:id', component: Asiakas },
  { path: '/customers/new', component: AsiakasForm },
  { path: '/invoices', component: Laskut },
  { path: '/invoices/new', component: LaskuForm },
  { path: '/reservations', component: Varaukset },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})


const app = createApp(App)
app.use(VueAxios, axios)
app.use(router)
app.mount('#app')
