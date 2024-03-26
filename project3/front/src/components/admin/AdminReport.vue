<script setup>
import axios from 'axios'
import { computed, onMounted, ref } from 'vue'
import ReportModal from './ReportModal.vue'

const replyData = ref([])

// 페이징용
const currentPage = ref(1)
const pageGroup = 10
const allPage = computed(() => {
  return Math.ceil(replyData.value.length / pageGroup)
}) // 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함

const getData = async () => {
  const res = await axios.get('/api/reportList')
  
  replyData.value = res.data
}

const showItem = computed(() => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  return replyData.value.slice(start, end)
})

// 페이지가 바뀔때 해당하는 항목으로 업데이트
const pageUpdate = () => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  showItem.value = replyData.value.slice(start, end)
}



onMounted(() => {
  getData()
})

</script>

<template>
      <v-card-title class="text-center">
        <h3>신고 처리</h3>
      </v-card-title>

    <v-card height="450" elevation="3" class="mt-4">
      <v-card-item style="border-bottom: 1px solid gray; background-color: whitesmoke;">
        <v-row class="text-center font-weight-bold">
          <v-col cols="2">글번호</v-col>
          <v-col cols="5">메뉴명</v-col>
          <v-col>작성자</v-col>
          <v-col cols="2">처리</v-col>
        </v-row>
      </v-card-item>
      
      <v-row v-show="showItem.length <= 0" justify="center" class="text-center mt-15">
          <v-col cols="6">
            <span>신고 받은 댓글이 없습니다.</span>
          </v-col>
      </v-row>
      <v-card-item
        v-for="item in showItem"
        :key="item.replyNo"
        class="text-center"
        :class="{ 'v-row-hover': true }"
        style="border-bottom: 1px solid lightgray;"
        >
        <v-row>
          <v-col cols="2">
            <span>{{ item.replyNo }}</span>
          </v-col>
          <v-col cols="5">
            <span>{{ item.menuTitle }}</span>
          </v-col>
          <v-col>
            <span>{{ item.id }}</span>
          </v-col>
          <v-col cols="2">
            <ReportModal :replyData="item" @reload="getData" />
          </v-col>
        </v-row>
      </v-card-item>
    </v-card>
    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
    
</template>