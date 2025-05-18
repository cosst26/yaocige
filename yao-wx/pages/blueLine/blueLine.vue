<template>
	<view class="container">
		<!-- 6个蓝色框 -->
		<view class="blue-box" v-for="(item, index) in 6" :key="index"></view>
		
		<!-- 内容区域 -->
		<view class="content-area">
			<view class="title" style="text-align: center;">卦辞原文</view>
			<view class="text">乾：元亨、利贞。</view>
			
			<view class="title" style="text-align: center;">卦辞译文</view>
			<view class="translation">
				这个卦是同卦(下乾上乾)相叠。象征天，喻龙(德才的君子)，又象征纯粹的阳和健，表明兴盛强健。乾卦是根据万物变通的道理，以"元、亨、利、贞"为卦辞,表示吉祥如意，教导人遵守天道的德行。
				<view class="paragraph">大象：天行刚健，自强不息。</view>
				<view class="paragraph">运势：飞龙在天，名利双收之象,宜把握机会，争取成果。</view>
				<view class="paragraph">事业：大吉大利，万事如意，心想事成，自有天佑，春风得意，事业如日中天。但阳气已达顶点，盛极必衰，务须提高警惕，小心谨慎。力戒骄傲，冷静处世，心境平和，如是则能充分发挥才智，保证事业成功。</view>
				<view class="paragraph">经商：十分顺利，有发展向上的大好机会。但切勿操之过急，宜冷静分析形势，把握时机，坚持商业道德，冷静对待中途出现的困难，定</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
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