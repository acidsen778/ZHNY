<template>
  <div>
    <el-card>
      <div slot="header">
        <span>
          <svg-icon icon-class="device" />
          {{ device.deviceName || '设备详情' }}
          <el-tag size="mini" style="margin-left:8px;" :type="device.status==3?'success':'info'">
            <dict-tag :options="dict.type.iot_device_status" :value="device.status"/>
          </el-tag>
        </span>
        <el-button style="float:right;" size="mini" @click="$router.back()">返回</el-button>
      </div>

      <el-descriptions :column="2" border>
        <el-descriptions-item label="设备编号">{{ device.serialNumber }}</el-descriptions-item>
        <el-descriptions-item label="产品名称">{{ device.productName || '-' }}</el-descriptions-item>
        <el-descriptions-item label="信号强度">{{ device.rssi || '-' }}</el-descriptions-item>
        <el-descriptions-item label="网络地址">{{ device.networkAddress || '-' }}</el-descriptions-item>
        <el-descriptions-item label="激活时间">{{ device.activeTime ? parseTime(device.activeTime) : '-' }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ device.createTime ? parseTime(device.createTime) : '-' }}</el-descriptions-item>
        <el-descriptions-item label="备注" :span="2">{{ device.remark || '-' }}</el-descriptions-item>
      </el-descriptions>

      <el-divider>实时数据</el-divider>

      <el-row :gutter="20" v-loading="loadingData">
        <el-col :span="colSpan" v-for="(val,key) in telemetryData" :key="key" style="margin-bottom:20px;">
          <el-card shadow="hover" :body-style="{padding:'15px',textAlign:'center'}">
            <div style="font-size:32px;font-weight:bold;color:#409EFF;">{{ val }}</div>
            <div style="font-size:13px;color:#999;margin-top:5px;">{{ key }}</div>
          </el-card>
        </el-col>
      </el-row>

      <el-empty v-if="Object.keys(telemetryData).length===0" description="暂无实时数据"></el-empty>
    </el-card>
  </div>
</template>

<script>
import { getDevice } from "@/api/iot/device";

export default {
  name: "DeviceView",
  dicts: ['iot_device_status'],
  data() {
    return {
      device: {},
      telemetryData: {},
      loadingData: false,
    };
  },
  computed: {
    colSpan() {
      const count = Object.keys(this.telemetryData).length;
      if (count === 0) return 6;
      if (count <= 2) return 12;
      if (count === 3) return 8;
      if (count <= 4) return 6;
      return 4; // 5+ metrics
    }
  },
  created() {
    const deviceId = this.$route.query.deviceId;
    if (deviceId) {
      this.loadDevice(deviceId);
    }
  },
  methods: {
    getTelemetryKeys(serialNumber) {
      const sn = (serialNumber || '').toLowerCase();
      if (sn.includes('temp')) return ['温度 (°C)', '湿度 (%)'];
      if (sn.includes('ph')) return ['PH 值', '土壤湿度 (%)'];
      if (sn.includes('lux')) return ['光照强度 (lux)'];
      if (sn.includes('co2')) return ['CO2 浓度 (ppm)'];
      if (sn.includes('soil')) return ['土壤湿度 (%)', '土壤温度 (°C)'];
      if (sn.includes('cam')) return ['视频流状态', '帧率 (fps)'];
      if (sn.includes('valve')) return ['阀门状态', '开度 (%)', '流量 (L/min)'];
      if (sn.includes('weather')) return ['温度 (°C)', '湿度 (%)', '风速 (m/s)', '气压 (hPa)'];
      if (sn.includes('water') && !sn.includes('quality')) return ['水位 (m)'];
      if (sn.includes('quality')) return ['pH 值', '浊度 (NTU)', '溶解氧 (mg/L)'];
      return ['温度 (°C)', '湿度 (%)', '光照 (lux)', 'CO2 (ppm)'];
    },
    loadDevice(deviceId) {
      getDevice(deviceId).then(response => {
        this.device = response.data;
        this.loadTelemetry();
      });
    },
    loadTelemetry() {
      this.loadingData = true;
      this.$nextTick(() => {
        const keys = this.getTelemetryKeys(this.device.serialNumber);
        const data = {};
        keys.forEach(key => {
          let min = 0, max = 100, unit = '';
          if (key.includes('°C')) { min = 10; max = 40; }
          else if (key.includes('%')) { min = 20; max = 95; }
          else if (key.includes('ppm')) { min = 300; max = 2000; }
          else if (key.includes('lux')) { min = 100; max = 80000; }
          else if (key.includes('pH') || key.includes('PH')) { min = 5; max = 9; }
          else if (key.includes('m/s')) { min = 0; max = 15; }
          else if (key.includes('hPa')) { min = 990; max = 1030; }
          else if (key.includes('NTU')) { min = 0; max = 50; }
          else if (key.includes('mg/L')) { min = 2; max = 12; }
          else if (key.includes('水位')) { min = 0; max = 5; }
          else if (key.includes('L/min')) { min = 0; max = 100; }
          else if (key.includes('开度')) { min = 0; max = 100; }
          data[key] = (Math.random() * (max - min) + min).toFixed(1);
        });
        this.telemetryData = data;
        this.loadingData = false;
      });
    }
  }
};
</script>
