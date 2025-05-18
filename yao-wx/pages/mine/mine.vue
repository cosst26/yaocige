<template>
	<view class="pagex">


		<hx-navbar :navBarHeight="50" :fixed="changeSearch" bgColor="#efe8e0">
			<!-- #ifndef MP -->
			<view :class="['search',changeSearch?'fixed':'']">
			<!-- #endif -->
				<!-- #ifdef MP -->
				<view class="search" :style="{'width':widthMp}">
				<!-- #endif -->

					<!-- 搜索框 -->


				</view>


		</hx-navbar>


		<view style="margin-top: 10px;padding: 0 20rpx;border-radius: 15px;">
			<view style="display: flex;align-items: center;justify-content: space-between;">
				<view style="display: flex;align-items: center;gap: 10px;">
					<image src="/static/cap.png" mode="" style="width: 100rpx;height: 100rpx;border-radius: 100%;">
					</image>
					<view>
						<view style="margin-bottom: 5px;font-size: 15px;font-weight: 700;">user</view>
						<view style="font-size: 13px;color: #666;">ID:111111111</view>
					</view>
				</view>
				<u-icon slot="icon" size="38" name="setting" color="#666"></u-icon>

			</view>
		</view>

		

		<view style="box-shadow:0 0 5px #00000010;width: 95%;margin: 30px auto !important;">
			<u-cell-group>

				


				<u-cell-item title="我的结果" arrow-direction="right" @click="toShippingList">
					<u-icon slot="icon" size="38" name="coupon" style="margin-right: 20px;"></u-icon>


				</u-cell-item>
				<u-cell-item title="我的评论" arrow-direction="right" @click="todasdasdas">
					<u-icon slot="icon" size="38" name="coupon" style="margin-right: 20px;"></u-icon>


				</u-cell-item>


				<u-cell-item title="我的资料" arrow-direction="right" @click="toUser">
					<u-icon slot="icon" size="38" name="integral" style="margin-right: 20px;"></u-icon>


				</u-cell-item>



			</u-cell-group>
		</view>
		<view
			style="background-image: linear-gradient(to bottom, #d52b1f, #ff8c8e);color: #fff;height: 80rpx;width: 90%;text-align: center;line-height: 80rpx;margin-top: 100rpx;border-radius: 6px;margin: 20px auto;"
			@click="outlogin">退出登录</view>

		<!-- <view class="uni-p-b-98"></view>　 -->
		<tabBar :pagePath="'/pages/mine/mine'"></tabBar>

	</view>
</template>

<script>
	import hxNavbar from "@/componentes/hx-navbar.vue";
	export default {
		data() {
			return {
				userData: {},
				widthMp: '90%',
				changeSearch: false,
				clist: []

			}
		},

		onShow() {
				this.getCoupon()
			this.getUserInfoByToken()
			

		},
		mounted() {
			var a = document.getElementsByClassName('uni-page-head-hd')[0]
			a.style.display = 'none';
		},
		components: {
			hxNavbar,
		},
		onLoad() {
			// #ifndef H5 || APP-PLUS || MP-ALIPAY
			let rect = uni.getMenuButtonBoundingClientRect()
			this.widthMp = rect.left - 30 + 'px'
			// #endif
		},
		//头条小程序必须在页面加 onPageScroll(e){} ，才能滑动显示背景
		onPageScroll(e) {
			if (e.scrollTop > 0) {
				this.changeSearch = true;
			} else {
				this.changeSearch = false;
			}
		},

		methods: {
			outlogin() {
				uni.setStorageSync('token', '')

				setTimeout(function() {
					uni.navigateTo({
						url: '../login/login'
					})
				}, 500)
			},
			todasdasdas(){
				uni.navigateTo({
					url:'/pages/chat/chat'
				})
			},
			getCoupon() {
				uni.request({
					url: this.$elyasApi + 'coupon/findByModal',
					method: 'POST',
					data: {},
					header: {
						"content-type": "application/json",
						"accessToken": uni.getStorageSync('token')
					},
					success: (data) => {
						data.data.data.list.forEach(item => {
							if (item.status != 2) {
								this.clist.push(item)
							}
						})

					}

				});
			},
			toCoupon() {
				uni.navigateTo({
					url: '/pages/coupon/coupon'
				})
			},
			dai() {
				uni.navigateTo({
					url: '/pages/mineOrder/mineOrder?id=' + 0
				})
			},
			toAll2(){
				uni.navigateTo({
					url:'/pages/mineOrder/mineOrder'
				})
			},
			toAll(id) {
				uni.navigateTo({
					url: '/pages/mineOrder/mineOrder?id='+id
				})
			},
			tomoreorder() {
				uni.navigateTo({
					url: '/pages/mineOrder/mineOrder'
				})
			},
			toShippingList() {
				uni.navigateTo({
					url: '/pages/shippingPage/shippingPage'
				})
			},
			getUserInfoByToken() {
				uni.request({
					url: this.$elyasApi + 'user/detailByToken',
					method: 'POST',
					data: {},
					header: {
						"content-type": "application/json",
						"accessToken": uni.getStorageSync('token')
					},
					success: (data) => {

						this.userData = data.data.data;




					}

				});
			},
			toUser() {
				uni.navigateTo({
					url: '/pages/userset/userset'
				})
			}


		}
	}
</script>

<style scoped lang="scss">
	.pagex {
		// background-color: #FEFAF6 !important;
		background-image: linear-gradient(to bottom, #ebe2d8, #FEFAF6, #fff);
		height: 100vh;

	}

	.status_bar {
		height: var(--status-bar-height);
		width: 100%;
	}

	.linklist {
		display: flex;
		align-items: center;
		margin-top: 16px;

		.linkItem {
			flex: 1;
			display: flex;
			align-items: center;
			flex-direction: column;
			gap: 10px;

			image {
				width: 100rpx;
				height: 100rpx;


			}

			.itemname {
				color: #666;
				font-size: 12px;
				font-weight: 700;

			}
		}
	}
</style>