<template>
  <el-card shadow="never" class="border-0">
    <!-- 搜索框 -->
    <div class="mb-6 flex items-center">
      <el-input v-model="searchTitle" placeholder="请输入标题搜索" class="w-100 mr-2" />
      <el-button type="primary" @click="fetchQuestions">搜索</el-button>
    </div>

    <!-- 操作按钮 -->
    <div class="flex items-center justify-between mb-4">
      <el-button type="primary" size="middle" @click="openAddModal">新增 MBTI 问题</el-button>
      <el-button text @click="fetchQuestions">
        <el-icon :size="20">
          <Refresh />
        </el-icon>
      </el-button>
    </div>

    <!-- 表格 -->
    <el-table :data="questions" stripe style="width: 100%">
      <el-table-column prop="title" label="标题" align="center" />
      <el-table-column prop="content" label="内容" align="center">
        <template #default="scope">
          <span class="detailDss">{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="openEditModal(scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="deleteQuestion(scope.row.id)">删除</el-button>
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
  getQuestions,
  createQuestion,
  updateQuestion,
  deleteQuestion as deleteApi
} from '~/api/manager';

// 表格数据
const questions = ref([]);
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
  content: ''
});
// 表单验证规则
const rules = {
  title: [
    { required: true, message: '请输入标题', trigger: 'blur' }
  ],
  content: [
    { required: true, message: '请输入内容', trigger: 'blur' }
  ]
};
// 表单引用
const formRef = ref(null);

// 获取 MBTI 问题数据
const fetchQuestions = async () => {
  try {
    const res = await getQuestions({
      page: currentPage.value,
      limit: pageSize.value,
      title: searchTitle.value
    });
    questions.value = res.data.list;
    total.value = res.data.total;
  } catch (error) {
    toast('获取数据失败', 'error');
  }
};

// 分页变化处理
const handlePageChange = (page) => {
  currentPage.value = page;
  fetchQuestions();
};

// 打开新增模态框
const openAddModal = () => {
  dialogTitle.value = '新增 MBTI 问题';
  form.id = null;
  form.title = '';
  form.content = '';
  dialogVisible.value = true;
};

// 打开编辑模态框
const openEditModal = (row) => {
  dialogTitle.value = '编辑 MBTI 问题';
  form.id = row.id;
  form.title = row.title;
  form.content = row.content;
  dialogVisible.value = true;
};

// 提交表单
const submitForm = async () => {
  await formRef.value.validate();
  try {
    if (form.id) {
      // 编辑操作
      await updateQuestion(form);
      toast('编辑成功');
    } else {
      // 新增操作
      await createQuestion(form);
      toast('新增成功');
    }
    dialogVisible.value = false;
    fetchQuestions();
  } catch (error) {
    toast('操作失败', 'error');
  }
};

// 删除 MBTI 问题
const deleteQuestion = async (id) => {
  const confirm = await messageBox('确定要删除该 MBTI 问题吗？');
  if (confirm) {
    try {
      await deleteApi(id);
      toast('删除成功');
      fetchQuestions();
    } catch (error) {
      toast('删除失败', 'error');
    }
  }
};

// 初始化数据
fetchQuestions();
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