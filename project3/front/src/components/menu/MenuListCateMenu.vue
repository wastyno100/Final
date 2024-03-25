<template>
  <v-window-item value="생선">
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
<!--          <v-card-title>{{item.menuNo}}</v-card-title>-->
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
    <v-window-item value="게/새우류" >
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
<!--              <v-card-title>{{item.menuNo}}</v-card-title>-->
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
  <v-window-item value="조개/전복류" >
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
<!--            <v-card-title>{{item.menuNo}}</v-card-title>-->
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
  <v-window-item value="멍게/해삼류" >
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
<!--            <v-card-title>{{item.menuNo}}</v-card-title>-->
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
  <v-window-item value="낙지/문어류" >
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
<!--            <v-card-title>{{item.menuNo}}</v-card-title>-->
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
  <v-window-item value="김/미역류" >
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
<!--            <v-card-title>{{item.menuNo}}</v-card-title>-->
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
</template>
<script setup>

import { computed, onMounted, ref, watch} from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { defineProps } from 'vue';


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

function gotomenuDetail(item){
  router.push({
    name: 'MenuDetail',
    state: { dataObj: { menuNo: item.menuNo }}
  })
}

watch(currentPage, () => {
  sessionStorage.setItem('menuList?menuCate', currentPage.value)
})

const props = defineProps({
  category: String
})

watch(() => props.category, async (newCategory) => {
  if (newCategory) {
    try{
    const res = await axios.get(`/api/menuList`);
    const categorys = res.data.filter(menu => menu.menuCate === newCategory);
    menu.value = categorys;
  }catch (error) {
      console.error("에러발생" + error);
    }
  }
}, { immediate: true });

onMounted(() => {
  console.log(props.category);
  currentPage.value = JSON.parse(sessionStorage.getItem('menuList?menuCate')) || 1
});
</script>
<style scoped>

</style>