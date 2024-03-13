<style>

</style>
<template>
  <v-main>
    <v-row class="testk">
      <v-col cols="12">
        <v-infinite-scroll :items="menulist.slice(0, 12)" :onLoad="menulist > null ? load : null">
          <v-row>
              <v-col cols="12" md="3" v-for="item in menulist.slice(0, 12)" :key="item.no">
            <v-card  class="mx-auto mt-3" width="200px" height="250px">
              <v-img
                weight="200px"
                height="150px"
                src=""
              cover
              ></v-img>

        <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
        <v-card-title> {{ item.title }} </v-card-title>
        <v-card-subtitle>{{ item.price }}원</v-card-subtitle>
        <v-card-subtitle>{{ item.content }}</v-card-subtitle>

      </v-card>
              </v-col>
        </v-row>
      </v-infinite-scroll>
      </v-col>
    </v-row>
    <v-row ref="observer"></v-row>
  </v-main>

</template>

<script setup>
import { ref } from 'vue';


const menulist = ref ([
  { no: 1, title: '문어', price: '70000', content:'완도산 문어' },
  { no: 2, title: '문어2', price: '70000', content:'완도산 문어2' },
  { no: 3, title: '문어3', price: '70000', content:'완도산 문어3' },
  { no: 4, title: '문어4', price: '70000', content:'완도산 문어4' },
  { no: 5, title: '문어4', price: '70000', content:'완도산 문어5' },
  { no: 6, title: '문어4', price: '70000', content:'완도산 문어6' },
  { no: 7, title: '문어4', price: '70000', content:'완도산 문어7' },
  { no: 8, title: '문어4', price: '70000', content:'완도산 문어8' },
  { no: 9, title: '문어4', price: '70000', content:'완도산 문어9' },
  { no: 10, title: '문어4', price: '70000', content:'완도산 문어10' },
  { no: 11, title: '문어4', price: '70000', content:'완도산 문어11' },
  { no: 12, title: '문어4', price: '70000', content:'완도산 문어12' },
  { no: 13, title: '문어4', price: '70000', content:'완도산 문어13' },
  { no: 14, title: '문어4', price: '70000', content:'완도산 문어14' },
  { no: 14, title: '문어4', price: '70000', content:'완도산 문어14' },
  { no: 14, title: '문어4', price: '70000', content:'완도산 문어14' },
  { no: 14, title: '문어4', price: '70000', content:'완도산 문어14' },
  { no: 14, title: '문어4', price: '70000', content:'완도산 문어14' },

]);

// 비동기 API 함수
const api = async () => {
  return new Promise(resolve => {
    setTimeout(() => {
      // 마지막 아이템 번호에서부터 시작하여 10개의 새로운 아이템을 생성
      resolve(Array.from({ length: 10 }, (k, v) => v + (menulist.value.at(-1) || 0) + 1));
    }, 1000);
  });
};

// 무한 스크롤 컴포넌트가 더 로드할 데이터를 요청할 때 호출되는 함수
const load = async ({ done }) => {
  const res = await api(); // API 호출을 통해 새 아이템을 가져옴
  menulist.value.push(...res); // 가져온 새 아이템을 기존 아이템 배열에 추가
  done('ok'); // 로드 작업이 완료되었음을 알림
};

</script>