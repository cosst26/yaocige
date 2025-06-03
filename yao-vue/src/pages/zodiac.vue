<template>
  <el-card shadow="never" class="border-0">
    <!-- 搜索框 -->
    <div class="mb-6 flex items-center">
      <el-input v-model="searchName" placeholder="请输入生肖名称搜索" class="w-100 mr-2" />
      <el-button type="primary" @click="fetchZodiacs">搜索</el-button>
    </div>

    <!-- 操作按钮 -->
    <div class="flex items-center justify-between mb-4">
      <el-button type="primary" size="middle" @click="openAddModal">新增生肖结果</el-button>
      <el-button text @click="fetchZodiacs">
        <el-icon :size="20">
          <Refresh />
        </el-icon>
      </el-button>
    </div>

    <!-- 表格 -->
    <el-table :data="zodiacs" stripe style="width: 100%">
      <el-table-column prop="name" label="生肖名称" align="center" />
      <el-table-column label="图片" align="center">
        <template #default="scope">
          <img v-if="scope.row.imgUrl" :src="scope.row.imgUrl" alt="生肖图片" style="max-width: 100px; max-height: 100px" />
        </template>
      </el-table-column>
      <el-table-column prop="zodiacDesc" label="描述" align="center" />
      <el-table-column prop="userProfileId" label="用户档案ID" align="center" />
      <el-table-column prop="zodiacBirth" label="属相生辰" align="center" />
      <el-table-column prop="ziweiDesc" label="紫薇描述" align="center" />
      <el-table-column prop="advantageDesc" label="优点" align="center" />
      <el-table-column prop="disadDesc" label="缺点" align="center" />
      <el-table-column prop="careerDesc" label="事业" align="center" />
      <el-table-column prop="wealthDesc" label="财富" align="center" />
      <el-table-column prop="loveDesc" label="爱情" align="center" />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="openEditModal(scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="deleteZodiac(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div class="flex items-center justify-center mt-5">
      <el-pagination
        background
        layout="prev, pager, next, total"
        :total="total"
        :current-page="currentPage"
        :page-size="pageSize"
        @current-change="handlePageChange"
      />
    </div>

    <!-- 新增/编辑模态框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="60%"
      :close-on-click-modal="false"
    >
      <el-form :model="form" :rules="rules" ref="formRef" label-width="120px">
        <el-form-item label="生肖名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入生肖名称" />
        </el-form-item>
       <el-form-item prop="avatar" label="图片">
                    <el-upload class="avatar-uploader" :action="$elyasApi+'/file/uploadFile'"
                        :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                        <img v-if="form.imgUrl" :src="form.imgUrl" class="avatar" />
                        <el-icon v-else class="avatar-uploader-icon">
                            <Plus />
                        </el-icon>
                    </el-upload>
                </el-form-item>
        <el-form-item label="描述" prop="zodiacDesc">
          <el-input type="textarea" v-model="form.zodiacDesc" placeholder="请输入描述" />
        </el-form-item>


        <el-form-item label="用户档案ID" prop="userProfileId">
          <el-select v-model="form.userProfileId" placeholder="用户档案ID">
                        <el-option v-for="item in ulist" :key="item.id" :label="item.userName" :value="item.id">
                        </el-option>
                    </el-select>
        </el-form-item>


        <el-form-item label="属相生辰" prop="zodiacBirth">
          <el-input type="textarea" v-model="form.zodiacBirth" placeholder="请输入属相生辰" />
        </el-form-item>
        <el-form-item label="紫薇描述" prop="ziweiDesc">
          <el-input type="textarea" v-model="form.ziweiDesc" placeholder="请输入紫薇描述" />
        </el-form-item>
        <el-form-item label="优点" prop="advantageDesc">
          <el-input type="textarea" v-model="form.advantageDesc" placeholder="请输入优点" />
        </el-form-item>
        <el-form-item label="缺点" prop="disadDesc">
          <el-input type="textarea" v-model="form.disadDesc" placeholder="请输入缺点" />
        </el-form-item>
        <el-form-item label="事业" prop="careerDesc">
          <el-input type="textarea" v-model="form.careerDesc" placeholder="请输入事业描述" />
        </el-form-item>
        <el-form-item label="财富" prop="wealthDesc">
          <el-input type="textarea" v-model="form.wealthDesc" placeholder="请输入财富描述" />
        </el-form-item>
        <el-form-item label="爱情" prop="loveDesc">
          <el-input type="textarea" v-model="form.loveDesc" placeholder="请输入爱情描述" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </el-card>
</template>

<script setup>
import { ref, reactive, computed } from 'vue';
import { toast, messageBox } from '~/composables/util';
import { Plus, Refresh } from '@element-plus/icons-vue';
import {
  getZodiacs,
  createZodiac,
  getUserProfiles,
  updateZodiac,
  deleteZodiac as deleteApi
} from '~/api/manager';


const ulist = ref([])
getUserProfiles().then(res=>{
  ulist.value = res.data.list;
})

// 表格数据
const zodiacs = ref([]);
// 总记录数
const total = ref(0);
// 当前页码
const currentPage = ref(1);
// 每页记录数
const pageSize = ref(10);
// 搜索生肖名称
const searchName = ref('');
// 模态框显示状态
const dialogVisible = ref(false);
// 模态框标题
const dialogTitle = ref('');
// 表单数据
const form = reactive({
  id: null,
  name: '',
  imgUrl: '',
  zodiacDesc: '',
  userProfileId: null,
  zodiacBirth: '',
  ziweiDesc: '',
  advantageDesc: '',
  disadDesc: '',
  careerDesc: '',
  wealthDesc: '',
  loveDesc: ''
});


const handleAvatarSuccess = (
    response,
    uploadFile
) => {
    form.imgUrl = uploadFile.response.data
    
}


// 表单验证规则
const rules = {
  name: [
    { required: true, message: '请输入生肖名称', trigger: 'blur' }
  ],
  userProfileId: [
    { required: true, message: '请输入用户档案ID', trigger: 'blur' },
    { type: 'number', message: '请输入有效的数字', trigger: 'blur' }
  ]
};
// 表单引用
const formRef = ref(null);
// 上传地址
const uploadUrl = ref('~/api/upload');

// 获取生肖结果数据
const fetchZodiacs = async () => {
  try {
    const res = await getZodiacs({
      page: currentPage.value,
      limit: pageSize.value,
      name: searchName.value
    });
    zodiacs.value = res.data.list;
    total.value = res.data.total;
  } catch (error) {
    toast('获取数据失败', 'error');
  }
};

// 分页变化处理
const handlePageChange = (page) => {
  currentPage.value = page;
  fetchZodiacs();
};

// 打开新增模态框
const openAddModal = () => {
  dialogTitle.value = '新增生肖结果';
  form.id = null;
  form.name = '';
  form.imgUrl = '';
  form.zodiacDesc = '';
  form.userProfileId = null;
  form.zodiacBirth = '';
  form.ziweiDesc = '';
  form.advantageDesc = '';
  form.disadDesc = '';
  form.careerDesc = '';
  form.wealthDesc = '';
  form.loveDesc = '';
  dialogVisible.value = true;
};

// 打开编辑模态框
const openEditModal = (row) => {
  dialogTitle.value = '编辑生肖结果';
  form.id = row.id;
  form.name = row.name;
  form.imgUrl = row.imgUrl;
  form.zodiacDesc = row.zodiacDesc;
  form.userProfileId = row.userProfileId;
  form.zodiacBirth = row.zodiacBirth;
  form.ziweiDesc = row.ziweiDesc;
  form.advantageDesc = row.advantageDesc;
  form.disadDesc = row.disadDesc;
  form.careerDesc = row.careerDesc;
  form.wealthDesc = row.wealthDesc;
  form.loveDesc = row.loveDesc;
  dialogVisible.value = true;
};

// 提交表单
const submitForm = async () => {
  await formRef.value.validate();
  try {
    if (form.id) {
      // 编辑操作
      await updateZodiac(form);
      toast('编辑成功');
    } else {
      // 新增操作
      await createZodiac(form);
      toast('新增成功');
    }
    dialogVisible.value = false;
    fetchZodiacs();
  } catch (error) {
    toast('操作失败', 'error');
  }
};

// 删除生肖结果
const deleteZodiac = async (id) => {
  const confirm = await messageBox('确定要删除该生肖结果吗？');
  if (confirm) {
    try {
      await deleteApi(id);
      toast('删除成功');
      fetchZodiacs();
    } catch (error) {
      toast('删除失败', 'error');
    }
  }
};

// 图片上传成功处理
const handleUploadSuccess = (response, file) => {
  form.imgUrl = response.data.url;
};

// 图片上传前处理
const beforeUpload = (file) => {
  const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
  if (!isJpgOrPng) {
    toast('只能上传 JPG/PNG 格式的图片', 'error');
    return false;
  }
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {
    toast('图片大小不能超过 2MB', 'error');
    return false;
  }
  return true;
};

// 初始化数据
fetchZodiacs();
</script>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}

.detailDss {
  width: 150px;
  height: 40px;
  font-size: 14px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
</style>