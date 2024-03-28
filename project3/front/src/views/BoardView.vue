<script setup>
import { onMounted, ref, watch } from 'vue'
import BoardEvent from '@/components/board/BoardEvent.vue'
import BoardNotice from '@/components/board/BoardNotice.vue'

const categorys = ref(0)
const changeList = (item) => {
  categorys.value = item
  sessionStorage.setItem('categorys', categorys.value)
}
categorys.value = sessionStorage.getItem('categorys', categorys.value)

// 현재 카테고리 감지해서 세션에 저장
watch(categorys, () => {
  sessionStorage.setItem('categorys', categorys.value)
})

onMounted(() => {
  // 페이지 돌아왔을때 세션에 저장되어있는 카테고리 가져옴
  categorys.value = JSON.parse(sessionStorage.getItem('categorys'))  || 0
})
</script>

<template>
  <v-main>
    <v-container>
      <v-row justify="center" class="text-center">
        <v-card flat>
        <v-tabs align-tabs="center" color="deep-purple-accent-4" v-model="categorys">
          <v-tab @click="changeList(0)">공지사항</v-tab>
          <v-tab @click="changeList(1)">이벤트</v-tab>
        </v-tabs>
      </v-card>
      </v-row>
      <v-row justify="center">  
        <!-- 더 넓게 보이도록 수정 -->
        <v-col cols="8 offset-1">
          <v-card class="elevation-2 rounded-lg pa-8 v-text-h6" style="font-size: 13px;" max-width="800">
            <BoardNotice v-if="categorys == 0" />
            <BoardEvent v-if="categorys == 1" />
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<style scoped>
.writeBtn {
  display: flex;
}
</style>
