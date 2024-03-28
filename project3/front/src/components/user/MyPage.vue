<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from "vue-router";
import { useStore } from 'vuex';

const store = useStore();
const router = useRouter();

const link = (url) => router.push(url);

const successMessage = ref('');
const errorMessage = ref('');
const modal = ref(false);

const userData = ref({ 
  name: '',
  id: '',
  phone: '',
  email: '',
  zipcode: '',
  address1: '',
  address2: '',
  pass: '',
  emailGet: '',
 
});

const oldPass = ref('');
const newPass = ref('');

async function updateUser() {
    try {
       // 비밀번호가 변경되었는지 확인
       const isPasswordChanged = newPass.value !== '';

        // 업데이트할 데이터 객체 생성
        const updatedData = {
            pass: isPasswordChanged ? newPass.value : userData.value.pass,
            phone: userData.value.phone,
            email: userData.value.email,
            zipcode: userData.value.zipcode,
            address1: userData.value.address1,
            address2: userData.value.address2,
            emailGet: userData.value.emailGet,
            userNo: store.state.loginStatus.userNo
        };

        // PUT 요청 보내기
        const response = await axios.put(`/api/mypage`, updatedData);

        // 업데이트 성공 여부 확인
        if (response.data) {
            store.dispatch('logout');
            alert('사용자 정보가 업데이트되었습니다. 다시 로그인 해주세요!');
            router.push('/login');
        } else {
            alert('사용자 정보 업데이트에 실패했습니다.');
            // 필요한 경우 실패 처리
        }
    } catch(error) {
        console.error('사용자 정보 업데이트 중 오류 발생: ', error);
        alert('사용자 정보 업데이트 중 오류가 발생했습니다.');
    }
}
async function getUserData(){
    try {
        const userId = store.state.loginStatus.userId;
        const res = await axios.get(`/api/mypage?id=${userId}`);    
        if (res.data) {
            console.log('API 응답데이터:' , res.data)
            const user = res.data[0]
            userData.value = {       
                name: user.username,
                id: user.id,
                phone: user.phone,
                email: user.email,
                address1: user.address1,
                pass : user.pass,
                address2 : user.address2,
                zipcode : user.zipcode,
                emailGet : user.emailGet
                
                
            }
            console.log('데이터 읽어오기 성공!')
        } else {
            console.error('사용자 정보를 가져오는 중 오류 발생: 응답 데이터가 비어 있습니다.');
        }

    } catch(error) {
        console.error('사용자 정보를 가져오는 중 오류 발생: ', error);
    }
}



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
                    userData.value.zipcode = data.zonecode; // 우편번호 필드에 값 할당
                    userData.value.address1 = addr; // 주소 필드에 값 할당
                    userData.value.address2 = ''; // address2 필드 초기화

                    resolve(); // Promise를 해결하여 우편번호 조회가 완료되었음을 알림
                }
            }).open();
        });
    } catch (error) {
        console.error("우편번호 조회 실패:", error);
        alert("우편번호 조회에 실패했습니다.");
    }
}

onMounted(() => {
    const initializePage = () => {
    const newPass = document.querySelector("#newPass");
    newPass.addEventListener("keyup", inputCharReplace);
  }

 
  initializePage()
  getUserData();
  initialize(); // 초기화 함수 호출
});

async function checkPassword() { 
    try {
        const userId = store.state.loginStatus.userId;
        const response = await axios.post(`/api/checkPass`, { id: userId, pass: oldPass.value });

        if (response.data.result) {
            successMessage.value = '기존 비밀번호가 일치합니다.'
            errorMessage.value = ''
            oldPass.value = ''
            setTimeout(() => {
                closeModal();
            }, 500);
    
        } else {
            successMessage.value = ''
            errorMessage.value = '기존 비밀번호가 일치하지 않습니다.'
            oldPass.value = ''
        }
    } catch(error) {
        console.error('비밀번호 확인 중 오류 발생: ', error);
        alert('비밀번호 확인 중 오류가 발생했습니다.');
    }
}

function closeModal() {
    modal.value = false;
    errorMessage.value = '';
}

function openModal() {
    modal.value = true;
}

const inputCharReplace = (event) => {
  const regExp = /[^A-Za-z0-9]/gi;
  const inputElement = event.target;
  
  if (regExp.test(inputElement.value)) {
    alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
    inputElement.value = inputElement.value.replace(regExp, "");
  }
  
}
</script>

<style>
    .text-field-center {
    margin: 0 auto; /* 좌우 여백을 자동으로 설정하여 가운데 정렬합니다. */
    text-align: center; /* 텍스트를 수평으로 가운데 정렬합니다. */
}
</style>

<template>
 
        <v-container>
            <v-row>
                <v-col>
                   <v-form> 
                        <v-row>
                            <v-col>
                                <v-card-title class="text-center">
                                    <h3>내 정보</h3>
                                </v-card-title>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="5 offset-1">
                                <v-card-title>
                                    <h5>이름</h5>
                                </v-card-title>
                                <v-text-field
                                v-model="userData.name"
                                variant="outlined"
                                readonly disabled>
                                </v-text-field>
                            </v-col>    
                            <v-col cols="5 offset+1">
                                <v-card-title>
                                    <h5>아이디</h5>
                                </v-card-title>
                                <v-text-field
                                v-model="userData.id"
                                variant="outlined"
                                readonly disabled>
                                </v-text-field>   
                            </v-col>    
                        </v-row> 
                        
                        <v-row>
                            <v-col cols="5 offset-1">
                            <v-card-title>
                                <h5>비밀번호</h5>
                            </v-card-title>  
                            <v-text-field
                            v-model="userData.pass"
                            type="password"
                            variant="outlined"
                            readonly disabled>
                            </v-text-field>   
                            </v-col>
                            <v-col cols="5 offset+1"  class="text-center"> 
                                <v-card-title class="mb-10">
                                <v-spacer></v-spacer>
                                </v-card-title> 
                                <v-btn color="primary" id="btnUpdatePass" @click="openModal" :disabled="successMessage !== ''">비밀번호확인</v-btn>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="5 offset-1">
                            <v-card-title>
                                <h5>새 비밀번호</h5>
                            </v-card-title>  
                            <v-text-field
                            v-model="newPass"
                            id="newPass"
                            type="password"
                            variant="outlined" 
                            :disabled="successMessage == ''"
                            maxlength="15">
                            </v-text-field>   
                            </v-col>
                            
                        </v-row>
                        
                        
                        <v-row>
                            <v-col cols="6 offset-1">
                                <v-card-title>
                                    <h5>휴대전화</h5>
                                </v-card-title>
                                <v-text-field
                                v-model="userData.phone"
                                variant="outlined">
                                </v-text-field>    
                            </v-col>
                        </v-row>
                        <v-row>        
                            <v-col cols="6 offset-1">
                                <v-card-title>
                                    <h5>이메일</h5>
                                </v-card-title> 
                                <v-text-field
                                v-model="userData.email"
                                variant="outlined">
                                </v-text-field>       
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="4 offset-1">
                                <v-card-title>
                                    <h5>주소</h5>
                                </v-card-title>
                                <v-text-field
                                v-model="userData.zipcode"
                                variant="outlined">
                                </v-text-field>  
                            </v-col>
                            <v-col cols="4 offset-1">
                                <v-card-title class="mb-10">
                                <v-spacer></v-spacer>
                                </v-card-title>
                                <v-btn type="button" color="primary" id="btnZipcode" @click="btnZipcode">우편번호 찾기</v-btn>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="8 offset-1">
                                <v-text-field
                                v-model="userData.address1"
                                variant="outlined">
                                </v-text-field>    
                            </v-col>    
                        </v-row>
                        <v-row>
                            <v-col cols="8 offset-1">
                                <v-text-field
                                v-model="userData.address2"
                                variant="outlined">
                                </v-text-field>    
                            </v-col>    
                        </v-row>

                        <v-row>
                            <v-col>
                                <v-radio-group v-model="userData.emailGet">
                                    <v-radio label="이메일 수신 동의(소식, 광고 등)" value="true" id="emailOk" ></v-radio>
                                    <v-radio label="이메일 수신 미동의" value="false" id="emailNo"></v-radio>
                                </v-radio-group>
                            </v-col>
                        </v-row>

                    </v-form>

                    <v-row>
                        <v-col class="text-center" cols="5 offset-1">
                            <v-btn type="submit" @click="updateUser">수정</v-btn>
                        </v-col>
                        <v-col class="text-center" cols="5 offset+1">
                            <v-btn type="button" @click="link('/')">홈으로</v-btn>
                        </v-col>
                    </v-row>

                    <v-dialog v-model="modal" persistent max-width="400px">
                        <v-card class="text-center">
                            <v-card-title>비밀번호 확인</v-card-title>
                            <v-card-text>
                                <v-text-field v-model="oldPass"
                                type="password" 
                                label="기존 비밀번호를 입력해 주세요"
                                maxlength="15"
                                style="max-width: 300px;"
                                class="text-field-center">
                                </v-text-field>
                                <v-alert v-if="errorMessage" type="error">{{ errorMessage }}</v-alert>
                                <v-alert v-if="successMessage" type="success">{{ successMessage }}</v-alert>
                            </v-card-text>
                            <v-card-actions class="justify-center">
                                <v-btn color="primary" @click="checkPassword">확인</v-btn>
                                <v-btn @click=closeModal>취소</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-col>    
            </v-row>
        </v-container>

</template>
