<template>
  <view class="container">

    <view class="top-box">

      <view class="top-nav">
        <view class="search-box">
          <input class="search-input" placeholder="搜索..." />
        </view>
        <view class="sign-in">
          <text>签到</text>
        </view>
      </view>
      

      <view class="user-info">
        <text class="username" style="font-size: 20px;color: #fff;">陈先生</text>
        <text class="mood"> · 今日心情</text>
      </view>
      

      <view class="score-container">

        <view class="total-score">
          <view class="semi-circle">
            <text class="score-number">83</text>
          </view>
          <text class="score-label">综合分数</text>
        </view>
        
       
        <view class="category-scores">
          <view class="score-item" v-for="item in scores" :key="item.name">
            <view class="progress-bar">
              <view 
                class="progress-fill" 
                :style="{ height: item.value + '%', backgroundColor: item.color }"
              ></view>
            </view>
            <text class="score-value">{{ item.value }}</text>
            <text class="score-name">{{ item.name }}</text>
          </view>
        </view>
      </view>
      

      <view class="mood-status">
        <text>今天情绪基本稳定、正向，继续保持良好状态，工作效率较高，人际关系和谐。</text>
      </view>
    </view>
    

    <view class="function-grid">
      <view class="grid-item" v-for="item in functions" :key="item.name">
        <image class="grid-icon" src="/static/typeimages/vip.png" mode="aspectFit"></image>
        <text class="grid-text">{{ item.name }}</text>
      </view>
    </view>
	
	
	
	
	
	
	
	

	  <view class="post-container">

	    <scroll-view class="category-scroll" scroll-x>
	      <view 
	        class="category-item" 
	        v-for="(item, index) in categories" 
	        :key="index"
	        :class="{ active: currentCategory === index }"
	        @click="changeCategory(index)"
	      >
	        <text>{{ item }}</text>
	      </view>
	    </scroll-view>
	

	    <view class="post-list">
	      <view 
	        class="post-item" 
	        v-for="(post, index) in posts" 
	        :key="index"
			@click="toArticleDetail"
	      >
	        <image class="post-image" src="/static/adaf2edda3cc7cd9f3006fa7dffd8631b90e91ae.jpeg" mode="aspectFill"></image>
	        <text class="post-title">{{ post.title }}</text>
	        <view class="post-footer">
	          <view class="user-info">
	            <image class="avatar" src="/static/newicons/avatar.png" mode="aspectFill"></image>
	            <text class="username">{{ post.username }}</text>
	          </view>
	          <view class="like-info">
	            <u-icon name="thumb-up" size="28" color="#999"></u-icon>
	            <text class="like-count">{{ post.likeCount }}</text>
	          </view>
	        </view>
	      </view>
	    </view>
	  </view>
	
	
	
	
	<tabBar :pagePath="'/pages/index/index'"></tabBar>
  </view>
</template>

<script>
export default {
  data() {
    return {
      scores: [
        { name: '爱情', value: 89, color: '#FF6B6B' },
        { name: '财富', value: 78, color: '#FFD166' },
        { name: '事业', value: 87, color: '#9B59B6' },
        { name: '学习', value: 81, color: '#54C1FB' }
      ],
      functions: [
        { name: '星座', icon: '/static/constellation.png' },
        { name: '星图', icon: '/static/starmap.png' },
        { name: '沙盘', icon: '/static/sandbox.png' },
        { name: '测试', icon: '/static/test.png' },
        { name: '冥想', icon: '/static/meditation.png' },
        { name: '日记', icon: '/static/diary.png' },
		{ name: '冥想', icon: '/static/meditation.png' },
		{ name: '日记', icon: '/static/diary.png' }
      ],
	  
	        currentCategory: 0,
	        categories: ['关注', '推荐', '星座', '测试', '心理', '交友'],
	        posts: [
	          {
	            image: '/static/post1.jpg',
	            title: '如何保持心理健康？这些小技巧很实用',
	            avatar: '/static/avatar1.jpg',
	            username: '心理专家',
	            likeCount: 245
	          },
	          {
	            image: '/static/post2.jpg',
	            title: '星座运势：本周水瓶座将迎来转机',
	            avatar: '/static/avatar2.jpg',
	            username: '星座达人',
	            likeCount: 189
	          },
	          {
	            image: '/static/post3.jpg',
	            title: '心理测试：测测你的抗压能力有多强',
	            avatar: '/static/avatar3.jpg',
	            username: '测试君',
	            likeCount: 312
	          },
	          {
	            image: '/static/post4.jpg',
	            title: '交友指南：如何建立健康的人际关系',
	            avatar: '/static/avatar4.jpg',
	            username: '社交顾问',
	            likeCount: 156
	          }
	        ]
    }
  },
   methods: {
      changeCategory(index) {
        this.currentCategory = index;
        
      },
	  toArticleDetail(){
		  uni.navigateTo({
		  	url:'/pages/articleDetail/articleDetail'
		  })
	  }
    }
}
</script>

<style>

page {
  background-color: #FEFEFE;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.container {
  padding: 0;
  position: relative;
}


.top-box {
  background: linear-gradient(to right, #8EA1FF, #9BD0FF);
  padding: 30rpx 30rpx 120rpx; 
  border-bottom-left-radius: 40rpx;
  border-bottom-right-radius: 40rpx;
  box-shadow: 0 10rpx 20rpx rgba(142, 161, 255, 0.2);
  position: relative;
  z-index: 1;
}


.top-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 60rpx;
}

.search-box {
  flex: 1;
  margin-right: 20rpx;
}

.search-input {
  background-color: rgba(255, 255, 255, 0.3);
  border-radius: 50rpx;
  padding: 15rpx 30rpx;
  color: white;
  font-size: 28rpx;
}

.search-input::placeholder {
  color: rgba(255, 255, 255, 0.7);
}

.sign-in {
  background-color: white;
  border-radius: 30rpx;
  padding: 10rpx 25rpx;
}

.sign-in text {
  color: #8EA1FF;
  font-size: 26rpx;
  font-weight: bold;
}


.user-info {
  margin-bottom: 50rpx;
  display: flex;
  align-items: center;
}

.username {
  color: white;
  font-size: 48rpx;
  font-weight: bold;
}

.mood {
  color: white;
  font-size: 28rpx;
  opacity: 0.8;
  margin-left: 10rpx;
  margin-top: 8rpx;
}


.score-container {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  margin-bottom: 40rpx;
}


.total-score {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 45%; 
}

.semi-circle {
  width: 300rpx; 
  height: 180rpx; 
  border-radius: 180rpx 180rpx 0 0; 
  background-color: rgba(255, 255, 255, 0.2);
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 15rpx;
 
}

.score-number {
  color: white;
  font-size: 100rpx; 
  font-weight: bold;
  margin-top: 30rpx; 
}

.score-label {
  color: white;
  font-size: 35rpx;
}

/* 分类分数 */
.category-scores {
  display: flex;
  justify-content: space-between;
  width: 50%; 
}

.score-item {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.progress-bar {
  width: 50rpx; 
  height: 140rpx; 
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 20rpx;
  overflow: hidden;
  position: relative;
  margin-bottom: 10rpx;
}

.progress-fill {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  border-radius: 20rpx;
}

.score-value {
  color: white;
  font-size: 30rpx; 
  margin-bottom: 5rpx;
  font-weight: bold;
}

.score-name {
  color: white;
  font-size: 26rpx; 
}


.mood-status {
  margin-top: 30rpx;
}

.mood-status text {
  color: white;
  font-size: 28rpx;
  line-height: 1.6;
  display: block;
}


.function-grid {
  background-color: white;
  border-radius: 20rpx;
  padding: 30rpx;
  margin: -60rpx 30rpx 30rpx; 
  box-shadow: 0 10rpx 30rpx rgba(0, 0, 0, 0.1); 
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  position: relative;
  z-index: 2;
}

.grid-item {
  width: 25%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 30rpx;
}

.grid-icon {
  width: 80rpx;
  height: 80rpx;
  margin-bottom: 15rpx;
}

.grid-text {
  color: #666;
  font-size: 26rpx;
}


.post-container {
  background-color: white;
  padding: 30rpx;
  margin-top: 20rpx;
}


.category-scroll {
  white-space: nowrap;
  width: 100%;
  padding-bottom: 20rpx;
}

.category-item {
  display: inline-block;
  margin-right: 50rpx;
  font-size: 30rpx;
  color: #666;
  position: relative;
  padding-bottom: 10rpx;
}

.category-item:last-child {
  margin-right: 0;
}

.category-item.active {
  font-weight: bold;
  color: #333;
}

.category-item.active::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 6rpx;
  background-color: #37BDDA;
  border-radius: 3rpx;
}


.post-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.post-item {
  width: 48%;
  margin-bottom: 30rpx;
  border-radius: 12rpx;
  overflow: hidden;
  box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.08);
}

.post-image {
  width: 100%;
  height: 440rpx;
  display: block;
}

.post-title {
  display: block;
  padding: 20rpx;
  font-size: 28rpx;
  color: #333;
  line-height: 1.4;
}

.post-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20rpx 20rpx;
}

.user-info {
  display: flex;
  align-items: center;
}

.avatar {
  width: 40rpx;
  height: 40rpx;
  border-radius: 50%;
  margin-right: 10rpx;
}

.username {
  font-size: 24rpx;
  color: #999;
}

.like-info {
  display: flex;
  align-items: center;
}

.like-count {
  font-size: 24rpx;
  color: #999;
  margin-left: 6rpx;
}


</style>