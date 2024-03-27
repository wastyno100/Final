

<template>
  <v-main>
  <v-container>
    <v-row>
      <v-col cols="12">
        <h1>결제 페이지</h1>
            <v-sheet width="600" class="mx-auto my-2">
              <v-container>
                <v-row>
                  <v-col>
                    <v-row>
                      <v-col class="text-center">
                      </v-col>
                    </v-row>
                    <v-form id="joinForm" name="joinForm">
                      <v-row>
                        <v-list-group>
                          <v-list>
                          </v-list>
                        </v-list-group>
                      </v-row>
                      <v-row>
                        <v-col>
                          <span>우편번호</span>
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col cols="4 offset+3">
                          <v-text-field
                            v-model="userData.zipcode"
                            name="zipcode"
                            label=""
                            id="zipcode"
                            >
                          </v-text-field>
                        </v-col>
                      </v-row>

                      <v-row>
                        <v-col cols="10">
                          <v-text-field
                            v-model="userData.address1"
                            label= ""
                            name="address1"
                            id="address1"
                            >
                          </v-text-field>
                        </v-col>
                        <v-col cols="5">
                          <v-text-field
                            v-model="userData.address2"
                            label="상세주소"
                            id="address2">
                          </v-text-field>
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col cols="6">
                          <v-text-field
                            v-model="userData.phone"
                            id="phone2"
                            maxlength="4">

                          </v-text-field>
                        </v-col>
                      </v-row>
                    </v-form>
                  </v-col>
                </v-row>
              </v-container>
            </v-sheet>
            <v-row>
        </v-row>
      </v-col>
    </v-row>
        <v-card>
          <v-card-title>결제 요약</v-card-title>
          <v-card-text>
            <span v-if="menu && menu.length > 0">
              메뉴명: {{ `${menu[0].menuTitle} 외 ${menu.length - 1}건` }}
            </span>
            <div>총 금액: {{ totalPrice }}원</div>
            <v-btn color="primary" @click="processPayment">결제하기</v-btn>
            <v-btn color="primary" @click="cancel">취소</v-btn>
          </v-card-text>
        </v-card>
  </v-container>
  </v-main>
</template>
<style scoped>

</style>
<script setup>
// 게시글 목록에서 선택한 게시글 번호를 선물로 받음
import axios from 'axios'
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'

// const { buyState } = history.state

const router = useRouter()
const menu = ref([])
const totalPrice = ref(0)
const userData = ref({})

const getData = async () => {
//   const res = await axios.post(`/api/BuyPage?menuNo=${buyState.menuNo}`)
//   menu.value = res.data[0]
//   console.log(menu.value)
  const res = await axios.get(`/api/buyUser?userNo=${menu.value[0].userNo}`)
  userData.value = res.data[0]
  console.log(userData.value)
}

const processPayment = () => {
  sessionStorage.setItem("buyData", JSON.stringify(userData.value))
  window.open("/pay", "_blank", "width=720, height=720")
}

const cancel = () => {
  router.push(-1)
}

onMounted(() => {
  menu.value = JSON.parse(sessionStorage.getItem("menu"))
  console.log(menu.value)
  totalPrice.value = JSON.parse(sessionStorage.getItem("totalPrice"))
  getData()
})
</script>