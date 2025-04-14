<script setup>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import { addTodo } from '@/api/todo';
import EditForm from '@/components/EditForm.vue';

let router = useRouter();

const editForm = reactive({
  description: '',
  completed: false,
});

const updateDescription = (description) => {
  editForm.description = description;
};

const updateCompleted = (completed) => {
  editForm.completed = completed;
};

const handleAdd = async () => {
  await addTodo(editForm);
  returnHome();
};

const returnHome = () => {
  router.push('/');
};
</script>

<template>
  <div>
    <br />
    <br />
    <el-text class="mx-1" size="large" type="success">Add Todo</el-text>
    <br />
    <br />
    <edit-form
      :description="editForm.description"
      :completed="editForm.completed"
      @updateDescription="updateDescription"
      @updateCompleted="updateCompleted"
    ></edit-form>
    <el-row>
      <el-button type="success" @click="handleAdd">Add</el-button>
      <el-button type="info" @click="returnHome">Return</el-button>
    </el-row>
  </div>
</template>

<style scoped>
</style>
