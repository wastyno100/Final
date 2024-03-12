<script setup>
import axios from 'axios'
import { computed, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const testData = ref([])

// 페이징용
const currentPage = ref(1)
const pageGroup = 10
const allPage = computed(() => {
  return Math.ceil(testData.value.length / pageGroup)
}) // 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함

const getData = async () => {
  const res = await axios.get('/api/notice')

  // 데이터 내림차순 (공지, 이벤트 만들때 써보자)
  testData.value = res.data.sort((a, b) => b.boardNo - a.boardNo)
}

onMounted(() => {
  getData()
})

const showItem = computed(() => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  console.log(testData.value)
  return testData.value.slice(start, end)
})

// 페이지가 바뀔때 해당하는 항목으로 업데이트
const pageUpdate = () => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  showItem.value = testData.value.slice(start, end)
}
</script> 
  


<template>
  <v-container>
    <v-card-text>
      <v-row justify="center">
        <v-col cols="3">
          
        </v-col>
        <v-col cols="6">
          <v-text-field
          v-model="searchText"
          :loading="loading"
          density="dense"
          variant="outlined"
          label="검색"
          hide-details>
          </v-text-field>
        </v-col>
        <v-col cols="3">
          <v-btn @click="router.push('/board/write')">글쓰기</v-btn>
        </v-col>
      </v-row>
    </v-card-text>
    <v-card height="500" elevation="3" class="mt-4">
      <v-card-item
        v-for="item in showItem"
        v-bind:key="item"
        class="text-center mt-1"
        :class="{ 'v-row-hover': true }"
        @click="
          router.push({
            name: 'detail',
            state: {dataObj: { no: item.boardNo }}
          })
        ">
        <v-row>
          <v-col>
            <span>{{ item.boardNo }}</span>
          </v-col>
          <v-col>
            <span>{{ item.title }}</span>
          </v-col>
          <v-col>
            <span>관리자</span>
          </v-col>
          <v-col>
            <span>{{ item.boardDate }}</span>
          </v-col>
        </v-row>
      </v-card-item>
    </v-card>
    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
  </v-container>
</template>

<style scoped>
.v-row-hover:hover {
  background-color: lightgray;
  cursor: pointer;
}
</style>