<script setup>
import { defineProps } from 'vue';
import Volume from '../assets/svg/volume.svg';
const props = defineProps({
  lines: { type: Array, default: () => [] }
});

const parseLine = (line) => {
  // 改进的正则表达式，更好地处理各种情况
  const regex = /^([\w()'-]+(?:\s+[\w()'-]+)*?)\s*(\[[^\]]+\])?\s*(.*)$/;

  // 处理特殊情况的备用正则
  const fallbackRegex = /^([\w()'-]+)\s*(.*)$/;

  const match = line.match(regex);

  if (!match) {
    // 尝试备用解析方案
    const fallbackMatch = line.match(fallbackRegex);
    if (!fallbackMatch) return null;

    return {
      word: fallbackMatch[1],
      phonetic: '',
      translation: fallbackMatch[2] || ''
    };
  }

  // 提取各部分并清理空格
  const word = match[1].trim();
  let phonetic = (match[2] || '').trim();
  let translation = (match[3] || '').trim();

  // 处理特殊情况：当翻译部分包含音标时
  if (!phonetic && translation.startsWith('[') && translation.includes(']')) {
    const phoneticEnd = translation.indexOf(']') + 1;
    phonetic = translation.substring(0, phoneticEnd);
    translation = translation.substring(phoneticEnd).trim();
  }

  return {
    word,
    phonetic,
    translation
  };
};

const parsedLines = props.lines
  .map(parseLine)
  .filter(item => item);

const playAudio = (word) => {
  const audio = new Audio(`/audio/${word.toLowerCase().replace(/\(.*?\)/g, '')}.mp3`);
  audio.play().catch(error => {
    console.log('Audio playback failed:', error);
    alert('Failed to play audio. Please check your resources.');
  })
}
</script>

<template>
  <ul class="entry">
    <li v-for="item in parsedLines" :key="item.word">
      <div class="word">{{ item.word }}</div>
      <button v-if="item.phonetic" @click="playAudio(item.word)" class="phonetic">
        <Volume class="volume" /> {{ item.phonetic }}
      </button>
      <div class="translation">{{ item.translation }}</div>
    </li>
  </ul>
</template>

<style scoped>
.entry {
  margin: 0;
  padding: 0;
}

.entry>li {
  margin: 0 16px;
  width: 704px;
  height: 42px;
  display: flex;
  align-items: center;
  list-style-type: none;
}

.word {
  margin-right: 10px;
}

.phonetic {
  height: 30px;
  display: flex;
  align-items: center;
  gap: 4px;
  cursor: pointer;
  background: transparent;
  border: none;
}

.volume {
  width: 12px;
  height: 12px;
}

.translation {
  margin-left: auto;
}
</style>
