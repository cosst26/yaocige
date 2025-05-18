<template>
	<view>

		<view style="display:flex;align-items: center;justify-content: center;margin-top: 50rpx;flex-direction: column;gap: 12px;">
				<image src="../../static/shopicon/商城-2 (1).png" mode="aspectFill" style="width: 250rpx;height: 250rpx;border-radius: 100%;"></image>
				<view style="font-size: 21px;font-weight: 700;">爻辞阁</view>
		</view>


		<!-- 表单 -->

		<view style="padding: 40rpx;margin-top: 40rpx;">
			<view>
				<input type="text" value="" style="border: 1px solid #ddd;padding: 0.7em;border-radius: 8px;" v-model="userLoginInfo.userName" placeholder="请输入用户名" />
			</view>
			
			<view style="margin-top: 10px;">
				<input type="text" value="" style="border: 1px solid #ddd;padding: 0.7em;border-radius: 8px;" v-model="userLoginInfo.password" placeholder="请输入密码" />
			</view>
			
			<view style="margin-top: 10px;">
				<input type="text" value="" style="border: 1px solid #ddd;padding: 0.7em;border-radius: 8px;" v-model="userLoginInfo.email" placeholder="请输入邮箱" />
			</view>
			
			<view style="margin-top: 10px;">
				<input type="text" value="" style="border: 1px solid #ddd;padding: 0.7em;border-radius: 8px;" v-model="userLoginInfo.phone" placeholder="请输入手机号" />
			</view>
			

			<view style="margin: 10px 0;color: #ff7a45;float: right;" @click="toLoginBtn">点击登录</view>
			<view @click="toLogin"
				style="background-color: #ff7a45;color: #fff;height: 80rpx;width: 100%;text-align: center;line-height: 80rpx;margin-top: 100rpx;border-radius: 6px;">
				点击注册</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				userLoginInfo: {
					userName: '',
					password: '',
					email: '',
					phone: ''
				}
			}
		},
		methods: {
			toLogin() {
				uni.request({
					url: this.$elyasApi + 'user/add',
					method: 'POST',
					data: this.userLoginInfo,
					header: {
						"content-type": "application/json"
					},
					success: (data) => {
						
						if (data.data.code !== 200) {
							uni.showToast({
								title: data.data.message
							})
							
							return;
						}else {
							uni.showToast({
								title: '注册成功'
							})
							
							setTimeout(()=>{
								uni.navigateTo({
									url:'/pages/login/login'
								})
							},800)
						}


						

						

					}

				})
			},
			toLoginBtn(){
				uni.navigateTo({
					url:'/pages/login/login'
				})
			}
		}
	}
</script>

<style>
	.formItem {
		height: 100rpx;
		padding-top: 56rpx;
		border-bottom: 1rpx solid #DD524D;
		margin: 0 100rpx;
		display: flex;
		align-items: center;
	}

	.formItem input {
		font-size: 28rpx;
	}

	.loginBtn {
		height: 86rpx;
		margin: 0 100rpx;
		color: #fff;
		background-color: #DD524D;
		text-align: center;
		line-height: 86rpx;
		font-size: 30rpx;
		margin-top: 60rpx;
	}
</style>