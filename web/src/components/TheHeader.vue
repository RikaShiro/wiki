<template>
  <a-layout-header class="header">
    <div class="logo"></div>
    <a-menu v-model:selectedKeys="selectedKeys1" theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
      <a-menu-item key="home">
        <RouterLink to="/">Home</RouterLink>
      </a-menu-item>
      <a-menu-item key="admin">
        <RouterLink to="/admin">Manage</RouterLink>
      </a-menu-item>
      <a-menu-item key="about">
        <RouterLink to="/about">About Us</RouterLink>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
export default defineComponent({
  name: 'TheHeader',
  components: {},
  setup() {
    return {
      selectedKeys1: ref<string[]>(['2']),
    };
  },
});
</script>