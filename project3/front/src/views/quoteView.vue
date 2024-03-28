<template>
  <v-main>
    <img src="/images/시세.webp" style="width: 70%; height:600px; margin-left: 220px" >
    <v-tabs v-model="item" bg-color="#004393" style="margin-top: 50px; display: flex; flex-direction: column; align-items: center;">
      <v-tab value="생선">생선</v-tab>
      <v-tab value="게/새우류" >게/새우류</v-tab>
      <v-tab value="조개/전복류">조개/전복류</v-tab>
      <v-tab value="멍게/해삼류">멍게/해삼류</v-tab>
      <v-tab value="낙지/문어류">낙지/문어류</v-tab>
    </v-tabs>
    <v-card-text>
      <v-window v-model="item">
        <v-window-item value="new">
          <v-col cols="12">
            <v-row>
              <v-col cols="12" md="3"
                     v-for="item in showItem"
                     v-bind:key="item">
                <v-card class="mx-auto mt-3 card" width="250px" height="100%" style=" width: 60%; height: inherit; position: relative; ">
                  <v-img
                    weight="200px"
                    height="100px"
                    :src="`/images/${item.qouteNo}.jpg`"
                    cover
                  ></v-img>
                  <div class="card-overlay">
                  <v-card-title> {{ item.name }} </v-card-title>
                  <v-card-subtitle>
                    <v-row>
                      <v-col :style="{ color: itemNumberColor(item.difference)}">
                         <span style="color: aliceblue">가격 : {{ item.price }}원</span> {{ item.difference !== 0 ? arrowIcon(item.difference) + ' ' + item.difference : '변동없음' }}<br>
                        <span style="color: aliceblue">등록일 : {{ item.date }}</span>
                      </v-col>
                    </v-row>
                  </v-card-subtitle>
                  </div>
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
import { computed, onMounted, ref   } from 'vue'
import axios from 'axios';
import QouteListQouteCate from '@/components/qoute/QouteListQoute.vue'
const item = ref('');
const qoute = ref([]);
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


onMounted(() => {
  getData();
  console.log(qoute.value)

  currentPage.value = JSON.parse(sessionStorage.getItem('qouteList')) || 1
});

const itemNumberColor = (difference) => {
  if (difference > 0) {
    return 'red'; // 숫자가 0보다 큰 경우 빨간색 반환
  } else if (difference < 0) {
    return 'blue'; // 숫자가 0보다 작은 경우 파란색 반환
  } else {
    return null; // 숫자가 0인 경우 null 반환
  }
};

const arrowIcon = (difference) => {
  if (difference > 0) {
    return '▲'; // 숫자가 0보다 큰 경우 up arrow 반환
  } else if (difference < 0) {
    return '▼'; // 숫자가 0보다 작은 경우 down arrow 반환
  } else {
    return ''; // 그 외의 경우에는 아무것도 반환하지 않음
  }
};
</script>

<style scoped>


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