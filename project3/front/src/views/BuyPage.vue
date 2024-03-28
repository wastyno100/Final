

<template>
  <v-main>
  <v-container>
    <v-row>
      <v-col cols="12">
        <h1 >결제 페이지</h1>
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
                        <v-col>
                          <span>주문자명</span>
                        </v-col>
                      </v-row>
                      <v-row>
                      <v-col cols="4 offset+4">
                        <v-text-field
                          v-model="userData.username"
                          name="name"
                          label="이름"
                          placeholder="이름을 입력해주세요."
                          id="name"
                          maxlength="10"
                        >
                        </v-text-field>
                      </v-col>
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
                        <v-col cols="4">
                          <v-btn type="button" color="primary" id="btnZipcode" @click="btnZipcode">우편번호 찾기</v-btn>
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
                        <v-col>
                          <span>전화번호</span>
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
              <span v-if="menu.length == 1">메뉴명: {{ `${menu[0].menuTitle}` }}</span>
              <span v-else>메뉴명: {{ `${menu[0].menuTitle} 외 ${menu.length - 1}건` }}</span>
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
import { useStore } from 'vuex'

// const { buyState } = history.state
const router = useRouter()
const menu = ref([])
const totalPrice = ref(0)
const userData = ref({})

const getData = async () => {
//   const res = await axios.post(`/api/BuyPage?menuNo=${buyState.menuNo}`)
//   menu.value = res.data[0]
//   console.log(menu.value)
  const userNo = sessionStorage.getItem('userNo')
  const res = await axios.get(`/api/buyUser?userNo=${userNo}`)
  userData.value = res.data[0]
  console.log(userData.value)
}

const processPayment = () => {
  sessionStorage.setItem("buyData", JSON.stringify(userData.value))
  router.push("/pay")
}

const cancel = () => {
  router.push(-1)
}

// //우편번호 조회
// async function findZipcode() {
//   try {
//     await new Promise((resolve) => {
//       new window.daum.Postcode({
//         oncomplete: function(data) {
//           var addr = ''; // 주소 변수
//           var extraAddr = ''; // 참고항목 변수
//
//           // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
//           if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
//             addr = data.roadAddress;
//           } else { // 사용자가 지번 주소를 선택했을 경우(J)
//             addr = data.roadAddress;
//           }
//
//           // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
//           if (data.userSelectedType === 'R') {
//             // 법정동명이 있을 경우 추가한다. (법정리는 제외)
//             // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
//             if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
//               extraAddr += data.bname;
//             }
//             // 건물명이 있고, 공동주택일 경우 추가한다.
//             if (data.buildingName !== '' && data.apartment === 'Y') {
//               extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
//             }
//             // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
//             if (extraAddr !== '') {
//               extraAddr = ' (' + extraAddr + ')';
//             }
//             addr += extraAddr;
//           }
//
//           // 우편번호와 주소 정보를 해당 필드에 넣는다.
//           zipcode.value = data.zonecode; // 우편번호 필드에 값 할당
//           address1.value = addr; // 주소 필드에 값 할당
//           document.querySelector("#address2").focus(); // 상세주소 필드로 포커스 이동
//
//           resolve(); // Promise를 해결하여 우편번호 조회가 완료되었음을 알림
//         }
//       }).open();
//     });
//   } catch (error) {
//     console.error("우편번호 조회 실패:", error);
//     alert("우편번호 조회에 실패했습니다.");
//   }
// }

onMounted(() => {
  menu.value = JSON.parse(sessionStorage.getItem("menu"))
  console.log(menu.value)
  totalPrice.value = JSON.parse(sessionStorage.getItem("totalPrice"))
  getData()
  // findZipcode()
})
</script>