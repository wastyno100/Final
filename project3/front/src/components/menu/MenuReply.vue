<template>
  <v-row>
    <v-col>
      <v-card>
        <v-card-title>리뷰쓰기</v-card-title>
        <v-card-text>
          <v-textarea
            v-model="newComment"
            label="리뷰를 입력하세요"
            outlined
          ></v-textarea>
        </v-card-text>
        <v-card-actions>
          <v-btn @click="addComment" color="primary">리뷰쓰기</v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
  <v-container>
      <v-card-item
        v-for="item in showItem"
        v-bind:key="item"
        class="text-center mt-1"
        :class="{ 'v-row-hover': true }"
        @click="goDetail(item)">
        <v-row>
          <v-col>
            <span>{{ item.boardNo }}</span>
          </v-col>
          <v-col>
            <span>{{ item.title }}</span>
          </v-col>
          <v-col>
            <span>{{ item.boardDate }}</span>
          </v-col>
        </v-row>
      </v-card-item>
    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
  </v-container>
</template>

<style scoped>
.v-row-hover:hover {
  background-color: lightgray;
  cursor: pointer;
}
</style>
<style scoped>

</style>
<script setup>
import axios from 'axios'
import { computed, onMounted, ref, watch } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const reply = ref([])

// 페이징용
const currentPage = ref(1)
const pageGroup = 6;
const allPage = computed(() => {
  return Math.ceil(reply.value.length / pageGroup)
}) // 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함

const getData = async () => {
  const res = await axios.get('/api/boardList?boardCate=공지사항')

  reply.value = res.data

}

const showItem = computed(() => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  if(searchFind.value) return searchFind.value
  else return reply.value.slice(start, end)
})

// 페이지가 바뀔때 해당하는 항목으로 업데이트
const pageUpdate = () => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  showItem.value = reply.value.slice(start, end)
}

// 현재 페이지 감지해서 세션에 저장
watch(currentPage, () => {
  sessionStorage.setItem('noticePage', currentPage.value)
})

onMounted(() => {
  getData()

  // 페이지 돌아왔을때 세션에 저장되어있는 페이지 가져옴
  currentPage.value = JSON.parse(sessionStorage.getItem('noticePage')) || 1
})
</script>