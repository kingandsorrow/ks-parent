<template>
  <header id='head_top'>
    <!--<slot name='logo'></slot>
    <slot name='search'></slot>-->
    <!--<section class="head_goback" v-if="goBack" @click="$router.go(-1)">
      <svg width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" version="1.1">
        <polyline points="12,18 4,9 12,0" style="fill:none;stroke:rgb(255,255,255);stroke-width:2"/>
      </svg>
    </section>
    <router-link :to="userInfo? '/profile':'/login'" v-if='signinUp' class="head_login">
      <svg class="user_avatar" v-if="userInfo">
        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#user"></use>
      </svg>
      <span class="login_span" v-else>登录|注册</span>
    </router-link>
    <section class="title_head ellipsis" v-if="headTitle">
      <span class="title_text">{{headTitle}}</span>
    </section>-->
    <!--375 44-->
    <div class="header">
      <div class="location">西安</div>
      <div class="search bar5">
        <input type="text" placeholder="请输入您要搜索的内容...">
        <button type="submit"></button>
      </div>
      <div class="profile">KS</div>
    </div>
    <!--<slot name="edit"></slot>
    <slot name="msite-title"></slot>
    <slot name="changecity"></slot>
    <slot name="changeLogin"></slot>-->
  </header>
</template>

<script>
  import {mapState, mapActions} from 'vuex'

  export default {
    data() {
      return {}
    },
    mounted() {
      //获取用户信息
      /*this.getUserInfo();*/

    },
    props: ['signinUp', 'headTitle', 'goBack'],
    computed: {
      /*...mapState([
        'userInfo'
      ]),*/
    },
    methods: {
      /*...mapActions([
        'getUserInfo'
      ]),*/
    },

  }

</script>

<style lang="css" scoped>

  /*@import '../../style/mixin';

  #head_top {
    background-color: $blue;
    position: fixed;
    z-index: 100;
    left: 0;
    top: 0;
    @include wh(100%, 1.95rem);
  }

  .head_goback {
    left: 0.4rem;
    @include wh(0.6rem, 1rem);
    line-height: 2.2rem;
    margin-left: .4rem;
  }

  .head_login {
    right: 0.55rem;
    @include sc(0.65rem, #fff);
    @include ct;

    .login_span {
      color: #fff;
    }

    .user_avatar {
      fill: #fff;
      @include wh(.8rem, .8rem);
    }
  }

  .title_head {
    @include center;
    width: 50%;
    color: #fff;
    text-align: center;

    .title_text {
      @include sc(0.8rem, #fff);
      text-align: center;
      font-weight: bold;
    }
  }*/
  .header {
    z-index: 9990;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 44px;
    border-bottom: 1px solid #eee;
    float: left;
    /*background-image: url(../../../static/img/zhihu1.jpg);*/
    /*background-size: contain;
    background-position: center;
    background-repeat: no-repeat;
    background-color: white;*/
  }

  .location {
    width: 13%;
    line-height: 44px;
    float: left;
    text-align: center;
  }

  .profile {
    width: 13%;
    line-height: 44px;
    float: left;
    text-align: center;
  }

  .search {
    width: 73%;
    line-height: 44px;
    float: left;
  }

  * {
    box-sizing: border-box;
  }

  body {
    margin: 0;
    padding: 0;
    background: #494A5F;
    font-weight: 500;
    font-family: "Microsoft YaHei", "宋体", "Segoe UI", "Lucida Grande", Helvetica, Arial, sans-serif, FreeSans, Arimo;
  }

  #container {
    width: 500px;
    height: 820px;
    margin: 0 auto;
  }

  /*div.search {padding: 30px 0;}*/
  form {
    position: relative;
    width: 300px;
    margin: 0 auto;
  }

  input, button {
    border: none;
    outline: none;
  }

  input {
    width: 100%;
    height: 33px;
    padding-left: 13px;
    padding-right: 46px;
  }

  button {
    height: 33px;
    width: 42px;
    cursor: pointer;
    position: absolute;
  }

  /*搜索框5*/
  .bar5 {
  }

  .bar5 input, .bar5 button {
    background: transparent;
  }

  .bar5 input {
    border: 2px solid #F9F0DA;
  }

  .bar5 button {
    top: 5px;
    left: 280px;
  }

  .bar5 button:before {
    content: "\f002";
    font-family: FontAwesome;
    font-size: 16px;
    color: #F9F0DA;
  }

  .bar5 input:focus {
    border-color: #311c24
  }
</style>
