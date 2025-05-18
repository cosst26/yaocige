<template>
  <el-card shadow="never" class="border-0">
    <!-- 搜索框 -->
    <div class="mb-6 flex items-center">
      <el-input v-model="searchTitle" placeholder="请输入标题搜索" class="w-100 mr-2" />
      <el-button type="primary" @click="fetchDailyHexagrams">搜索</el-button>
    </div>

    <!-- 操作按钮 -->
    <div class="flex items-center justify-between mb-4">
      <el-button type="primary" size="middle" @click="openAddModal">新增每日一卦</el-button>
      <el-button text @click="fetchDailyHexagrams">
        <el-icon :size="20">
          <Refresh />
        </el-icon>
      </el-button>
    </div>

    <!-- 表格 -->
    <el-table :data="dailyHexagrams" stripe style="width: 100%">
      <el-table-column prop="title" label="标题" align="center" />
      <el-table-column label="图片" align="center">
        <template #default="scope">
          <img v-if="scope.row.img_url" :src="scope.row.img_url" alt="每日一卦图片" style="max-width: 100px; max-height: 100px" />
        </template>
      </el-table-column>
      <el-table-column prop="author" label="作者" align="center" />
      <el-table-column prop="sort" label="排序" align="center" />
      <el-table-column prop="view_count" label="浏览次数" align="center" />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="openEditModal(scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="deleteDailyHexagram(scope.row.id)">删除</el-button>
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
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input type="textarea" v-model="form.content" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="图片" prop="img_url">
          <el-upload
            class="avatar-uploader"
            :action="uploadUrl"
            :show-file-list="false"
            :on-success="handleUploadSuccess"
            :before-upload="beforeUpload"
          >
            <img v-if="form.img_url" :src="form.img_url" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon">
              <Plus />
            </el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
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
  getDailyHexagrams,
  createDailyHexagram,
  updateDailyHexagram,
  deleteDailyHexagram as deleteApi
} from '~/api/manager';

// 表格数据
const dailyHexagrams = ref([]);
// 总记录数
const total = ref(0);
// 当前页码
const currentPage = ref(1);
// 每页记录数
const pageSize = ref(10);
// 搜索标题
const searchTitle = ref('');
// 模态框显示状态
const dialogVisible = ref(false);
// 模态框标题
const dialogTitle = ref('');
// 表单数据
const form = reactive({
  id: null,
  title: '',
  content: '',
  img_url: '',
  sort: null,
  author: ''
});
// 表单验证规则
const rules = {
  title: [
    { required: true, message: '请输入标题', trigger: 'blur' }
  ],
  content: [
    { required: true, message: '请输入内容', trigger: 'blur' }
  ],
  sort: [
    { required: true, message: '请输入排序', trigger: 'blur' },
    { type: 'number', message: '请输入有效的数字', trigger: 'blur' }
  ],
  author: [
    { required: true, message: '请输入作者', trigger: 'blur' }
  ]
};
// 表单引用
const formRef = ref(null);
// 上传地址
const uploadUrl = ref('~/api/upload');

// 获取每日一卦数据
const fetchDailyHexagrams = async () => {
  try {
    const res = await getDailyHexagrams({
      page: currentPage.value,
      limit: pageSize.value,
      title: searchTitle.value
    });
    dailyHexagrams.value = res.data.list;
    total.value = res.data.total;
  } catch (error) {
    toast('获取数据失败', 'error');
  }
};

// 分页变化处理
const handlePageChange = (page) => {
  currentPage.value = page;
  fetchDailyHexagrams();
};

// 打开新增模态框
const openAddModal = () => {
  dialogTitle.value = '新增每日一卦';
  form.id = null;
  form.title = '';
  form.content = '';
  form.img_url = '';
  form.sort = null;
  form.author = '';
  dialogVisible.value = true;
};

// 打开编辑模态框
const openEditModal = (row) => {
  dialogTitle.value = '编辑每日一卦';
  form.id = row.id;
  form.title = row.title;
  form.content = row.content;
  form.img_url = row.img_url;
  form.sort = row.sort;
  form.author = row.author;
  dialogVisible.value = true;
};

// 提交表单
const submitForm = async () => {
  await formRef.value.validate();
  try {
    if (form.id) {
      // 编辑操作
      await updateDailyHexagram(form);
      toast('编辑成功');
    } else {
      // 新增操作
      await createDailyHexagram(form);
      toast('新增成功');
    }
    dialogVisible.value = false;
    fetchDailyHexagrams();
  } catch (error) {
    toast('操作失败', 'error');
  }
};

// 删除每日一卦
const deleteDailyHexagram = async (id) => {
  const confirm = await messageBox('确定要删除该每日一卦吗？');
  if (confirm) {
    try {
      await deleteApi(id);
      toast('删除成功');
      fetchDailyHexagrams();
    } catch (error) {
      toast('删除失败', 'error');
    }
  }
};

// 图片上传成功处理
const handleUploadSuccess = (response, file) => {
  form.img_url = response.data.url;
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
fetchDailyHexagrams();
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