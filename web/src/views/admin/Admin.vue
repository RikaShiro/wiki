<template>
  <a-table :columns="columns" :data-source="ebooks" :pagination="pagination" :loading="loading"
    @change="handleTableChange">
    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'cover'">
        <img src="/image/cover1.png">
      </template>
      <template v-else-if="column.key === 'action'">
        <a-button type="primary" @click="showEditModal">Edit</a-button>
        <a-button danger>Delete</a-button>
      </template>
      <template v-else>
        {{ record[column.dataIndex] ? record[column.dataIndex] : 'null' }}
      </template>
    </template>
  </a-table>
  <a-modal v-model:visible="editVisible" title="Basic Modal" @ok="editHandleOk">
    <p>Edit</p>
  </a-modal>
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
    const editVisible = ref(false)

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
    const editHandleOk = () => {
      editVisible.value = false
    }
    const showEditModal = () => {
      editVisible.value = true
    }
    onMounted(() => {
      queryData({ page: 1, size: pagination.value.pageSize })
    })
    return {
      loading,
      ebooks,
      pagination,
      columns,
      handleTableChange,

      editVisible,
      showEditModal,
      editHandleOk
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