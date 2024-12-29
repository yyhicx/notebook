import { defineStore } from 'pinia';

export const useGlobalStore = defineStore('global', {
  state: () => ({
    routeLoaded: false,
    firstRoute: null,
    menuTree: null
  }),
  actions: {
    setRouteLoaded(loaded) {
      this.routeLoaded = loaded;
    },
    setFirstRoute(route) {
      this.firstRoute = route;
    },
    setMenuTree(data) {
      this.menuTree = data;
    }
  }
});
