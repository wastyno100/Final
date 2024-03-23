<script setup>
import axios from 'axios'
import { computed, onMounted, ref } from 'vue'
import AdminQnaDetail from './AdminQnaDetail.vue'

const testData = ref([])

// 상세페이지로 넘기기 위함
const qnaDetail = ref()
const propsData = ref({})

// 페이징용
const currentPage = ref(1)
const pageGroup = 10
const allPage = computed(() => {
  return Math.ceil(testData.value.length / pageGroup)
}) // 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함

// 검색용
const searchCate = ref("전체")

const getData = async () => {
  const res = await axios.get('/api/qnaList')
  
  // 데이터 내림차순
  testData.value = res.data

  testData.value.forEach((item) => {
    item.qnaDate = item.qnaDate.replace(/^(\d{4})-(\d{2})-(\d{2}).*/, '$1-$2-$3')
  })
}

const showItem = computed(() => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  if(searchFind.value) return searchFind.value
  else return testData.value.slice(start, end)
})

// 페이지가 바뀔때 해당하는 항목으로 업데이트
const pageUpdate = () => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  showItem.value = testData.value.slice(start, end)
}

// 검색 텍스트에 맞는 항목을 필터링
const searchFind = computed(() => {
  console.log("작동함")
    return testData.value.filter((item) => {
      if(searchCate.value == "전체") return item
      if(searchCate.value == "미완료") return item.answer == null
      if(searchCate.value == "완료") return item.answer != null
    })

})

const goDetail = (item) => {
  propsData.value = item
  qnaDetail.value = true
}

const cancel = () => {
  qnaDetail.value = false;
}

onMounted(() => {
  getData()
})
</script>

<template>
  <template v-if="!qnaDetail">
      <v-card-title class="text-center">
        <h3>문의 답변</h3>
      </v-card-title>
      
      <v-card-text>
      <v-row justify="center">
        <v-col cols="3">
          <v-select
            v-model="searchCate"
            :items="['전체', '미완료', '완료']"
          ></v-select>
        </v-col>
      </v-row>
    </v-card-text>
    
    <v-card height="450" elevation="3" class="mt-4">

      <v-card-item style="border-bottom: 2px solid gray;">
        <v-row class="text-center font-weight-bold">
          <v-col cols="1">No</v-col>
          <v-col cols="5">제목</v-col>
          <v-col cols="2">ID</v-col>
          <v-col cols="2">작성일</v-col>
          <v-col cols="2">처리여부</v-col>
        </v-row>
      </v-card-item>

      <v-card-item
        v-for="item in showItem"
        v-bind:key="item"
        class="text-center mt-1"
        :class="{ 'v-row-hover': true }"
        @click="goDetail(item)">
        <v-row>
          <v-col cols="1">
            <span>{{ item.qnaNo }}</span>
          </v-col>
          <v-col cols="5">
            <span>{{ item.qnaTitle }}</span>
          </v-col>
          <v-col>
            <span>{{ item.id }}</span>
          </v-col>
          <v-col cols="2">
            <span>{{ item.qnaDate }}</span>
          </v-col>
          <v-col cols="2">
            <span v-if="item.answer == null" style="color: red; font-size: 10px;"> 답변 미완료</span>
            <span v-if="item.answer != null" style="color: blue; font-size: 10px;"> 답변 완료</span>
          </v-col>
        </v-row>
      </v-card-item>
    </v-card>
    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
  </template>

  <template v-if="qnaDetail">
    <AdminQnaDetail :propsData="propsData" :qnaDetail="qnaDetail" @cancel="cancel"/>
  </template>
    
</template>