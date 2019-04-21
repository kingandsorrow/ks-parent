<template>
  <!--标题-->
  <div class="header">
    <!--菜单-->
    <div class="nav">
      <p class="active" i="0">未支付订单</p>
      <p i="1">已支付订单</p>
    </div>
  </div>
  <div class="mescroll order_page" id="mescroll0">
    <!--首页-->
    <div id="mescroll0" class="mescroll">
      <div id="list1" class="list">
        <ul class="data-list" id="dataList0">
          <li v-for="item in orderList" :key="item.id">
            <div class="div1"><i></i> <span class="league" style="color: rgb(228, 158, 97);">澳超</span>
              <p class="time">04-20 17:50</p><span class="result"></span></div>
            <div class="div2"><span class="date">周六<br>013</span>
              <p class="team team1">
                中央海岸水手
              </p>
              <p class="score">VS</p>
              <p class="team team2">
                西悉尼漫步者
                <!----> <!----></p></div>
            <div class="div3"><p class="odds odds1"><span>1.91</span> <span class="span-active">*1</span>
              <span>1.99</span></p>
              <p class="score"></p>
              <p class="odds odds2"><span>4.35</span> <span>4.20</span> <span>1.45</span></p></div>
            <div class="div4"><p><span>+</span> 选单</p></div>
          </li>
        </ul>
      </div>
    </div>

    <!--奶粉专区 (如果是vue的话 这里写v-show不是v-if)-->
    <div id="mescroll1" class="mescroll hide">
      <div id="list1" class="list">
        <ul class="data-list" id="dataList1">
          <li v-for="item in orderList" :key="item.id">
            <div class="div1"><i></i> <span class="league" style="color: rgb(228, 158, 97);">澳超</span>
              <p class="time">04-20 17:50</p><span class="result"></span></div>
            <div class="div2"><span class="date">周六<br>013</span>
              <p class="team team1">
                中央海岸水手
              </p>
              <p class="score">VS</p>
              <p class="team team2">
                西悉尼漫步者
                <!----> <!----></p></div>
            <div class="div3"><p class="odds odds1"><span>1.91</span> <span class="span-active">*1</span>
              <span>1.99</span></p>
              <p class="score"></p>
              <p class="odds odds2"><span>4.35</span> <span>4.20</span> <span>1.45</span></p></div>
            <div class="div4"><p><span>+</span> 选单</p></div>
          </li>
        </ul>
      </div>
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
  import 'src/utils/mescroll.min.js'
  import 'src/style/mescroll.min.css'


  export default {
    data() {
      return {
        orderList: null, //订单列表
        offset: 0,
        preventRepeat: false,  //防止重复获取
        showLoading: true, //显示加载动画
        imgBaseUrl,
        curNavIndex: 0,
        mescrollArr: new Array(2)
      }
    },
    mounted() {
      this.initData();
    },
    mixins: [loadMore],
    components: {
      /*headTop,*/
      footGuide,
      loading,
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
      //初始化获取信息
      async initData() {
        var vm = this;
        vm.mescrollArr[0] = vm.initMescroll("mescroll0", "dataList0");
        /*初始化菜单*/
        $(".nav p").click(function () {
          var i = Number($(this).attr("i"));
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
        })
      },
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
      initMescroll(mescrollId, clearEmptyId) {
        //创建MeScroll对象,内部已默认开启下拉刷新,自动执行up.callback,刷新列表数据;
        var vm = this;
        var mescroll = new MeScroll(mescrollId, {
          //上拉加载的配置项
          up: {
            callback: vm.getListData, //上拉回调,此处可简写; 相当于 callback: function (page) { getListData(page); }
            isBounce: false, //此处禁止ios回弹,解析(务必认真阅读,特别是最后一点): http://www.mescroll.com/qa.html#q10
            noMoreSize: 4, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
            empty: {
              icon: "../res/img/mescroll-empty.png", //图标,默认null
              tip: "暂无相关数据~", //提示
              btntext: "去逛逛 >", //按钮,默认""
              btnClick: function () {//点击按钮的回调,默认null
                alert("点击了按钮,具体逻辑自行实现");
              }
            },
            clearEmptyId: clearEmptyId, //相当于同时设置了clearId和empty.warpId; 简化写法;默认null; 注意vue中不能配置此项
            toTop: { //配置回到顶部按钮
              src: "../res/img/mescroll-totop.png", //默认滚动到1000px显示,可配置offset修改
              //offset : 1000
            },
            lazyLoad: {
              use: true // 是否开启懒加载,默认false
            }
          }
        });
        return mescroll;
      }
    },
    /*联网加载列表数据  page = {num:1, size:10}; num:当前页 从1开始, size:每页数据条数 */
    getListData(page) {
      //联网加载数据
      var vm = this;
      var dataIndex = vm.curNavIndex; //记录当前联网的nav下标,防止快速切换时,联网回来curNavIndex已经改变的情况;
      vm.getListDataFromNet(dataIndex, page.num, page.size, function (pageData) {
        //联网成功的回调,隐藏下拉刷新和上拉加载的状态;
        //mescroll会根据传的参数,自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
        console.log("dataIndex=" + dataIndex + ", curNavIndex=" + curNavIndex + ", page.num=" + page.num + ", page.size=" + page.size + ", pageData.length=" + pageData.length);

        //方法一(推荐): 后台接口有返回列表的总页数 totalPage
        //mescrollArr[dataIndex].endByPage(pageData.length, totalPage); //必传参数(当前页的数据个数, 总页数)

        //方法二(推荐): 后台接口有返回列表的总数据量 totalSize
        //mescrollArr[dataIndex].endBySize(pageData.length, totalSize); //必传参数(当前页的数据个数, 总数据量)

        //方法三(推荐): 您有其他方式知道是否有下一页 hasNext
        //mescrollArr[dataIndex].endSuccess(pageData.length, hasNext); //必传参数(当前页的数据个数, 是否有下一页true/false)

        //方法四 (不推荐),会存在一个小问题:比如列表共有20条数据,每页加载10条,共2页.如果只根据当前页的数据个数判断,则需翻到第三页才会知道无更多数据,如果传了hasNext,则翻到第二页即可显示无更多数据.
        mescrollArr[dataIndex].endSuccess(pageData.length);

        //提示:pageData.length必传的原因:
        // 1.判断是否有下一页的首要依据: 当传的值小于page.size时,则一定会认为无更多数据.
        // 2.比传入的totalPage, totalSize, hasNext具有更高的判断优先级
        // 3.使配置的noMoreSize生效

        //设置列表数据
        setListData(pageData, dataIndex);
      }, function () {
        //联网失败的回调,隐藏下拉刷新和上拉加载的状态;
        mescrollArr[dataIndex].endErr();
      });
    },
    /*设置列表数据
			 * pageData 当前页的数据
			 * dataIndex 数据属于哪个nav */
    setListData(pageData, dataIndex) {
      var listDom = document.getElementById("dataList" + dataIndex);
      for (var i = 0; i < pageData.length; i++) {
        var pd = pageData[i];

        var str = '<img class="pd-img" src="../res/img/loading-sq.png" imgurl="' + pd.pdImg + '"/>';
        str += '<p class="pd-name">' + pd.pdName + '</p>';
        str += '<p class="pd-price">' + pd.pdPrice + ' 元</p>';
        str += '<p class="pd-sold">已售' + pd.pdSold + '件</p>';

        var liDom = document.createElement("li");
        liDom.innerHTML = str;
        listDom.appendChild(liDom);
      }
    },/*联网加载列表数据
			 在您的实际项目中,请参考官方写法: http://www.mescroll.com/api.html#tagUpCallback
			 请忽略getListDataFromNet的逻辑,这里仅仅是在本地模拟分页数据,本地演示用
			 实际项目以您服务器接口返回的数据为准,无需本地处理分页.
			 * */
    getListDataFromNet(curNavIndex, pageNum, pageSize, successCallback, errorCallback) {
      var vm = this;
      //延时一秒,模拟联网
      this.$http({
        url: this.$http.adornUrl('/orderList?curNavIndex=' + curNavIndex + '&pageNum=' + pageNum + "&pageSize=" + pageSize),
        method: 'get',
        params: this.$http.adornParams({})
      }).then(({data}) => {
        if (data && data.errCode === '0') {
          var listData = [];
          //curNavIndex 首页0; 奶粉1; 面膜2; 图书3;
          if (curNavIndex == 0) {
            //首页 (模拟分页数据)
            for (var i = (pageNum - 1) * pageSize; i < pageNum * pageSize; i++) {
              if (i == data.length) break;
              listData.push(data.orderBeanList, data.count);
            }

          } else if (curNavIndex == 1) {
            //奶粉
            for (var i = 0; i < data.length; i++) {
              if (data[i].pdName.indexOf("奶粉") != -1) {
                listData.push(data.orderBeanList, data.count);
              }
            }

          }

          //回调
          successCallback(listData);
        } else {
          this.orderList = []
        }
        this.hideLoading();
      })
      /*$.ajax({
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
      });*/
    },
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

</style>
