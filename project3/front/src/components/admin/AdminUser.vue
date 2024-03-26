<script setup>
import axios from 'axios'
import { computed, onMounted, ref } from 'vue'
import UserModal from './UserModal.vue'

// 데이터를 담자
const userData = ref([])

// 페이징용
const currentPage = ref(1)
const pageGroup = 7
const allPage = computed(() => { return Math.ceil(userData.value.length / pageGroup) }) // 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함
const originalRole = ref()

// 검색용
const searchText = ref("")

// 데이터를 가져옴
const getData = async () => {
  const res = await axios.get('/api/adminUser')
  userData.value = res.data;

  // 권한에 이름 변경
  userData.value.forEach((item) => {
    if (item.role == "user") item.role = '일반'
    if (item.role == "manager") item.role = '사업자'
    if (item.role == "admin") item.role = '관리자'
    
    // 바꾼 권한의 유저를 알수있게 기존 권한을 저장해놓음
    originalRole.value = item.role;
  })
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
  else return userData.value.slice(start, end)
})

// 페이지가 바뀔때 해당하는 항목으로 업데이트
const pageUpdate = () => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  showItem.value = userData.value.slice(start, end)
}

// 검색 텍스트에 맞는 항목을 필터링
const searchFind = computed(() => {
  if(searchText.value) {
    return userData.value.filter((item) => {
      return (
              item.username.includes(searchText.value) 
              || item.id.includes(searchText.value) 
              || item.role.includes(searchText.value)
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
          class="custom-select"
          density="compact"
          variant="underlined"
          label="검색"
          hide-details>
          </v-text-field>
        </v-col>
      </v-row>
    </v-card-text>
    <v-card height="500" elevation="3" class="mt-4">
      <v-card-item style="border-bottom: 1px solid gray; background-color: whitesmoke;">
        <v-row class="text-center font-weight-bold">
          <v-col cols="3">이름</v-col>
          <v-col cols="3">ID</v-col>
          <v-col cols="3">권한</v-col>
          <v-col cols="3">정보</v-col>
        </v-row>
      </v-card-item>
      <!-- 유저 리스트를 출력 -->
      <v-card-item 
        v-for="item in showItem" 
        v-bind:key="item" class="text-center" 
        style="font-size: 12px; border-bottom: 1px solid lightgray;">
        <v-row align="center">
          <v-col cols="3">
            <span>{{ item.username }}</span>
          </v-col>
          <v-col cols="3">
            <span>{{ item.id }}</span>
          </v-col>
          <v-col cols="3">
            <span>
              {{ item.role }}
            </span>
          </v-col>
          <v-col cols="3">
            <UserModal :userData = "item" @reload="getData"/>
          </v-col>
        </v-row>
      </v-card-item>
      <!-- 유저 리스트 출력 끝 -->
    </v-card>
    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate"></v-pagination>
  </v-container>
</template>

<style scoped>

</style>