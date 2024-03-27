<script setup>
  import { onMounted, computed } from 'vue'
  import { useRouter } from 'vue-router';
  import {useStore} from 'vuex'


  const router = useRouter()
  const store = useStore();
  const loginStatus = computed(() => store.state.loginStatus);
  const isLoggedIn = computed(() => loginStatus.value.isLogIn); // 사용자 로그인 상태
  const adminCheck = computed(() => loginStatus.value.role)

  // @click 파라미터로 받은 url로 이동
  const link = (url) => router.push(url);


  async function logout() {
  try {
    await store.dispatch('logout'); // 스토어의 logout 액션을 호출합니다.
    router.push('/'); // 로그아웃 후 홈으로 리다이렉트
  } catch (error) {
    console.error('로그아웃 액션 실행 중 오류 발생:', error);
  }
}


onMounted(() => {
   store.dispatch('checkLoginStatus')
})

//   async function checkLoginStatus() {
  //   try {
  //     await axios.post(`/api/status`, {}, { withCredentials: true }
  //     ).then((res) => {
  //    // 응답으로 받은 로그인 상태 업데이트
  //     const { isLogIn, userId } = res.data;
  //     loginStatus.value = { isLogIn, userId };
  //   }) // Axios 요청
        
  //   } catch (error) {
  //     console.error('로그인 상태 확인 중 오류 발생:', error)
  //     loginStatus.value = { isLogIn: false, userId: null };
  //   }
  // }
</script>

<template>
  <v-container>
    <v-app-bar app absolute height="100" class="bg-light-blue-darken-3">
      <v-toolbar-title @click="link('/')" style="cursor: pointer;" class="logo-custom">
        <img src="https://cdn-icons-png.flaticon.com/512/866/866469.png" style="width: 50%;">
      </v-toolbar-title>
      <v-row>
        <v-col>
          <v-row>
            <!-- 로그인 상태일 때 -->
            <v-col v-if="isLoggedIn" class="text-end" >
                    <span>{{ loginStatus.userId }}님, 환영합니다!</span>
                    <v-btn @click="logout">로그아웃</v-btn>
            </v-col>

            <!-- 비로그인 상태일 때 -->
            <v-col v-else class="text-end">
              <v-btn @click="link('/login')">로그인</v-btn>
              <v-btn @click="link('/Member')">회원가입</v-btn>
            </v-col>


      </v-row>
      <v-row class="pt-1"> 
        <v-col cols="12" class="text-center btn-custom">
          <v-btn @click="link('/')">홈</v-btn>
          <v-btn @click="link('/menuList')">메뉴</v-btn>
          <v-btn @click="link('/qoute')">시세</v-btn>
          <v-btn @click="link('/board')">소식</v-btn>
          <v-btn @click="link('/mypage')">마이페이지</v-btn>
          <v-btn v-if="adminCheck == 'admin'" @click="link('/admin')">관리자</v-btn>
        </v-col>
      </v-row> 
    </v-col>
      </v-row>
    </v-app-bar>
  </v-container>
</template>

<style scoped>
.logo-custom {
  position: absolute;
  width: 300px;
}

.btn-custom .v-btn:hover{
  border-radius: 0px;
  border-bottom: 2px solid white
}
</style>

