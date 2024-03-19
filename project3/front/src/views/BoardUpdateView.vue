<script setup>

import axios from "axios"
import { onMounted, ref } from 'vue';
import { useRouter } from "vue-router";

const {dataObj} = history.state;
const boardData = ref({})
const router = useRouter()

const getData = async() => {
  await axios.get(`/api/boardDetail?boardNo=${dataObj.no}`)
  .then((res) => { 
    boardData.value = res.data[0]
  })
}

const updateData = async() => {
  await axios.put(`/api/boardUpdate`, {boardNo: boardData.value.boardNo, title: boardData.value.title, content: boardData.value.content})
  .then((res) => {
    if(res.status == 200) {
      alert("게시글 수정을 성공 했습니다.")
      router.go(-1)
    }else alert("게시글 수정을 실패 했습니다.")
  })
}

onMounted(() => { getData() })
</script>

<template>
  <v-main>
    <v-text-field label="제목" v-model="boardData.title"/>
    <v-textarea label="내용" v-model="boardData.content"/>
    <v-btn text="수정" @click="updateData"></v-btn>
  </v-main>
</template>