<script setup>
  function 로그인가자() {
    location.href = "/login";
  }

  function 메뉴가자() {
    location.href = "/menuList";
  }
</script>

<template>
  <v-main>
    <h2>메인입니다</h2><br>
    <v-btn @click="로그인가자">로그인 테스트 하러가기</v-btn>
    <v-btn @click="메뉴가자">메뉴 보러가기</v-btn>
  </v-main>
</template>

<style>
</style>