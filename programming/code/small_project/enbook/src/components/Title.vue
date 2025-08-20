<script setup>
import { defineProps } from 'vue';
const props = defineProps({
  text: String
});
</script>

<template>
  <div class="title">
    <div class="title-text">{{ text }}</div>
  </div>
</template>

<style scoped>
.title {
  margin: 0 auto;
  width: 100%;
  height: 30px;
  display: flex;
  justify-content: start;
  align-items: center;
  background: #b0cfd4;
}

.title-text {
  margin-left: 14px;
  color: #fff;
  font-size: 10px;
}
</style>
