import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import AdminView from '../views/AdminView.vue'
import MenuListView from "@/views/MenuListView.vue";
import MemberView from '@/views/MemberView.vue';
import BoardView from "@/views/BoardView.vue";
import BoardDetailView from "@/views/BoardDetailView.vue";
import BoardWriteView from "@/views/BoardWriteView.vue";
import BoardUpdateView from "@/views/BoardUpdateView.vue";
import qouteView from '@/views/quoteView.vue';
import Cart from '@/views/Cart.vue'
import BuyPage from '@/views/BuyPage.vue'
import MenuDetail from '@/views/MenuDetail.vue'
import MypageView from '@/views/MypageView.vue';
import MenuWrite from '@/views/MenuWrite.vue'
import EventView from '@/views/EventView.vue';

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
      path: '/MenuList/MenuDetail',
      name: 'MenuDetail',
      component: MenuDetail,
    },
    {
      path: '/Cart',
      name: 'Cart',
      component: Cart
    },
    {
      path: '/menu/write',
      name: 'menuWrite',
      component: MenuWrite
    },
    {
      path: '/BuyPage',
      name: 'BuyPage',
      component: BuyPage
    },
    {
      path: '/Member',
      name: 'Member',
      component: MemberView
    },
    {
      path: '/board',
      name: 'board',
      component: BoardView
    },
    {
      path: '/board/detail',
      name: 'detail',
      component: BoardDetailView,
    },
    {
      path: '/board/write',
      name: 'write',
      component: BoardWriteView,
    },
    {
      path: '/board/update',
      name: 'update',
      component: BoardUpdateView,
    },
    {
      path: '/qoute',
      name: 'qoute',
      component: qouteView,
    },
    {
      path: '/mypage',
      name: 'Mygage',
      component: MypageView,
    },
    {
      path: '/bizRegi',
      name: 'bizRegi',
      component: MypageView,
    },
    {
      path: '/qoute',
      name: 'qoute',
      component: qouteView,
    },
    {
      path: '/event',
      name: 'event',
      component: EventView,
    },
    {
      path: '/pay',
      name: 'pay',
      component: () => import('../views/pay/CheckoutView.vue')
    },
    {
      path: '/success',
      name: 'success',
      component: () => import('../views/pay/SuccessView.vue')
    },
    {
      path: '/fail',
      name: 'fail',
      component: () => import('../views/pay/FailView.vue')
    }
  ]
})

export default router
