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

// 每日一卦
export function getDailyHexagrams(data) {
    return axios.post("/daily/hexagram/findByModal",data)
}

export function createDailyHexagram(data) {
    return axios.post("/daily/hexagram/add",data)
}

export function deleteDailyHexagram(id) {
    return axios.post("/daily/hexagram/delete?id="+id)
}

export function updateDailyHexagram(data) {
    return axios.post("/daily/hexagram/update",data)
}

// 消息管理
export function getMessages(data) {
    return axios.post("/message/findByModal",data)
}

export function createMessage(data) {
    return axios.post("/message/add",data)
}

export function deleteMessage(id) {
    return axios.post("/message/delete?id="+id)
}

export function updateMessageStatus(data) {
    return axios.post("/message/update",data)
}

// 用户档案
export function getUserProfiles(data) {
    return axios.post("/user/profile/findByModal",data)
}

export function createUserProfile(data) {
    return axios.post("/user/profile/add",data)
}

export function deleteUserProfile(id) {
    return axios.post("/user/profile/delete?id="+id)
}

export function updateUserProfile(data) {
    return axios.post("/user/profile/update",data)
}

// 生肖结果
export function getZodiacs(data) {
    return axios.post("/zodiac/findByModal",data)
}

export function createZodiac(data) {
    return axios.post("/zodiac/add",data)
}

export function deleteZodiac(id) {
    return axios.post("/zodiac/delete?id="+id)
}

export function updateZodiac(data) {
    return axios.post("/zodiac/update",data)
}

// MBTI题目
export function getQuestions(data) {
    return axios.post("/question/findByModal",data)
}

export function createQuestion(data) {
    return axios.post("/question/add",data)
}

export function deleteQuestion(id) {
    return axios.post("/question/delete?id="+id)
}

export function updateQuestion(data) {
    return axios.post("/question/update",data)
}

// MBTI选项
export function getOptions(data) {
    return axios.post("/options/findByModal",data)
}

export function createOption(data) {
    return axios.post("/options/add",data)
}

export function deleteOption(id) {
    return axios.post("/options/delete?id="+id)
}

export function updateOption(data) {
    return axios.post("/options/update",data)
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