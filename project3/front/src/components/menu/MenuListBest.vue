<template>
  <v-col cols="12">
    <v-row>
      <v-col cols="12" md="3"
             v-for="item in showItem"
             v-bind:key="item"
             @click="gotomenuDetail(item)">
       <v-card class="mx-auto mt-3 card" width="200px" height="250px" style=" width: 60%; height: inherit; position: relative; ">
                  <img
                    weight="200px"
                    height="100px"
                    :src="item.menuImg"
                  />
                  <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
                  <!--                        <v-card-title>{{item.menuNo}}</v-card-title>-->
                  <div class="card-overlay">
                  <v-card-title> {{ item.menuTitle }} </v-card-title>
                  <v-card-subtitle>
                    <span style="font-size: 20px">가격 : {{ item.menuPrice }}원</span>
                  </v-card-subtitle>
                    <v-card-subtitle>
                      <span style="font-size: 20px"> 추천 : {{item.heart}}</span>
                    <v-btn
                      class="ma-2"
                      color="blue-lighten-2"
                      icon="mdi-thumb-up"
                      variant="text"
                      v-size="small"
                    ></v-btn></v-card-subtitle>
                  </div>
                </v-card>
      </v-col>
    </v-row>
  </v-col>
  <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
</template>
<script setup>

import { computed, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

let menu = ref([]);
const router = useRouter();
// 페이징용
const currentPage = ref(1)
const pageGroup = 8
const allPage = computed(() => {
  return Math.ceil(menu.value.length / pageGroup)
}) // 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함


const showItem = computed(() => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  return menu.value.slice(start, end)
})

const pageUpdate = () => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  showItem.value = menu.value.slice(start, end)
}

const getData = async () => {
  console.log("best들어왔니?")
  const res = await axios.get('/api/menuListBest')
  menu.value = res.data;
  console.log(res.data)


  menu.value.forEach(async (item) => {
    item.menuImg = JSON.parse(item.menuImg)

    await axios.get(`/api/getMImage/${item.menuImg}`)
      .then((res) => {

        item.menuImg = res.data

      });
  });
}

function gotomenuDetail(item) {
  router.push({
    name: 'MenuDetail',
    state: { dataObj: { menuNo: item.menuNo } }
  })
}

onMounted(() => {
  getData();
});

</script>
<style scoped>
.card {
  height: 200px; /* 칸의 높이를 조정 */
  border: solid 1px rgba(0, 0, 0, 0.7);
}

.card img {
  width: 200px; /* 이미지를 칸에 꽉 채움 */
  height: 100px; /* 이미지를 칸에 꽉 채움 */
  object-fit: cover; /* 이미지를 비율 유지하면서 꽉 채움 */
}

.card-overlay {
  /* position: absolute; */
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.7);/* 투명한 검은색 배경 */
  padding: 5px;
  color: white;
  transition: opacity 0.3s ease; /* 투명도 변화에 대한 부드러운 전환 */
  opacity: 1;

}
</style>