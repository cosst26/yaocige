<template>
	<view class="container">

		<view class="user-info">
			<view class="avatar-container">
				<view class="avatar">
					<image class="avatar-img" :src="isData.avatar" mode="aspectFill"></image>
					<view class="verified">V</view>
				</view>
			</view>
			<view class="user-detail">
				<view class="username">{{isData.userName}}</view>
				<view class="user-meta">
					<text class="date">{{dateString(isData.createdAt)}}</text>
					<text class="location">黑龙江·xxx达人</text>
				</view>
			</view>
			<view class="follow-btn">
				<text>+关注</text>
			</view>
		</view>


		<view class="article-content">
			<view class="tag">#{{isData.articleTypeName}}</view>
			<view class="content">{{isData.content}}</view>
			<image class="article-image" :src="isData.imgUrl" mode="widthFix"></image>
		</view>


		<view class="comment-section">
			<view class="comment-title">全部评论（{{isData.momentCommentList ? isData.momentCommentList.length : 0}}）</view>

			<view class="comment-item" v-for="item in isData.momentCommentList" :key="item.id">
				<view class="comment-avatar">
					<image class="avatar-img" :src="item.avatar" mode="aspectFill"></image>
				</view>
				<view class="comment-content">
					<view class="comment-user">
						<text class="comment-username">{{item.userName}}</text>
						<view class="gender-tag" :style="{backgroundColor: '#FFB6C1'}">
							<u-icon name="woman" color="#FF1493" size="28"></u-icon>
						</view>
						<text class="constellation">狮子座</text>
					</view>
					<view class="comment-text">{{item.content}}</view>
				</view>
			</view>






		</view>


		<view class="bottom-bar">
			<view class="comment-input">
				<input placeholder="写下你的评论..." placeholder-class="placeholder" v-model="comPar.content"
					 />
					 
					 <view style="width: 50px;font-size: 13px;" @click="sendComment">发送</view>
			</view>
			<view class="actionBar">
				<view class="actionItem" @click="like" v-if="isData.likeState == 0 || isData.likeState == null">
				    <u-icon name="thumb-up" :color="isData.likeState == 1 ? '#fb7299' : '#666'" size="41"></u-icon>
				    <text class="actionText" :style="{color: isData.likeState == 1 ? '#fb7299' : '#666'}">
				        {{isData.likeNum ? isData.likeNum : 0}}
				    </text>
				</view>
				
				<view class="actionItem" @click="like2" v-if="isData.likeState == 1">
				    <u-icon name="thumb-up" :color="isData.likeState == 1 ? '#fb7299' : '#666'" size="41"></u-icon>
				    <text class="actionText" :style="{color: isData.likeState == 1 ? '#fb7299' : '#666'}">
				        {{isData.likeNum ? isData.likeNum : 0}}
				    </text>
				</view>
			
				<view class="actionItem" @click="save" v-if="isData.collectState == 0 || isData.collectState == null">
				    <u-icon name="star" :color="isData.collectState == 1 ? '#FA5014' : '#666'" size="41"></u-icon>
				    <text class="actionText" :style="{color: isData.collectState == 1 ? '#FA5014' : '#666'}">
				        {{isData.collectNum ? isData.collectNum : 0}}
				    </text>
				</view>
				
				<view class="actionItem" @click="save2" v-if="isData.collectState == 1">
				    <u-icon name="star" :color="isData.collectState == 1 ? '#FA5014' : '#666'" size="41"></u-icon>
				    <text class="actionText" :style="{color: isData.collectState == 1 ? '#FA5014' : '#666'}">
				        {{isData.collectNum ? isData.collectNum : 0}}
				    </text>
				</view>
				
				<view class="actionItem" @click="comShow = true">
					<u-icon name="more-circle" color="#666" size="41"></u-icon>
					<text class="actionText" style="color: #666;">
						{{isData.momentCommentList ? isData.momentCommentList.length : 0}} 条
					</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isId: '',
				isData: {},
				comPar: {
					content: '',
					momentId: ''
				}
			}
		},
		onLoad(option) {
			this.comPar.momentId = option.id
			this.getDetail(option.id)
			this.isId = option.id
		},
		methods: {
			save(){
				uni.request({
					url:this.$elyasApi+'collect/add',
					method:'POST',
					data:{momentId:this.isId,status:1},
					header:{
					    "content-type":"application/json",
						"accessToken":uni.getStorageSync('token')
					},
					success:(data)=> {	
						
						this.getDetail(this.isId)
						
						
					}
					
				})
			},
			save2(){
				uni.request({
					url:this.$elyasApi+'collect/add',
					method:'POST',
					data:{momentId:this.isId,status:0},
					header:{
					    "content-type":"application/json",
						"accessToken":uni.getStorageSync('token')
					},
					success:(data)=> {	
						
						this.getDetail(this.isId)
						
						
					}
					
				})
			},
			like(){
				uni.request({
					url:this.$elyasApi+'moment/like/add',
					method:'POST',
					data:{subjectId:this.isId,status:1},
					header:{
					    "content-type":"application/json",
						"accessToken":uni.getStorageSync('token')
					},
					success:(data)=> {	
						
						this.getDetail(this.isId)
						
						
					}
					
				})
			},
			like2(){
				uni.request({
					url:this.$elyasApi+'moment/like/add',
					method:'POST',
					data:{subjectId:this.workId,status:2},
					header:{
					    "content-type":"application/json",
						"accessToken":uni.getStorageSync('token')
					},
					success:(data)=> {	
						
						this.getDetail(this.workId)
						
						
					}
					
				})
			},
			sendComment() {
				uni.request({
					url: this.$elyasApi + 'moment/comment/add',
					method: 'POST',
					data: this.comPar,
					header: {
						"content-type": "application/json",
						"accessToken": uni.getStorageSync('token')
					},
					success: (data) => {
				
						this.getDetail(this.isId)
				
				this.comPar.content = '';
				
					}
				
				})
			},
			dateString(time) {
				var date = new Date(time);
				var year = date.getFullYear();
				var month = date.getMonth() + 1;
				var day = date.getDate();
				month = month < 10 ? "0" + month : month;
				day = day < 10 ? "0" + day : day;
				return year + "-" + month + "-" + day;
			},
			getDetail(ids) {
				uni.request({
					url: this.$elyasApi + 'article/detail?id=' + ids,
					method: 'POST',
					data: {},
					header: {
						"content-type": "application/json",
						"accessToken": uni.getStorageSync('token')
					},
					success: (data) => {

						this.isData = data.data.data


					}

				})
			},
		}
	}
</script>

<style lang="scss" scoped>
	.actionBar {
		width: 150px;
		display: flex;
		justify-content: space-around;
		padding: 10px 0;
		border-top: 1px solid #f1f1f1;
		border-bottom: 1px solid #f1f1f1;
	}
	
	.actionItem {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	
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