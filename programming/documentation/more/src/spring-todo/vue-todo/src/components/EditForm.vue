<script setup>
import { reactive, watch } from 'vue';

const props = defineProps({
  description: String,
  completed: Boolean,
});

const form = reactive({
  description: props.description,
  completed: props.completed,
});

const emit = defineEmits(['updateDescription', 'updateCompleted']);

watch([() => form.description, () => form.completed], (newVal, oldVal) => {
  const [oldDescription, oldCompleted] = oldVal;
  const [newDescription, newCompleted] = newVal;

  if (oldDescription !== newDescription) {
    emit('updateDescription', newDescription);
  }
  if (oldCompleted !== newCompleted) {
    emit('updateCompleted', newCompleted);
  }
});

const rules = {
  description: [
    { required: true, message: 'Please input description', trigger: 'blur' },
  ],
};
</script>

<template>
  <el-form label-width="100px" :model="form" :rules="rules">
    <el-form-item label="Description" prop="description">
      <el-input v-model="form.description" />
    </el-form-item>
    <el-form-item label="Completed" prop="completed">
      <el-radio-group v-model="form.completed">
        <el-radio :value="true">Yes</el-radio>
        <el-radio :value="false">No</el-radio>
      </el-radio-group>
    </el-form-item>
  </el-form>
</template>

<style scoped>
</style>
