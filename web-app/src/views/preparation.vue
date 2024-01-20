<template>
  <div class="preparation-page">
    <a-layout>
      <a-layout-header class="header">
        <div class="head-box">
          <div class="title">Welcome to the tuition inquiry interface  ^_^</div>
          <div class="sub-title">Please select the following information before you fill in your tuition fee</div>
        </div>
      </a-layout-header>
      <a-layout-content class="content">
        <div :class="(option1==''&& isNext)? 'question no-select': 'question'">1. What degree are you studying for?</div>
        <a-row type="flex" align="middle" justify="center" :gutter="[100,50]">
          <a-col>
            <div :class="option1 == 'Undergraduate' && optionSelect1 ? 'content-item in-section' : 'content-item'" @click="onSelect1('Undergraduate')">
              Undergraduate
            </div>
          </a-col>
          <a-col>
            <div :class="option1 == 'Postgraduate' && optionSelect4 ? 'content-item in-section' : 'content-item'" @click="onSelect4('Postgraduate')">
              Postgraduate
            </div>
          </a-col>
        </a-row>
        <a-divider  style="height: 2px" />
        <div :class="(option2==''&& isNext)? 'question no-select': 'question'">2. What kind of student are you?</div>
        <a-row type="flex" align="middle" justify="center" :gutter="[100,50]">
          <a-col>
            <div :class="option2 == 'Local' && optionSelect2 ? 'content-item in-section' : 'content-item'" @click="onSelect2('Local')">
              Local
            </div>
          </a-col>
          <a-col>
            <div :class="option2 == 'International' && optionSelect5 ? 'content-item in-section' : 'content-item'" @click="onSelect5('International')">
              International
            </div>
          </a-col>
        </a-row>
        <a-divider style="height: 2px" />
        <div :class="(option3==''&& isNext)? 'question no-select': 'question'">3. What is your academic system?</div>
        <a-row type="flex" align="middle" justify="center" :gutter="[100,50]">
          <a-col>
            <div :class="option3 == 'Full-time' && optionSelect3 ? 'content-item in-section' : 'content-item'" @click="onSelect3('Full-time')">
              Full-time
            </div>
          </a-col>
          <a-col>
            <div :class="option3 == 'Part-time' && optionSelect6 ? 'content-item in-section' : 'content-item'" @click="onSelect6('Part-time')">
              Part-time
            </div>
          </a-col>
        </a-row>
        <a-row style="float: right;margin-bottom: 50px;margin-right: 300px">
          <a-button class="return-btn" @click="returnBtn">Return</a-button>
          <a-button type="primary" style="margin-left: 10px" @click="toNext">Next</a-button>
        </a-row>
      </a-layout-content>
    </a-layout>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      option1: '',
      option2: '',
      option3: '',
      keyWord: '',
      isNext: false,
      optionSelect1: false,
      optionSelect2: false,
      optionSelect3: false,
      optionSelect4: false,
      optionSelect5: false,
      optionSelect6: false,
    }
  },
  mounted(){
    this.option1 = this.$store.getters.option1
    this.option2 = this.$store.getters.option2
    this.option3 = this.$store.getters.option3
    this.keyWord = this.$route.query?.keywords
    if(this.option1 == 'Undergraduate') {
      this.optionSelect1 = true
    }else if(this.option1 == 'Postgraduate' ) {
      this.optionSelect4 = true
    }else {
      this.optionSelect1 = false
      this.optionSelect4 = false
    }
    if(this.option2 == 'Local') {
      this.optionSelect2 = true
    }else if(this.option2 == 'International' ) {
      this.optionSelect5 = true
    }else {
      this.optionSelect2 = false
      this.optionSelect5 = false
    }
    if(this.option3 == 'Full-time') {
      this.optionSelect3 = true
    }else if(this.option3 == 'Part-time' ) {
      this.optionSelect6 = true
    }else {
      this.optionSelect3 = false
      this.optionSelect6 = false
    }
  },
  methods: {
    ...mapActions(["updateOption1", "updateOption2", "updateOption3"]),
    toInformation(type) {
      this.$router.push({
        path:'/info',
        query:{
          type:type
        }
      })
    },
    onSelect1(option) {
      this.optionSelect4 = false
      this.optionSelect1 = !this.optionSelect1
      if(this.optionSelect1) {
        this.option1 = option
        this.updateOption1(this.option1)
      }else {
        this.option1 = ""
        this.updateOption1(this.option1)
      }
    },
    onSelect2(option) {
      this.optionSelect5 = false
      this.optionSelect2 = !this.optionSelect2
      if(this.optionSelect2) {
        this.option2 = option
        this.updateOption2(this.option2)
      }else {
        this.option2 = ""
        this.updateOption2(this.option2)
      }
    },
    onSelect3(option) {
      this.optionSelect6 = false
      this.optionSelect3 = !this.optionSelect3
      if(this.optionSelect3) {
        this.option3 = option
        this.updateOption3(this.option3)
      }else {
        this.option3 = ""
        this.updateOption3(this.option3)
      }
    },
    onSelect4(option) {
      this.optionSelect1 = false
      this.optionSelect4 = !this.optionSelect4
      if(this.optionSelect4) {
        this.option1 = option
        this.updateOption1(this.option1)
      }else {
        this.option1 = ""
        this.updateOption1(this.option1)
      }
    },
    onSelect5(option) {
      this.optionSelect2 = false
      this.optionSelect5 = !this.optionSelect5
      if(this.optionSelect5) {
        this.option2 = option
        this.updateOption2(this.option2)
      }else {
        this.option2 = ""
        this.updateOption2(this.option2)
      }
    },
    onSelect6(option) {
      this.optionSelect3 = false
      this.optionSelect6 = !this.optionSelect6
      if(this.optionSelect6) {
        this.option3 = option
        this.updateOption3(this.option3)
      }else {
        this.option3 = ""
        this.updateOption3(this.option3)
      }
    },
    toNext() {
      if(!this.option1 || !this.option2 || !this.option3) {
        this.isNext = true
        this.$message.warning(`You haven't finished selecting, please continue selecting`);
        return
      }

      this.isNext = false
      
      this.$router.push({
        path: '/tuitionInquiry',
        query: {
          keyWord: this.keyWord
        }
      })
    },
    returnBtn() {
      this.updateOption1('')
      this.updateOption2('')
      this.updateOption3('')
      this.$router.push({
        path: '/'
      })
    }
  }
};
</script>

<style lang="less" scoped>
.preparation-page {
  width: 100%;
  height: 100%;
  overflow: hidden;
  .img-box {
    position: absolute;
    top:10px;
    left: 0px;
  }
  .header {
    background: #FF8D1A;
    height: 250px;
    display: flex;
    justify-content: center;
    .head-box {
      width: 800px;
      margin-top: 50px;
      .title {
        font-size: 24px;
        font-weight: 900;
        color: #000;
        display: flex;
        justify-content: center;
      }
      .sub-title {
        display: flex;
        justify-content: center;
      }
    }
  }
  .content {
    padding-top: 20px;
    .question {
      font-size: 16px;
      font-weight: 900;
      color: #000;
      display: flex;
      justify-content: center;
      margin-bottom: 20px;
    }
    .no-select {
      color: red;
    }
    .content-item {
      cursor: pointer;
      background: #FF8D1A;
      width: 150px;
      height: 80px;
      border: 1px solid #9c9b9b;
      color: #000;
      font-weight: 900;
      display: flex;
      justify-content: center;
      align-items: center;
      margin-bottom: 20px;
    }
    .in-section {
      border: 3px solid #0095ff;
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
}
</style>
