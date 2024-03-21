<script setup>
  // script setup에서는 useRouter를 불러와서 사용해야함
  import { useRouter } from 'vue-router';
  const router = useRouter()

  // @click 파라미터로 받은 url로 이동
  const link = (url) => router.push(url);
</script>

<template>
  <v-container>
    <v-app-bar app absolute height="100" class="bg-light-blue-darken-3">
      <v-row>
        <v-col>
          <v-row>
        <v-col cols="1" class="pl-6">
          <v-toolbar-title @click="link('/')" style="cursor: pointer;">로고</v-toolbar-title>
        </v-col>
      </v-row>
      <v-row> 
        <v-col cols="12" class="text-center">
          <v-btn @click="link('/')">홈</v-btn>
          <v-btn @click="link('#')">아무거나1</v-btn>
          <v-btn @click="link('#')">아무거나2</v-btn>
          <v-btn @click="link('#')">아무거나3</v-btn>
          <v-btn @click="link('#')">아무거나4</v-btn>
        </v-col>
      </v-row> 
    </v-col>
      </v-row>
    </v-app-bar>
  </v-container>
</template>