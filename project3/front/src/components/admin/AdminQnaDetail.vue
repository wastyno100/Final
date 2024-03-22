<script setup>
import axios from 'axios';
import { onBeforeMount, ref } from 'vue';

  const props = defineProps(['propsData', 'qnaDetail'])
  const qnaData = props.propsData
  const qnaDetail = ref(props.qnaDetail)
  const emit = defineEmits(['cancel'])
  const roleName = ref()

  // 현재 날짜
  const date = new Date()
  const today = `${date.getFullYear()}-${('0' + (date.getMonth() + 1)).slice(-2)}-${('0' + date.getDate()).slice(-2)} ${('0' + date.getHours()).slice(-2)}:${('0' + date.getMinutes()).slice(-2)}:${('0' + date.getSeconds()).slice(-2)}`;

  if (qnaData.role == "user") roleName.value = '일반유저'
  if (qnaData.role == "manager") roleName.value = '사업자'
  if (qnaData.role == "admin") roleName.value = '관리자'

  // axios 쓰지말고 부모 페이지에서 넘겨주자

  const getQna = async() => {
    console.log(qnaData.answer)
    if(qnaData.answer == '' || qnaData.answer == null) {
      alert("답변을 입력해주세요.")
    }else{
      await axios.put('/api/qnaAnswer', {qnaNo: qnaData.qnaNo, answer: qnaData.answer, answerDate: today})
      .then(() => {
        alert("답변 작성을 완료했습니다.")
        cancel()
      })
    }
  }

  // 부모 페이지로 이벤트 보내자
  const cancel = () => emit('cancel')

  // 뒤로가기 키 누르면 페이지 이동을 막고 cancel 함수 실행시키자
  onBeforeMount(() => {
    window.history.pushState(null, null, window.location.href);
    window.onpopstate = () => {
      if(qnaDetail.value == true) {
        console.log("못감")
        cancel()
      }
      if(qnaDetail.value == false) {
        console.log("뒤로")
        window.history.back();
      }
    };
  });
</script>


<template>
  <v-container>
    <v-row>
      <v-col>
        <v-text-field v-model="qnaData.id" readonly/>
      </v-col>
      <v-col>
        <v-text-field v-model="roleName" readonly/>
      </v-col>
      <v-col>
        <v-text-field  v-model="qnaData.qnaDate" readonly/>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <v-text-field v-model="qnaData.qnaTitle" readonly />
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col>
        <v-textarea label="내용" v-model="qnaData.qnaContent" readonly />
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col>
        <v-textarea label="답변" v-model="qnaData.answer" />
      </v-col>
    </v-row>
    <v-btn @click="getQna">작성</v-btn>
    <v-btn @click="cancel">취소</v-btn>
  </v-container>
</template>
