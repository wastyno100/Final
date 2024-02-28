<script setup>
  // script setup에서는 useRouter를 불러와서 사용해야함
  import { useRouter } from 'vue-router';
  const router = useRouter()

  // @click 파라미터로 받은 url로 이동
  const link = (url) => router.push(url);
</script>

<template>
  <v-main>
    <h2>메인입니다</h2><br>
    <v-btn @click="link('/login')">로그인 테스트 하러가기</v-btn><br><br>
    <v-btn @click="link('/admin')">관리자 하러가기</v-btn>
    <v-btn @click="link('/menuList')">메뉴 보러가기</v-btn>
  </v-main>
</template>

<style>
</style>