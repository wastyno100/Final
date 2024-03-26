<template>
  <v-row>
    <v-col>
      <v-card>
        <v-card-title>리뷰쓰기</v-card-title>
        <v-card-text>
          {{ newComment }}
          <v-textarea
            v-model="newComment"
            label="리뷰를 입력하세요"
            outlined
          ></v-textarea>
          <v-file-input
                label="사진"
                prepend-icon="mdi-camera"
                variant="filled"
                v-model="file"
                @change="fileChange"
                multiple
              ></v-file-input>
            <v-col>
              <v-btn @click="addComment" color="primary" class="mb-3">리뷰쓰기</v-btn>
            </v-col>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
  
  <v-container>
    <v-card>
      <h2 class="ma-5">리뷰</h2>
      <v-card-item style="border-bottom: 1px solid gray; background-color: whitesmoke;">
          <v-row class="text-center font-weight-bold">
            <v-col cols="1">id</v-col>
            <v-col cols="8">내용</v-col>
            <v-col cols="3">작성일</v-col>
          </v-row>
        </v-card-item>

      <v-card-item
        v-for="item in showItem"
        v-bind:key="item"
        class="text-center mt-1"
        style="border-bottom: 1px solid lightgray;">
        <v-row>
          <v-col cols="1">
            <span>{{ item.id }}</span>
          </v-col>
          <v-col cols="8">
           <img :src="item.rimg" width="30%">
            <span>{{ item.replyContent }}</span>
          </v-col>
          <v-col cols="3">
            <span class="pb-5">{{ item.regDate }}</span><br><br>
            <v-btn class="mr-3" color="primary" @click="replyLike(item)">{{ item.heart }} 좋아요</v-btn>
            <v-btn color="red" @click="replyReport(item)">신고</v-btn>
          </v-col>
        </v-row>
      </v-card-item>
    </v-card>
      



    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
  </v-container>
</template>

<style scoped>

</style>
<style scoped>

</style>
<script setup>
import axios from 'axios'
import { computed, onMounted, ref, watch } from 'vue'
import { useRouter } from 'vue-router'

const props = defineProps(['dataObj'])
const menuData = props.dataObj
const newComment = ref()
const file = ref([])

const router = useRouter()
const reply = ref([])

// 페이징용
const currentPage = ref(1)
const pageGroup = 3;
const allPage = computed(() => {
  return Math.ceil(reply.value.length / pageGroup)
}) // 데이터의 개수를 페이지당 보여줄 항목수로 나누고 올림 함

const getData = async () => {
  const res = await axios.get(`/api/getReply?menuNo=${menuData.menuNo}`)
  reply.value = res.data
  getImg()
}


// 서버에 이미지 요청
const getImg = () => {
  reply.value.forEach(async (item) => {
    // 1. 받은 데이터의 이미지 이름 parse
    // 2. 이미지 이름으로 요청

    if(item.rimg != null) {
      item.rimg = JSON.parse(item.rimg)
      await axios.get(`/api/getImage/${item.rimg[0]}`)
      .then((res) => {
      item.rimg = res.data
    })
    }


  })
}

const replyReport = async (item) => {
  await axios.put(`/api/replyReport?replyNo=${item.replyNo}`)
  .then(() => {
    alert("신고완료")
  })
}

const replyLike = async (item) => {
  await axios.put(`/api/replyLike?replyNo=${item.replyNo}`)
  .then(() => {
    getData()
  })
}


const addComment = async () => {
  const userNo = JSON.parse(sessionStorage.getItem("userNo"))
  if(userNo){

    const formData = new FormData();
    
    formData.append('replyContent', newComment.value);
    formData.append('menuNo', menuData.menuNo);
    formData.append('userNo', userNo);

    file.value.forEach((item) => {
      formData.append('uploadImg', item);
    })

    await axios.post("/api/insertReply", formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    }).then((res) => { 
      if(res.status == 200) {
        newComment.value = ""
        getData()
      }else{
        alert("리뷰 작성을 실패 했습니다.")
      }
  
    })

    // await axios.post('/api/insertReply', {replyContent: newComment.value, menuNo: menuData.menuNo, userNo: userNo})
    // .then(() => {
    //   newComment.value = ""
    //   getData()
    // })
  }
  if(!userNo){
    alert("로그인 해주세요")
  }

  
}

const showItem = computed(() => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  return reply.value.slice(start, end)
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