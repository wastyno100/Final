import { createStore } from 'vuex';
import axios from 'axios';
import router from './router'; // router를 import해야 합니다.

const store = createStore({
  state() {
    return {
      loginStatus: { isLogIn: false, userId: null },
    };
  },
  mutations: {
    SET_LOGIN_STATUS(state, payload) {
      state.loginStatus = payload;
    },
  },
  actions: {
    async login({ commit }, { id, pass }) {
      if (!id || !pass) {
        alert("입력부터 해주세요");
      } else {
        try {
          const res = await axios.post(`/api/login`, {
            id: id,
            pass: pass
          });
          console.log(res.data)
          if (res.data === 1) {
            console.log("로그인 성공");
            alert("로그인 성공");
            sessionStorage.setItem('isLogIn', 'true');
            sessionStorage.setItem('userId', id);
            commit('SET_LOGIN_STATUS', { isLogIn: true, userId: id });
            router.push('/');
          } else if (res.data === 0) {
            console.log("비밀번호가 다릅니다.");
            alert("비밀번호가 다릅니다."); 
          } else if (res.data === -1) {
            console.log("아이디가 존재하지 않습니다.");
            alert("아이디가 존재하지 않습니다.");
          }
        } catch (error) {
          console.error("로그인 요청 실패:", error);
          alert("로그인 요청 실패");
        }
      }
    },
    async logout({ commit }) {
      try {
        await axios.post(`/api/logout`, {}, { withCredentials: true });
        sessionStorage.removeItem('isLogIn');
        sessionStorage.removeItem('userId');
        commit('SET_LOGIN_STATUS', { isLogIn: false, userId: null });
        console.log('로그아웃 되었습니다.');
      } catch (error) {
        console.error('로그아웃 중 오류 발생:', error);
      }
    },
    async checkLoginStatus({ commit }) {
      try {
        // const response = await axios.post(`/api/status`, {}, { withCredentials: true });
        // const { isLogIn, userId } = response.data;
        const isLogIn = sessionStorage.getItem('isLogIn') === 'true';
        const userId = sessionStorage.getItem('userId');
        commit('SET_LOGIN_STATUS', { isLogIn, userId });
      } catch (error) {
        console.error('로그인 상태 확인 중 오류 발생:', error);
        commit('SET_LOGIN_STATUS', { isLogIn: false, userId: null });
      }
    },
  },
});

export default store;
