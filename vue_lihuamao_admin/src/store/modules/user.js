// import { login, logout, getInfo } from '@/api/login/login'
// import { getToken, setToken, removeToken } from '@/utils/auth'
// import router, { resetRouter } from '@/router'

// const state = {
//   token: getToken(),
//   loginuser:{
//     userName: '', // 用户名
//     orgName: '',  // 所属机构名
//     orgId: '',  // 所属机构主键
//     loginId: '',  // 登录名
//     telphone: '', // 电话号码
//     eMail:'', // 邮箱
//   },
//   roles: []
// }

// const mutations = {
//   SET_TOKEN: (state, token) => {
//     state.token = token
//   },

//   SETLOGINUSER:(state, user) => {
//     localStorage.setItem('loginuser', JSON.stringify(user))
//   },

//   SET_LOGINUSER:(state, user) => {
//     state.loginuser = user
//   },

// }

// const actions = {

//   setUser ({ commit }, data){
//     commit('SETLOGINUSER', data);
//   },

//   // user login
//   login({ commit }, userInfo) {
//     const { username, password } = userInfo
//     return new Promise((resolve, reject) => {
//       login({ username: username.trim(), password: password }).then(response => {
//         const { data } = response
//         commit('SET_TOKEN', data.token)
//         setToken(data.token)
//         resolve()
//       }).catch(error => {
//         reject(error)
//       })
//     })
//   },

//   // user logout
//   logout({ commit, state, dispatch }) {
//     return new Promise((resolve, reject) => {
//       logout().then(() => {
//         // console.log("我进来了")
//         commit('SET_TOKEN', '')
//         commit('SETLOGINUSER', [])
//         commit('SET_LOGINUSER', [])
//         removeToken()
//         resetRouter()
//         localStorage.removeItem('loginuser')

//         resolve()
//       }).catch(error => {
//         reject(error)
//       })
//     })
//   },

//   // remove token
//   resetToken({ commit }) {
//     return new Promise(resolve => {
//       commit('SET_TOKEN', '')
//       commit('SET_ROLES', [])
//       removeToken()
//       resolve()
//     })
//   },

// }

// export default {
//   namespaced: false,
//   state,
//   mutations,
//   actions
// }
