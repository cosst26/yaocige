<template>
  <view class="container" style="background-color: #E3CCFF;">

    <view class="nav-container">
      <scroll-view class="nav-scroll" scroll-x="true" show-scrollbar="false">
        <view 
          v-for="(item, index) in navList" 
          :key="index" 
          class="nav-item" 
          :class="{ active: currentNav === index }"
          @click="changeNav(index)"
        >
          {{ item }}
        </view>
      </scroll-view>
    </view>
    

    <view class="content">
 
      <view class="module">
        <view class="title">明珠出海格</view>
        <view class="subtitle">利文化艺术，可名利双收</view>
        
        <view class="white-cards">
          <view class="white-card" v-for="(item, index) in cards" :key="index">
            <view class="card-left">
              <text class="card-title">{{ item.title }}</text>
              <text class="card-desc">{{ item.desc }}</text>
            </view>
            <image class="card-icon" src="/static/星系星座.png" mode="aspectFit"></image>
          </view>
        </view>
      </view>
      

      <view class="disclaimer">
        当前内容为免费内容，仅供您在娱乐中探索自我，不等于专业测评，不代表价值评判，无任何现实指导意义
      </view>
      

      <view class="comment-module" v-for="item in 3">
        <view class="comment-header">
          <image class="avatar" src="/static/星座.png" mode="aspectFit"></image>
          <text class="comment-title">命宫·天钺</text>
        </view>
        
        <view class="comment-content-bg">
          <text class="comment-content">天钺五行属丁火，为科名之星，主声名远播。</text>
        </view>
        
        <view class="comment-detail">
          <text class="comment-text">
            天钺星在命宫一般代表你的个性温文尔雅，正直、心善，你上进心较强，也较为乐观。男命的气质可能偏明柔，女命比较受欢迎，也爱打扮自己
          </text>
          <text class="read-more">...全文</text>
        </view>
        
        <view class="divider"></view>
        
        <view class="discussion">
          <text class="discussion-title">同类交流地</text>
          <text class="discussion-count">·3426测友正在讨论</text>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      currentNav: 0,
      navList: ['生辰', '星宿', '紫微', '政余', '生肖', '八字', '面相', '手相'],
      cards: [
        { title: '命宫', desc: '无主量' },
        { title: '身宫', desc: '天同' },
        { title: '福德', desc: '太阳' },
        { title: '事业', desc: '天机' }
      ]
    }
  },
  methods: {
    changeNav(index) {
      this.currentNav = index
    }
  }
}
</script>

<style>

.container {
  min-height: 100vh;
  padding: 20rpx;
  box-sizing: border-box;
}


.nav-container {
  margin-bottom: 30rpx;
}
.nav-scroll {
  white-space: nowrap;
  width: 100%;
}
.nav-item {
  display: inline-block;
  padding: 20rpx 30rpx;
  font-size: 28rpx;
  color: #a781c6;
  position: relative;
}
.nav-item.active {
  color: #7a518c;
  font-weight: bold;
}
.nav-item.active::after {
  content: '';
  position: absolute;
  bottom: 10rpx;
  left: 50%;
  transform: translateX(-50%);
  width: 40rpx;
  height: 4rpx;
  background-color: #7a518c;
  border-radius: 2rpx;
}


.content {
  padding-bottom: 40rpx;
}

.module {
  /* background-color: white; */
  border-radius: 16rpx;
  padding: 30rpx;
  margin-bottom: 20rpx;
  text-align: center;
}

.title {
  font-size: 36rpx;
  font-weight: bold;
  margin-bottom: 10rpx;
}

.subtitle {
  font-size: 28rpx;
  color: #999;
  margin-bottom: 30rpx;
}

.white-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.white-card {
  background-color: white;
  border-radius: 12rpx;
  padding: 30rpx 20rpx;
  width: 48%;
  margin-bottom: 20rpx;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 4rpx 12rpx rgba(0,0,0,0.05);
}

.card-left {
  display: flex;
  flex-direction: column;
}

.card-title {
  font-size: 30rpx;
  font-weight: bold;
  margin-bottom: 8rpx;
}

.card-desc {
  font-size: 24rpx;
  color: #999;
}

.card-icon {
  width: 60rpx;
  height: 60rpx;
}

.disclaimer {
  background-color: #fff;
  color: #999;
  font-size: 24rpx;
  padding: 20rpx;
  border-radius: 12rpx;
  /* text-align: center; */
  margin-bottom: 20rpx;
}

/* 评论模块样式 */
.comment-module {
  background-color: white;
  border-radius: 16rpx;
  padding: 30rpx;
  margin-bottom: 10px;
}

.comment-header {
  display: flex;
  align-items: center;
  margin-bottom: 20rpx;
}

.avatar {
  width: 80rpx;
  height: 80rpx;
  border-radius: 50%;
  margin-right: 20rpx;
}

.comment-title {
  font-size: 32rpx;
  font-weight: bold;
}

.comment-content-bg {
  background-color: #F7F7F7;
  padding: 20rpx;
  border-radius: 8rpx;
  margin-bottom: 20rpx;
}

.comment-content {
  font-size: 28rpx;
  color: #333;
}

.comment-detail {
  position: relative;
  margin-bottom: 30rpx;
}

.comment-text {
  font-size: 28rpx;
  color: #333;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
  text-overflow: ellipsis;
}

.read-more {
  position: absolute;
  right: 0;
  bottom: 0;
  color: #ACE2E5;
  font-size: 28rpx;
  background-color: white;
  padding-left: 10rpx;
}

.divider {
  height: 1rpx;
  background: linear-gradient(to right, transparent, #CEA8F0, transparent);
  margin: 20rpx 0;
}

.discussion {
  display: flex;
  align-items: center;
}

.discussion-title {
  font-size: 28rpx;
  font-weight: bold;
}

.discussion-count {
  font-size: 28rpx;
  color: #999;
  margin-left: 10rpx;
}
</style>