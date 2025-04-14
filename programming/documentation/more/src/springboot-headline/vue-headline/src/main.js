import { createApp } from 'vue';
import { createPinia } from 'pinia';
import mitt from 'mitt';
import 'element-plus/dist/index.css';

import App from './App.vue';
import router from './router';

const app = createApp(App);

app.config.globalProperties.mittBus = mitt();

app.use(createPinia());
app.use(router);

app.mount('#app');
