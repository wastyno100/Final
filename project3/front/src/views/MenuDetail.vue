<style scoped>
.MDmain{
  padding: 10px;
}
.heart{

}
.MDBTN{
  margin: 5px;
}
</style>
<template>
 <v-container> 
  <v-main>
   
    <v-row>
      <v-col cols="12">
       <!-- 상품 사진 및 이름 등 -->
        <v-row>
          <!-- 상품 사진 -->
          <v-col cols="5 offset-2">
            <img :src="imgFile[0]" width="80%" height="100%">
          </v-col>
         
           <!-- 상품명 원산지 좋아요 -->
          <v-col cols="3 offset+2">
            <!-- 상품명,원산지, 가격 -->
            <v-row>
              <v-col>
                <v-card-text>
                <h2>{{ menu.menuTitle }}</h2><br>
                <h3>원산지 : 상세페이지 참조</h3>
                </v-card-text>  
                <v-card-text>
                <h4> 가격: {{ menu.menuPrice }}원</h4> 
                </v-card-text>
              </v-col>  
            </v-row>
            <!-- 버튼 -->
              <v-row>
                <v-col >

                  <v-row>
                    <v-col cols="8 offset+1">
                      <!-- 숫자 조절 버튼과 input 필드 -->
                      <v-row >
                        <v-col align="start">
                        <v-text-field id = "number" v-model="menuCount" type="number" class="mx-2" style="width: 100px;"></v-text-field>
                        </v-col>
                        <v-col>
                          <v-dialog v-model="dialog" max-width="400" persistent>
                            <template v-slot:activator="{ props: activatorProps }">
                                      <v-btn v-bind="activatorProps" color="primary" @click="saveCart"> 장바구니담기 </v-btn>
                            </template>

                            <v-card
                              prepend-icon="mdi-map-marker"
                              text="쇼핑을 계속 하시려면 확인 장바구니로 이동하고 싶으시면 장바구니를 클릭해주시길 바랍니다. "
                              title="쇼핑을 계속 하시겠습니까?"
                            >
                              <template v-slot:actions>
                                <v-spacer></v-spacer>

                                <v-btn color="primary" @click="addToCart"> 장바구니 </v-btn>

                                <v-btn color="primary" @click="dialog = false"> 확인 </v-btn>
                              </template>
                            </v-card>
                          </v-dialog>
                        </v-col>
                      </v-row>
                    </v-col> 
                  </v-row>

                  <v-row>
                    <v-col cols="4">
                      <v-btn color="primary" variant = flat @click="buy">
                      구매하기
                      </v-btn>
                    </v-col>

                    <v-col cols="6 offset-1" class="heart">
                      <h4 class="heart">{{menu.heart}}
                      <v-btn 
                        color="blue-lighten-2"
                        icon="mdi-thumb-up"
                        variant="text"
                        @click="menuLike"
                      ></v-btn></h4>
                <!-- <h4><a href="#">리뷰</a></h4> -->
                    </v-col>
                  </v-row>

                 
                
                </v-col>
              </v-row>  

          </v-col>
        </v-row>

        <v-row>
          <v-col cols="8 offset-2" class="text-end">
            <v-dialog v-model="deleteQ" max-width="400" persistent>
              <template v-slot:activator="{ props: activatorPropsD}">
                <v-btn v-bind="activatorPropsD" text="삭제" v-if="isAdmin" />
              </template>

              <v-card
                prepend-icon="mdi-map-marker"
                text="데이터가 삭제를 원하시면 삭제를, 취소를 원하시면 취소를 클릭해주시기 바랍니다."
                title="메뉴 삭제를 계속 하시겠습니까?"
              >
                <template v-slot:actions>
                  <v-spacer></v-spacer>

                  <v-btn color="primary" @click="delData"> 삭제 </v-btn>

                  <v-btn color="primary" @click="deleteQ = false"> 취소 </v-btn>
                </template>
              </v-card>
            </v-dialog>
            <v-btn text="수정" v-if="isAdmin" />
            <v-divider></v-divider>
          </v-col>  
        </v-row>

        <!-- 리뷰 -->
        <v-row>
          <v-col cols="10 offset-1" class="">
            <v-tabs v-model="item" bg-color="#004393" style="margin-top: 50px; display: flex; flex-direction: column; align-items: center;">
              <v-tab value="one">상세정보</v-tab>
              <v-tab value="two">리뷰</v-tab>
            </v-tabs>
            <v-card-text>
              <v-window v-model="item">
                <v-window-item value="one">
                  <MenuDetailInfo />
                </v-window-item>
                <v-window-item value="two">
                  <MenuReply :dataObj="dataObj" />
                </v-window-item>
                <v-window-item value="three">
                  <MenuQeustion />
                </v-window-item>
              </v-window>
            </v-card-text>
          </v-col>  
        </v-row>  

      </v-col>  
    </v-row> 
  </v-main>
</v-container> 

</template>

<style scoped>

</style>

<style scoped>

</style>
<script setup>
import axios from 'axios'
import { onMounted, ref, computed } from 'vue'
import MenuDetailInfo from '../components/menu/MenuDetailInfo.vue'
import MenuQeustion from '@/components/menu/MenuQeustion.vue'
import MenuReply from '@/components/menu/MenuReply.vue'
import { useRouter } from 'vue-router'
const router = useRouter();


// 게시글 목록에서 선택한 게시글 번호를 선물로 받음
const { dataObj } = history.state

const item = ref()
// 게시글 내용 넣기
const menu = ref({}) // DB
// 수량을 관리하는
const menuCount = ref(1)

const dialog = ref(false);
const deleteQ = ref(false);

const imgFile =ref([]);

// 게시글 번호를 서버로 보내서 해당 게시물의 데이터들만 가져오자
// 게시글 업데이트도 put을 사용해서 해버리자
const getData = async () => {
  const res = await axios.post(`/api/menuDetail?menuNo=${dataObj.menuNo}`)
  menu.value = res.data[0]
  if(menu.value.menuImg != null) {
    menu.value.menuImg = JSON.parse(res.data[0].menuImg)
    menu.value.menuImg.forEach( async (menuImg) => {
      await axios.get(`/api/getMImage/${menuImg}`)
        .then((res) => {
          imgFile.value.push(res.data)
          console.log(imgFile.value);
        });
    });
  }
}
const getData2 = async () => {
  const res = await axios.post(`/api/menuDetail?menuNo=${dataObj.menuNo}`)
  menu.value = res.data[0]
}
// const getmenuImg = () => {
//   menu.value.menuImg.forEach( async (menuImg) => {
//     await axios.get(`/api/getMImage/${menuImg}`)
//       .then((res) => {
//         imgFile.value.push(res.data)
//       })
//   });
// }

const isAdmin = computed(() => sessionStorage.getItem('userId') === 'admin');

const delData = async() => {
  await axios.delete(`/api/menuDelete?menuNo=${dataObj.menuNo}`)
    .then((res) => {
      if(res.status == 200) {
        alert("게시글 삭제를 성공 했습니다.")
        router.push('/menuList')
      }
      else alert("메뉴 삭제를 실패 했습니다.")
    })
}
onMounted(() => {
  getData()
  // getmenuImg()
})

const saveCart = async() => {
  console.log(menu.value.menuNo);
  console.log(sessionStorage.userNo);
    await axios.post('/api/cartSave',({
      menuNo: menu.value.menuNo,
      menuCount: menuCount.value,
      userNo:sessionStorage.userNo
    }), {
    });
}

const menuLike = async () => {
  console.log("제발",menu.value)
  await axios.put(`/api/menuLike?menuNo=${menu.value.menuNo}`)
  .then(() => {
    getData2()
  })
}

function addToCart(item){
  router.push({
    name: 'Cart',
    state: { dataObj: { menuNo: item.menuNo }}
  })
  }

async function buy() {
  sessionStorage.removeItem('buyData')
  sessionStorage.removeItem('totalPrice')
  sessionStorage.removeItem('menu')

  sessionStorage.setItem('totalPrice', (menuCount.value * menu.value.menuPrice) )
  sessionStorage.setItem('menu', JSON.stringify([menu.value]))
  router.push({
    name: 'BuyPage',
    state: { buyState:
        { menuNo: menu.value.menuNo,
          menuCount: menuCount.value
        } } //유저번호, 상품번호 , 로그인 했을때만 구매 및 장바구니 가능.
  })
}



</script>