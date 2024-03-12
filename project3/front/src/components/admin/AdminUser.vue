<script setup>
import axios from 'axios'
import { computed, onMounted, ref } from 'vue'

// 데이터를 담자
const testData = ref([])

// 페이징용
const currentPage = ref(1)
const pageGroup = 10
const allPage = computed(() => { return Math.ceil(testData.value.length / pageGroup) }) // 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함

// 검색용
const searchText = ref("")


// 데이터를 가져옴
const getData = async () => {
  const res = await axios.get('/api/adminUser')
  testData.value = res.data;

  // 권한에 이름 줌
  testData.value.forEach((item) => {
    if (item.roles == 0) return item.roles = '일반'
    if (item.roles == 1) return item.roles = '사업자'
    if (item.roles == 2) return item.roles = '관리자'
  })
  console.log('@@@@@@@@', testData.value)
}

// DOM 불러와진 후에 렌더링 하자
onMounted(() => {
  getData()
})

// computed는 데이터가 변경을 감지하고 다시 계산을 해줌
// 데이터를 수에 맞게 잘라서 보여주는용
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
  if(searchText.value) {
    return testData.value.filter((item) => {
      return (
              item.name.includes(searchText.value) 
              || item.id.includes(searchText.value) 
              || item.roles.includes(searchText.value)
              )
    })
  }
  else return ""
})


</script>

<template>
  <v-container>
    <v-card-title class="text-center">
      <h3>유저 관리</h3>
    </v-card-title>
    <v-card-text>
      <v-row justify="center">
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
      </v-row>
    </v-card-text>
    <v-card height="500" elevation="3" class="mt-4">
      <!-- 유저 리스트를 출력 -->
      <v-card-item v-for="item in showItem" v-bind:key="item" class="text-center mt-2">
        <v-row>
          <v-col>
            <input type="checkbox" class="mr-3" />
            <span>{{ item.name }}</span>
          </v-col>
          <v-col>
            <span>{{ item.id }}</span>
          </v-col>
          <v-col>
            <span>{{ item.roles }}</span>
          </v-col>
        </v-row>
      </v-card-item>
      <!-- 유저 리스트 출력 끝 -->
    </v-card>
    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate"></v-pagination>
    <v-row justify="center"><v-col cols="3"><v-btn class="mt-4" block>버튼</v-btn></v-col></v-row>
  </v-container>
</template>