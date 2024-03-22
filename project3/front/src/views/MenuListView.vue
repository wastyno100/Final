<style>
</style>
<template>
  <v-main>
    <H2>메뉴리스트</H2>
    {{items}}
    <v-col cols="12">
      <v-autocomplete
        :items="items"
        class="mx-auto"
        density="comfortable"
        menu-icon=""
        placeholder="검색어를 입력하세요"
        prepend-inner-icon="mdi-magnify"
        style="max-width: 350px"
        theme="light"
        variant="solo"
        auto-select-first
        item-props
        rounded
        @input="searchItems"
      ></v-autocomplete>
    </v-col>
    <v-row class="testk">
      <v-col cols="12">
        <v-infinite-scroll :items="items" :onLoad="load">
          <v-row>
            <v-col cols="12" md="3" v-for="(item, menuNo) in items" :key="menuNo" @click="gotomenuDetail(item)">
              <v-card class="mx-auto mt-3 card" width="200px" height="250px">
                <v-img
                  weight="200px"
                  height="100px"
                  src=""
                  cover
                ></v-img>
                <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
                <v-card-title>{{item.menuNo}}</v-card-title>
                <v-card-title> {{ item.menuTitle }} </v-card-title>
                <v-card-subtitle>
                  {{ item.menuPrice }}원
                </v-card-subtitle>
                <v-btn
                  class="ma-2"
                  color="blue-lighten-2"
                  icon="mdi-thumb-up"
                  variant="text"
                ></v-btn>{{item.heart}}
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
import { onMounted, ref,} from 'vue'
import axios from 'axios';
import { useRouter } from "vue-router";

//페이지네이션
let items = ref([]);

const router = useRouter();

const getData = async () => {
  try {
    const res = await axios.get('/api/menuList')
    items.value = res.data;
    return new Promise(resolve => {
      setTimeout(() => {
        resolve(Array.from({ length: 10 }, (k, v) => v + (res.data.at(-1) || 0) + 1))
      }, 1000)
    })
    // 데이터베이스로부터 받은 데이터를 반환합니다.
  } catch (error) {
    console.error('API 호출 중 에러 발생:', error)
    return []  // 에러가 발생한 경우 빈 배열을 반환
  }
}
const load = async ({ done }) => {

  const test = await getData()

  items.value.push(...test);

  // 완료 콜백 호출
  done('ok')
}
function gotomenuDetail(item){
  router.push({
    name: 'MenuDetail',
    state: { dataObj: { menuNo: item.menuNo }}
  })
}
onMounted(() => {
  getData();
});
</script>