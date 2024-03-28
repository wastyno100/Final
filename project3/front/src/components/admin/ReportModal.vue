<script setup>
import axios from 'axios'
import { onMounted, ref } from 'vue'

const props = defineProps(['replyData'])
const replyData = props.replyData
const imgFile = ref([])

const emit = defineEmits(['reload'])
const dialog = ref(false)
const radioValue = ref("Keep")


const reqAxios = async() => {
  if(radioValue.value == "Keep") {
    await axios.put(`/api/reportKeep?replyNo=${replyData.replyNo}`)

  }
  if(radioValue.value == "Delete") {
    await axios.delete(`/api/reportDelete?replyNo=${replyData.replyNo}`)
  }
  dialog.value = false
  reload()
}

// 서버에 이미지 요청
const getImg = async () => {
  replyData.rImg = JSON.parse(replyData.rimg)
  await axios.get(`/api/getImage/${replyData.rImg}`)
  .then((res) => {
    console.log(res.data)
    imgFile.value.push(res.data)
  })
}

const reload = () => emit('reload')

onMounted(() => { getImg() })
</script>

<template>
  <div class="text-center">
    <v-btn @click="dialog = true" style="height: 30px; font-size: 12px;" class="ma-1"> 처리 </v-btn>

    <v-dialog v-model="dialog" width="auto">
      <v-card max-width="400" prepend-icon="mdi-update" title="신고 처리">
        <v-card-text>
          <v-row class="mt-2">
            <v-col style="font-size: 15px">
              <div
                class="font-weight-bold mb-1 text-center"
                style="font-size: 10px; border-bottom: 1px solid gray; width: 35px"
              >
                메뉴명
              </div>
              <div class="pa-1">{{ replyData.menuTitle }}</div>
            </v-col>
          </v-row>

          <v-row>
            <v-col>
              <div
                class="font-weight-bold mb-1 text-center"
                style="font-size: 10px; border-bottom: 1px solid gray; width: 35px"
              >
                내용
              </div>
              {{ replyData.replyContent }}
            </v-col>
          </v-row>

          <v-row class="text-center">
            <v-col>
              <img
                :src="imgFile"
                style="width: 100%"
              />
            </v-col>
          </v-row>
          <v-radio-group v-model="radioValue" inline>
              <v-radio color="primary" label="유지" value="Keep"></v-radio>
              <v-radio color="red" label="삭제" value="Delete"></v-radio>
          </v-radio-group>
          
          <v-row justify="center">
            <v-col cols="3">
              <v-btn @click="reqAxios">처리</v-btn>
            </v-col>
            <v-col cols="3">
              <v-btn class="ms-auto" text="취소" @click="dialog = false"></v-btn>
            </v-col>
          </v-row>
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>