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
  await axios.put(`/api/boardUpdate`, {
    boardNo: boardData.value.boardNo, 
    title: boardData.value.title, 
    content: boardData.value.content,
    eventStart: boardData.value.eventStart,
    eventEnd: boardData.value.eventEnd,
  })
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
    <v-container>
      <v-row justify="center">
        <v-col cols="12" sm="8" md="6">
          <v-text-field label="제목" v-model="boardData.title" outlined dense></v-text-field>
        </v-col>
      </v-row>
      <v-row v-if="boardData.boardCate == '이벤트'" justify="center">
        <v-col cols="12" sm="8" md="6">
          <v-row>
            <v-col cols="12" md="6">
              <v-text-field v-model="boardData.eventStart" label="이벤트 시작일" outlined dense></v-text-field>
            </v-col>
            <v-col cols="12" md="6">
              <v-text-field v-model="boardData.eventEnd" label="이벤트 종료일" outlined dense></v-text-field>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-col cols="12" sm="8" md="6">
          <v-textarea label="내용" v-model="boardData.content" outlined dense></v-textarea>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-col cols="12" sm="8" md="6">
          <v-btn color="primary" @click="updateData">수정</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>