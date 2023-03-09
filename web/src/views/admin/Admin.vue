<template>
  <a-table :columns="columns" :data-source="ebooks" :pagination="pagination" :loading="loading"
    @change="handleTableChange">
    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'cover'">
        <img src="/image/cover1.png">
      </template>
      <template v-else-if="column.key === 'action'">
        <a-button type="primary">Edit</a-button>
        <a-button danger>Delete</a-button>
      </template>
      <template v-else>
        {{ record[column.dataIndex] ? record[column.dataIndex] : 'null' }}
      </template>
    </template>
  </a-table>
</template>

<script lang="ts">
import axios from 'axios';
import { ref, defineComponent, onMounted } from 'vue';

const columns = [
  {
    title: 'Cover',
    dataIndex: 'cover',
    key: 'cover'
  },
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: 'Category1',
    dataIndex: 'category1Id',
    key: 'c1'
  },
  {
    title: 'Category2',
    dataIndex: 'category2Id',
    key: 'c2'
  },
  {
    title: 'Docs',
    dataIndex: 'docCount',
    key: 'doc'
  },
  {
    title: 'Views',
    dataIndex: 'viewCount',
    key: 'view'
  },
  {
    title: 'Likes',
    dataIndex: 'voteCount',
    key: 'like'
  },
  {
    title: 'Action',
    dataIndex: 'action',
    key: 'action'
  },
];

export default defineComponent({
  name: 'Admin',
  setup() {
    const ebooks = ref([])
    const loading = ref(false)
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0
    })
    const queryData = (params: any) => {
      loading.value = true
      axios.get('/ebook/list', {
        params: {
          page: params.page,
          size: params.size
        }
      }).then((res) => {
        const $ = res.data.content
        ebooks.value = $.list
        pagination.value.current = params.page
        pagination.value.total = $.total
        loading.value = false
      })
    }
    const handleTableChange = () => {
      queryData({
        page: pagination.value.current,
        size: pagination.value.pageSize
      })
    }
    onMounted(() => {
      queryData({ page: 1, size: pagination.value.pageSize })
    })
    return {
      ebooks,
      loading,
      pagination,
      columns,
      handleTableChange
    }
  }
})
</script>

<style scoped>
img {
  width: 50px;
  height: 50px;
}
</style>