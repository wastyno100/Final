<template >

  <v-container> 
    <v-main>
    <v-row>
      <v-col>   
    <Carousel  :autoplay="2000" :wrap-around="true" >
      <Slide v-for="(image, index) in images" :key="index">
        <img :src="image.src" :alt="image.alt" style="width: 100%; height: inherit; ">
      </Slide>

      <template #addons>
        <Pagination />
      </template>
    </Carousel>
      </v-col>
    </v-row>
  </v-main>
  </v-container>

    <v-container> 
      <v-card-title class="text-center">
        <h3>오늘의 시세</h3><br>
        <h5>시세 확인하고 현명한 쇼핑하세요!</h5>
      </v-card-title>
    <v-tabs v-model="item" bg-color="#004393" style="margin-top: 50px; display: flex; flex-direction: column; align-items: center;">
      <v-tab value="생선">생선</v-tab>
      <v-tab value="게/새우류" >게/새우류</v-tab>
      <v-tab value="조개/전복류">조개/전복류</v-tab>
      <v-tab value="멍게/해삼류">멍게/해삼류</v-tab>
      <v-tab value="낙지/문어류">낙지/문어류</v-tab>
      <v-tab value="김/미역류">김/미역류</v-tab>
    </v-tabs>
    <v-card-text>
      <v-window v-model="item">
        <v-window-item value="new">
          <v-col cols="12" class="my-3 py-5">
            <v-row>
              <v-col cols="12" md="3"
                     v-for="item in showItem"
                     v-bind:key="item"
                     @click="gotoqouteDetail(item)">
                <v-card class="mx-auto mt-3 card" width="250px" height="100%" style=" width: 60%; height: inherit; position: relative; ">
                  <v-img
                    weight="200px"
                    height="100px"
                    :src="`/images/${item.qouteNo}.jpg`"
                    aspect-ratio="1.5"
                    cover
                  ></v-img>
                  <div class="card-overlay">
                    <v-card-title> {{ item.name }} </v-card-title>
                    <v-card-subtitle>
                      <v-row>
                        <v-col>
                         가격 : {{ item.price }} 원 <br>
                         등록일 : {{ item.date }}
                        </v-col>
                      </v-row>
                      
                    </v-card-subtitle>
                  </div>
                </v-card>
              </v-col>
            </v-row>
          </v-col>
          <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" style="padding: 20px;" />
        </v-window-item>
        <QouteListQouteCate :category = "item"/>
      </v-window>
    </v-card-text>

  </v-container>


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
  width: 100%; /* 이미지를 칸에 꽉 채움 */
  height: 100%; /* 이미지를 칸에 꽉 채움 */
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
import { Carousel, Pagination, Slide } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'
import { computed, onMounted, ref, watch } from 'vue'
import axios from 'axios';
import { useRouter } from 'vue-router'
import QouteListQouteCate from '@/components/qoute/QouteListQouteCate.vue'

const images = [
  { src: '/images/바지락.jpg', alt: 'Image 1' },
  { src: '/images/멍게.jpg', alt: 'Image 2' },
  { src: '/images/꼬막.jpg', alt: 'Image 3' },
  // Add more images as needed
];



const item = ref('');
const qoute = ref([]);
const router = useRouter();
const currentPage = ref('');
const pageGroup = 12
const allPage = computed(() => {
  return Math.ceil(qoute.value.length / pageGroup)
}) // 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함


const showItem = computed(() => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  return qoute.value.slice(start, end)
})

const pageUpdate = () => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  showItem.value = qoute.value.slice(start, end)
}

const getData = async () => {
  const res = await axios.get('/api/qouteList')
  qoute.value = res.data;
  console.log("리스트 잘 들어왔어")
}


function gotoqouteDetail(item){
  router.push({
    name: 'qouteDetail',
    state: { qouteObj: { qouteNo: item.qouteNo }}
  })
}

watch(currentPage, () => {
  sessionStorage.setItem('qouteList', currentPage.value)
})

onMounted(() => {
  getData();
  console.log(qoute.value)

  currentPage.value = JSON.parse(sessionStorage.getItem('qouteList')) || 1
});
</script>