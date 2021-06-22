
const state = {
  currentKey:'1',
    
}

const mutations = {
  SET_CURRENTKEY: (state, currentKey) => {
    state.currentKey = currentKey
  },
}

const actions = {

}

export default {
  namespaced: false,
  state,
  mutations,
  actions
}
