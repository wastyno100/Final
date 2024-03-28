<template>
  <v-main>
    <v-row justify="center">
      <v-col cols="8" class="pa-5 mt-15">
        <!-- 결제 성공 시 -->
        <section v-if="confirmed">
          <div class="result wrapper">
            <div class="box_section">
                <v-row justify="center">
                  <v-col cols="3" class="text-center">
                    <v-card class="mt-5 pb-2">
                      <h2 style="padding: 20px 0px 10px 0px">⭕ 결제 성공</h2>
                    </v-card>
                  </v-col>
                </v-row>
                <!-- <p>paymentKey = {{ this.$route.query.paymentKey }}</p>
            <p>orderId = {{ this.$route.query.orderId }}</p> -->
                <v-row justify="center">
                  <v-col cols="10">
                    <v-card class="mt-5 pa-15 elevation-5" height="600px">
                      <div
                        class="font-weight-bold mb-1 mt-5 text-center"
                        style="font-size: 25px; border-bottom: 2px solid gray; width: 100px"
                      >
                        주문 ID
                      </div>
                      <span class="pa-1" style="font-size: 20px;">{{ this.$route.query.orderId }}</span>

                      <div
                        class="font-weight-bold mb-1 mt-5 text-center"
                        style="font-size: 25px; border-bottom: 2px solid gray; width: 100px"
                      >
                        주문명
                      </div>
                      <span class="pa-1" style="font-size: 20px;">{{ orderName }}</span>

                      <div
                        class="font-weight-bold mb-1 mt-5 text-center"
                        style="font-size: 25px; border-bottom: 2px solid gray; width: 100px"
                      >
                        가격
                      </div>
                      <span class="pa-1" style="font-size: 20px;">{{ this.$route.query.amount }}원</span>
                      <br><br><br><br><br><br><br><br>
                      <v-row justify="center">
                        <v-col cols="12" class="text-center">
                          <v-btn @click="closeBtn" color="primary" style="height: 50px; width: 100px; font-size: 20px;">확인</v-btn>
                        </v-col>
                      </v-row>
                    </v-card>
                  </v-col>
                </v-row>
            </div>
          </div>
        </section>
      </v-col>
    </v-row>
  </v-main>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { confirmPayment } from '@/confirmPayment'
import axios from 'axios'
const route = useRoute()
const router = useRouter()
const confirmed = ref(false)
const orderName = sessionStorage.getItem('orderName')
const userNo = JSON.parse(sessionStorage.getItem('userNo'))

const closeBtn = async () => {
  await axios.delete(`/api/cartDeleteAll?userNo=${userNo}`).then(() => {
    router.push('/')
  })
}

onMounted(async () => {
  const requestData = {
    orderId: route.query.orderId,
    amount: route.query.amount,
    paymentKey: route.query.paymentKey
  }

  async function confirm() {
    try {
      const { response, json } = await confirmPayment(requestData)
      console.log(json)
      if (!response.ok) {
        router.push(`/fail?message=${json.message}&code=${json.code}`)
      } else {
        confirmed.value = true
      }
    } catch (error) {
      console.log(error)
    }
  }

  confirm()
})
</script>