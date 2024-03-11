<script setup>

</script>

<template>
    <v-main>
        
        <v-sheet width="600" class="mx-auto my-2">
            <v-container>
                <v-row>
                    <v-col>
                        <v-row>
                            <v-col text-center>
                                    <h1>회원가입 창</h1>
                            </v-col>
                        </v-row>
                        <v-form>
                            <v-row>
                                <v-col cols="8">
                                    <v-text-field
                                    v-model="id"
                                    label="아이디"
                                    placeholder="사용할 아이디를 입력해주세요."
                                    id="userId">
                                    </v-text-field>
                                </v-col>
                                <v-col cols="4">
                                    <v-btn type="button" color="primary" id="btnOverlapId">중복확인</v-btn>
                                </v-col>
                            </v-row><!-- 아이디 끝 라인 -->

                            <v-row>
                                <v-col cols="6">
                                    <v-text-field
                                    v-model="pass"
                                    label="비밀번호"
                                    placeholder="사용할 비밀번호를 입력해주세요"
                                    type="password"
                                    id="pass">
                                    </v-text-field>
                                </v-col>

                                <v-col cols="6">
                                    <v-text-field
                                    v-model="rePass"
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
                                    v-model="zipcode"
                                    label="우편번호"
                                    id="zipcode"
                                    readonly>
                                    </v-text-field>
                                </v-col>
                                <v-col cols="4">
                                    <v-btn type="button" color="primary" id="btnZipcode">우편번호 찾기</v-btn>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="7">
                                    <v-text-field
                                    v-model="address"
                                    label="자택주소"
                                    id="address"
                                    readonly>                                        
                                    </v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                    v-model="address"
                                    label="상세주소"
                                    id="address">                                        
                                    </v-text-field>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="4">
                                    <v-text-field
                                    id="emailId">
                                    </v-text-field>
                                </v-col>
                                <!-- 중간에 @ 표시 추가해야함 -->
                                <v-col cols="4">
                                    <v-text-field
                                    id="emailDomain"
                                    readonly>
                                    </v-text-field>
                                </v-col>
                                <!-- 도메인을 선택했을 때  emailDomain에 출력 후 선택하세요로 리턴
                                직접입력했을 때 reonly가 풀리면서 창입력할 수 이게끔 -->
                                <v-col cols="4">
                                    <v-select
                                    label="선택해주세요"
                                    :items="['@naver.com', '@hanmail.net', '@gmail.com', '직접입력']">
                                    <v-if items="직접입력">
                                    </v-if>
                                    </v-select>                       
                                </v-col>
                            </v-row>
                        </v-form>
                    </v-col>
                </v-row>
            </v-container>
        </v-sheet>

    </v-main>
    
    
</template>