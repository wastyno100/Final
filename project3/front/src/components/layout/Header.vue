<script setup>
  // script setup에서는 useRouter를 불러와서 사용해야함
  import { useRouter } from 'vue-router';
  const router = useRouter()

  // @click 파라미터로 받은 url로 이동
  const link = (url) => router.push(url);
</script>

<template>
  <v-app-bar app color="primary" absolute>
    <v-toolbar-title @click="link('/')" style="cursor: pointer;">로고</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn @click="link('/')">홈</v-btn>
    <v-btn @click="link('#')">아무거나1</v-btn>
    <v-btn @click="link('#')">아무거나2</v-btn>
  </v-app-bar>
</template>