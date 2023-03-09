<template>
  <a-button type="primary" size="large" @click="add">Add</a-button>

  <a-table :columns="columns" :data-source="ebooks" :pagination="pagination" :loading="loading"
    @change="handleTableChange">
    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'cover'">
        <img src="/image/cover1.png">
      </template>
      <template v-else-if="column.key === 'action'">
        <a-button type="primary" @click="showEditModal(record)">Edit</a-button>
        <a-popconfirm title="Are you sure delete this task?" ok-text="Yes" cancel-text="No"
          @confirm="confirmDelete(record.id)" @cancel="cancelDelete">
          <a-button danger>Delete</a-button>
        </a-popconfirm>
      </template>
      <template v-else>
        {{ record[column.dataIndex] ? record[column.dataIndex] : 'null' }}
      </template>
    </template>
  </a-table>

  <a-modal v-model:visible="editVisible" title="Basic Modal" @ok="edit" :loading="editLoading">
    <a-form>
      <a-form-item label="Cover">
        <a-input v-model:value="ebook.cover" />
      </a-form-item>
      <a-form-item label="Name">
        <a-input v-model:value="ebook.name" />
      </a-form-item>
      <a-form-item label="Category 1">
        <a-input v-model:value="ebook.category1Id" />
      </a-form-item>
      <a-form-item label="Category 2">
        <a-input v-model:value="ebook.category2Id" />
      </a-form-item>
      <a-form-item label="Description">
        <a-input v-model:value="ebook.description" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import axios from 'axios';
import { ref, defineComponent, onMounted } from 'vue';
import { message } from 'ant-design-vue';

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
    const ebook: any = ref({})
    const ebooks = ref([])
    const loading = ref(false)
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0
    })

    const editLoading = ref(false)
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
        pagination.value.total = parseInt($.total)
        loading.value = false
      })
    }
    const handleTableChange = () => {
      queryData({
        page: pagination.value.current,
        size: pagination.value.pageSize
      })
    }
    const edit = () => {
      editLoading.value = true
      axios.post('/ebook/save', ebook.value).then((res) => {
        const $ = res.data
        if ($.success) {
          setTimeout(() => {
            editLoading.value = false
            editVisible.value = false
            // reload list
            queryData({
              page: pagination.value.current,
              size: pagination.value.pageSize
            })
          }, 1000)
        }
      })
    }
    const showEditModal = (record: any) => {
      editVisible.value = true
      ebook.value = record
    }
    const add = () => {
      editVisible.value = true
      ebook.value = {}
    }
    const del = (id: string) => {
      axios.delete(`/ebook/delete/${id}`).then((res) => {
        const $ = res.data
        if ($.success) {
          setTimeout(() => {
            queryData({
              page: pagination.value.current,
              size: pagination.value.pageSize
            })
          }, 1000)
        }
      })
    }
    const confirmDelete = (id: string) => {
      del(id)
      message.success('delete confirmed');
    };

    const cancelDelete = () => {
      message.error('delete canceled');
    };

    onMounted(() => {
      queryData({ page: 1, size: pagination.value.pageSize })
    })
    return {
      loading,
      ebooks,
      pagination,
      columns,
      handleTableChange,

      add,
      edit,
      confirmDelete,
      cancelDelete,
      ebook,
      editVisible,
      editLoading,
      showEditModal,
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