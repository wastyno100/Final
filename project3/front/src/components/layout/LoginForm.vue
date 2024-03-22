<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from 'vuex'; // Vuex의 useStore를 import합니다.

const id = ref('');
const pass = ref('');
const router = useRouter();
const store = useStore(); // 스토어 인스턴스를 가져옵니다.

const link = (url) => router.push(url);

// goLogin 함수를 수정합니다.
async function goLogin() {
  if (!id.value || !pass.value) {
    alert("입력부터 해주세요");
  } else {
    // store.dispatch를 사용하여 'login' 액션을 호출합니다.
    store.dispatch('login', { id: id.value, pass: pass.value });
  }
}
</script>

<!-- <input
  :value="text"
  @input="event => text = event.target.value">

== <input v-model="text"> -->

<template>
  <v-container>
    <v-row>
      <v-col>
        <v-main>
          <v-sheet width="300" class="mx-auto">
            <v-form>
              <v-text-field
                v-model="id"
                label="아이디"
              ></v-text-field>
              <v-text-field
                v-model="pass"
                label="비밀번호"
                type="password"
              ></v-text-field>
            </v-form>
          </v-sheet>
        </v-main>     
        <v-row>
          <v-col cols="5 offset-1" class="text-end">
            <v-main>
              <v-btn type="button" @click="goLogin">로그인</v-btn>
            </v-main>
          </v-col>
          <v-col class="text-start">
            <v-main>
              <v-btn type="button" @click="link('/Member')">회원가입</v-btn>
            </v-main>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
</v-container>  
</template>

<style>
</style>