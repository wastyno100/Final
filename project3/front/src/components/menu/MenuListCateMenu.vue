<template>
  <v-window-item value="생선">
  <v-col cols="12" class="my-3 py-5">
    <v-row>
      <v-col cols="12" md="3"
             v-for="item in showItem"
             v-bind:key="item"
             @click="gotomenuDetail(item)">
       <v-card class="mx-auto mt-3 card" width="200px" height="250px" style=" width: 75%; height: inherit; position: relative; ">
                  <img
                    style="width: 100%; height: 200px; object-fit: cover;"
                    :src="item.menuImg"
                  />
                  <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
                  <!--                        <v-card-title>{{item.menuNo}}</v-card-title>-->
                  <div class="card-overlay" style="padding: 0; margin: 0;">
                  <v-card-title> {{ item.menuTitle }} </v-card-title>
                  <v-card-subtitle>
                  가격 : {{ item.menuPrice }}원
                  </v-card-subtitle>
                    <v-btn
                      class="ma-2"
                      color="blue-lighten-2"
                      icon="mdi-thumb-up"
                      variant="text"
                      v-size="small"
                    ></v-btn>{{item.heart}}
                  </div>
                </v-card>
      </v-col>
    </v-row>
  </v-col>
  <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
  </v-window-item>
    <v-window-item value="게/새우류" >
      <v-col cols="12" class="my-3 py-5">
        <v-row>
          <v-col cols="12" md="3" style="height: auto;"
                 v-for="item in showItem"
                 v-bind:key="item"
                 @click="gotomenuDetail(item)">
          <v-card class="mx-auto mt-3 card" width="250px" height="250px" style=" width: 75%; height: inherit; position: relative; ">
                  <img
                  style="width: 100%; height: 200px; object-fit: cover;"
                    :src="item.menuImg"
                  />
                  <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
                  <!--                        <v-card-title>{{item.menuNo}}</v-card-title>-->
                  <div class="card-overlay" style="padding: 0; margin: 0;">
                    <v-card-title> {{ item.menuTitle }} </v-card-title>
                  <v-card-subtitle>
                    {{ item.menuPrice }}원
                  </v-card-subtitle>
                  <v-btn
                    class="ma-2"
                    color="blue-lighten-2"
                    icon="mdi-thumb-up"
                    variant="text"
                    v-size="small"
                  ></v-btn>{{item.heart}}
                  </div>
                </v-card>
          </v-col>
        </v-row>
      </v-col>
      <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
    </v-window-item>
  <v-window-item value="조개/전복류" >
    <v-col cols="12" class="my-3 py-5">
      <v-row>
        <v-col cols="12" md="3" style="height: auto;"
               v-for="item in showItem"
               v-bind:key="item"
               @click="gotomenuDetail(item)">
         <v-card class="mx-auto mt-3 card" width="250px" height="250px" style=" width: 75%; height: inherit; position: relative; ">
                  <img
                  style="width: 100%; height: 200px; object-fit: cover;"
                    :src="item.menuImg"
                  />
                  <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
                  <!--                        <v-card-title>{{item.menuNo}}</v-card-title>-->
                  <div class="card-overlay" style="padding: 0; margin: 0;">
                  <v-card-title> {{ item.menuTitle }} </v-card-title>
                  <v-card-subtitle>
                    {{ item.menuPrice }}원
                  </v-card-subtitle>
                  <v-btn
                    class="ma-2"
                    color="blue-lighten-2"
                    icon="mdi-thumb-up"
                    variant="text"
                    v-size="small"
                  ></v-btn>{{item.heart}}
                  </div>
                </v-card>
        </v-col>
      </v-row>
    </v-col>
    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
  </v-window-item>
  <v-window-item value="멍게/해삼류" >
    <v-col cols="12" class="my-3 py-5">
      <v-row>
        <v-col cols="12" md="3" style="height: auto;"
               v-for="item in showItem"
               v-bind:key="item"
               @click="gotomenuDetail(item)">
         <v-card class="mx-auto mt-3 card" width="250px" height="250px" style=" width: 75%; height: inherit; position: relative; ">
                  <img
                    style="width: 100%; height: 200px; object-fit: cover;"
                    :src="item.menuImg"
                  />
                  <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
                  <!--                        <v-card-title>{{item.menuNo}}</v-card-title>-->
                  <div class="card-overlay" style="padding: 0; margin: 0;">
                  <v-card-title> {{ item.menuTitle }} </v-card-title>
                  <v-card-subtitle>
                    {{ item.menuPrice }}원
                  </v-card-subtitle>
                  <v-btn
                    class="ma-2"
                    color="blue-lighten-2"
                    icon="mdi-thumb-up"
                    variant="text"
                    v-size="small"
                  ></v-btn>{{item.heart}}
                  </div>
                </v-card>
        </v-col>
      </v-row>
    </v-col>
    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
  </v-window-item>
  <v-window-item value="낙지/문어류" >
    <v-col cols="12" class="my-3 py-5">
      <v-row>
        <v-col cols="12" md="3" style="height: auto;"
               v-for="item in showItem"
               v-bind:key="item"
               @click="gotomenuDetail(item)">
         <v-card class="mx-auto mt-3 card" width="250px" height="250px" style=" width: 75%; height: inherit; position: relative; ">
                  <img
                    style="width: 100%; height: 200px; object-fit: cover;"
                    :src="item.menuImg"
                  />
                  <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
                  <!--                        <v-card-title>{{item.menuNo}}</v-card-title>-->
                  <div class="card-overlay" style="padding: 0; margin: 0;">
                  <v-card-title> {{ item.menuTitle }} </v-card-title>
                  <v-card-subtitle>
                    {{ item.menuPrice }}원
                  </v-card-subtitle>
                  <v-btn
                    class="ma-2"
                    color="blue-lighten-2"
                    icon="mdi-thumb-up"
                    variant="text"
                    v-size="small"
                  ></v-btn>{{item.heart}}
                  </div>
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

const props = defineProps({
  category: String
})

watch(() => props.category, async (newCategory) => {
  if (newCategory) {
    try{
    const res = await axios.get(`/api/menuList`);
    const categorys = res.data.filter(menu => menu.menuCate === newCategory);
    menu.value = categorys;
    menu.value.forEach( async (item) => {

      item.menuImg = JSON.parse(item.menuImg)

      await axios.get(`/api/getMImage/${item.menuImg}`)
        .then((res) => {

          item.menuImg = res.data

        });
    });
  }catch (error) {
      console.error("에러발생" + error);
    }
  }
}, { immediate: true });

watch(menu, (newValue, oldValue) => {
  if(newValue != oldValue){
    currentPage.value = 1
  }
});

onMounted(() => {
  console.log(props.category);
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