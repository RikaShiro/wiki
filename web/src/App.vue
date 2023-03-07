<template>
  <a-layout>
    <TheHeader></TheHeader>
    <a-layout-content style="padding: 0 50px">
      <RouterView></RouterView>
    </a-layout-content>
    <TheFooter></TheFooter>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import TheHeader from '@/components/TheHeader.vue'
import TheFooter from '@/components/TheFooter.vue'

export default defineComponent({
  components: {
    TheHeader,
    TheFooter
  }
});
</script>
