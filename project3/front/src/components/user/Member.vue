

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { onMounted } from 'vue';


const router = useRouter();
// const id = ref(null);

const userId = ref('');


function joinFormCheck(){
    const joinFormFields = [
        {id:'userId', label:'아이디'},
        {id:'pass', label:'비밀번호'},
        {id:'pass2', label:'비밀번호확인'},
        {id:'zipcode', label:'우편번호'},
        {id:'address', label:'자택주소'},
        {id:'address2', label:'상세주소'},
        {id:'address', label:'자택주소'},
        {id:'emailId', label:'이메일 아이디'},
        {id:'custom', label:'직접입력'},
        {id:'mobile1', label:'휴대전화'},
        {id:'mobile2', label:'휴대전화'},
        {id:'mobile3', label:'휴대전화'},
    ];
    for(const field of joinFormFields){
    const value = document.querySelector(`#${field.id}`).value;
    if (value.trim() === '') {
        alert(`${field.label} 칸이 입력되지 않았습니다.`);
        document.querySelector(`#${field.id}`).focus();
        return;
    }

    }
   
}

// const postcode = () =>{
//     return new Promise((resolve)=>{
//         const script = document.createElement('script');
//         script.src = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js';
//         script.onload = resolve;
//         document.head.appendChild(script)
//     })
// }

// onMounted(async () => {
//     await postcode();
// })


// function btnZipcode() { 
//     new daum.Postcode({
//         oncomplete: function(data) {
//             var addr = ''; // 주소 변수
//             var extraAddr = ''; // 참고항목 변수

//             // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
//             if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
//                 addr = data.roadAddress;
//             } else { // 사용자가 지번 주소를 선택했을 경우(J)
//                 addr = data.roadAddress;
//             }

//             // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
//             if (data.userSelectedType === 'R') {
//                 // 법정동명이 있을 경우 추가한다. (법정리는 제외)
//                 // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
//                 if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
//                     extraAddr += data.bname;
//                 }
//                 // 건물명이 있고, 공동주택일 경우 추가한다.
//                 if (data.buildingName !== '' && data.apartment === 'Y') {
//                     extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
//                 }
//                 // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
//                 if (extraAddr !== '') {
//                     extraAddr = ' (' + extraAddr + ')';
//                 }
//                 addr += extraAddr;
//             }

//             // 우편번호와 주소 정보를 해당 필드에 넣는다.
//             document.querySelector("#zipcode").value = data.zonecode;
//             document.querySelector("#address1").value = addr;
//             document.querySelector("#address2").focus();
//         }
//     }).open();
// }


function btnHome(){
    router.push("/")
}

//회원가입 시 유효성 검사
const checkUserId = () => {
  if (userId.value.length === 0) {
    alert("아이디를 입력해주세요.\n아이디는 5자 이상 15자 이하로 작성해주세요.");
    return false;
  } else if (userId.value.length < 5) {
    alert("아이디는 5자 이상으로 입력해 주세요.");
    return false;
  }
  
}
const inputCharReplace = (event) => {
  const regExp = /[^A-Za-z0-9]/gi;
  const inputElement = event.target;
  
  if (regExp.test(inputElement.value)) {
    alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
    inputElement.value = inputElement.value.replace(regExp, "");
  }
}

const inputEmailDomainReplace = (event) => {
  const regExp = /[^A-Za-z0-9.@]/gi;
  const inputElement = event.target;
  
  if (regExp.test(inputElement.value)) {
    alert("영문 대소문자, 숫자, 점(.), @만 입력할 수 있습니다.");
    inputElement.value = inputElement.value.replace(regExp, "");
  }
}

onMounted(() => {
  const userId = document.getElementById("userId");
  const pass1 = document.getElementById("pass");
  const pass2 = document.getElementById("pass2");
  const emailId = document.getElementById("emailId");
  const emailDomain = document.getElementById("emailDomain")
  pass1.addEventListener("keyup", inputCharReplace);
  pass2.addEventListener("keyup", inputCharReplace);
  userId.addEventListener("keyup", inputCharReplace);
  emailId.addEventListener("keyup", inputCharReplace);
  emailDomain.addEventListener("keyup", inputEmailDomainReplace);
});
    
</script>

<template>
    <v-main>
        
        <v-sheet width="600" class="mx-auto my-2">
            <v-container>
                <v-row>
                    <v-col>
                        <v-row>
                            <v-col class="text-center">
                                    <h1>회원가입 창</h1>
                            </v-col>
                        </v-row>
                        <v-form>
                            <v-row>
                                <v-col cols="8">
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
                                    name="rePass"
                                    label="비밀번호확인"
                                    placeholder="비밀번호를 다시 입력해 주세요"
                                    type="password"
                                    id="pass2">
                                    </v-text-field>
                                </v-col>
                                
                            </v-row> <!-- 비밀번호 끝 라인 -->

                            <v-row>
                                <v-col cols="4 offset+3">
                                    <v-text-field
                                    name="zipcode"
                                    label="우편번호"
                                    id="zipcode"
                                    readonly>
                                    </v-text-field>
                                </v-col>
                                <v-col cols="4">
                                    <v-btn type="button" color="primary" id="btnZipcode" @click="btnZipcode">우편번호 찾기</v-btn>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="7">
                                    <v-text-field
                                    name="address"
                                    label="자택주소"
                                    id="address"
                                    readonly>                                         
                                    </v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                    name="address2"
                                    label="상세주소"
                                    id="address2">                                        
                                    </v-text-field>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="5">
                                    <v-text-field
                                    label="이메일 아이디"
                                    id="emailId"
                                    name="emailId">
                                    </v-text-field>
                                </v-col>
                                <!-- 중간에 @ 표시 추가해야함 -->
                                <!-- 도메인을 선택했을 때  emailDomain에 출력 후 선택하세요로 리턴
                                직접입력했을 때 reonly가 풀리면서 창입력할 수 있게끔 -->
                                <v-col
                                    v-if="emailDomain === '직접입력'"
                                    cols="5"
                                    label="직접입력"
                                    id="custom"
                                >
                                    <v-text-field name="emailDomain" type="text"></v-text-field>
                                </v-col>
                                <v-col cols="1 pt-5 text-center">
                                    <span>@</span>
                                </v-col>

                                <v-col cols="5" v-if="emailDomain !== '직접입력'">
                                    <v-select
                                    label="선택해주세요"
                                    :items="['naver.com', 'hanmail.net', 'gmail.com', '직접입력']"
                                    id="emailDomain"
                                    name="emailDomain"
                                    >
                                    </v-select>                                             
                                </v-col>
                                <v-col v-else cols="5">
                                    <v-text-field
                                        label="직접입력"
                                        name="emailDomain"
                                        id="emailDomain">
                                    </v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3">
                                    <v-select
                                    label="휴대전화"
                                    id="mobile1"
                                    name="mobile1"
                                    :items="['010']">
                                    </v-select>
                                </v-col>
                                <v-col cols="3">
                                    <v-text-field
                                    id="mobile2"
                                    name="mobile2"
                                    maxlength="4">

                                    </v-text-field>
                                    
                                </v-col>
                                <v-col cols="3">
                                    <v-text-field
                                    id="mobile3"
                                    name="mobile3"
                                    maxlength="4">

                                    </v-text-field>
                                    
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-radio-group>
                                        <v-radio label="이메일 수신 동의" value="ture" id="emailOk" name="emailGet"></v-radio>
                                        <v-radio label="이메일 수신 미동의" value="false" id="emailNo" name="emailGet"></v-radio>
                                    </v-radio-group>
                                </v-col>
                            </v-row>

                        </v-form>

                        <v-row class="text-center">
                                <v-col>
                                    <v-btn type="submit" color="primary" id="btnJoin" @click="joinFormCheck">가입하기</v-btn>
                                </v-col>
                                <v-col>
                                    <v-btn type="button" color="primary" @click="btnHome">홈으로</v-btn>
                                </v-col>
                            </v-row>
                    </v-col>
                </v-row>
            </v-container>
        </v-sheet>

    </v-main>
    
 
</template>