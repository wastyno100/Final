<script setup>
import { ref } from 'vue'
import BoardEvent from '@/components/board/BoardEvent.vue'
import BoardNotice from '@/components/board/BoardNotice.vue'

const categorys = ref(0)
const changeList = (item) => {
  categorys.value = item
}
</script>

<template>
  <v-main>
    <v-container>
      <v-row justify="center" class="text-center">
        <v-card flat>
        <v-tabs align-tabs="center" color="deep-purple-accent-4">
          <v-tab :value="1" @click="changeList(0)">공지사항</v-tab>
          <v-tab :value="2" @click="changeList(1)">이벤트</v-tab>
        </v-tabs>
      </v-card>
      </v-row>
      <v-row justify="center">  
        <v-col cols="8">
          <v-card class="elevation-2 rounded-lg pa-8 v-text-h6" style="font-size: 14px;" max-width="800">
            <BoardNotice v-if="categorys == 0" />
            <BoardEvent v-if="categorys == 1" />
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<style scoped>
</style>
