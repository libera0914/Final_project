<template>
  <div class="main-page">
    <a-layout >
      <div class="img-box"><img src="../static/logo.png" width="600px" height="100px"></div>
      <a-layout-header class="header">
        <div class="search-box">
          <div class="title">Wayfinder</div>
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
          <div class="sub-title">If you have a problem you can't solve, please contact us at <a>xxxx@uni.coventry.ac.uk</a></div>
        </div>
      </a-layout-header>
      <a-layout-content class="content">
        <a-row type="flex" align="middle" justify="center" :gutter="[100,50]">
          <a-col>
            <div class="content-item">
              <img src="../static/campus.jpg" @click="toInformation('campus')">
              <div class="img-text" @click="toInformation('campus')">Campus Information</div>
            </div>
          </a-col>
          <a-col>
            <div class="content-item">
              <img src="../static/academic.jpg" @click="toInformation('academic')">
              <div class="img-text" @click="toInformation('academic')">Academic information</div>
            </div>
          </a-col>
        </a-row>
        <a-row type="flex" align="middle" justify="center" :gutter="[100,50]">
          <a-col>
            <div class="content-item">
              <img src="../static/life.jpg" @click="toInformation('life')">
              <div class="img-text" @click="toInformation('life')">Life information</div>
            </div>
          </a-col>
          <a-col>
            <div class="content-item">
              <img src="../static/other.jpg" @click="toInformation('other')">
              <div class="img-text" @click="toInformation('other')">Other school websites</div>
            </div>
          </a-col>
        </a-row>
        <a-card title="Search record" class="record-card">
          <p v-for="(item,index) in record" :key="index" @click="toDetail">{{ item }}</p>
        </a-card>
        <a-card title="Commend Tel Number" class="left-card">
          Student center: xxxxxxx
          Hub: xxxxxx
          International center：xxxxxx
          Seek police, fire or ambulance help：999
          Protection service：xxxxxxx
        </a-card>
      </a-layout-content>
    </a-layout>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchText: '',
      questionArrayList:[],
      isShowCard: false,
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
      record:[]
    }
  },
  mounted(){
    
    if(JSON.parse(localStorage.getItem("searchRecord")).length>0) {
      this.record = JSON.parse(localStorage.getItem("searchRecord"));
    }
  },
  methods: {
    toInformation(type) {
      this.$router.push({
        path:'/info',
        query:{
          type:type
        }
      })
    },
    onSearch() {
      if(this.searchText == 'tuition fee' || this.searchText == 'Tuition Fee') {
        this.$router.push({
          path: '/preparation',
          query: {
            searchText: this.searchText
          }
        })
      }else {
        this.$router.push({
          path: '/404'
        })
      }
      this.record.push(this.searchText)
      if(this.record.length > 5) {
        this.record.shift()
      }
      localStorage.setItem("searchRecord",JSON.stringify(this.record))
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
    onChange() {
      this.isShowCard = true
      const that = this
      if(this.searchText) {
        setTimeout(function() {
          that.questionArrayList = that.query(that.data,that.searchText,'question')
        },1000)
        console.log(that.questionArrayList)
      }else {
        this.questionArrayList = []
        this.isShowCard = false
      }
    },
    select(item) {
      let matchTag =/<(?:.|s)*?>/g;
      this.searchText = item.replace(matchTag,"");
      this.isShowCard = false
    },
    toDetail() {
      this.$router.push({
        path: '/404'
      })
    }
  }
};
</script>

<style lang="less" scoped>
.main-page {
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
      margin-top: 80px;
      .title {
        font-size: 24px;
        font-weight: 900;
        color: #000;
        display: flex;
        justify-content: center;
      }
      .sub-title {
        margin-top: -40px;
        display: flex;
        justify-content: center;
      }
    }
  }
  .content {
    padding-top: 50px;
    height: 100%;
    .content-item {
      cursor: pointer;
    }
    .img-text{
      position: absolute;
      top: 50%;
      left:50%;
      transform:translate(-50%,-50%);
      text-align:center;
      font-size: 22px;
      font-weight: 900;
      color: #FFC300;
      -webkit-text-stroke: 1px #000;
    }
  }
  .record-card {
    width: 300px;
    position: absolute;
    right: 20px;
    top: 300px;
  }
  .left-card {
    width: 300px;
    position: absolute;
    left: 20px;
    top: 300px;
  }
}
</style>
