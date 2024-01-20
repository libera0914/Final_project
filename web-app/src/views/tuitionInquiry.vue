<template>
  <div class="preparation-page">
    <a-layout>
      <a-layout-header class="header">
        <div class="head-box">
          <div class="title">Tuition inquiry interface</div>
        </div>
      </a-layout-header>
      <a-layout>
        <a-layout-sider class="sider" width="350">
          <div>
            <div class="menu-title">Course name</div>
            <a-menu mode="inline" v-model="menuValue" :open-keys="openKeys"  style="width: 350px" @openChange="onOpenChange" @select="select">
              <a-sub-menu key="sub1">
                <span slot="title"><a-icon type="appstore" /><span>College name</span></span>
                <a-menu-item v-for="item in collegeItems" :key="item" @click="itemClick" >
                  {{ item }} 
                </a-menu-item>
              </a-sub-menu>
            </a-menu>
            <a-menu mode="inline" v-model="menuValue2" :open-keys="openKeys2"   style="width: 350px" @openChange="onOpenChange2" @select="select2">
              <a-sub-menu key="sub2">
                <span slot="title"><a-icon type="appstore" /><span>Subject name</span></span>
                <a-menu-item v-for="item in subjectItems2" :key="item" @click="itemClick" >
                  {{ item }} 
                </a-menu-item>
              </a-sub-menu>
            </a-menu>
          </div>
        </a-layout-sider>
        <a-layout-content>
          <div class="content-head">
             <a-breadcrumb class="content-breadcrumb">
                <a-breadcrumb-item>{{ option1 }}</a-breadcrumb-item>
                <a-breadcrumb-item>{{ option2 }}</a-breadcrumb-item>
                <a-breadcrumb-item>{{ option3 }}</a-breadcrumb-item>
                <a-input-search v-model="queryParams.keyWord" placeholder="input search text" style="width: 200px;margin-left: 100px" @search="onSearch" />
                <a-button class="return-btn" @click="returnBtn">Return</a-button>
              </a-breadcrumb>
          </div>
          <div class="content-table">
            <a-table :columns="columns" 
              :data-source="tableData"  
              :pagination="pagination"
              :loading="loading"
              @change="onChange"
               bordered>
            </a-table>
          </div>
        </a-layout-content>
      </a-layout>
      <a-layout-footer>
        <div class="footer">If you have a problem you can't solve, please contact us at xxxx@uni.coventry.ac.uk</div>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
export default {
  data() {
    return {
      option1: "",
      option2: "",
      option3: "",
      loading: false,
      tableData: [],
      openKeys: ['sub1'],
      openKeys2: ['sub2'],
      rootSubmenuKeys: ['sub1'],
      rootSubmenuKeys2: ['sub2'],
      columns: [
        {
          title: 'COURSECODE',
          dataIndex: 'courseCode'
        },
        {
          title: 'COURSETITLE',
          dataIndex: 'courseTitle',
          sorter: (a, b) => a.courseTitle.localeCompare(b.courseTitle),
          sortDirections: ['descend', 'ascend'],
        },
        {
          title: 'TUITIONFEE',
          dataIndex: 'tuitionFee'
        },
        {
          title: 'FIRSTYENROLLED',
          dataIndex: 'firstyEnrolled'
        },
        {
          title: 'DEGREE',
          dataIndex: 'degree'
        },
        {
          title: 'TYPES',
          dataIndex: 'types'
        },
        {
          title: 'FROM',
          dataIndex: 'from'
        }
      ],
      pagination: {
        total: 0,
        current:1,
        pageSize: 5,//每页中显示5条数据
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: total => `Total ${total} items`,  //分页中显示总的数据
      },
      queryParams: {
        keyWord: '',
        pageSize: 10,
        pageNum: 1,
        degree: '',
        from: '',
        types: '',
        courseTitle: '',
        courseName: [],
        courseTitles: []
      },
      autoExpandParent: true,
      defaultExpandAll: true,
      checkedKeys: [],
      selectedKeys: [],
      checked: [],
      collegeItems:[],
      subjectItems:[],
      subjectItems2:[],
      menuValue:[],
      menuValue2:[]
    }
  },
  watch:{
    menuValue:{
      handler(val) {
        if(val) {
          this.loadData()
        }
      },
      deep:true
    },
    menuValue2:{
      handler(val) {
        if(val) {
          this.loadData()
        }
      },
      deep: true
    }
  },
  mounted(){
   
    this.option1 = this.$store.getters.option1
    this.option2 = this.$store.getters.option2
    this.option3 = this.$store.getters.option3
    this.queryParams.degree = this.option1
    this.queryParams.from = this.option2
    this.queryParams.types = this.option3
    this.loadData()
    this.loadMenuData()
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
    onOpenChange(openKeys) {
      const latestOpenKey = openKeys.find(key => this.openKeys.indexOf(key) === -1);
      if (this.rootSubmenuKeys.indexOf(latestOpenKey) === -1) {
        this.openKeys = openKeys;
      } else {
        this.openKeys = latestOpenKey ? [latestOpenKey] : [];
      }
    },
    onOpenChange2(openKeys) {
      const latestOpenKey = openKeys.find(key => this.openKeys2.indexOf(key) === -1);
      if (this.rootSubmenuKeys2.indexOf(latestOpenKey) === -1) {
        this.openKeys2 = openKeys;
      } else {
        this.openKeys2 = latestOpenKey ? [latestOpenKey] : [];
      }
    },
    loadMenuData() {
      let params = {
        degree:this.option1,
        from: this.option2,
        types: this.option3
      }
      console.log(params)
      this.$api.getCourse(params).then(res => {
        this.collegeItems = res.data.flat()
      });
      this.$api.getMajor(params).then(res => {
        this.subjectItems = res.data
      })
    },
    onChange(pagination) {
      this.pagination.current = pagination.current;
      this.pagination.pageSize = pagination.pageSize;
      this.queryParams.pageNum = pagination.current;
      this.queryParams.pageSize = pagination.pageSize;
      this.loadData();
    },
    onSearch() {
      this.loadData();
    },
    returnBtn() {
      this.$router.push({
        path:'/preparation',
      })
    },
    loadData() {
      
      
      this.loading = true;
      this.$api.getTutionByPage(this.queryParams).then(res => {
        if(res.data.data.length == 0) {
          this.$message.warning(`There are no matching items currently searched`);
        }
        this.loading = false;
        if(res.status) {
          this.tableData = res.data.data
          this.pagination.total = res.data.total
        }
      })
    },
    itemClick(obj) {
      this.loadData();
    },
    select(obj, key) {
      console.log(obj)
      this.queryParams.courseName = []
      this.queryParams.courseName.push(obj.key)
      this.queryParams.courseTitles = []
      // this.menuValue2 = []
      this.subjectItems2 = []
      this.subjectItems.forEach(item => {
        if(obj.key == item.course_name) {
          this.subjectItems2.push(item.course_title)
        }
      })
      this.loadData();
    },
    select2(item) {
      console.log(item)
      console.log(this.menuValue2)
      
      this.queryParams.courseTitles = []
      this.queryParams.courseTitles.push(item.key)
      this.loadData();
    }
    
  }
};
</script>

<style lang="less" scoped>
.preparation-page {
  width: 100%;
  height: 100%;
  overflow: hidden;
  background: #dbd8d8;
  .img-box {
    position: absolute;
    top:10px;
    left: 0px;
  }
  .header {
    background: #FF8D1A;
    height: 150px;
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
  .sider {
    min-height: 500px;
    background: #fff;
    .menu-title {
      color: #000;
      font-weight: 900;
    }
  }
  .content-head {
    width: 100%;
    height: 50px;
    background: #fff;
    .content-breadcrumb{
      padding-top: 10px;
    }
    .return-btn {
      float: right;
      margin-right: 40px;
    }
  }
  .content-table {
    margin:10px;
    height: 650px;
  }
  .footer {
    display: flex;
    justify-content: center;
  }
}
</style>
