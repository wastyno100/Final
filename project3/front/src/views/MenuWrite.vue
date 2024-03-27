<template>
  <v-main>
    <v-container class="mx-auto">
      <v-card height="1000" elevation="3" class="mt-4">
        <v-sheet class="mx-auto" width="1000">
          <v-col>
          <v-row>
            <v-select
              v-model="menuCate"
              label="카테고리"
              :items="['생선', '게/새우류', '조개/전복류', '멍게/해삼류', '낙지/문어류', '김/미역류']"
            ></v-select
            ></v-row>
          </v-col>
          <v-row justify="center">
            <v-col cols="6" class="ma-3">
              <v-text-field v-model="menuTitle" label="제목" />
            </v-col>
            <v-col cols="2" class="ma-3">
              <v-text-field v-model="menuPrice" label="가격" />
            </v-col>
            <v-col cols="6" class="ma-3">
              <v-text-field v-model="business" label="사업자" />
            </v-col>
            <v-col cols="2" class="ma-3">
              <v-text-field v-model="today" label="작성일" readonly></v-text-field>
            </v-col>
          </v-row>

<!--          <v-row v-if="boardCate == '이벤트'" justify="center">-->
<!--            <v-col cols="3"><v-text-field v-model="eventStart" label="이벤트 시작일" /></v-col>-->
<!--            <v-col cols="3"><v-text-field v-model="eventEnd" label="이벤트 종료일" /></v-col>-->
<!--          </v-row>-->

          <v-row justify="center">
            <v-col cols="11" class="ma-3">
              <v-textarea label="내용" v-model="menuContent"></v-textarea>
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
        </v-sheet>
      </v-card>
    </v-container>
    <v-btn @click="reqAxios" text="작성" />
  </v-main>
</template>
<script setup>
import axios from 'axios';
import { ref } from 'vue'
import { useRouter } from 'vue-router';


const router = useRouter()

const file = ref([])
const src = ref([])

// 현재 날짜
const date = new Date()
const today = `${date.getFullYear()}-${('0' + (date.getMonth() + 1)).slice(-2)}-${('0' + date.getDate()).slice(-2)}`
// const regDate = Date.now();

const menuTitle = ref();
const menuContent = ref();
const menuCate = ref();
const menuPrice = ref();
const business = ref();

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
const reqAxios = async () => {
  const formData = new FormData();

  formData.append('menuTitle', menuTitle.value);
  formData.append('menuPrice', menuPrice.value);
  formData.append('menuContent', menuContent.value);
  formData.append('menuCate', menuCate.value);
  formData.append('business', business.value);
  // formData.append('regDate', regDate);

  file.value.forEach((item) => {
    formData.append('uploadImg', item);
  })

  await axios.post("/api/menuWrite", formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  }).then((res) => {
    if(res.status == 200) router.push("/menuList")
    else alert("게시글 작성을 실패 했습니다.")
  })
}

</script>

<style scoped>
</style>
