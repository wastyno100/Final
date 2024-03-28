

<script setup>
import { ref } from 'vue';
import axios from "axios";
import { useRouter } from 'vue-router';
import { onMounted } from 'vue';


const router = useRouter();

const name = ref('');
const userId = ref('');
const pass = ref('');
const pass2 = ref('');
const zipcode = ref('');
const address1 = ref('');
const address2= ref('');
const emailId = ref('');
const emailDomain = ref('');
const phone1 = ref('');
const phone2 = ref('');
const phone3 = ref('');
const emailGet = ref('false');

const isFormValid = ref(false); //폼 유효성 상태
const passwordMatchErrorMessages = ref([]);
const passwordMatchSuccessMessages = ref([]);
const successMessage = ref('');
const errorMessage = ref('');
const modal = ref(false);


// 입력 필드 값 변경 시 폼 유효성 검사 함수
// function joinFormFieldsCheck() {
//     const requiredFields = [
//         { field: name, label: '이름' },
//         { field: userId, label: '아이디' },
//         { field: pass, label: '비밀번호' },
//         { field: pass2, label: '비밀번호확인' },
//         { field: zipcode, label: '우편번호' },
//         { field: address1, label: '자택주소' },
//         { field: address2, label: '상세주소' },
//         { field: emailId, label: '이메일 아이디' },
//         { field: emailDomain, label: '이메일 도메인' },
//     ];

//     for (const fieldData of requiredFields) {
//         if (fieldData.field.value.trim() === '') {
//             alert(`${fieldData.label} 칸이 입력되지 않았습니다.`);
//             isFormValid.value = false; // 폼 유효성 상태를 false로 설정
//             return false;
//         }
//     }

//     // 비밀번호와 비밀번호 확인 필드가 일치하는지 확인
//     if (pass.value !== pass2.value) {
//         alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
//         isFormValid.value = false; // 폼 유효성 상태를 false로 설정
//         return false;
//     }

//     // 모든 필드의 유효성 검사가 통과되면 폼 유효성 상태를 true로 설정
//     isFormValid.value = true;
//     return true;
// }

function validateForm() {
    // 필수 필드를 확인하고 유효성을 설정.
    isFormValid.value = name.value.trim() !== '' &&
                        userId.value.trim() !== '' &&
                        pass.value.trim() !== '' &&
                        pass2.value.trim() !== '' &&
                        zipcode.value.trim() !== '' &&
                        address1.value.trim() !== '' &&
                        address2.value.trim() !== '' &&
                        emailId.value.trim() !== '' &&
                        emailDomain.value.trim() !== '' &&
                        pass.value === pass2.value; // 비밀번호 확인
}


async function joinFormCheck() {
    validateForm();
    validatePassword();
    if (isFormValid.value) { // 폼 유효성 검사
        try {
            await axios.post(`/api/member`, {
                username: name.value,
                id: userId.value,
                pass: pass.value,
                zipcode: zipcode.value,
                address1: address1.value,
                address2: address2.value,
                emailId: emailId.value,
                emailDomain: emailDomain.value,
                phone1: phone1.value,
                phone2: phone2.value,
                phone3: phone3.value,
                emailGet: emailGet.value,
            }).then((res) => {
                console.log(res.data);
                alert("회원가입이 성공적으로 완료되었습니다.");
                router.push('/login');
            });

            isFormValid.value = true;

        } catch (error) {
            console.error("회원가입 요청 실패:", error);
            alert("회원가입 요청 실패");
        }
    } else {
        // 폼이 유효하지 않은 경우 서버로의 제출을 막는다
        alert("다시 작성해주세요.");
        isFormValid.value = false;
    }
}

async function btnCheckId() {
    try {
        const response = await axios.get(`/api/checkId?id=${userId.value}`);
        if (response.data) {
            errorMessage.value = '이미 사용 중인 아이디입니다.';
            successMessage.value = '';
        } else {
            errorMessage.value = '';
            successMessage.value = '사용 가능한 아이디입니다.';
            setTimeout(() => {
                closeModal();
            }, 500);     
        }
    } catch (error) {
        console.error('중복 확인 실패:', error);
        errorMessage.value = '중복 확인 중 오류가 발생했습니다.';
        successMessage.value = '';
    }
  
}

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
                    zipcode.value = data.zonecode; // 우편번호 필드에 값 할당
                    address1.value = addr; // 주소 필드에 값 할당
                    document.querySelector("#address2").focus(); // 상세주소 필드로 포커스 이동

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
    const userId = document.getElementById("userId");
    const pass1 = document.getElementById("pass");
    const pass2 = document.getElementById("pass2");
    const emailId = document.getElementById("emailId");
    // const customDomain = document.getElementById("customDomain");
    
    pass1.addEventListener("keyup", inputCharReplace);
    pass2.addEventListener("keyup", inputCharReplace);
    pass2.addEventListener("keyup", validatePassword);
    userId.addEventListener("keyup", inputCharReplace);
    emailId.addEventListener("keyup", inputCharReplace);
    // customDomain.addEventListener("keyup", inputEmailDomainReplace);
  };
  initializePage();
});

function btnHome(){
    router.push("/")
}

//회원가입 시 유효성 검사
const checkUserId = () => {
  if (userId.value.length === 0) {
    alert("아이디를 입력해주세요.\n아이디는 5자 이상 15자 이하로 작성해주세요.");
    return false;
  } else if (userId.value.length < 5) {
       userId.value = '';
    alert("아이디는 5자 이상으로 입력해 주세요.");
    return false;
  }
  openModal() 
}
const inputCharReplace = (event) => {
  const regExp = /[^A-Za-z0-9]/gi;
  const inputElement = event.target;
  
  if (regExp.test(inputElement.value)) {
    alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
    inputElement.value = inputElement.value.replace(regExp, "");
  }
  
}

// 비밀번호 일치 여부를 확인하는 메서드
function validatePassword() {
    if (pass.value !== pass2.value) {
        passwordMatchErrorMessages.value = ['비밀번호가 일치하지 않습니다.'];
        passwordMatchSuccessMessages.value = [];
        isFormValid.value = false;
    } else {
        passwordMatchErrorMessages.value = [];
        passwordMatchSuccessMessages.value = ['비밀번호가 일치합니다.'];
        // successMessage.value = '비밀번호가 일치합니다.'; 
        isFormValid.value = true;
    }
}

// const inputEmailDomainReplace = (event) => {
//   const regExp = /[^A-Za-z0-9.]/gi;
//   const inputElement = event.target;
  
//   if (regExp.test(inputElement.value)) {
//     alert("영문 대소문자, 숫자, 점(.)만 입력할 수 있습니다.");
//     inputElement.value = inputElement.value.replace(regExp, "");
//   }
// }   

function closeModal() {
    modal.value = false;
    errorMessage.value = '';
}

function openModal() {
    modal.value = true;
}


</script>

<template>
    <v-main>
        
        <v-sheet width="600" class="mx-auto my-2">
            <v-container>
                <v-row>
                    <v-col>
                        <v-row>
                            <v-col class="text-center">
                                    <h1>회원가입</h1>
                            </v-col>
                        </v-row>
                        <v-form id="joinForm" name="joinForm">
                            <v-row>
                                <v-col cols="4 offset+4">
                                    <v-text-field
                                    v-model="name"
                                    name="name"
                                    label="이름"
                                    placeholder="이름을 입력해주세요."
                                    id="name"
                                    maxlength="10"
                                    >
                                    </v-text-field>
                                </v-col>
                                <v-col cols="4">
                                    <v-text-field
                                    v-model="userId"
                                    name="id"
                                    label="아이디"
                                    placeholder="사용할 아이디를 입력해주세요."
                                    id="userId"
                                    maxlength="15"
                                    >
                                    </v-text-field>
                                </v-col>
                                <v-col cols="4">
                                    <v-btn color="primary" id="btnOverlapId" @click="checkUserId">중복확인</v-btn>
                                </v-col>
                            </v-row><!-- 아이디 끝 라인 -->

                            <v-row>
                                <v-col cols="6">
                                    <v-text-field
                                    v-model="pass"
                                    name="pass"
                                    label="비밀번호"
                                    placeholder="사용할 비밀번호를 입력해주세요"
                                    type="password"
                                    id="pass"
                                    maxlength="15">
                                    </v-text-field>
                                </v-col>

                                <v-col cols="6">
                                    <v-text-field
                                    v-model="pass2"
                                    name="rePass"
                                    label="비밀번호확인"
                                    placeholder="비밀번호를 다시 입력해 주세요"
                                    type="password"
                                    id="pass2"
                                    :error-messages="passwordMatchErrorMessages"
                                    :success-messages="passwordMatchSuccessMessages">
                                </v-text-field>
                                </v-col>
                                
                            </v-row> <!-- 비밀번호 끝 라인 -->

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
                                <v-col cols="5">
                                    <v-text-field
                                    v-model="emailId"
                                    label="이메일 아이디"
                                    id="emailId">
                                    </v-text-field>
                                </v-col>
                                <!-- 중간에 @ 표시 추가해야함 -->
                                <!-- 도메인을 선택했을 때  emailDomain에 출력 후 선택하세요로 리턴
                                직접입력했을 때 reonly가 풀리면서 창입력할 수 있게끔 -->
                                <v-col cols="1 pt-5 text-center">
                                    <span>@</span>
                                </v-col>

                                <v-col cols="5" v-if="emailDomain !== '직접입력'">
                                    <v-select
                                    v-model="emailDomain"
                                    label="선택해주세요"
                                    :items="['naver.com', 'hanmail.net', 'gmail.com', '직접입력']"
                                    id="selectDomain"
                                    >
                                    </v-select>                                             
                                </v-col>
                                <v-col cols="5" v-if="emailDomain === '직접입력'">
                                    <v-text-field
                                        v-model="emailDomain"
                                        id="customDomain">
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
                            <v-row>
                                <v-col>
                                    <v-radio-group v-model="emailGet">
                                        <v-radio  label="이메일 수신 동의(소식, 광고 등)" value="true" id="emailOk" ></v-radio>
                                        <v-radio  label="이메일 수신 미동의" value="false" id="emailNo"></v-radio>
                                    </v-radio-group>
                                </v-col>
                            </v-row>

                        </v-form>

                        <v-row class="text-center">
                                <v-col>
                                    <v-btn type="submit" color="primary" id="btnJoin" @click="joinFormCheck" >가입하기</v-btn>
                                </v-col>
                                <v-col>
                                    <v-btn type="button" color="primary" @click="btnHome">홈으로</v-btn>
                                </v-col>
                            </v-row>
                    </v-col>
                </v-row>
            </v-container>
        </v-sheet>

        <!-- 아이디 중복 확인 모달 -->
        <v-dialog v-model="modal" max-width="400">
            <v-card>
                <v-card-title class="headline">아이디 중복 확인</v-card-title>
                <v-card-text>
                <v-text-field v-model="userId" label="아이디" required></v-text-field>
                <v-alert v-if="errorMessage" type="error">{{ errorMessage }}</v-alert>
                <v-alert v-if="successMessage" type="success">{{ successMessage }}</v-alert>
                </v-card-text>
                <v-card-actions>
                <v-btn color="primary" @click="btnCheckId">확인</v-btn>
                <v-btn color="error" @click="closeModal">취소</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-main>
    
 
</template>