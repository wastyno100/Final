<script setup>
import axios from "axios";
import { onMounted, ref } from "vue";

// 게시글 목록에서 선택한 게시글 번호를 선물로 받음
const {dataObj} = history.state;
// 게시글 내용 넣어줄거임
const boardData = ref({})

// 게시글 번호를 서버로 보내서 해당 게시물의 데이터들만 가져오자
// 게시글 업데이트도 put을 사용해서 해버리자
const getData = async() => {
  await axios.get(`/api/noticeDetail?boardNo=${dataObj.no}`)
  .then((res) => { 
    console.log('@@@@@@@', res.data)
    boardData.value = res.data 
  })
}
onMounted(() => { getData() })
</script>

<template>
  <v-main>
    <h2>게시물 번호는 {{ dataObj.no }}번 입니다.</h2>
  </v-main>
</template>

<style scoped>
</style>