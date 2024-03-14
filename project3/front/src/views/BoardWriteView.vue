<script setup>
import axios from 'axios';
import { ref } from 'vue'
import { useRouter } from 'vue-router';


const router = useRouter()
const file = ref([])
const src = ref([])

const title = ref();
const content = ref();
const boardCate = ref();

// 현재 날짜
const date = new Date()
const today = `${date.getFullYear()}-${('0' + (date.getMonth() + 1)).slice(-2)}-${('0' + date.getDate()).slice(-2)}`

// 이미지 미리보기
const fileChange = (e) => {
  file.value = Array.from(e.target.files)
  const previews = file.value.map((file) => {
    const blob = new Blob([file], { type: file.type })
    return URL.createObjectURL(blob)
  })

  src.value = previews
}

// 게시글 저장하기 위한 요청, 이미지는 수정해야됨
const reqAxios = () => {
  axios.post("/api/boardWrite",{
    title: title.value,
    content: content.value,
    boardCate: boardCate.value,
    // boardImg: file.value[0],
  })
  .then((res) => { 
    if(res.status == 200) router.push("/board") 
    else alert("게시글 작성을 실패 했습니다.")
  })
}

</script>

<template>
  <v-main>
    <v-container>
      <v-card height="800" elevation="3" class="mt-4">
        <v-sheet class="mx-auto" width="1000">

            <v-row justify="center">
              <v-col cols="8" class="ma-3">
                <v-text-field v-model="title" label="제목" />
              </v-col>
              <v-col cols="2" class="ma-3">
                <v-text-field v-model="today" label="작성일" readonly></v-text-field>
              </v-col>
            </v-row>
            <v-row
              ><v-select
                v-model="boardCate"
                label="Select"
                :items="['공지사항', '이벤트']"
              ></v-select
            ></v-row>
            <v-row justify="center">
              <v-col cols="11" class="ma-3">
                <v-textarea label="내용" v-model="content" counter single-line></v-textarea>
              </v-col>
            </v-row>
            <v-row style="height: 200px">
              <v-col v-show="file == ''" class="text-center mt-12">이미지가 없습니다</v-col>
              <v-col class="text-center" v-for="(item, i) in file.length" :key="item"
                ><img
                  :src="src[i]"
                  style="max-width: 50%; max-height: 50%; width: auto; height: auto"
              /></v-col>
            </v-row>
            <v-row>
              <v-file-input
                label="사진"
                prepend-icon="mdi-camera"
                variant="filled"
                v-model="file"
                @change="fileChange"
                multiple
              ></v-file-input>
            </v-row>
            <v-btn @click="reqAxios" text="작성" />

        </v-sheet>
      </v-card>
    </v-container>
  </v-main>
</template>

<style scoped>
</style>
