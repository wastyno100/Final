<template>
  <v-main>
      <v-row justify="center">
        <v-col cols="12" class="pa-5">
          <div class="wrapper">
            <div class="box_section">
              <!-- 결제 UI -->
              <div id="payment-method"></div>
              <!-- 이용약관 UI -->
              <div id="agreement"></div>
              <!-- 쿠폰 체크박스 -->
              <!-- <div style="padding-left: 25px">
                <div class="checkable typography--p">
                  <label for="coupon-box" class="checkable__label typography--regular"
                    ><input @change="updateAmount" id="coupon-box" class="checkable__input" type="checkbox" aria-checked="true" /><span class="checkable__label-text">5,000원 쿠폰 적용</span></label
                  >
                </div>
              </div> -->
              <!-- 결제하기 버튼 -->
              <div class="result wrapper text-center">
                <button @click="requestPayment" class="button" id="payment-button" style="margin-top: 30px">결제하기</button>
              </div>
            </div>
          </div>
        </v-col>
      </v-row>
  </v-main>
</template>

<script>
import { loadPaymentWidget, ANONYMOUS } from "@tosspayments/payment-widget-sdk";
import { nanoid } from "nanoid";
import axios from 'axios';

export default {
  data() {
    return {
      paymentWidget: null,
      paymentMethodWidget: null,
      // TODO: clientKey는 개발자센터의 결제위젯 연동 키 > 클라이언트 키로 바꾸세요.
      // TODO: customerKey는 구매자와 1:1 관계로 무작위한 고유값을 생성하세요.
      clientKey: "test_ck_Z61JOxRQVEok74v4QRl28W0X9bAq",
      customerKey: nanoid(),
      amount: 0,
      menu: [],
      buyData: {},
    };
  },
  methods: {
    async requestPayment() {
      try {
        if (this.paymentWidget) {
          // ------ '결제하기' 버튼 누르면 결제창 띄우기 ------
          // 결제를 요청하기 전에 orderId, amount를 서버에 저장하세요.
          // 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도입니다.
          // @docs https://docs.tosspayments.com/reference/widget-sdk#requestpayment결제-정보
          await this.paymentWidget.requestPayment({
            orderId: nanoid(),
            orderName: this.menu.length == 1 ? `${this.menu[0].menuTitle}` : `${this.menu[0].menuTitle} 외 ${this.menu.length - 1}건`,
            customerName: this.buyData.username,
            customerEmail: this.buyData.email,
            customerMobilePhone: this.buyData.phone,
            successUrl: `${window.location.origin}/success`,
            failUrl: `${window.location.origin}/fail`,
          });
        }
      } catch (error) {
        // 에러 처리하기
        console.error(error);
      }
    },
    async updateAmount() {
      const coupon = document.getElementById("coupon-box");
      // ------  결제 금액 업데이트 ------
      // @docs https://docs.tosspayments.com/reference/widget-sdk#updateamount결제-금액
      if (coupon.checked) {
        this.paymentMethodWidget.updateAmount(this.amount - 5000);
      } else {
        this.paymentMethodWidget.updateAmount(this.amount);
      }
    },
  },
  async mounted() {
    // ------  결제위젯 초기화 ------
    // @docs https://docs.tosspayments.com/reference/widget-sdk#sdk-설치-및-초기화
    this.paymentWidget = await loadPaymentWidget(this.clientKey, ANONYMOUS);

    // ------  결제 UI 렌더링 ------
    // @docs https://docs.tosspayments.com/reference/widget-sdk#renderpaymentmethods선택자-결제-금액-옵션
    this.paymentMethodWidget = this.paymentWidget.renderPaymentMethods("#payment-method", { value: this.amount }, { variantKey: "DEFAULT" });
    // ------  이용약관 UI 렌더링 ------
    // @docs https://docs.tosspayments.com/reference/widget-sdk#renderagreement선택자-옵션
    this.paymentWidget.renderAgreement("#agreement", { variantKey: "AGREEMENT" });

    this.menu = JSON.parse(sessionStorage.getItem("menu"))
    this.buyData = JSON.parse(sessionStorage.getItem("buyData"))
    this.paymentMethodWidget.updateAmount(JSON.parse(sessionStorage.getItem("totalPrice")));
    this.buyData.phone = this.buyData.phone.replace(/-/g, "")
    sessionStorage.setItem("orderName", this.menu.length == 1 ? `${this.menu[0].menuTitle}` : `${this.menu[0].menuTitle} 외 ${this.menu.length - 1}건`)
    console.log(`${this.menu[0].menuTitle} 외 ${this.menu.length - 1}건`)
    console.log(this.buyData.phone)
  },
};
</script>

<style>
.button {
  color: #f9fafb;
  background-color: #3182f6;
  margin: 0;
  font-size: 15px;
  font-weight: 400;
  line-height: 18px;
  white-space: nowrap;
  text-align: center;
  /* vertical-align: middle; */
  cursor: pointer;
  border: 0 solid transparent;
  user-select: none;
  transition: background 0.2s ease, color 0.1s ease;
  text-decoration: none;
  border-radius: 7px;
  padding: 11px 16px;
}
.button:hover {
  color: #fff;
  background-color: #1b64da;
}
</style>
