

<template>
  <v-main>
  <v-container>
    <v-row>
      <v-col cols="12">
        <h1>결제 페이지</h1>
            <v-list-group>
              <list>
                <v-list-item>
                  머라고 나오는지 보자
                  {{menu.menuNo}}
                </v-list-item>
              </list>
            </v-list-group>
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
                            v-model=zipcode
                            name="zipcode"
                            label=""
                            id="zipcode"
                            readonly disabled>
                          </v-text-field>
                        </v-col>
                        <v-col cols="4">
                          <v-btn type="button" color="primary" id="btnZipcode" @click="btnZipcode">우편번호 찾기</v-btn>
                        </v-col>
                      </v-row>

                      <v-row>
                        <v-col cols="7">
                          <v-text-field
                            v-model=address1
                            label= ""
                            name="address1"
                            id="address1"
                            readonly disabled>
                          </v-text-field>
                        </v-col>
                        <v-col>
                          <v-text-field
                            v-model="address2"
                            label="상세주소"
                            id="address2">
                          </v-text-field>
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col cols="3">
                          <v-select
                            v-model="phone1"
                            label="휴대전화"
                            id="phone1"
                            :items="['010']">
                          </v-select>
                        </v-col>
                        <v-col cols="3">
                          <v-text-field
                            v-model="phone2"
                            id="phone2"
                            maxlength="4">

                          </v-text-field>

                        </v-col>
                        <v-col cols="3">
                          <v-text-field
                            v-model="phone3"
                            id="phone3"
                            maxlength="4">

                          </v-text-field>

                        </v-col>
                      </v-row>
                    </v-form>
                  </v-col>
                </v-row>
              </v-container>
            </v-sheet>
      </v-col>
    </v-row>
        <v-card>
          <v-card-title>결제 요약</v-card-title>
          <v-card-text>
            <div>총 금액: {{ totalPrice }}원</div>
            <v-btn color="primary" @click="processPayment">결제하기</v-btn>
            <v-btn color="primary" @click="processPayment">취소</v-btn>
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

const { buyState } = history.state

const menu = ref({}) // DB



const getData = async () => {
  const res = await axios.post(`/api/BuyPage?menuNo=${buyState.menuNo}`)
  menu.value = res.data[0]
  console.log(menu.value)
}

const processPayment = () => {
  window.open("/pay", "_blank", "width=480, height=720")
}


onMounted(() => {
  console.log("구매를 진행시켜라!!!!!" + buyState);
  getData()

})
</script>