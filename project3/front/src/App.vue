<script setup>
import { RouterView, useRoute } from 'vue-router'
import HeaderView from './components/layout/Header.vue'
import FooterView from './components/layout/Footer.vue'

const route = useRoute()

const getURL = () => {
  const URLs1 = '/pay'
  const URLs2 = '/success'
  const URLs3 = '/fail'
  return route.path === URLs1 || route.path === URLs2 || route.path === URLs3
}
</script>

<template>
  <v-app>
    <HeaderView v-if="!getURL()" />
    <RouterView />
    <FooterView v-if="!getURL()" />
  </v-app>
</template>

<style>

</style>