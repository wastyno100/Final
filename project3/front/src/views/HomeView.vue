<template>
  <v-main>
    <Carousel  :autoplay="2000" :wrap-around="true">
      <Slide v-for="(image, index) in images" :key="index">
        <img :src="image.src" :alt="image.alt">
      </Slide>

      <template #addons>
        <Pagination />
      </template>
    </Carousel>
    <h2> 현재 시세</h2>
    <v-tabs v-model="item" bg-color="primary" style="margin-top: 100px;">
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
          <v-col cols="12">
            <v-row>
              <v-col cols="12" md="3"
                     v-for="item in showItem"
                     v-bind:key="item"
                     @click="gotoqouteDetail(item)">
                <v-card class="mx-auto mt-3 card" width="200px" height="250px">
                  <v-img
                    weight="200px"
                    height="100px"
                    :src="`/images/${item.qouteNo}.jpg`"
                    cover
                  ></v-img>
                  <v-card-title> {{ item.qouteNo }} </v-card-title>
                  <v-card-title> {{ item.name }} </v-card-title>
                  <v-card-subtitle>
                    {{ item.price }} 원
                    {{ item.date }}
                  </v-card-subtitle>
                </v-card>
              </v-col>
            </v-row>
          </v-col>
          <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
        </v-window-item>
        <QouteListQouteCate :category = "item"/>
      </v-window>
    </v-card-text>

</v-main>
</template>
<script setup>
import { Carousel, Pagination, Slide } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'
import { computed, onMounted, ref, watch } from 'vue'
import axios from 'axios';
import { useRouter } from 'vue-router'
import QouteListQouteCate from '@/components/qoute/QouteListQouteCate.vue'

const images = [
  { src: '/images/1.jpg', alt: 'Image 1' },
  { src: '/images/1.jpg', alt: 'Image 2' },
  { src: '/images/1.jpg', alt: 'Image 3' },
  // Add more images as needed
];



const item = ref('');
const qoute = ref([]);
const router = useRouter();
const currentPage = ref(1)
const pageGroup = 8
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