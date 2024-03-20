<script setup>
  // script setup에서는 useRouter를 불러와서 사용해야함
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';

  const router = useRouter()

  // @click 파라미터로 받은 url로 이동
  const link = (url) => router.push(url);

  const 숫자 = ref(1);

  function 허허() {
    숫자.value = Math.floor(Math.random() * 9) + 1;
    console.log(숫자.value)
  }
</script>

<template>
  <v-main>
    <h2>메인입니다</h2><br>
    <v-btn @click="link('/login')">로그인 테스트 하러가기</v-btn><br><br>
    <v-btn @click="link('/Member')">회원가입 테스트 하러가기</v-btn><br><br>
    <v-btn @click="link('/board')">공지벤트</v-btn><br /><br />
    <v-btn @click="link('/admin')">관리자 하러가기</v-btn>
    <v-btn @click="link('/menuList')">메뉴 보러가기</v-btn><br><br><br><br>
    <v-btn @click="link('/menuCart')">장바구니 보러가기</v-btn><br><br><br><br>

    
    <v-container>
      <v-row class="mb-4">
        <v-col class="pa-2 ma-2">
          <v-btn v-show="숫자 == 1" @mouseover="허허">나잡아봐라</v-btn>
        </v-col>
        <v-col class="pa-2 ma-2">
          <v-btn v-show="숫자 == 2" @mouseover="허허">나잡아봐라</v-btn>
        </v-col>
        <v-col class="pa-2 ma-2">
          <v-btn v-show="숫자 == 3" @mouseover="허허">나잡아봐라</v-btn>
        </v-col>
      </v-row>
      <v-row class="mb-4">
        <v-col class="pa-2 ma-2">
          <v-btn v-show="숫자 == 4" @mouseover="허허">나잡아봐라</v-btn>
        </v-col>
        <v-col class="pa-2 ma-2">
          <v-btn v-show="숫자 == 5" @mouseover="허허">나잡아봐라</v-btn>
        </v-col>
        <v-col class="pa-2 ma-2">
          <v-btn v-show="숫자 == 6" @mouseover="허허">나잡아봐라</v-btn>
        </v-col>
      </v-row>
      <v-row class="mb-4">
        <v-col class="pa-2 ma-2">
          <v-btn v-show="숫자 == 7" @mouseover="허허">나잡아봐라</v-btn>
        </v-col>
        <v-col class="pa-2 ma-2">
          <v-btn v-show="숫자 == 8" @mouseover="허허">나잡아봐라</v-btn>
        </v-col>
        <v-col class="pa-2 ma-2">
          <v-btn v-show="숫자 == 9" @mouseover="허허">나잡아봐라</v-btn>
        </v-col>
      </v-row>
    </v-container>
    
    
  </v-main>
</template>

<style scoped>
  .v-btn{background: pink;}

</style>