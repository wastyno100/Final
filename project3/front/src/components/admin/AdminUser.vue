<script setup>
import axios from 'axios'
import { computed, ref } from 'vue'

// 나중에 서버로 데이터 요청하자
// const result = axios.get('/api/')

// 일단 임시
const testData = [
  { userNo: 1, name: '박태석1', id: 'qkrxotjr1', rols: 1 },
  { userNo: 2, name: '박태석2', id: 'qkrxotjr2', rols: 1 },
  { userNo: 3, name: '박태석3', id: 'qkrxotjr3', rols: 1 },
  { userNo: 4, name: '박태석4', id: 'qkrxotjr4', rols: 2 },
  { userNo: 5, name: '박태석5', id: 'qkrxotjr5', rols: 2 },
  { userNo: 6, name: '박태석6', id: 'qkrxotjr6', rols: 2 },
  { userNo: 7, name: '박태석7', id: 'qkrxotjr7', rols: 0 },
  { userNo: 8, name: '박태석8', id: 'qkrxotjr8', rols: 0 },
  { userNo: 9, name: '박태석9', id: 'qkrxotjr9', rols: 0 },
  { userNo: 10, name: '박태석10', id: 'qkrxotjr10', rols: 0 },
  { userNo: 11, name: '박태석11', id: 'qkrxotjr11', rols: 0 },
  { userNo: 12, name: '박태석12', id: 'qkrxotjr7', rols: 0 },
  { userNo: 13, name: '박태석13', id: 'qkrxotjr8', rols: 0 },
  { userNo: 14, name: '박태석14', id: 'qkrxotjr9', rols: 0 },
  { userNo: 15, name: '박태석15', id: 'qkrxotjr10', rols: 0 },
  { userNo: 16, name: '박태석16', id: 'qkrxotjr11', rols: 0 },
  { userNo: 17, name: '박태석17', id: 'qkrxotjr7', rols: 0 },
  { userNo: 18, name: '박태석18', id: 'qkrxotjr8', rols: 0 },
  { userNo: 19, name: '박태석19', id: 'qkrxotjr9', rols: 0 },
  { userNo: 20, name: '박태석20', id: 'qkrxotjr10', rols: 0 },
  { userNo: 21, name: '박태석21', id: 'qkrxotjr11', rols: 0 },
  { userNo: 22, name: '박태석22', id: 'qkrxotjr7', rols: 0 },
  { userNo: 23, name: '박태석23', id: 'qkrxotjr8', rols: 0 },
  { userNo: 24, name: '박태석24', id: 'qkrxotjr9', rols: 0 },
  { userNo: 25, name: '박태석25', id: 'qkrxotjr10', rols: 0 },
  { userNo: 26, name: '박태석26', id: 'qkrxotjr11', rols: 0 },
]

// 권한에 이름 줌
testData.forEach((item) => {
  if (item.rols == 0) return item.rols = '일반'
  if (item.rols == 1) return item.rols = '관리자'
  if (item.rols == 2) return item.rols = '사업자'
})

// 데이터 내림차순 (공지, 이벤트 만들때 써보자)
// const testData = getData.sort((a, b) => b.userNo - a.userNo)


// 페이징 테스트용
const 현재페이지 = ref(1)
const 페이지당항목수 = 10
// 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함
const 전체페이지수 = Math.ceil(testData.length / 페이지당항목수)

// computed는 데이터가 변경을 감지하고 다시 계산을 해줌
// 1페이지 -> 0 ~ 10, 2페이지 -> 10 ~ 20
const 보여줄항목 = computed(() => {
  const start = (현재페이지.value - 1) * 페이지당항목수
  const end = start + 페이지당항목수
  // 시작값부터 마지막값까지를 잘라서 리턴
  return testData.slice(start, end)
})
const 페이지갱신 = () => {
  const start = (현재페이지.value - 1) * 페이지당항목수
  const end = start + 페이지당항목수
  보여줄항목.value = testData.slice(start, end)
}

</script>

<template>
  <v-container>
    <v-card-title class="text-center">
      <h3>유저 관리</h3>
    </v-card-title>

    <v-card-text>
      <v-text-field
        :loading="loading"
        density="dense"
        variant="outlined"
        label="검색"
        append-icon="mdi-magnify"
        hide-details
      ></v-text-field>
    </v-card-text>
    <v-card height="500" elevation="3" class="mt-4">
      <!-- 유저 리스트를 출력 -->
      <v-card-item v-for="item in 보여줄항목" v-bind:key="item" class="text-center mt-2">
        <v-row>
          <v-col>
            <input type="checkbox" class="mr-3" />
            <span>{{ item.name }}</span>
          </v-col>
          <v-col>
            <span>{{ item.id }}</span>
          </v-col>
          <v-col>
            <span>{{ item.rols }}</span>
          </v-col>
        </v-row>
      </v-card-item>
      <!-- 유저 리스트 출력 끝 -->
    </v-card> <h3>현재페이지 : <span style="color: red;">{{ 현재페이지 }}</span></h3>
    <v-pagination v-model="현재페이지" :length="전체페이지수" @input="페이지갱신"></v-pagination>
    <v-btn class="mt-4" block>버튼</v-btn>
  </v-container>
</template>