<script setup>
import { ref } from 'vue';
import axios from 'axios';

const userData = ref({
  name: '',
  id: '',
  phone: '',
  email: '',
  address: '',
  pass: '',
});

const newPass = ref('');

async function getUserData(){
    try {
        const res = await axios.get(`/api/userData`);
        
        if (res.data && res.data.length > 0) {
            const user= res.data[0];

            userData.value = {
                name: user.name,
                id: user.id,
                phone: user.phone,
                email: user.email,
                address: user.address1,
            }
        } else {
            console.error('사용자 정보를 가져오는 중 오류 발생: 응답 데이터가 비어 있습니다.');
        }

    } catch(error) {
        console.error('사용자 정보를 가져오는 중 오류 발생: ', error);
    }
}

getUserData();
</script>

<template>
    <v-main>
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
                                readonly>
                                </v-text-field>
                            </v-col>    
                            <v-col cols="5 offset+1">
                                <v-card-title>
                                    <h5>아이디</h5>
                                </v-card-title>
                                <v-text-field
                                v-model="userData.id"
                                variant="outlined">
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
                            variant="outlined">
                            </v-text-field>   
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="5 offset-1">
                            <v-card-title>
                                <h5>새 비밀번호</h5>
                            </v-card-title>  
                            <v-text-field
                            v-model="newPass"
                            type="password"
                            variant="outlined">
                            </v-text-field>   
                            </v-col>
                            <v-col cols="5 offset+1"  class="text-center"> 
                                <v-card-title class="mb-10">
                                <v-spacer></v-spacer>
                            </v-card-title> 
                            <v-btn color="primary" id="btnUpdatePass" @click="updatePass">비밀번호변경</v-btn>
                            </v-col>
                        </v-row>
                        
                        
                        <v-row>
                            <v-col cols="5 offset-1">
                                <v-card-title>
                                    <h5>휴대전화</h5>
                                </v-card-title>
                                <v-text-field
                                v-model="userData.phone"
                                variant="outlined">
                                </v-text-field>    
                            </v-col>

                            <v-col cols="5 offset+1">
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
                            <v-col cols="6 offset-1">
                                <v-card-title>
                                    <h5>주소</h5>
                                </v-card-title>
                                <v-text-field
                                v-model="userData.address1"
                                variant="outlined">
                                </v-text-field>    
                            </v-col>    
                        </v-row>

                    </v-form>
                </v-col>    
            </v-row>
        </v-container>
    </v-main>
</template>