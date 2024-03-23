<script setup>
import axios from 'axios'
import { computed, onMounted, ref, watchEffect } from 'vue'

// 데이터를 담자
const userData = ref([])

// 페이징용
const currentPage = ref(1)
const pageGroup = 10
const allPage = computed(() => { return Math.ceil(userData.value.length / pageGroup) }) // 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함
const originalRole = ref()

// 검색용
const searchText = ref("")

// 권한수정
const editDisabled = ref(true)

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

// 수정버튼 클릭 시
const editBtn = () => { editDisabled.value = false }
// 완료버튼 클릭 시
const updateBtn = () => { 
  editDisabled.value = true
  alert("수정완료")
}

const updateRole = () => { 
  // 권한이 바뀐것만 필터링
  const roleChange = computed(() => {
  return showItem.value.filter((user) => {
    return user.role != originalRole.value;
  });
});

  // 유저번호랑 권한을 보내자
  if (roleChange.value.length > 0) {
    axios.put('/api/updateRole', { userNo: roleChange.value[0].userNo, role: roleChange.value[0].role})
  }
}

watchEffect(() => {
  updateRole()
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
      <v-card-item style="border-bottom: 2px solid gray;">
        <v-row class="text-center font-weight-bold">
          <v-col cols="3">이름</v-col>
          <v-col cols="3">id</v-col>
          <v-col cols="3">권한</v-col>
          <v-col cols="3">정보</v-col>
        </v-row>
      </v-card-item>
      <!-- 유저 리스트를 출력 -->
      <v-card-item v-for="item in showItem" v-bind:key="item" class="text-center mt-2">
        <v-row>
          <v-col cols="3">
            <span>{{ item.username }}</span>
          </v-col>
          <v-col cols="3">
            <span>{{ item.id }}</span>
          </v-col>
          <v-col cols="3">
            <span>
              <v-select
                v-model="item.role"
                :items="['일반', '사업자', '관리자']"
                :disabled="editDisabled"
                >
              </v-select>
            </span>
          </v-col>
          <v-col cols="3">
            <v-btn text="정보"/>
          </v-col>
        </v-row>
      </v-card-item>
      <!-- 유저 리스트 출력 끝 -->
    </v-card>
    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate"></v-pagination>
    <v-row justify="center">
      <v-col cols="3">
        <v-btn v-if="editDisabled" class="mt-4" block text="수정" @click="editBtn" />
        <v-btn v-if="!editDisabled" class="mt-4" block text="완료" @click="updateBtn" />
      </v-col>
    </v-row>
  </v-container>
</template>