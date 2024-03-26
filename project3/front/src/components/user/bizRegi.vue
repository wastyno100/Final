<script setup>
import axios from 'axios';
import { ref } from 'vue';
//import { useStore } from 'vuex';

//const store = useStore();

const b_no = ref('');
const tax_type = ref('');
const b_stt = ref('');
const utcc_yn = ref('');
const result = ref(null);
const serviceKey = "AvdlHoQ7nhTZ6YS3vZNtOJXgv5%2Bncf5d%2FD%2BH9tOxDnnatY4cooAT9Jo2D16aEoyeUgF1INl3kYUJxjFNCRTq%2FQ%3D%3D";
const baseUrl = "https://api.odcloud.kr/api/nts-businessman/v1/status";
const isBtnDisabled = ref(false);
const isBtnRegi =ref(false);
const bizName =ref('');
const storeName =ref('');
const bizZipcode =ref('');
const bizAddress1 =ref('');
const bizAddress2 =ref('');

async function bizRegi(){
  try {
       await axios.post(`/api/bizRegi`, {
        bNo: b_no.value,
        storeName: storeName.value,
        bizName: bizName.value,
        texType: tax_type.value,
        bStt: b_stt.value,
        utccYn: utcc_yn.value,
        bizZipcode: bizZipcode.value,
        bizAddress1: bizAddress1.value,
        bizAddress2: bizAddress2.value,
//        userNo: store.state.loginStatus.userNo
      }).then((res)=> {
        console.log(res.data)
      })
    }catch (error) {
            console.error("요청 실패:", error);
            alert("요청 실패");
        }
}
async function registerBiz() {
  // 입력된 사업자 번호를 가져와서 올바른 형식으로 변환
  const formattedBizNo = b_no.value.trim();
  
  // API 요청에 필요한 JSON 데이터 형식으로 구성
  const data = { b_no: [formattedBizNo] };

  try {
    // API 요청
    const response = await axios.post(
      `${baseUrl}?serviceKey=${serviceKey}&returnType=JSON`,
      data,
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    );
    
    // API 응답 결과를 결과 변수에 할당
    result.value = response.data;
    // tax_type 값을 추출하여 할당
    if (response.data.status_code === "OK" && response.data.data.length > 0) {
      const data = response.data.data[0];
      tax_type.value = data.tax_type || '';
      b_stt.value = data.b_stt || '';
      utcc_yn.value = data.utcc_yn || '';
    } else {
      tax_type.value = ''; // 데이터가 없을 경우 빈 문자열로 처리
      b_stt.value = '';
      utcc_yn.value = '';
    }
    // 사업자 확인 버튼 활성화 또는 비활성화

    // await checkBtnStatus();
  } catch (error) {
    // API 요청 실패 시 오류 메시지를 결과 변수에 할당하여 표시
    result.value = error.response ? error.response.data : error.message;
  }

  
}
// async function checkBtnStatus() {
//     isBtnDisabled.value = b_stt.value === '계속사업자' &&
//         tax_type.value !== '국세청에 등록되지 않은 사업자등록번호입니다' &&
//         utcc_yn.value === 'N';

    

//     if (!isBtnDisabled.value) {
//         // 등록하기 버튼 활성화 여부 계산
//         isBtnRegi.value = bizName.value.trim() !== '' &&
//             storeName.value.trim() !== '' &&
//             zipcode.value.trim() !== '' &&
//             address1.value.trim() !== '' 
//     }
// }

//우편번호 api

//해당 api의 팝업창을 띄우기 위해 설정한 코드
const btnZipcode = () => {
   document.getElementById("btnZipcode").addEventListener("click", findZipcode);
}

//우편번호 api를 가져오기 위한 코드
const postcode = () => {
  return new Promise((resolve) => {
    const script = document.createElement('script');
    script.src = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js';
    script.onload = resolve;
    document.head.appendChild(script);
  });
  
};

async function initialize() {
  await postcode(); // 스크립트가 로드될 때까지 대기
  btnZipcode(); // 스크립트가 로드된 후에 btnZipcode 함수 호출
}

initialize(); // 초기화 함수 호출

//실제 우편 api 내용
async function findZipcode() { 
    try {
        await new Promise((resolve) => {
            new window.daum.Postcode({
                oncomplete: function(data) {
                    var addr = ''; // 주소 변수
                    var extraAddr = ''; // 참고항목 변수

                    // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.roadAddress;
                    }

                    // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                    if (data.userSelectedType === 'R') {
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                            extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if (data.buildingName !== '' && data.apartment === 'Y') {
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if (extraAddr !== '') {
                            extraAddr = ' (' + extraAddr + ')';
                        }
                        addr += extraAddr;
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    bizZipcode.value = data.zonecode; // 우편번호 필드에 값 할당
                    bizAddress1.value = addr; // 주소 필드에 값 할당
                    document.querySelector("#bizAddress2").focus(); // 상세주소 필드로 포커스 이동

                    resolve(); // Promise를 해결하여 우편번호 조회가 완료되었음을 알림
                }
            }).open();
        });
    } catch (error) {
        console.error("우편번호 조회 실패:", error);
        alert("우편번호 조회에 실패했습니다.");
    }
}

</script>


<template>
  <v-container>
    <v-row>
      <v-col>
        <v-form>
          <v-row>
            <v-col class="text-center">
              <v-card-title>
                <h2>사업자등록</h2>
              </v-card-title>
            </v-col> 
          </v-row>

          <v-row>
            <v-col>
              <v-card-title>
                <h5>사업자번호</h5>
              </v-card-title>
              <v-text-field
                v-model="b_no"
                label="사업자 번호를 입력해 주세요"
                maxlength="10">                                
              </v-text-field>
            </v-col>
            <v-col>
              <v-card-title class="mb-10">
                <v-spacer></v-spacer>
              </v-card-title> 
              <v-btn @click="registerBiz" :disabled="isBtnDisabled">사업자 확인</v-btn>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8 offset -1">
              <v-text-field
              v-model="tax_type" 
              label="납세자 상태">
              </v-text-field>  
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="5 offset -1">
              <v-text-field
                v-model="b_stt" 
                label="과세 유형">
              </v-text-field>  
            </v-col>

            <v-col cols="5 offset +1">
              <v-text-field
                v-model="utcc_yn" 
                label="폐업여부">
              </v-text-field>  
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="5 offset -1">
              <v-text-field
              v-model="storeName"
              label="상호명">
            </v-text-field>
            </v-col>

            <v-col cols="5 offset -1">
              <v-text-field
              v-model="bizName"
              label="사업자명">
            </v-text-field>
            </v-col>
          </v-row>

          <v-row>
              <v-col cols="5 offset -1">
                <v-card-text>
                  <h5>사업자 주소</h5>
                </v-card-text>  
              <v-text-field
              v-model="bizZipcode"
              label="우편번호">
            </v-text-field>
            </v-col>
            <v-col cols="4">
              <v-card-title class="mb-11">
                <v-spacer></v-spacer>
              </v-card-title> 
                <v-btn type="button" color="primary" id="btnZipcode" @click="btnZipcode">우편번호 찾기</v-btn>
            </v-col>
          </v-row>

          <v-row>
              <v-col cols="5 offset -1">
              <v-text-field
              v-model="bizAddress1"
              label="주소">
            </v-text-field>
            </v-col>
            <v-col cols="5 offset +1">
              <v-text-field
              v-model="bizAddress2"
              label="상세주소"
              id="bizAddress2">
            </v-text-field>
          </v-col>
          </v-row>
        </v-form>
        
        <v-row>
          <v-col cols="5 offset-1" class="text-center">
            <v-btn type="submit" @click="bizRegi" :disabled="isBtnRegi">등록하기</v-btn>
          </v-col>
          <v-col cols="4 offset+4" class="text-center">
            <v-btn>마이페이지</v-btn>
          </v-col>  
        </v-row>
        
      </v-col>
    </v-row>            
  </v-container>
</template>
