<style>

</style>
<template>
  <v-main>
    <v-row class="testk">
      <v-col cols="12">
        <v-infinite-scroll :items="menu" :onLoad="menulist > null ? load : null">
          <v-row>
            <v-col cols="12" md="3" v-for="item in menu" :key="item.no">
              <v-card  class="mx-auto mt-3" width="200px" height="250px">
                <v-img
                  weight="200px"
                  height="150px"
                  src=""
                  cover
                ></v-img>

                <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
                <v-card-title> {{ item.menutitle }} </v-card-title>
                <v-card-subtitle>{{ item.menuprice }}원</v-card-subtitle>
                <v-card-subtitle>{{ item.menucontent }}</v-card-subtitle>

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
import { onMounted, ref } from 'vue'
import axios from 'axios';

//menudata
const menu = ref([]);

// 비동기 API 함수
const getData = async () => {
  console.log("탓냐!!!")
  const res = await axios.get('/api/menuList')
  menu.value = res.data;
  return new Promise(resolve => {
    setTimeout(() => {
      // 마지막 아이템 번호에서부터 시작하여 10개의 새로운 아이템을 생성
      resolve(Array.from({ length: 10 }, (k, v) => v + (menu.value.at(-1) || 0) + 1));
    }, 1000);
  });
};

onMounted(()=>{
  getData()
})

// 무한 스크롤 컴포넌트가 더 로드할 데이터를 요청할 때 호출되는 함수
const load = async ({ done }) => {
  const res = await getData(); // API 호출을 통해 새 아이템을 가져옴
  menu.value.push(...res); // 가져온 새 아이템을 기존 아이템 배열에 추가
  done('ok'); // 로드 작업이 완료되었음을 알림
};

</script>