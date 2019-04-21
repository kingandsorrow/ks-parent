<template>
  <!--标题-->
  <div class="box">
    <!--菜单-->
    <ul class="tab">
      <li class="li-active" @click="switchLi(0)">未支付订单</li>
      <li class="" @click="switchLi(1)">已支付订单</li>
      <i></i></ul>
    <div>
      <!--首页-->
      <mescroll-vue ref="mescroll" :down="mescrollDown" :up="mescrollUp" @init="mescrollInit">
        <!--内容...-->
        <ul class="data-list match-content" id="dataList0">
          <li v-for="item in dataList" :key="item.shopName">
            <div class="div1"><span class="date">周六<br>013</span>
              <p class="team team1">
                {{item.shopName}}
              </p>
              <p class="score">VS</p>
              <p class="team team2">
                {{item.shopName}}
                <!----> <!----></p></div>
          </li>
        </ul>
      </mescroll-vue>
    </div>

    <!--奶粉专区 (如果是vue的话 这里写v-show不是v-if)-->
    <div id="mescroll1" class="mescroll hide">
      <ul class="data-list match-content" id="dataList0">
        <li v-for="item in orderList" :key="item.id">
          <div class="div1"><span class="date">周六<br>013</span>
            <p class="team team1">
              {{item.shopName}}
            </p>
            <p class="score">VS</p>
            <p class="team team2">
              {{item.shopName}}
              <!----> <!----></p></div>
        </li>
      </ul>
    </div>

    <!--<head-top head-title="订单列表" go-back='true'></head-top>-->
    <!--<ul id="dataList0" class="data-list">
      <li class="order_list_li" v-for="item in orderList" :key="item.id">
        <img :src="imgBaseUrl + item.imageUrl" class="restaurant_image">
        <section class="order_item_right">
          <section @click="showDetail(item)">
            <header class="order_item_right_header">
              <section class="order_header">
                <h4>
                  <span class="ellipsis">{{item.restaurant_name}} </span>
                  <svg fill="#333" class="arrow_right">
                    <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#arrow-right"></use>
                  </svg>
                </h4>
                <p class="order_time">{{item.formatted_created_at}}</p>
              </section>
              <p class="order_status">
                {{item.status_bar.title}}
              </p>
            </header>
            <section class="order_basket">
              <p class="order_name ellipsis">{{item.basket.group[0][0].name}}{{item.basket.group[0].length > 1 ? ' 等' +
                item.basket.group[0].length + '件商品' : ''}}</p>
              <p class="order_amount">¥{{item.total_amount.toFixed(2)}}</p>
            </section>
          </section>
          <div class="order_again">
            <compute-time v-if="item.status_bar.title == '等待支付'" :time="item.time_pass"></compute-time>
            <router-link :to="{path: '/shop', query: {geohash, id: item.restaurant_id}}" tag="span" class="buy_again"
                         v-else>再来一单
            </router-link>
          </div>
        </section>
      </li>
    </ul>-->
    <foot-guide></foot-guide>
    <!--<transition name="loading">
      <loading v-show="showLoading"></loading>
    </transition>-->
    <transition name="router-slid" mode="out-in">
      <router-view></router-view>
    </transition>

  </div>
</template>

<script>
  import {mapState, mapMutations} from 'vuex'
  import computeTime from 'src/components/common/computeTime'
  import {getImgPath} from 'src/components/common/mixin'
  import footGuide from 'src/components/footer/footGuide'
  import {getOrderList} from 'src/service/getData'
  import {imgBaseUrl} from 'src/config/env'
  import MescrollVue from 'mescroll.js/mescroll.vue'


  export default {
    data() {
      return {
        orderList: null, //订单列表
        /*offset: 0,
        preventRepeat: false,  //防止重复获取
        showLoading: true, //显示加载动画*/
        imgBaseUrl,
        /*curNavIndex: 0,
        mescrollArr: new Array(2),*/
        /*dataList: [] //列表数据*/
        mescroll: null, // mescroll实例对象
        mescrollDown: {},
        mescrollUp: { // 上拉加载的配置.
          callback: this.upCallback, // 上拉回调,此处简写; 相当于 callback: function(page, mescroll) { }
          //以下是一些常用的配置,当然不写也可以的.
          page: {
            num: 0, //当前页 默认0,回调之前会加1; 即callback(page)会从1开始
            size: 10 //每页数据条数,默认10
          },
          htmlNodata: '<p class="upwarp-nodata">-- END --</p>',
          noMoreSize: 5, //如果列表已无数据,可设置列表的总数量要大于5才显示无更多数据;
          toTop: {
            //回到顶部按钮
            src: "./static/mescroll/mescroll-totop.png", //图片路径,默认null,支持网络图
            offset: 1000 //列表滚动1000px才显示回到顶部按钮
          },
          empty: {
            //列表第一页无任何数据时,显示的空提示布局; 需配置warpId才显示
            warpId: "xxid", //父布局的id (1.3.5版本支持传入dom元素)
            icon: "./static/mescroll/mescroll-empty.png", //图标,默认null,支持网络图
            tip: "暂无相关数据~" //提示
          }
        },
        dataList: [] // 列表数据
      }
    },
    beforeRouteEnter(to, from, next) { // 如果没有配置回到顶部按钮或isBounce,则beforeRouteEnter不用写
      next(vm => {
        // 找到当前mescroll的ref,调用子组件mescroll-vue的beforeRouteEnter方法
        vm.$refs.mescroll && vm.$refs.mescroll.beforeRouteEnter() // 进入路由时,滚动到原来的列表位置,恢复回到顶部按钮和isBounce的配置
      })
    },
    beforeRouteLeave(to, from, next) { // 如果没有配置回到顶部按钮或isBounce,则beforeRouteLeave不用写
      // 找到当前mescroll的ref,调用子组件mescroll-vue的beforeRouteLeave方法
      this.$refs.mescroll && this.$refs.mescroll.beforeRouteLeave() // 退出路由时,记录列表滚动的位置,隐藏回到顶部按钮和isBounce的配置
      next()
    },
    components: {
      /*headTop,*/
      MescrollVue,
      footGuide,
      computeTime,
    },
    computed: {
      ...mapState([
        'userInfo', 'geohash'
      ]),
    },
    methods: {
      ...mapMutations([
        'SAVE_ORDER'
      ]),
      mescrollInit(mescroll) {
        this.mescroll = mescroll  // 如果this.mescroll对象没有使用到,则mescrollInit可以不用配置
      },
      upCallback(page, mescroll) {
        // 联网请求
        /*axios.get('xxxxxx', {
          params: {
            num: page.num, // 页码
            size: page.size // 每页长度
          }
        }).then((response) => {
          // 请求的列表数据
          let arr = response.data
          // 如果是第一页需手动制空列表
          if (page.num === 1) this.dataList = []
          // 把请求到的数据添加到列表
          this.dataList = this.dataList.concat(arr)
          // 数据渲染成功后,隐藏下拉刷新的状态
          this.$nextTick(() => {
            mescroll.endSuccess(arr.length)
          })
        }).catch((e) => {
          // 联网失败的回调,隐藏下拉刷新和上拉加载的状态;
          mescroll.endErr()
        })*/
        this.$http({
          url: this.$http.adornUrl('/orderList'),
          method: 'get',
          params: this.$http.adornParams({
            pageNum: page.num, // 页码
            pageSize: page.size, // 每页长度
            curNavIndex: 0
          })
        }).then(({data}) => {
          debugger;
          if (data && data.errCode === '0') {
            var listData = data.orderBeanList;
            //curNavIndex 首页0; 奶粉1; 面膜2; 图书3;
            if (page.num === 1) this.dataList = []
            // 把请求到的数据添加到列表
            this.dataList = this.dataList.concat(listData)
            // 数据渲染成功后,隐藏下拉刷新的状态
            this.$nextTick(() => {
              mescroll.endSuccess(listData.length)
            })
          } else {
            this.dataList = []
          }
          //this.hideLoading();
        }).catch((e) => {
          // 联网失败的回调,隐藏下拉刷新和上拉加载的状态;
          mescroll.endErr()
        })
      }
    },
    //初始化获取信息
    /*async initData() {
      var vm = this;
      vm.mescrollArr[0] = vm.initMescroll("mescroll0", "dataList0");
    },
    switchLi(i) {
      if (curNavIndex != i) {
        //更改列表条件
        $(".nav .active").removeClass("active");
        $(this).addClass("active");
        //隐藏当前列表和回到顶部按钮
        $("#mescroll" + curNavIndex).hide();
        mescrollArr[curNavIndex].hideTopBtn();
        //显示对应的列表
        $("#mescroll" + i).show();
        //取出菜单所对应的mescroll对象,如果未初始化则初始化
        if (mescrollArr[i] == null) {
          mescrollArr[i] = initMescroll("mescroll" + i, "dataList" + i);
        } else {
          //检查是否需要显示回到到顶按钮
          var curMescroll = mescrollArr[i];
          var curScrollTop = curMescroll.getScrollTop();
          if (curScrollTop >= curMescroll.optUp.toTop.offset) {
            curMescroll.showTopBtn();
          } else {
            curMescroll.hideTopBtn();
          }
        }
        //更新标记
        curNavIndex = i;
      }
    },*/
    //显示详情页
    showDetail(item) {
      this.SAVE_ORDER(item);
      this.preventRepeat = false;
      this.$router.push('/order/orderDetail');
    },
    //生产环境与发布环境隐藏loading方式不同
    hideLoading() {
      this.showLoading = false;
    },
    /*getListDataFromNet(curNavIndex, pageNum, pageSize, successCallback, errorCallback) {
      debugger
      var vm = this;

      /!*$.ajax({
        type: 'GET',
        url: '../res/pdlist1.json',
  //		                url: '../res/pdlist1.json?curNavIndex='+curNavIndex+'&num='+pageNum+'&size='+pageSize,
        dataType: 'json',
        success: function (data) {
          var listData = [];

          //curNavIndex 首页0; 奶粉1; 面膜2; 图书3;
          if (curNavIndex == 0) {
            //首页 (模拟分页数据)
            for (var i = (pageNum - 1) * pageSize; i < pageNum * pageSize; i++) {
              if (i == data.length) break;
              listData.push(data[i]);
            }

          } else if (curNavIndex == 1) {
            //奶粉
            for (var i = 0; i < data.length; i++) {
              if (data[i].pdName.indexOf("奶粉") != -1) {
                listData.push(data[i]);
              }
            }

          }

          //回调
          successCallback(listData);
        },
        error: errorCallback
      });*!/
    },*/

    watch: {
      userInfo: function (value) {
        if (value && value.user_id && !this.orderList) {
          this.initData();
        }
      }
    }
  }
</script>

<style lang="css" scoped>
  .box {
    width: 100%;
    height: 100%;
    margin-top: 0 !important;
    background-color: #F7F7F7;
  }

  .tab {
    height: 4.3733333333rem;
    line-height: 4.3733333333rem;
    -webkit-box-shadow: 0.1066666667rem 0.1066666667rem 0.2133333333rem #C0C0C0;
    box-shadow: 0.1066666667rem 0.1066666667rem 0.2133333333rem #C0C0C0;
    /* position: absolute; */
    z-index: 80;
    padding: 0 6.4rem;
    background: -webkit-gradient(linear, left top, right top, from(#ffbb33), to(#ffaa00));
    background: linear-gradient(90deg, #ffbb33, #ffaa00);
    position: relative;
  }

  .tab .li-active {
    font-size: 1.8133333333rem;
    font-weight: bold;
  }

  .tab li {
    float: left;
    height: 4.3733333333rem;
    line-height: 4.3733333333rem;
    font-size: 1.4933333333rem;
    width: 9.0666666667rem;
    text-align: center;
    color: #fff;
  }

  li {
    cursor: pointer;
  }

  li {
    list-style: none;
  }

  .list {
    margin-top: 0.5333333333rem;
  }

  .match-content {
    padding: 0 1.0666666667rem;
  }

  .match-content li {
    width: 37.3333333333rem;
    overflow: hidden;
    background: #ffffff;
    border-radius: 0.5333333333rem;
    margin-top: 0.5333333333rem;
    padding-left: 0.5333333333rem;
    position: relative;
  }

  .match-content li .div1 {
    height: 2.6666666667rem;
    display: box;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-align: center;
    -moz-align-items: center;
    -ms-align-items: center;
    -o-align-items: center;
    -ms-flex-align: center;
    align-items: center;
    position: relative;
  }

  .match-content li .div1 {
    height: 2.6666666667rem;
    display: box;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-align: center;
    -moz-align-items: center;
    -ms-align-items: center;
    -o-align-items: center;
    -ms-flex-align: center;
    align-items: center;
    -webkit-box-pack: center;
    -moz-justify-content: center;
    -ms-justify-content: center;
    -o-justify-content: center;
    -ms-flex-pack: center;
    justify-content: center;
    position: relative;
  }
</style>
