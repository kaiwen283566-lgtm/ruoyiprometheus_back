<template>
  <div class="app-container">
    <el-form ref="queryForm" :model="queryParams" :inline="true" size="small">
      <el-form-item label="查询语句" prop="query">
        <el-input v-model="queryParams.query" placeholder="LogQL 查询语句" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="时间范围" prop="minutes">
        <el-select v-model="queryParams.minutes" placeholder="请选择时间范围" clearable @change="handleQuery">
          <el-option label="5分钟" :value="5" />
          <el-option label="15分钟" :value="15" />
          <el-option label="30分钟" :value="30" />
          <el-option label="1小时" :value="60" />
          <el-option label="2小时" :value="120" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">查询记录</el-button>
        <el-button type="success" icon="el-icon-caret-right" size="mini" @click="handleExecute">执行查询</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="queryList">
      <el-table-column label="查询语句" align="center" prop="query" :show-overflow-tooltip="true" min-width="300" />
      <el-table-column label="时间范围" align="center" prop="minutes" width="100">
        <template slot-scope="scope">{{ scope.row.minutes }}分钟</template>
      </el-table-column>
      <el-table-column label="限制条数" align="center" prop="limit" width="100" />
      <el-table-column label="状态" align="center" prop="status" width="100">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 'success' ? 'success' : 'danger'">
            {{ scope.row.status === 'success' ? '成功' : '失败' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)">查看结果</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <el-dialog title="查询结果" :visible.sync="resultOpen" width="800px" append-to-body>
      <el-input v-model="currentResult" type="textarea" :rows="18" readonly />
      <div slot="footer" class="dialog-footer">
        <el-button @click="resultOpen = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listQuery, delQuery, executeQuery } from '@/api/logs/query'

export default {
  name: 'LogQuery',
  data() {
    return {
      loading: false,
      queryList: [],
      total: 0,
      resultOpen: false,
      currentResult: '',
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        query: null,
        minutes: 30,
        limit: 100
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      listQuery(this.queryParams).then(response => {
        this.queryList = response.rows || response.data || []
        this.total = response.total || this.queryList.length
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    handleExecute() {
      if (!this.queryParams.query) {
        this.$modal.msgWarning('请输入LogQL查询语句')
        return
      }
      executeQuery({
        logql: this.queryParams.query,
        minutes: this.queryParams.minutes || 30,
        limit: this.queryParams.limit || 100
      }).then(response => {
        this.currentResult = JSON.stringify(response.data, null, 2)
        this.resultOpen = true
        this.getList()
      })
    },
    handleView(row) {
      this.currentResult = row.result || row.errorMsg || '暂无结果'
      this.resultOpen = true
    },
    handleDelete(row) {
      this.$modal.confirm('是否确认删除此查询记录？').then(() => {
        return delQuery(row.id)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    }
  }
}
</script>
