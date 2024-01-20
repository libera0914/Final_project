<template>
  <div class="information-page">
    <a-layout>
      <div class="img-box"><img src="../static/logo.png" width="600px" height="100px"></div>
      <a-layout-header class="header">
        <div class="search-box">
          <div class="title">Wayfinder: {{ moduleTitle }}</div>
          <a-input-search v-model="searchText" placeholder="Please enter keywords" size="large" @search="onSearch" @change="onChange">
            <a-icon slot="prefix" type="form" />
            <a-button slot="enterButton">
              Search
            </a-button>
          </a-input-search>
          <a-card v-show="isShowCard" class="search-card">
            <div v-for="(item,index) in questionArrayList" :key="index">
              <div v-html="item" @click="select(item)"></div>
            </div>
          </a-card>
        </div>
      </a-layout-header>
      <a-layout>
          <a-button class="return-btn" @click="returnBtn">Return</a-button>
          <a-layout-sider width="300" style="background:#fff">
            <div v-if="type != 'campus'" class="content-item">
              <img src="../static/campus.jpg" width="300px" height="200px" @click="toInformation('campus')">
              <div class="img-text" @click="toInformation('campus')">Campus Information</div>
            </div>
            <div v-if="type != 'academic'" class="content-item">
              <img src="../static/academic.jpg" width="300px" height="200px" @click="toInformation('academic')">
              <div class="img-text" @click="toInformation('academic')">Academic information</div>
            </div>
            <div v-if="type != 'life'" class="content-item">
              <img src="../static/life.jpg" width="300px" height="200px" @click="toInformation('life')">
              <div class="img-text" @click="toInformation('life')">Life information</div>
            </div>
            <div v-if="type != 'other'" class="content-item">
              <img src="../static/other.jpg" width="300px" height="200px" @click="toInformation('other')">
              <div class="img-text" @click="toInformation('other')">Other school websites</div>
            </div>
          </a-layout-sider>
          <a-layout-content class="content">
            <a-card style="width: 90%">
              <div>
                <a-card v-for="(item,index) in list" :key="index" class="card-item">
                <div @click="toDetail(item)" :class="item==searchText&&isActive?'active-option':'option'">{{index+1+'.'+item}}</div>
                </a-card>
              </div>
              <div class="page-box">
                <a-pagination v-model="current" :total="50" show-less-items />
              </div>
            </a-card>
          </a-layout-content>
      </a-layout>
    </a-layout>
  </div>
</template>

<script>
export default {
  data() {
    return {
      current: 1,
      type: "",
      list: [],
      questionArrayList:[],
      isActive:false,
      data: [
        {
          id: 1,
          type: 'campus',
          question: 'Campus Map (Coventry Campus)'
        },
        {
          id: 2,
          type: 'campus',
          question: 'How to get your first student card'
        },
        {
          id: 3,
          type: 'campus',
          question: 'The phone numbers of other departments in the school'
        },
        {
          id: 4,
          type: 'campus',
          question: 'What can you do at the Hub'
        },
        {
          id: 5,
          type: 'campus',
          question: 'school news'
        },
        {
          id: 6,
          type: 'academic',
          question: 'Coventry University paper format'
        },
        {
          id: 7,
          type: 'academic',
          question: 'Academic extension/make-up examination'
        },
        {
          id: 8,
          type: 'academic',
          question: 'Rules of academic ethics and punishment'
        },
        {
          id: 9,
          type: 'academic',
          question: 'How to use the school hardware / software facilities'
        },
        {
          id: 10,
          type: 'academic',
          question: 'Rules of graduation degree assessment'
        },
        {
          id: 11,
          type: 'life',
          question: 'Rent a dormitory on campus'
        },
        {
          id: 12,
          type: 'life',
          question: 'How to deal with interpersonal relationships'
        },
        {
          id: 13,
          type: 'life',
          question: 'How to protect property security'
        },
        {
          id: 14,
          type: 'life',
          question: 'How to keep healthy (nearby hospital)'
        },
        {
          id: 15,
          type: 'life',
          question: 'Food in the Coventry'
        },
        {
          id: 16,
          type: 'other',
          question: 'SOLAR -- Check your results/ Information'
        },
        {
          id: 17,
          type: 'other',
          question: 'NOVA -- View your Your Assessments and get your Coursework Submission Coversheets/ Get your student letter'
        },
        {
          id: 18,
          type: 'other',
          question: 'Time table -- Check your course times and specific information'
        },
        {
          id: 19,
          type: 'other',
          question: 'MODEL --  Check the specific content of your course'
        },
        {
          id: 20,
          type: 'other',
          question: 'Moodle -- Coventry University online teaching platform'
        }
        
      ],
      isShowCard: false,
      searchText:"",
      moduleTitle: ''
    }
  },
  mounted(){
    this.type = this.$route.query.type
    this.toInformation(this.type)
    this.changeTitle(this.type)
  },
  methods: {
    changeTitle(type) {
      switch(type) {
        case 'campus':
          this.moduleTitle = 'campus information'
          break;
        case 'life':
          this.moduleTitle = 'life information'
          break;
        case 'academic':
          this.moduleTitle = 'academic information'
          break;
        case 'other':
          this.moduleTitle = 'other school websites'
          break;
      }
    },
    toInformation(type) {
      this.list = []
      this.type = type
      this.changeTitle(type)
      this.data.forEach(item => {
        if(item.type == type) {
          this.list.push(item.question)
        }
      })
    },
    query(list, keyWord, attribute) {
      const reg = new RegExp((`(${keyWord})`),"gi") // 创建正则表达式
      const arr = []
      for (let i = 0; i < list.length; i++) {
        if (reg.test(list[i][attribute])) {
          let replace = '<span style="color:#FD463E;font-weight:bold;">$1</span>'
          arr.push(list[i][attribute].replace(reg, replace))
        }
      }
      return arr
    },
    onChange(e) {
      this.isShowCard = true
      const that = this
      if(this.searchText) {
        setTimeout(function() {
          that.questionArrayList = that.query(that.data,that.searchText,'question')
        },1000)
      }else {
        this.questionArrayList = []
        this.isShowCard = false
        this.isActive = false
      }
    },
    select(item) {
      let matchTag =/<(?:.|s)*?>/g;
      this.searchText = item.replace(matchTag,"");
      this.isShowCard = false
    },
    onSearch() {
      if(!this.searchText) {
        this.isActive = false
      }
      this.isActive = true
      this.data.forEach(item =>{
        if(item.question == this.searchText) {
          this.type = item.type
          this.toInformation(this.type)
        }
      })
    },
    toDetail() {
      this.$router.push({
        path: '/404'
      })
    },
    returnBtn() {
      this.$router.push({
        path: '/'
      })
    }
  }
};
</script>

<style lang="less" scoped>
.information-page {
  width: 100%;
  height: 100%;
  .img-box {
    position: absolute;
    top:10px;
    left: 0px;
  }
  .search-box .search-card {
    width: 100%;
    margin-top:-20px;
    z-index: 999;
  }
  .header {
    background: #B2CAEB;
    height: 250px;
    display: flex;
    justify-content: center;
    .search-box {
      width: 800px;
      margin-top: 120px;
      .title {
        font-size: 24px;
        font-weight: 900;
        color: #000;
        display: flex;
        justify-content: center;
      }
    }
  }
  .return-btn {
    position: absolute;
    top:260px;
    right: 10px;
  }
  .content {
    padding-top: 30px;
    padding-left: 50px;
    .card-item {
      margin-top: 20px;
      width: 90%;
    }
    .page-box {
      margin-top: 100px;
      display: flex;
      justify-content: center;
    }
    .active-option{
      border: 3px solid #0095ff;
    }
    option {
      border: 1px solid #e8e8e8;
    }
  }
  .content-item {
    cursor: pointer;
    position: relative;
  }
  .img-text{
    width: 300px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform:translate(-50%,-50%);
    text-align:center;
    font-size: 18px;
    font-weight: 900;
    color: #FFC300;
    -webkit-text-stroke: 1px #000;
  }
}
</style>
<style>
.ant-card-body {
  padding: 10px !important;
  zoom: 1;
}
</style>
