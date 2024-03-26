<script setup>
import axios from "axios";
import { computed, onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import {useStore} from 'vuex'

const store = useStore()
const loginStatus = computed(() => store.state.loginStatus)
const adminCheck = computed(() => loginStatus.value.role)


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

      <!-- 게시글 정보 -->
      <v-row justify="center">
        <v-col cols="12" sm="10" md="8">
          <v-card class="elevation-3">
            <v-card-title class="headline">{{ boardData.title }}</v-card-title>
            <v-divider></v-divider>
            <v-card-text class="body-1">
              <div class="caption mb-2"> 관리자</div>
              <div class="caption text-end pb-3" style="border-bottom: 1px solid lightgray;"> {{ boardData.boardDate }}</div>
              <v-img v-if="boardData.boardCate == '공지사항'" src="https://newhep.co.kr/wp-content/uploads/2021/11/gonggi_top.jpg" width="100%"/>
              <v-col cols="12" class="mb-5">
                <v-img v-for="(item, i) in imgFile" :src="item" :key="i" class="mb-4" width="100%" />
              </v-col>
              <div>{{ boardData.content }}</div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>

      <!-- 수정, 삭제 버튼 -->
      <v-row justify="center" class="mt-4">
        <v-col v-if="adminCheck == 'admin'" cols="12" sm="10" md="8">
          <v-btn @click="
          router.push({
            name: 'update',
            state: {dataObj: { no: boardData.boardNo }}
          })" color="primary" class="mr-2">수정</v-btn>
          <v-btn @click="delData" color="error">삭제</v-btn>
        </v-col>
        <v-col cols="8">
          <v-btn v-if="adminCheck != 'admin'" @click="router.push('/board')" color="primary" class="mr-2">목록</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<style scoped>
</style>