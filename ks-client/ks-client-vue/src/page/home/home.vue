<template>
  <div>
    <head-top signin-up='home'>
      <span slot='logo' class="head_logo" @click="reload">ks.mall</span>
    </head-top>
    <!--<nav class="city_nav">
        <div class="city_tip">
            <span>当前定位城市：</span>
            <span>定位不准时，请在城市列表中选择</span>
        </div>
        <router-link :to="'/city/' + guessCityid" class="guess_city">
            <span>{{guessCity}}</span>
            <svg class="arrow_right">
                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#arrow-right"></use>
            </svg>
        </router-link>
    </nav>-->
    <mescroll-vue ref="mescroll" :up="mescrollUp" @init="mescrollInit">
      <!--模拟的内容-->
      <img src="../../../static/img/zhihu2.jpg"/>
      <img src="../../../static/img/zhihu3.jpg"/>
      <img src="../../../static/img/zhihu4.jpg"/>
      <!--展示上拉加载的数据列表-->
      <ul id="newsList" class="news-list">
        <!--<li>
                    <p>【话题1】标题标题标题标题</p>
                    <p class="new-content">内容内容内容内容内容内容内容内容内容</p>
                </li>-->
      </ul>
    </mescroll-vue>
    <div class="footer"></div>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'
  // import {cityGuess, hotcity, groupcity} from '../../service/getData'
  import MescrollVue from 'mescroll.js/mescroll.vue'

  export default {
    name: 'mescrollComponent',
    components: {
      headTop, MescrollVue
    },
    data() {
      return {
        mescroll: null, // mescroll实例对象
        mescrollUp: {
          callback: this.upCallback, // 上拉回调,此处可简写; 相当于 callback: function (page, mescroll) { getListData(page); }
          page: {
            num: 0, // 当前页码,默认0,回调之前会加1,即callback(page)会从1开始
            size: 10 // 每页数据的数量
          },
          noMoreSize: 5, // 如果列表已无数据,可设置列表的总数量要大于等于5条才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看
          toTop: {
            src: './static/mescroll/mescroll-totop.png' // 回到顶部按钮的图片路径,支持网络图
          },
          empty: {
            // 列表第一页无任何数据时,显示的空提示布局; 需配置warpId才生效;
            warpId: 'dataList', // 父布局的id;
            icon: './static/mescroll/mescroll-empty.png', // 图标,支持网络图
            tip: '暂无相关数据~', // 提示
            btntext: '去逛逛 >', // 按钮,默认""
            btnClick() { // 点击按钮的回调,默认null
              alert('点击了按钮,具体逻辑自行实现')
            }
          },
          lazyLoad: {
            use: true // 是否开启懒加载,默认false
          }
        },
        dataList: [], // 列表数据
        pdType: 0 // 菜单
      }
    },
    beforeRouteEnter(to, from, next) { // 如果没有配置回到顶部按钮或isBounce,则beforeRouteEnter不用写
      next(vm => {
        // 找到当前mescroll的ref,调用子组件mescroll-vue的beforeRouteEnter方法
        vm.$refs.mescroll && vm.$refs.mescroll.beforeRouteEnter() // 进入路由时,滚动到原来的列表位置,恢复回到顶部按钮和isBounce的配置
      })
    },
    beforeRouteLeave(to, from, next) { // 如果没有配置回到顶部按钮或isBounce,则beforeRouteLeave不用写
      // 找到当前mescroll的ref,调用子组件mescroll-vue的beforeRouteEnter方法
      this.$refs.mescroll && this.$refs.mescroll.beforeRouteLeave() // 退出路由时,记录列表滚动的位置,隐藏回到顶部按钮和isBounce的配置
      next()
    },
    mounted() {
    },
    computed: {},
    methods: {
      // mescroll组件初始化的回调,可获取到mescroll对象
      mescrollInit(mescroll) {
        this.mescroll = mescroll
      },
      // 上拉回调 page = {num:1, size:10}; num:当前页 ,默认从1开始; size:每页数据条数,默认10
      upCallback(page, mescroll) {
        // 模拟联网
        this.getListDataFromNet(this.pdType, page.num, page.size, (arr, total) => {
          // 如果是第一页需手动制空列表
          if (page.num === 1) this.dataList = []
          // 把请求到的数据添加到列表
          this.dataList = this.dataList.concat(arr)
          // 数据渲染成功后,隐藏下拉刷新的状态
          this.$nextTick(() => {
            mescroll.endByPage(arr.length, total)
          })
        }, () => {
          // 联网异常,隐藏上拉和下拉的加载进度
          mescroll.endErr()
        })
      },
      /* 联网加载列表数据
         在您的实际项目中,请参考官方写法: http://www.mescroll.com/api.html#tagUpCallback
         请忽略getListDataFromNet的逻辑,这里仅仅是在本地模拟分页数据,本地演示用
         实际项目以您服务器接口返回的数据为准,无需本地处理分页.
         * */
      getListDataFromNet(pdType, pageNum, pageSize, successCallback, errorCallback) {
        var listData = []
        // pdType 全部商品0; 奶粉1; 图书2;
        /*this.$http({
          url: this.$http.adornUrl('/orderList'),
          method: 'get',
          params: this.$http.adornParams({
            pageNum: pageNum, // 页码
            pageSize: pageSize, // 每页长度
            curNavIndex: pdType
          })
        }).then(({data}) => {
          if (data && data.errCode === '0') {
            var arr = data.orderBeanList
            for (var i = 0; i < arr.length; i++) {
              listData.push(arr[i]);
            }
            successCallback(listData, data.total)
          }

        }).catch((e) => {
          // 联网失败的回调,隐藏下拉刷新和上拉加载的状态;
          errorCallback && errorCallback()
        })*/
      },
      //点击图标刷新页面
      reload() {
        window.location.reload();
      }

    }
  }

</script>

<style lang="css" scoped>

  * {
    margin: 0;
    padding: 0;
    -webkit-touch-callout:none;
    -webkit-user-select:none;
    -webkit-tap-highlight-color:transparent;
  }
  body{
    background-color: white
  }
  ul{
    list-style-type: none
  }
  img{
    width: 100%;
    vertical-align: bottom;
  }
  /*模拟的标题,底部*/
  .header{
    z-index: 9990;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 3.6666666666666665rem;
    border-bottom: 0.08333333333333333rem solid #eee;
    background-image: url(../../../static/img/zhihu1.jpg);
    background-size: contain;
    background-position: center;
    background-repeat: no-repeat;
    background-color: white;
  }
  .footer{
    z-index: 9990;
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 4.166666666666667rem;
    border-top: 0.08333333333333333rem solid #eee;
    background-image: url(../../../static/img/zhihu5.jpg);
    background-size: contain;
    background-position: center;
    background-repeat: no-repeat;
    background-color: white;
  }
  /*列表*/
  .mescroll{
    position: fixed;
    top: 3.6666666666666665rem;
    bottom: 4.25rem;
    height: auto;
  }
  /*回到顶部按钮*/
  .mescroll-totop {
    bottom: 5.833333333333333rem;
  }
  /*下拉刷新回调的提示*/
  .download-tip{
    z-index: 9900;
    position: fixed;
    top: 1.6666666666666667rem;
    left: 0;
    width: 100%;
    height: 2rem;
    line-height: 2rem;
    font-size: 1rem;
    text-align: center;
    background-color: rgba(80,175,85,.7);
    color: white;
    -webkit-transition: top 300ms;
    transition: top 300ms;
  }
  /*展示上拉加载的数据列表*/
  .news-list li{
    padding: 1.3333333333333333rem;
    border-bottom: 0.08333333333333333rem solid #eee;
  }
  .news-list .new-content{
    font-size: 1.1666666666666667rem;
    margin-top: 0.5rem;
    margin-left: 0.8333333333333334rem;
    color: #666;
  }

</style>
