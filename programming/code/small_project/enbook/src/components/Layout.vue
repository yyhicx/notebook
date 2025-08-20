<script setup>
import Header from './Header.vue';
</script>

<template>
  <div class="container">
    <Header></Header>
    <main>
      <router-view></router-view>
    </main>
  </div>
</template>

<style scoped>
.container {
  margin: 0 auto;
  width: 800px;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;
}
</style>
