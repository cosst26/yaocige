<template>
  <view class="container">
 
    <view class="user-info">
      <view class="avatar-container">
        <view class="avatar">
          <image class="avatar-img" src="/static/newicons/avatar.png" mode="aspectFill"></image>
          <view class="verified">V</view>
        </view>
      </view>
      <view class="user-detail">
        <view class="username">用户名</view>
        <view class="user-meta">
          <text class="date">2023-06-15</text>
          <text class="location">黑龙江·xxx达人</text>
        </view>
      </view>
      <view class="follow-btn">
        <text>+关注</text>
      </view>
    </view>


    <view class="article-content">
      <view class="tag">#星宿都在修什么?</view>
      <view class="content">友.衰的相处模式</view>
      <image class="article-image" src="/static/adaf2edda3cc7cd9f3006fa7dffd8631b90e91ae.jpeg" mode="widthFix"></image>
    </view>

 
    <view class="comment-section">
      <view class="comment-title">全部评论（1）</view>
      
      <view class="comment-item">
        <view class="comment-avatar">
          <image class="avatar-img" src="/static/newicons/avatar.png" mode="aspectFill"></image>
        </view>
        <view class="comment-content">
          <view class="comment-user">
            <text class="comment-username">user</text>
            <view class="gender-tag" :style="{backgroundColor: '#FFB6C1'}">
              <u-icon name="woman" color="#FF1493" size="28"></u-icon>
            </view>
            <text class="constellation">狮子座</text>
          </view>
          <view class="comment-text">这是一条评论内容，友衰关系确实很有意思</view>
        </view>
      </view>
	  
	  
	  <view class="comment-item">
	    <view class="comment-avatar">
	      <image class="avatar-img" src="/static/newicons/avatar.png" mode="aspectFill"></image>
	    </view>
	    <view class="comment-content">
	      <view class="comment-user">
	        <text class="comment-username">user</text>
	        <view class="gender-tag" :style="{backgroundColor: '#FFB6C1'}">
	          <u-icon name="woman" color="#FF1493" size="28"></u-icon>
	        </view>
	        <text class="constellation">狮子座</text>
	      </view>
	      <view class="comment-text">这是一条评论内容，友衰关系确实很有意思</view>
	    </view>
	  </view>
	  
	  
	  <view class="comment-item">
	    <view class="comment-avatar">
	      <image class="avatar-img" src="/static/newicons/avatar.png" mode="aspectFill"></image>
	    </view>
	    <view class="comment-content">
	      <view class="comment-user">
	        <text class="comment-username">user</text>
	        <view class="gender-tag" :style="{backgroundColor: '#b7d8ff'}">
	          <u-icon name="man" color="#55aaff" size="28"></u-icon>
	        </view>
	        <text class="constellation">金牛座</text>
	      </view>
	      <view class="comment-text">这是一条评论内容，友衰关系确实很有意思</view>
	    </view>
	  </view>
	  
	  
    </view>


    <view class="bottom-bar">
      <view class="comment-input">
        <input placeholder="写下你的评论..." placeholder-class="placeholder" />
      </view>
      <view class="action-icons">
        <view class="icon-item">
          <u-icon name="thumb-up" size="42"></u-icon>
          <text class="icon-count">12</text>
        </view>
        <view class="icon-item">
          <u-icon name="chat" size="42"></u-icon>
          <text class="icon-count">5</text>
        </view>
        <view class="icon-item">
          <u-icon name="star" size="42"></u-icon>
          <text class="icon-count">8</text>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
    
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  padding: 30rpx 40rpx 150rpx;
  font-size: 30rpx;
}

.user-info {
  display: flex;
  align-items: center;
  margin-bottom: 40rpx;
}

.avatar-container {
  position: relative;
  margin-right: 30rpx;
}

.avatar {
  width: 100rpx;
  height: 100rpx;
  border-radius: 50%;
  overflow: hidden;
  position: relative;
}

.avatar-img {
  width: 100%;
  height: 100%;
}

.verified {
  position: absolute;
  bottom: 0;
  right: 0;
  width: 32rpx;
  height: 32rpx;
  background-color: #7985F8;
  border-radius: 50%;
  color: white;
  font-size: 22rpx;
  display: flex;
  align-items: center;
  justify-content: center;
  transform: translate(20%, 20%);
}

.user-detail {
  flex: 1;
}

.username {
  font-size: 34rpx;
  font-weight: bold;
  margin-bottom: 8rpx;
}

.user-meta {
  font-size: 26rpx;
  color: #999;
}

.user-meta .date::after {
  content: "·";
  margin: 0 10rpx;
}

.follow-btn {
  border: 1rpx solid #82C8E4;
  border-radius: 40rpx;
  color: #82C8E4;
  padding: 12rpx 30rpx;
  font-size: 28rpx;
  background-color: white;
}

.article-content {
  margin-bottom: 50rpx;
}

.tag {
  color: #82C8E4;
  font-size: 32rpx;
  margin-bottom: 30rpx;
}

.content {
  font-size: 36rpx;
  line-height: 1.8;
  margin-bottom: 40rpx;
}

.article-image {
  width: 100%;
  border-radius: 16rpx;
}

.comment-section {
  border-top: 1rpx solid #eee;
  padding-top: 40rpx;
}

.comment-title {
  font-size: 36rpx;
  font-weight: bold;
  margin-bottom: 40rpx;
}

.comment-item {
  display: flex;
  margin-bottom: 40rpx;
}

.comment-avatar {
  width: 80rpx;
  height: 80rpx;
  border-radius: 50%;
  overflow: hidden;
  margin-right: 30rpx;
}

.comment-content {
  flex: 1;
}

.comment-user {
  display: flex;
  align-items: center;
  margin-bottom: 15rpx;
}

.comment-username {
  font-size: 32rpx;
  font-weight: bold;
  margin-right: 20rpx;
}

.gender-tag {
  width: 44rpx;
  height: 44rpx;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 20rpx;
}

.constellation {
  font-size: 28rpx;
  color: #999;
}

.comment-text {
  font-size: 32rpx;
  line-height: 1.6;
}

.bottom-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  height: 120rpx;
  background-color: white;
  border-top: 1rpx solid #eee;
  display: flex;
  align-items: center;
  padding: 0 40rpx;
  box-shadow: 0 -4rpx 20rpx rgba(0, 0, 0, 0.08);
  z-index: 100;
}

.comment-input {
  flex: 1;
  background-color: #F7F7F7;
  border-radius: 60rpx;
  padding: 0 40rpx;
  height: 80rpx;
  display: flex;
  align-items: center;
  margin-right: 40rpx;
}

.placeholder {
  color: #DEDEDE;
  font-size: 32rpx;
}

.action-icons {
  display: flex;
  align-items: center;
}

.icon-item {
  display: flex;
  align-items: center;
  margin-left: 40rpx;
}

.icon-count {
  font-size: 28rpx;
  margin-left: 10rpx;
  color: #666;
  font-weight: 500;
}
</style>