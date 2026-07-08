<template>
  <div class="app-container">
    <el-form ref="queryForm" :model="queryParams" :inline="true" size="small">
      <el-form-item label="配置名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入配置名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="enabled">
        <el-select v-model="queryParams.enabled" placeholder="全部" clearable @change="handleQuery">
          <el-option label="已启用" :value="true" />
          <el-option label="已停用" :value="false" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-connection" size="mini" @click="handleHealth">健康检查</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="configList">
      <el-table-column label="配置名称" align="center" prop="name" :show-overflow-tooltip="true" />
      <el-table-column label="Prometheus URL" align="center" prop="prometheusUrl" :show-overflow-tooltip="true" />
      <el-table-column label="Alertmanager URL" align="center" prop="alertmanagerUrl" :show-overflow-tooltip="true" />
      <el-table-column label="Alertmanager" align="center" prop="alertmanagerEnabled">
        <template slot-scope="scope">
          <el-tag :type="scope.row.alertmanagerEnabled ? 'success' : 'info'">
            {{ scope.row.alertmanagerEnabled ? '启用' : '停用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="采集间隔" align="center" prop="scrapeInterval" />
      <el-table-column label="状态" align="center" prop="enabled">
        <template slot-scope="scope">
          <el-tag :type="scope.row.enabled ? 'success' : 'danger'">
            {{ scope.row.enabled ? '启用' : '停用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="最后同步" align="center" prop="lastSync" width="180" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
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

    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="配置名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入配置名称" />
        </el-form-item>
        <el-form-item label="Prometheus URL" prop="prometheusUrl">
          <el-input v-model="form.prometheusUrl" placeholder="请输入Prometheus URL" />
        </el-form-item>
        <el-form-item label="Alertmanager URL" prop="alertmanagerUrl">
          <el-input v-model="form.alertmanagerUrl" placeholder="请输入Alertmanager URL" />
        </el-form-item>
        <el-form-item label="启用Alertmanager">
          <el-switch v-model="form.alertmanagerEnabled" />
        </el-form-item>
        <el-form-item label="采集间隔" prop="scrapeInterval">
          <el-select v-model="form.scrapeInterval" placeholder="请选择采集间隔">
            <el-option label="10秒" value="10s" />
            <el-option label="15秒" value="15s" />
            <el-option label="30秒" value="30s" />
            <el-option label="60秒" value="60s" />
          </el-select>
        </el-form-item>
        <el-form-item label="启用状态">
          <el-switch v-model="form.enabled" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listConfig, getConfig, delConfig, addConfig, updateConfig, healthCheck } from '@/api/monitor/prometheus'

export default {
  name: 'PrometheusConfig',
  data() {
    return {
      loading: false,
      configList: [],
      total: 0,
      open: false,
      title: '',
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        enabled: null
      },
      form: {},
      rules: {
        name: [{ required: true, message: '配置名称不能为空', trigger: 'blur' }],
        prometheusUrl: [{ required: true, message: 'Prometheus URL不能为空', trigger: 'blur' }],
        scrapeInterval: [{ required: true, message: '采集间隔不能为空', trigger: 'change' }]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      listConfig(this.queryParams).then(response => {
        this.configList = response.rows || response.data || []
        this.total = response.total || this.configList.length
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    reset() {
      this.form = {
        id: null,
        name: null,
        prometheusUrl: null,
        alertmanagerUrl: null,
        alertmanagerEnabled: false,
        scrapeInterval: '15s',
        enabled: true
      }
      this.resetForm('form')
    },
    cancel() {
      this.open = false
      this.reset()
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加Prometheus配置'
    },
    handleUpdate(row) {
      this.reset()
      getConfig(row.id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改Prometheus配置'
      })
    },
    submitForm() {
      this.$refs.form.validate(valid => {
        if (!valid) return
        const request = this.form.id != null ? updateConfig(this.form) : addConfig(this.form)
        request.then(() => {
          this.$modal.msgSuccess(this.form.id != null ? '修改成功' : '新增成功')
          this.open = false
          this.getList()
        })
      })
    },
    handleDelete(row) {
      this.$modal.confirm('是否确认删除名称为"' + row.name + '"的数据项？').then(() => {
        return delConfig(row.id)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    },
    handleHealth() {
      healthCheck().then(response => {
        const data = response.data || {}
        this.$modal.msgSuccess(data.healthy ? 'Prometheus连接正常，版本：' + data.version : 'Prometheus连接异常')
      })
    }
  }
}
</script>
