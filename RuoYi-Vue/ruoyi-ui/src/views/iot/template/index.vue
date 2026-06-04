<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物模型名称" prop="templateName">
        <el-input
          v-model="queryParams.templateName"
          placeholder="请输入物模型名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租户ID" prop="tenantId">
        <el-input
          v-model="queryParams.tenantId"
          placeholder="请输入租户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租户名称" prop="tenantName">
        <el-input
          v-model="queryParams.tenantName"
          placeholder="请输入租户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标识符，产品下唯一" prop="identifier">
        <el-input
          v-model="queryParams.identifier"
          placeholder="请输入标识符，产品下唯一"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否系统通用" prop="isSys">
        <el-input
          v-model="queryParams.isSys"
          placeholder="请输入是否系统通用"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否首页显示" prop="isTop">
        <el-input
          v-model="queryParams.isTop"
          placeholder="请输入是否首页显示"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否实时监测" prop="isMonitor">
        <el-input
          v-model="queryParams.isMonitor"
          placeholder="请输入是否实时监测"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否只读数据(0-否，1-是)" prop="isReadonly">
        <el-input
          v-model="queryParams.isReadonly"
          placeholder="请输入是否只读数据(0-否，1-是)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序，值越大，排序越靠前" prop="modelOrder">
        <el-input
          v-model="queryParams.modelOrder"
          placeholder="请输入排序，值越大，排序越靠前"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['iot:template:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['iot:template:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['iot:template:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['iot:template:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="templateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="物模型ID" align="center" prop="templateId" />
      <el-table-column label="物模型名称" align="center" prop="templateName" />
      <el-table-column label="租户ID" align="center" prop="tenantId" />
      <el-table-column label="租户名称" align="center" prop="tenantName" />
      <el-table-column label="标识符，产品下唯一" align="center" prop="identifier" />
      <el-table-column label="模型类别" align="center" prop="type" />
      <el-table-column label="数据类型" align="center" prop="datatype" />
      <el-table-column label="数据定义" align="center" prop="specs" />
      <el-table-column label="是否系统通用" align="center" prop="isSys" />
      <el-table-column label="是否首页显示" align="center" prop="isTop" />
      <el-table-column label="是否实时监测" align="center" prop="isMonitor" />
      <el-table-column label="是否只读数据(0-否，1-是)" align="center" prop="isReadonly" />
      <el-table-column label="排序，值越大，排序越靠前" align="center" prop="modelOrder" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['iot:template:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['iot:template:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改物模型模板对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="物模型名称" prop="templateName">
              <el-input v-model="form.templateName" placeholder="请输入物模型名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="租户ID" prop="tenantId">
              <el-input v-model="form.tenantId" placeholder="请输入租户ID" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="租户名称" prop="tenantName">
              <el-input v-model="form.tenantName" placeholder="请输入租户名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="标识符，产品下唯一" prop="identifier">
              <el-input v-model="form.identifier" placeholder="请输入标识符，产品下唯一" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="是否系统通用" prop="isSys">
              <el-input v-model="form.isSys" placeholder="请输入是否系统通用" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="是否首页显示" prop="isTop">
              <el-input v-model="form.isTop" placeholder="请输入是否首页显示" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="是否实时监测" prop="isMonitor">
              <el-input v-model="form.isMonitor" placeholder="请输入是否实时监测" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="是否只读数据(0-否，1-是)" prop="isReadonly">
              <el-input v-model="form.isReadonly" placeholder="请输入是否只读数据(0-否，1-是)" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="排序，值越大，排序越靠前" prop="modelOrder">
              <el-input v-model="form.modelOrder" placeholder="请输入排序，值越大，排序越靠前" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="删除标志" prop="delFlag">
              <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTemplate, getTemplate, delTemplate, addTemplate, updateTemplate } from "@/api/iot/template"

export default {
  name: "Template",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 物模型模板表格数据
      templateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        templateName: null,
        tenantId: null,
        tenantName: null,
        identifier: null,
        type: null,
        datatype: null,
        specs: null,
        isSys: null,
        isTop: null,
        isMonitor: null,
        isReadonly: null,
        modelOrder: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        templateName: [
          { required: true, message: "物模型名称不能为空", trigger: "blur" }
        ],
        tenantId: [
          { required: true, message: "租户ID不能为空", trigger: "blur" }
        ],
        tenantName: [
          { required: true, message: "租户名称不能为空", trigger: "blur" }
        ],
        identifier: [
          { required: true, message: "标识符，产品下唯一不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "模型类别不能为空", trigger: "change" }
        ],
        datatype: [
          { required: true, message: "数据类型不能为空", trigger: "change" }
        ],
        specs: [
          { required: true, message: "数据定义不能为空", trigger: "blur" }
        ],
        isSys: [
          { required: true, message: "是否系统通用不能为空", trigger: "blur" }
        ],
        isTop: [
          { required: true, message: "是否首页显示不能为空", trigger: "blur" }
        ],
        isMonitor: [
          { required: true, message: "是否实时监测不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询物模型模板列表 */
    getList() {
      this.loading = true
      listTemplate(this.queryParams).then(response => {
        this.templateList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        templateId: null,
        templateName: null,
        tenantId: null,
        tenantName: null,
        identifier: null,
        type: null,
        datatype: null,
        specs: null,
        isSys: null,
        isTop: null,
        isMonitor: null,
        isReadonly: null,
        modelOrder: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.templateId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加物模型模板"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const templateId = row.templateId || this.ids
      getTemplate(templateId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改物模型模板"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.templateId != null) {
            updateTemplate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addTemplate(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const templateIds = row.templateId || this.ids
      this.$modal.confirm('是否确认删除物模型模板编号为"' + templateIds + '"的数据项？').then(function() {
        return delTemplate(templateIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('iot/template/export', {
        ...this.queryParams
      }, `template_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
