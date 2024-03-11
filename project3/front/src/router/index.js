import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import AdminView from '../views/AdminView.vue'
import MenuListView from "@/views/MenuListView.vue";
import MemberView from '@/views/MemberView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/admin',
      name: 'admin',
      component: AdminView
    },
    {
      path: '/MenuList',
      name: 'MenuList',
      component: MenuListView
    },
    {
      path: '/Member',
      name: 'Member',
      component: MemberView
    },
  ]
})

export default router
