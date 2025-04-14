<script setup>
import { ref, reactive, onMounted } from 'vue';
import { getTodoListByPage, updateTodo, removeTodoById } from '@/api/todo';
import EditForm from '@/components/EditForm.vue';

let data = reactive({
  // 假数据
  todos: [
    { id: 1, description: 'Learn C', completed: false },
    { id: 2, description: 'Learn C++', completed: false },
    { id: 3, description: 'Learn C#', completed: false },
    { id: 4, description: 'Learn Python', completed: true },
    { id: 5, description: 'Learn Ruby', completed: false },
    { id: 6, description: 'Learn C', completed: false },
    { id: 7, description: 'Learn C++', completed: false },
    { id: 8, description: 'Learn C#', completed: false },
    { id: 9, description: 'Learn Python', completed: true },
    { id: 10, description: 'Learn Ruby', completed: false },
  ],
  pageSize: 5,
  currentPage: 1,
  total: 10,
});

// 封装数据查询和更新方法
let showData = (pageSize = data.pageSize, currentPage = data.currentPage) => {
  getTodoListByPage(pageSize, currentPage).then((response) => {
    Object.assign(data, response.data.data);
    console.log(data);
  });
};

// 初始化周期加载数据
onMounted(() => {
  showData();
});

let editDialogVisible = ref(false);
let editForm = reactive({
  id: 0,
  description: '',
  completed: false,
});

const updateDescription = (description) => {
  editForm.description = description;
};

const updateCompleted = (completed) => {
  editForm.completed = completed;
};

const handleEdit = (row) => {
  // 将要修改的本行数据放在 editForm 中
  editForm.id = row.id;
  editForm.description = row.description;
  editForm.completed = row.completed;

  // 设置修改对话框显示
  editDialogVisible.value = true;
};

const handleDelete = async (id, description) => {
  // 确认删除
  if (!confirm(`Are you sure to delete "${description}"?`)) {
    return;
  }

  // 异步删除数据
  await removeTodoById(id);

  // 查询新数据
  showData();
};

const handleUpdate = async () => {
  // 修改数据
  await updateTodo(editForm);

  // 查询新数据
  showData();

  // 关闭对话框
  editDialogVisible.value = false;
};

const handlePageChange = (page) => {
  showData(data.pageSize, page);
};
</script>

<template>
  <div class="common-layout">
    <el-container>
      <router-link to="/add">
        <el-button type="primary">Add</el-button>
      </router-link>
    </el-container>

    <el-container>
      <el-main>
        <!-- 表格 -->
        <el-table :data="data.todos" style="width: 100%">
          <el-table-column label="ID" width="100">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <span style="margin-left: 10px">{{ scope.row.id }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="Description" width="180">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <span style="margin-left: 10px">{{
                  scope.row.description
                }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="Completed" width="130">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <span style="margin-left: 10px">{{
                  scope.row.completed ? 'Yes' : 'No'
                }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="Action">
            <template #default="scope">
              <el-button size="small" @click="handleEdit(scope.row)"
                >Edit</el-button
              >
              <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.row.id, scope.row.description)"
                >Delete</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页处理 -->
        <br />
        <div class="pagination">
          <el-pagination
            :current-page="data.currentPage"
            :page-size="data.pageSize"
            :total="data.total"
            layout="prev, pager, next"
            @current-change="handlePageChange"
            background
          >
          </el-pagination>
        </div>

        <!-- 编辑对话框 -->
        <el-dialog
          title="Edit Todo"
          v-model="editDialogVisible"
          :close-on-click-modal="false"
        >
          <edit-form
            :description="editForm.description"
            :completed="editForm.completed"
            @updateDescription="updateDescription"
            @updateCompleted="updateCompleted"
          ></edit-form>
          <div slot="footer">
            <el-button @click="editDialogVisible = false">Cancel</el-button>
            <el-button type="primary" @click="handleUpdate"
              >Confirmation</el-button
            >
          </div>
        </el-dialog>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
</style>
