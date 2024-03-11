<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

  const email = ref(null);
  const pass = ref(null);
  const router = useRouter();

  // 로그인 테스트입니다.
  async function goLogin() {
    if(email.value == null && pass.value == null){
      alert("입력부터 해라")
    }else {
      await fetch('/api/login', {
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email:email.value, pass:pass.value})
    }).then((res) => res.json()).then( data => {
      console.log('받은결과:', data)
      if(data){
        alert("로그인 성공");
        console.log(data);
        router.push("/");
      } else(
        alert("실패")
      )
    })
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
          v-model="email"
          label="이메일"
        ></v-text-field>
        <!-- <v-text-field
          v-model="userId"
          label="아이디"
          ></v-text-field> -->

        <v-text-field
          v-model="pass"
          label="비밀번호"
        ></v-text-field>

        <v-btn type="button" @click="goLogin" block class="mt-2">로그인</v-btn>
      </v-form>
    </v-sheet>
  </v-main>
</template>

<style>
</style>