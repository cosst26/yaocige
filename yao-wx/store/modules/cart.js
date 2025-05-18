export default {
	state:{
		list:[],
		selectList:[]
	},
	getters:{
		// 判断是否全选
		checkedAll(state){
			return state.list.length === state.selectList.length
		},
		// 合计计算属性
		totalCount(state){
			let total = {
				price:0,
				num:0
			}
			
			state.list.forEach(v=>{
				if (state.selectList.indexOf(v) > -1) {
					total.price += v.sellingPrice*v.number;
					total.num  = state.selectList.length;
				}
			})
			console.log(total,'total')
			return total;
		}
	},
	mutations:{
		// 拿到请求数据赋值list
		initList(state,list){
			state.list = list
		},
		
		// 全选
		cAll(state){
			state.selectList = state.list.map(v=>{
				v.checked = true
				return v
			})
		},
		//不全选
		uncAll(state){
			state.list.forEach(v=>{
				v.checked = false
			})
			
			state.selectList = []
		},
		//单选
		selectItem(state,index) {
			let data = state.list[index]
			let idx = state.list[index].id
			let i = state.selectList.indexOf(data)
			
			if (i > -1) {
				state.list[index].checked = false;
				return state.selectList.splice(i,1)
			}
			
			state.list[index].checked = true;
			state.selectList.push(data)
		},
		addShopCart(state,goods) {
			state.list.push(goods)
		}
		
	},
	actions:{
		checkAllFn({commit,getters}){
			getters.checkedAll ? commit("uncAll") : commit("cAll")
		}
	}
}