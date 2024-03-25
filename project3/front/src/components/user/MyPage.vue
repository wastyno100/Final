<script setup>
</script>

<template>
    <v-main>
        <v-container>
            <v-row>
                <v-col>
                    
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
                            v-model="username"
                            variant="outlined">
                            </v-text-field>
                        </v-col>    
                        <v-col cols="5 offset+1">
                            <v-card-title>
                                <h5>아이디</h5>
                            </v-card-title>
                            <v-text-field
                            v-model="id"
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
                           v-model="pass"
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
                           v-model="pass"
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
                            v-model="phone"
                            variant="outlined">
                            </v-text-field>    
                        </v-col>

                        <v-col cols="5 offset+1">
                            <v-card-title>
                                <h5>이메일</h5>
                            </v-card-title> 
                            <v-text-field
                            v-model="email"
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
                            v-model="address1"
                            variant="outlined">
                            </v-text-field>    
                        </v-col>    
                    </v-row>
                </v-col>    
            </v-row>
        </v-container>
    </v-main>
</template>