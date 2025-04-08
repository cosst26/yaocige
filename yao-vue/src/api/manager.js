import axios from "~/axios";

// 登录方法
export function login(userName,password,roleId){
    return axios.post('/user/login',{
        userName,
        password,
        roleId
    })
}

// 公共
export function getUserData() {
    return axios.post('/user/detailByToken')
}

export function logOut() {
    return axios.post('/user/logout')
}

export function updatePassword(data) {
    return axios.post('/user/update',data)
}


// 用户管理
export function getUserList(data) {
    return axios.post("/user/findByModal",data)
}

export function createUser(data) {
    return axios.post("/user/add",data)
}

export function deleteUser(id) {
    return axios.post("/user/delete?id="+id)
}

export function updateUser(data) {
    return axios.post("/user/update",data)
}

// 角色

export function getRole(data) {
    return axios.post("/sys/role/findByModal",data)
}

export function createRole(data) {
    return axios.post("/sys/role/add",data)
}

export function deleteRole(id) {
    return axios.post("/sys/role/delete?id="+id)
}

export function updateRole(data) {
    return axios.post("/sys/role/update",data)
}

// 封面
export function getCover(data) {
    return axios.post("/cover/findByModal",data)
}

export function createCover(data) {
    return axios.post("/cover/add",data)
}

export function deleteCover(id) {
    return axios.post("/cover/delete?id="+id)
}


// 科普资讯
export function getArticle(data) {
    return axios.post("/article/findAllByModal",data)
}

export function createArticle(data) {
    return axios.post("/article/add",data)
}

export function deleteArticle(id) {
    return axios.post("/article/delete?id="+id)
}

export function updateArticle(data) {
    return axios.post("/article/update",data)
}
export function detailArticle(data) {
    return axios.post("/article/detail?id="+data)
}






// 公告
export function getAnnounce(data) {
    return axios.post("/announce/findByModal",data)
}

export function createAnnounce(data) {
    return axios.post("/announce/add",data)
}

export function deleteAnnounce(id) {
    return axios.post("/announce/delete?id="+id)
}

export function updateAnnounce(data) {
    return axios.post("/announce/update",data)
}
export function detailAnnounce(data) {
    return axios.post("/announce/detail?id="+data)
}

// 
export function getComment(data) {
    return axios.post("/moment/comment/findByModal",data)
}

export function createComment(data) {
    return axios.post("/moment/comment/add",data)
}

export function deleteComment(id) {
    return axios.post("/moment/comment/delete?id="+id)
}

export function updateComment(data) {
    return axios.post("/moment/comment/update",data)
}
export function detailComment(data) {
    return axios.post("/moment/comment/detail?id="+data)
}