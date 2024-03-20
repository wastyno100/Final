<script setup>
import axios from "axios";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter()
// 게시글 목록에서 선택한 게시글 번호를 선물로 받음
const {dataObj} = history.state;
// 게시글 내용 넣기
const boardData = ref({})
// 서버에서 받은 이미지파일
const imgFile = ref([])

// 게시글 번호를 서버로 보내서 해당 게시물의 데이터들만 가져오자
// 게시글 업데이트도 put을 사용해서 해버리자
const getData = async() => {
  await axios.get(`/api/boardDetail?boardNo=${dataObj.no}`)
  .then((res) => { 
    console.log('@@@@@@@', res.data)
    boardData.value = res.data[0]
    boardData.value.boardImg = JSON.parse(res.data[0].boardImg)
  })
}

// 삭제는 관리자만 가능하게 바꾸자
const delData = async() => {
  await axios.delete(`/api/boardDelete?boardNo=${dataObj.no}`)  
  .then((res) => {
    if(res.status == 200) { 
      alert("게시글 삭제를 성공 했습니다.")
      router.push('/board')
     }
    else alert("게시글 삭제를 실패 했습니다.")
  })
}

// 서버에 이미지 요청
const getImg = () => {
  boardData.value.boardImg.forEach( async (name) => {
    await axios.get(`/api/getImage/${name}`)
    .then((res) => {
      imgFile.value.push(res.data)
    })
  });
}

onMounted(() => { 
  getData().then(() => { getImg() })
  })
</script>

<template>
  <v-main>
    <v-container>
      <img v-for="(item, i) in imgFile" :src="item" :key="i"/>

      <h3>{{ boardData.boardNo }}번</h3><br>
      제목: {{ boardData.title }}<br>
      내용: {{ boardData.content }}<br>
      카테고리: {{ boardData.boardCate }}<br>
      작성일: {{ boardData.boardDate }}<br>
      <v-btn @click="
          router.push({
            name: 'update',
            state: {dataObj: { no: boardData.boardNo }}
          })"
          text="수정" /><br>
      <v-btn @click="delData" text="삭제" />
    </v-container>
  
  </v-main>
</template>

<style scoped>
</style>