<script setup>
import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";


const link = (url) => router.push(url);


const id = ref('');
const pass = ref('');
const router = useRouter();

async function goLogin() {
  if (!id.value || !pass.value) {
    alert("입력부터 해주세요");
  } else {
    try {
      await axios.post(`/api/login`, {
        id: id.value,
        pass: pass.value
      }).then((res)=> {
      console.log(res.data)
      if (res.data === 1) {
        console.log("로그인 성공");
        alert("로그인 성공");
        router.push('/');

      } else if (res.data === 0) {
        console.log("비밀번호가 다릅니다.");
        alert("비밀번호가 다릅니다."); 
      } 
      else if(res.data === -1){
        console.log("아이디가 존재하지 않습니다.");
        alert("아이디가 존재하지 않습니다."); 
      }
    })

    } catch (error) {
      console.error("로그인 요청 실패:", error);
      alert("로그인 요청 실패");
    }
  }

}


</script>


<!-- <input
  :value="text"
  @input="event => text = event.target.value">

== <input v-model="text"> -->

<template>
  <v-main>
    <v-sheet width="300" class="mx-auto">
      <v-form>
        <v-text-field
          v-model="id"
          label="아이디"
        ></v-text-field>
        <!-- <v-text-field
          v-model="userId"
          label="아이디"
          ></v-text-field> -->

        <v-text-field
          v-model="pass"
          label="비밀번호"
          type="password"
        ></v-text-field>

        
      </v-form>
      <v-btn type="button" @click="goLogin" block class="mt-2">로그인</v-btn>
      <v-btn type="button" @click="link('/Member')">회원가입</v-btn>
    </v-sheet>
  </v-main>
</template>

<style>
</style>