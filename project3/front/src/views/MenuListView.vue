<style>
</style>
<template>
  <v-main>
    <H2>메뉴리스트</H2>
    <v-tabs v-model="item" bg-color="primary">
      <v-tab value="new">신제품</v-tab>
      <v-tab value="best">인기순</v-tab>
      <v-tab value="생선" >생선</v-tab>
      <v-tab value="게/새우류" >게/새우류</v-tab>
      <v-tab value="조개/전복류">조개/전복류</v-tab>
      <v-tab value="멍게/해삼류">멍게/해삼류</v-tab>
      <v-tab value="낙지/문어류">낙지/문어류</v-tab>
      <v-tab value="김/미역류">김/미역류</v-tab>
    </v-tabs>
        <v-card-text>
          <v-window v-model="item">
            <v-window-item value="new">
              <v-col cols="12">
                  <v-row>
                    <v-col cols="12" md="3"
                           v-for="item in showItem"
                           v-bind:key="item"
                           @click="gotomenuDetail(item)">
                      <v-card class="mx-auto mt-3 card" width="200px" height="250px">
                        <v-img
                          weight="200px"
                          height="100px"
                          src=""
                          cover
                        ></v-img>
                        <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
<!--                        <v-card-title>{{item.menuNo}}</v-card-title>-->
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
              </v-col>
              <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
            </v-window-item>
            <v-window-item value="best">
             <MenuListBest/>
            </v-window-item>
            <MenuListCateMenu :category = "item"/>
          </v-window>
        </v-card-text>
    <v-row ref="observer"></v-row>
  </v-main>
</template>
<script setup>
import { computed, onMounted, ref, watch} from 'vue'
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
}


function gotomenuDetail(item){
  router.push({
    name: 'MenuDetail',
    state: { dataObj: { menuNo: item.menuNo }}
  })
}

watch(currentPage, () => {
  sessionStorage.setItem('menuList', currentPage.value)
})

onMounted(() => {
  getData();
  console.log(menu.value)

  currentPage.value = JSON.parse(sessionStorage.getItem('menuList')) || 1
});


</script>