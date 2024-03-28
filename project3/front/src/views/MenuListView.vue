
<template>
  <v-main>
    <img src="/images/main.jpg" style="margin-left: 300px">
    <v-btn @click="router.push('/menu/write')" v-if="isAdmin">글쓰기</v-btn>
    <v-tabs v-model="item" bg-color="#004393" style="margin-top: 50px; display: flex; flex-direction: column; align-items: center;">
      <v-tab value="신제품">신제품</v-tab>
      <v-tab value="인기순">인기순</v-tab>
      <v-tab value="생선" >생선</v-tab>
      <v-tab value="게/새우류" >게/새우류</v-tab>
      <v-tab value="조개/전복류">조개/전복류</v-tab>
      <v-tab value="멍게/해삼류">멍게/해삼류</v-tab>
      <v-tab value="낙지/문어류">낙지/문어류</v-tab>
    </v-tabs>
    <v-card-text>
      <v-window v-model="item">
        <v-window-item value="신제품">
          <v-col cols="12">
            <v-row>
              <v-col cols="12" md="3"
                     v-for="item in showItem"
                     v-bind:key="item"
                     @click="gotomenuDetail(item)">
                <v-card class="mx-auto mt-3 card" style=" width: 60%; height: inherit; position: relative;">
                  <img
                    weight="200px"
                    height="100px"
                    :src="item.menuImg"
                    cover
                  />
                  <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
                  <!--                        <v-card-title>{{item.menuNo}}</v-card-title>-->
                  <div class="card-overlay">
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
                  </div>
                </v-card>
              </v-col>
            </v-row>
          </v-col>
          <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" style="padding: 20px;" />
        </v-window-item>
        <v-window-item value="인기순">
          <MenuListBest/>
        </v-window-item>
        <MenuListCateMenu :category = "item"/>
      </v-window>
    </v-card-text>
    <v-row ref="observer"></v-row>
  </v-main>
</template>

<style scoped>
.carousel-image {
  width: 100%;
  height: auto;
}

.card {
  height: 200px; /* 칸의 높이를 조정 */
  border: solid 1px rgba(0, 0, 0, 0.7);
}

.card img {
  width: 220px; /* 이미지를 칸에 꽉 채움 */
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

<script setup>
import { computed, onMounted, ref, watch } from 'vue'
import axios from 'axios';
import { useRouter } from "vue-router";
import MenuListBest from '@/components/menu/MenuListBest.vue'
import MenuListCateMenu from '@/components/menu/MenuListCateMenu.vue'

//페이지네이션
const item = ref('');
const menu = ref([]);
const router = useRouter();
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
  const res = await axios.get('/api/menuList')
  menu.value = res.data;
  console.log("리스트 잘 들어왔어")

  menu.value.forEach( async (item) => {
    item.menuImg = JSON.parse(item.menuImg)

    await axios.get(`/api/getMImage/${item.menuImg}`)
      .then((res) => {

        item.menuImg = res.data

      });
  });
}

const isAdmin = computed(() => sessionStorage.getItem('userId') === 'admin');

function gotomenuDetail(item){
  router.push({
    name: 'MenuDetail',
    state: { dataObj: { menuNo: item.menuNo }}
  })
}

watch(item, (newValue, oldValue) => {
  if(newValue != oldValue){
    currentPage.value = 1
  }
});

onMounted(() => {
  getData();
  console.log(menu.value)
});


</script>
<style scoped>


.card {
  height: 200px; /* 칸의 높이를 조정 */
  border: solid 1px rgba(0, 0, 0, 0.7);
}

.card img {
  width: 220px; /* 이미지를 칸에 꽉 채움 */
  height: 120px; /* 이미지를 칸에 꽉 채움 */
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