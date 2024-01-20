const state = {
  option1: '',
  option2: '',
  option3: ''
};

const mutations = {
  updateOption1: (state, data) => {
    state.option1 = data;
  },
  updateOption2: (state, data) => {
    state.option2 = data;
  },
  updateOption3: (state, data) => {
    state.option3 = data;
  }
};

const actions = {
  updateOption1 ({ commit }, data) {
    commit('updateOption1', data);
  },
  updateOption2 ({ commit }, data) {
    commit('updateOption2', data);
  },
  updateOption3 ({ commit }, data) {
    commit('updateOption3', data);
  },
};

export default {
  state,
  mutations,
  actions,
};
