<script setup>
</script>

<template>
  <header class="header">
    <div class="button">
      <router-link to="/">Word List</router-link>
    </div>
    <div class="button">
      <router-link to="/knowledge">Knowledge</router-link>
    </div>
  </header>
</template>

<style scoped>
.header {
  margin: 8px 24px 10px 24px;
  width: calc(100% - 48px);
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 24px;
  background-color: #88b8c0;
  border-radius: 16px;
}

.button {
  width: 100px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border-radius: 20px;
  border: none;
  box-shadow: inset 0 0 0 1px #fff;
  cursor: pointer;
  color: white;
  text-align: center;
}

.button>a {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 10px;
  color: inherit;
  text-decoration: none;
}
</style>
