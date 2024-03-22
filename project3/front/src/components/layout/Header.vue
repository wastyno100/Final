<script setup>
  // script setup에서는 useRouter를 불러와서 사용해야함
  import axios from 'axios';
import { onMounted, ref, computed } from 'vue'
  import { useRouter } from 'vue-router';
  const router = useRouter()
  const loginStatus = ref({ isLogIn: false, userId: null }); // 사용자 로그인 상태

  // @click 파라미터로 받은 url로 이동
  const link = (url) => router.push(url);
  const isLoggedIn = computed(() => loginStatus.value.isLogIn);

  async function checkLoginStatus() {
  try {
    await axios.post(`/api/status`, {}, { withCredentials: true }
    ).then((res) => {
   // 응답으로 받은 로그인 상태 업데이트
    const { isLogIn, userId } = res.data;
    loginStatus.value = { isLogIn, userId };
  }) // Axios 요청
      
  } catch (error) {
    console.error('로그인 상태 확인 중 오류 발생:', error)
    loginStatus.value = { isLogIn: false, userId: null };
  }
}
async function logout() {
  try {
    await axios.post(`/api/logout`, {}, { withCredentials: true }); // 로그아웃 요청을 서버에 보냄
    loginStatus.value = { isLogIn: false, userId: null };
    router.push('/'); // 로그아웃 후 메인페이지로 이동
    console.log('로그아웃 되었습니다.')
  } catch (error) {
    console.error('로그아웃 중 오류 발생:', error);
  }
}

onMounted(() => {
   checkLoginStatus()
})
</script>

<template>
  <v-container>
    <v-app-bar app absolute height="100" class="bg-light-blue-darken-3">
      <v-row>
        <v-col>
          <v-row>
            <v-col cols="3" class="pl-6">
              <v-toolbar-title @click="link('/')" style="cursor: pointer;">로고</v-toolbar-title>
            </v-col>
          
            <v-col v-if="isLoggedIn" class="text-end" >
              <p>{{ loginStatus.value.userId }}님, 환영합니다!</p>
              <v-btn @click="logout">로그아웃</v-btn>
            </v-col>
            <v-col v-else class="text-end">
              <v-btn @click="link('/login')">로그인</v-btn>
              <v-btn @click="link('/Member')">회원가입</v-btn>
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