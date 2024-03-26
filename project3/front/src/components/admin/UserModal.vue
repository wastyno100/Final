<script setup>
import axios from 'axios'
import { ref } from 'vue'

const props = defineProps(['userData'])
const userData = props.userData

const emit = defineEmits(['reload'])
const dialog = ref(false)
const leave1 = ref("")
const leave2 = ref("")

const reqAxios = async() => {
  if(leave1.value && !leave2.value) return alert("탈퇴 확인 체크가 필요합니다.")

  if(leave1.value && leave2.value){
    axios.delete(`/api/deleteUser?userNo=${userData.userNo}`)
    .then(() => {
      alert("탈퇴가 완료 되었습니다.")
      dialog.value = false
      reload()
    })
  }else {
    axios.put('/api/updateRole', { userNo: userData.userNo, role: userData.role})
    .then(() => {
      alert("수정이 완료 되었습니다.")
      dialog.value = false
      reload()
    })
  }
}

const reload = () => emit('reload')

</script>

<template>
  <div class="text-center">
    <v-btn @click="dialog = true" style="height: 30px; font-size: 12px;" class="ma-1"> 편집 </v-btn>

    <v-dialog v-model="dialog" width="auto">
      <v-card max-width="400" prepend-icon="mdi-update" title="유저 정보">
        <v-card-text>
          <v-row class="mt-2">
            <v-col style="font-size: 15px">
              <div
                class="font-weight-bold mb-1 text-center"
                style="font-size: 10px; border-bottom: 1px solid gray; width: 35px"
              >
                ID
              </div>
              <div class="pa-1">{{ userData.id }}</div>
            </v-col>
          </v-row>
          <v-divider/>
          <v-row class="mt-3">
            <v-col>
              <div
                class="font-weight-bold mb-1 text-center"
                style="font-size: 10px; border-bottom: 1px solid gray; width: 35px"
              >
                이름
              </div>
              {{ userData.username }}
            </v-col>
          </v-row>
          <v-divider/>
          <v-row class="mt-3">
            <v-col>
              <div
                class="font-weight-bold mb-1 text-center"
                style="font-size: 10px; border-bottom: 1px solid gray; width: 35px"
              >
                이메일
              </div>
              {{ userData.email }}<br>
              <div style="font-size: 10px; margin-top: 5px;">
                <span class="font-weight-bold text-center">메일 수신 여부: </span>{{ userData.emailGet ? "받음" : "안받음" }}
              </div>
            </v-col>
          </v-row>
          <v-divider/>
          <v-row class="mt-3">
            <v-col cols="6">
              <div
                class="font-weight-bold mb-1 text-center"
                style="font-size: 10px; border-bottom: 1px solid gray; width: 35px"
              >
                권한
              </div>
              <v-select
                v-model="userData.role"
                :items="['일반', '사업자', '관리자']"
                :disabled="editDisabled"
                density="compact"
                variant="underlined"
                dense
                >
              </v-select>
            </v-col>
          </v-row>

          <v-row class="mt-3">
            <v-col>
              <div
                class="font-weight-bold mb-1 text-center"
                style="font-size: 10px; border-bottom: 1px solid gray; width: 35px"
              >
                주소
              </div>
              {{ userData.zipcode }}
              {{ userData.address1 }}
              {{ userData.address2 }}
            </v-col>
          </v-row>
          <v-divider/>
          <v-row class="mt-3">
            <v-col>
              <label for="ck1">
                <input type="checkbox" v-model="leave1" value="탈퇴" id="ck1" style="margin-right: 7px;">탈퇴
              </label>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <label for="ck2">
                <input type="checkbox" v-model="leave2" value="탈퇴 확인" id="ck2" style="margin-right: 7px;" :disabled="!leave1" >탈퇴 확인
              </label>
            </v-col>
          </v-row>
          <v-row>
            <span v-if="leave1" style="color: red; font-size: 12px;">(주의) 회원이 탈퇴 됩니다.</span>
          </v-row>

          <v-row justify="center">
            <v-col cols="3">
              <v-btn @click="reqAxios">수정</v-btn>
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