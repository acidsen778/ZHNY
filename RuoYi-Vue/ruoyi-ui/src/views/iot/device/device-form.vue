<template>
  <div class="device-form">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-row :gutter="100">
        <el-col :span="24">
          <el-form-item label="设备名称" prop="deviceName">
            <el-input v-model="form.deviceName" placeholder="请输入设备名称" />
          </el-form-item>
          <el-form-item label="设备编号" prop="serialNumber">
            <el-input v-model="form.serialNumber" placeholder="请输入设备编号" maxlength="32" />
          </el-form-item>
          <el-form-item label="设备类型" prop="deviceType">
            <el-select v-model="form.deviceType" placeholder="请选择设备类型" style="width:100%;">
              <el-option v-for="dict in dict.type.iot_device_type" :key="dict.value" :label="dict.label" :value="parseInt(dict.value)" />
            </el-select>
          </el-form-item>
          <el-form-item label="设备状态" prop="status">
            <el-radio-group v-model="form.status">
              <el-radio v-for="dict in dict.type.iot_device_status" :key="dict.value" :label="parseInt(dict.value)">{{ dict.label }}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="固件版本" prop="firmwareVersion">
            <el-input v-model="form.firmwareVersion" placeholder="请输入固件版本">
              <template slot="prepend">Version</template>
            </el-input>
          </el-form-item>
          <el-form-item label="设备图片" prop="imgUrl">
            <el-input v-model="form.imgUrl" placeholder="请输入图片URL，多个用逗号分隔" />
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer" style="text-align:center;padding-top:15px;">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </div>
</template>

<script>
import { getDevice, addDevice, updateDevice } from "@/api/iot/device";

export default {
  name: "DeviceForm",
  dicts: ['iot_device_type', 'iot_device_status'],
  props: {
    deviceId: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      form: {},
      rules: {
        deviceName: [{ required: true, message: "设备名称不能为空", trigger: "blur" }],
        serialNumber: [{ required: true, message: "设备编号不能为空", trigger: "blur" }],
        status: [{ required: true, message: "设备状态不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    if (this.deviceId && this.deviceId > 0) {
      getDevice(this.deviceId).then(response => {
        this.form = response.data;
      });
    } else {
      this.form = {
        deviceName: null,
        serialNumber: null,
        deviceType: null,
        status: 1,
        firmwareVersion: null,
        imgUrl: null,
        remark: null
      };
    }
  },
  methods: {
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deviceId && this.form.deviceId > 0) {
            updateDevice(this.form).then(response => {
              this.$emit("success", "编辑成功");
            });
          } else {
            addDevice(this.form).then(response => {
              this.$emit("success", "新增成功");
            });
          }
        }
      });
    },
    cancel() {
      this.$emit("close");
    }
  }
};
</script>
