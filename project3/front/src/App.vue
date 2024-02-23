<script setup>
import { RouterView } from 'vue-router'
import Header from './components/layout/Header.vue'
import Footer from './components/layout/Footer.vue'
</script>

<template>
  <v-container>
    <v-layout>
      <Header />
      <RouterView />
      <Footer />
    </v-layout>
  </v-container>
</template>

<style>
</style>
