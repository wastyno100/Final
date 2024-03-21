<style>
</style>
<template>
  <v-main>
    <H2>메뉴리스트</H2>
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
        <v-infinite-scroll :items="menu" :onLoad="load">
          <v-row>
            <v-col cols="12" md="3" v-for="(item, menuNo) in menu" :key="menuNo" @click="gotomenuDetail(item)">
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

//menudata
const menus = ref([]);
let menu = ref([]);

const router = useRouter();


// 비동기 API 함수
// function sleep(ms) {
//   return new Promise(resolve => setTimeout(resolve, ms));
// }

// const getData = async () => {
//   try {
//     console.log("데이터 로딩 중...");
//     await sleep(1000);
//
//     const res = await axios.get('/api/menuList', { params: { page: currentPage } });
//     loadMoreItems();
//     console.log("currentPage :"+currentPage);
//     Math.ceil(res.data.length/8);
//
//     currentPage++;
//     // menu.value = res.data; // 여기서는 전체 데이터를 menu.value에 할당했습니다.
//   } catch (error) {
//     console.error('API 호출 중 에러 발생:', error);
//   } finally {
//     isLoading.value = false;
//     console.log("다뿌렸지머야");
//   }
// }
const getData = async () => {
  try {
    const res = await axios.get('/api/menuList')
    // 예시 URL입니다. 실제 요청할 API 엔드포인트로 변경해야 합니다.
    return new Promise(resolve => {
      setTimeout(() => {
        menus.value = res.data;
        resolve(Array.from({ length: 10 }, (k, v) => v + (menus.value.at(-1) || 0) + 1))
      }, 1000)
    })
    // 데이터베이스로부터 받은 데이터를 반환합니다.
  } catch (error) {
    console.error('API 호출 중 에러 발생:', error)
    return []  // 에러가 발생한 경우 빈 배열을 반환
  }
}
  const load = async ({ done }) => {

  // 완료 콜백 호출
  done('ok')
}

//
// function loadMoreItems() {
//   const startIndex = (currentPage - 1) * 10;
//   const endIndex = startIndex + 10;
//   const itemsToDisplay = menu.value.slice(startIndex, endIndex);
//
//   console.log(itemsToDisplay);
// }
//
function gotomenuDetail(item){
  router.push({
    name: 'MenuDetail',
    state: {dataObj: { menuNo: item.menuNo }}
  })
}
// function handleScroll() {
//   const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
//
//   if (scrollTop + clientHeight >= scrollHeight - 5 && !isLoading.value) {
//     getData();
//   }
// }

onMounted(() => {
  getData();
  // window.addEventListener('scroll', handleScroll);
});
//
// watch(() => menu.value, () => {
//   loadMoreItems();
// });



//탭


</script>