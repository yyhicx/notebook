<script setup>
import { ref, onMounted } from 'vue';
import Title from './Title.vue';
import Entry from './Entry.vue';
import txtUrl from '../assets/data/wordlist.txt?url';

const sections = ref([]);

onMounted(async () => {
  const text = await fetch(txtUrl).then(res => res.text());
  const lines = text.split(/\r?\n/);

  let current = null;
  for (const raw of lines) {
    const line = raw.trim();
    if (!line) continue;

    // 如果是单个大写字母，开启新 section
    // 否则当成当前 section 的一条数据
    if (/^[A-Z]$/.test(line)) {
      current = { title: line, items: [] };
      sections.value.push(current);
    }
    else if (current) {
      current.items.push(line);
    }
  }
});
</script>

<template>
  <div class="list">
    <div v-for="(section, index) in sections" :key="index" class="section">
      <Title :text="section.title"></Title>
      <Entry :lines="section.items"></Entry>
    </div>
  </div>
</template>

<style scoped>
.list {
  margin: 0 8px;
  width: 736px;
  height: 100%;
}
</style>
