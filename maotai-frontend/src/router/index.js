import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ProduceView from '../views/ProduceView.vue'
import ShelfView from '../views/ShelfView.vue'
import ConsumeView from '../views/ConsumeView.vue'
import TraceView from '../views/TraceView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/produce',
      name: 'produce',
      component: ProduceView
    },
    {
      path: '/shelf',
      name: 'shelf',
      component: ShelfView
    },
    {
      path: '/consume',
      name: 'consume',
      component: ConsumeView
    },
    {
      path: '/trace',
      name: 'trace',
      component: TraceView
    }

  ]
})

export default router
