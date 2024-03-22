<script setup>
import { onMounted, ref, watch } from 'vue'
import AdminUser from '../components/admin/AdminUser.vue'
import AdminReport from '../components/admin/AdminReport.vue'
import AdminQna from '../components/admin/AdminQna.vue'

// onclick에서 변하는 값으로 컴포넌트가 리렌더링 되기 위해 ref사용
const listNo = ref(1)

// ref로 초기화한 값을 변경하려면 .value를 붙여야함
const changeList = (no) => listNo.value = no;

// 카테고리를 v-for로 돌리기위해 배열에 담음
const categorys = [
  { no: 1, name: '유저관리', icon: 'mdi-account-group' },
  { no: 2, name: '신고내역', icon: 'mdi-alert-circle' },
  { no: 3, name: '문의답변', icon: 'mdi-email' },
]

// 현재 페이지 감지해서 세션에 저장
watch(listNo, () => {
  sessionStorage.setItem('adminCate', listNo.value)
})

onMounted(() => {
  // 페이지 돌아왔을때 세션에 저장되어있는 페이지 가져옴
  listNo.value = JSON.parse(sessionStorage.getItem('adminCate')) || 1
})
</script>

<template>
  <v-main>
    <v-container>
      <v-row>
        <!-- 카테고리 리스트 -->
        <v-col cols="2" class="d-flex justify-end">
          <v-card class="elevation-2 rounded-lg text-center" max-width="100" height="190">
            <v-list nav dense>
              <v-list-item-group v-for="item in categorys" v-bind:key="item.no">
                <v-list-item @click="changeList(item.no)" class="mb-2" :class="{ 'selected': listNo === item.no }">
                  <v-list-item-icon><v-icon>{{ item.icon }}</v-icon></v-list-item-icon>
                  <v-list-item-title>{{ item.name }}</v-list-item-title>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-card>
        </v-col>
        <!-- 카테고리 리스트 끝 -->

        <!-- 카테고리에 맞는 페이지 출력 -->
        <v-col cols="8">
          <v-card class="elevation-2 rounded-lg pa-6" max-width="750">
            <v-card-title class="custom-card-title">관리자 페이지</v-card-title>
            <v-divider></v-divider>
            <v-card-text>
              <AdminUser v-if="listNo === 1" />
              <AdminReport v-if="listNo === 2" />
              <AdminQna v-if="listNo === 3" />
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="2"/>
        <!-- 카테고리 페이지 끝 -->
      </v-row>
    </v-container>
  </v-main>
</template>

<style scoped>
.selected {
  background-color: #e0e0e0; /* 선택된 카테고리의 배경색 변경 */
}
</style>
