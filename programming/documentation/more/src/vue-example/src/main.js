import 'normalize.css';
import '@/mock';

import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from '@/App.vue';
import i18n from '@/i18n';
import router from '@/router';

const app = createApp(App);
const pinia = createPinia();

app.use(router).use(pinia).use(i18n).mount('#app');
