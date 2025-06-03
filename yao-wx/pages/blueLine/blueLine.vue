<template>
	<view class="container">
		<!-- 6个蓝色框 -->
		<view class="blue-box" v-for="(item, index) in 6" :key="index"></view>
		
		<!-- 内容区域 -->
		<view class="content-area">
			<view class="title" style="text-align: center;">{{isData.title}}</view>
			<view class="text">作者：{{isData.author}}</view>
			
			
			<view class="translation">
				{{isData.content}}
				
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isData:{}
			}
		},
		onLoad(option) {
			this.getData(option.id)
		},
		methods: {
			getData(ids){
				uni.request({
					url:this.$elyasApi+'daily/hexagram/detail?id='+ids,
					method:'POST',
					data:{},
					header:{
					    "content-type":"application/json"
					},
					success:(data)=> {	
						
						this.isData = data.data.data
								
						
					}
					
				})
			}
		}
	}
</script>

<style>
	.container {
		width: 100%;
		background-color: #FFFFFF;
		padding: 20rpx;
		box-sizing: border-box;
	}
	
	.blue-box {
		width: 100%;
		height: 70rpx;
		background-color: #5e76ff;
		margin-bottom: 20rpx;
		border-radius: 8rpx;
	}
	
	.content-area {
		width: 100%;
		background-color: #EEEEEE;
		padding: 20rpx;
		border-radius: 8rpx;
		margin-top: 20rpx;
		box-sizing: border-box;
	}
	
	.title {
		font-weight: bold;
		font-size: 32rpx;
		margin: 20rpx 0;
	}
	
	.text {
		font-size: 28rpx;
		line-height: 1.6;
		margin-bottom: 30rpx;
	}
	
	.translation {
		font-size: 28rpx;
		line-height: 1.6;
	}
	
	.paragraph {
		margin-top: 20rpx;
		text-indent: 2em;
	}
</style>