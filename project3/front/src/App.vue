<script setup>
import { RouterView } from 'vue-router'
import HeaderView from './components/layout/Header.vue'
import FooterView from './components/layout/Footer.vue'
</script>

<template>
  <v-app>
    <HeaderView />
    <RouterView />
    <FooterView />
  </v-app>
</template>

<style>
</style>