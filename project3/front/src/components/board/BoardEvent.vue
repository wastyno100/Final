<script setup>
import axios from 'axios'
import { computed, onMounted, ref, watch } from 'vue'
import { useRouter } from 'vue-router'
import {useStore} from 'vuex'

const store = useStore()
const loginStatus = computed(() => store.state.loginStatus);
const adminCheck = computed(() => loginStatus.value.role)
const router = useRouter()
const boardData = ref([])


// 현재 날짜
const date = new Date()
const today = `${date.getFullYear()}-${('0' + (date.getMonth() + 1)).slice(-2)}-${('0' + date.getDate()).slice(-2)}`

// 페이징용
const currentPage = ref(1)
const pageGroup = 5
const allPage = computed(() => {
  return Math.ceil(boardData.value.length / pageGroup)
}) // 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함


// 검색용
const searchText = ref("")
const searchCate = ref("제목")

const getData = async () => {
  const res = await axios.get('/api/boardList?boardCate=이벤트')
  
  // 데이터 내림차순
  boardData.value = res.data.sort((a, b) => b.boardNo - a.boardNo)

  boardData.value.forEach(async (item) => {
    item.boardDate = item.boardDate.replace(/^(\d{4})-(\d{2})-(\d{2}).*/, '$1-$2-$3')

    // 1. 받은 데이터의 이미지 이름 parse
    // 2. 이미지 이름으로 요청
    if(item.boardImg != null) {
      item.boardImg = JSON.parse(item.boardImg)
      
      await axios.get(`/api/getImage/${item.boardImg[0]}`)
      .then((res) => {
      item.boardImg = res.data
    })
    }


  })
}

const showItem = computed(() => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  if(searchFind.value) return searchFind.value
  else return boardData.value.slice(start, end)
})

// 페이지가 바뀔때 해당하는 항목으로 업데이트
const pageUpdate = () => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  showItem.value = boardData.value.slice(start, end)
}

// 검색 텍스트에 맞는 항목을 필터링
const searchFind = computed(() => {
  if(searchText.value) {
    return boardData.value.filter((item) => {
      if(searchCate.value == "제목") return item.title.includes(searchText.value) 
      if(searchCate.value == "내용") return item.content.includes(searchText.value)
    })
  }
  else return ""
})

const goDetail = (item) => {
  router.push({
    name: 'detail',
    state: {dataObj: { no: item.boardNo }}
  })
}

// 현재 페이지 감지해서 세션에 저장
watch(currentPage, () => {
  sessionStorage.setItem('eventPage', currentPage.value)
})

onMounted(() => {
  getData()

  // 페이지 돌아왔을때 세션에 저장되어있는 페이지 가져옴
  currentPage.value = JSON.parse(sessionStorage.getItem('eventPage')) || 1
})
</script> 
  


<template>
  <v-container>
    <v-row>
      <v-col>
    <v-card 
    class="rounded-0 pt-5 mx-auto" 
    elevation="1" 
    height="70"
    image="https://st4.depositphotos.com/15985744/29588/v/1600/depositphotos_295885612-stock-illustration-party-people-vector-horizontal-banner.jpg"
    >
    </v-card>
    <v-card height="466" elevation="3" class="mt-5 rounded-0">
      <v-card-item style="border-bottom: 1px solid gray; background-color: whitesmoke;">
          <v-row class="text-center font-weight-bold">
            <v-col cols="2">No</v-col>
            <v-col cols="6">제목</v-col>
            <v-col cols="2">작성자</v-col>
            <v-col cols="2">작성일</v-col>
          </v-row>
        </v-card-item>

      <v-card-item
        v-for="item in showItem"
        v-bind:key="item"
        class="text-center"
        :class="{ 'v-row-hover': true }"
        style="border-bottom: 1px solid lightgray;"
        @click="goDetail(item)">
        <v-row align="center">
          <v-col cols="2">
            <span>{{ item.boardNo }}</span>
          </v-col>
          <v-col cols="2">
            <img :src="item.boardImg" :width="60" />
          </v-col>
          <v-col cols="4" class="text-start">
            <span v-if="item.eventEnd > today" style="color: blue;">(진행중) </span>
            <span v-if="item.eventEnd < today" style="color: red;">(종료) </span>
            <span>{{ item.title }}</span>
          </v-col>
          <v-col cols="2">
            <span>관리자</span>
          </v-col>
          <v-col cols="2">
            <span>{{ item.boardDate }}</span>
          </v-col>
        </v-row>
      </v-card-item>
    </v-card>
    
    <v-card class="rounded-0 pt-5 mb-5" elevation="3" height="90">
        <v-card-text>
      <v-row justify="center" class="custom-row">
        <v-col cols="2" />
        <v-col cols="2">
          <v-select
            v-model="searchCate"
            density="compact"
            variant="underlined"
            :items="['제목', '내용']"
          ></v-select>
        </v-col>
        <v-col cols="4">
          <v-text-field
          v-model="searchText"
          :loading="loading"
          density="compact"
          variant="underlined"
          label="검색"
          hide-details>
          </v-text-field>
        </v-col>
        <v-col cols="2" />
        <v-col cols="2" class="text-end">
          <v-btn v-if="adminCheck == 'admin'" @click="router.push('/board/write')">글쓰기</v-btn>
        </v-col>
      </v-row>
    </v-card-text>
    </v-card>

    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate"/>
   </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.v-row-hover:hover {
  background-color: lightgray;
  cursor: pointer;
}
.custom-row {
  height: 50px;
}
</style>