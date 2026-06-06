<template>
<div style="padding:6px;">
    <el-card style="margin-bottom:6px;">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="75px" style="margin-bottom:-20px;">
            <el-form-item label="设备名称" prop="deviceName">
                <el-input v-model="queryParams.deviceName" placeholder="请输入设备名称" clearable size="small" @keyup.enter.native="handleQuery" style="width:150px;" />
            </el-form-item>
            <el-form-item label="设备编号" prop="serialNumber">
                <el-input v-model="queryParams.serialNumber" placeholder="请输入设备编号" clearable size="small" @keyup.enter.native="handleQuery" style="width:150px;" />
            </el-form-item>
            <el-form-item label="设备状态" prop="status">
                <el-select v-model="queryParams.status" placeholder="请选择设备状态" clearable size="small" style="width:150px;">
                    <el-option v-for="dict in dict.type.iot_device_status" :key="dict.value" :label="dict.label" :value="dict.value" />
                </el-select>
            </el-form-item>
            <el-form-item label="设备类型">
                <el-select v-model="queryParams.deviceType" placeholder="请选择设备类型" clearable size="small" style="width:150px;" >
                    <el-option v-for="dict in dict.type.iot_device_type" :key="dict.value" :label="dict.label" :value="parseInt(dict.value)"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
            <el-form-item style="float:right;">
                <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAddDevice" v-hasPermi="['iot:device:add']">新增</el-button>
            </el-form-item>
        </el-form>
    </el-card>

    <el-card style="padding-bottom:100px;">
        <el-row :gutter="30" v-loading="loading">
            <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6" v-for="(item,index) in deviceList" :key="index" style="margin-bottom:30px;text-align:center;">
                <el-card :body-style="{ padding: '20px'}" shadow="always" class="card-item">
                    <el-row type="flex" :gutter="10" justify="space-between">
                        <el-col :span="20" style="text-align:left;">
                            <el-link type="" :underline="false" @click="handleEditDevice(item)" style="font-weight:bold;font-size:16px;line-height:32px;">
                                <svg-icon icon-class="device" />
                                <span style="margin:0 5px;">{{item.deviceName}}</span>
                                <el-tag size="mini" type="info" v-if="item.firmwareVersion">Ver {{item.firmwareVersion}}</el-tag>
                            </el-link>
                        </el-col>
                        <el-col :span="4">
                            <div style="font-size:28px;color:#ccc;">
                                <svg-icon v-if="item.status==3 && item.rssi >= '-55'" icon-class="wifi_4" />
                                <svg-icon v-else-if="item.status==3 && item.rssi >= '-70' && item.rssi < '-55' " icon-class="wifi_3" />
                                <svg-icon v-else-if="item.status==3 && item.rssi >= '-85' && item.rssi < '-70' " icon-class="wifi_2" />
                                <svg-icon v-else-if="item.status==3 && item.rssi >= '-100' && item.rssi < '-85' " icon-class="wifi_1" />
                                <svg-icon v-else icon-class="wifi_0" />
                            </div>
                        </el-col>
                    </el-row>
                    <el-row :gutter="10">
                        <el-col :span="15">
                            <div style="text-align:left;line-height:40px;white-space:nowrap;">
                                <dict-tag :options="dict.type.iot_device_status" :value="item.status" size="small" style="display:inline-block;" />
                                <dict-tag :options="dict.type.iot_device_type" :value="item.deviceType" size="small" style="display:inline-block;" />
                            </div>
                            <el-descriptions :column="1" size="mini" style="white-space:nowrap;">
                                <el-descriptions-item label="编号">
                                    {{item.serialNumber}}
                                </el-descriptions-item>
                                <el-descriptions-item label="产品" v-if="item.productName">
                                    {{item.productName}}
                                </el-descriptions-item>
                                <el-descriptions-item label="激活时间" v-if="item.activeTime">
                                    {{ parseTime(item.activeTime, '{y}-{m}-{d}') }}
                                </el-descriptions-item>
                            </el-descriptions>
                        </el-col>
                        <el-col :span="9">
                            <div style="margin-top:10px;">
                                <el-image style="height:100px;border-radius:10px;" v-if="item.imgUrl" lazy :preview-src-list="[item.imgUrl.split(',')[0]]" :src="item.imgUrl.split(',')[0]" fit="cover">
                                    <div slot="error" class="image-slot" style="width:100%;height:100px;background:#f5f5f5;line-height:100px;color:#999;font-size:12px;">
                                        <i class="el-icon-picture-outline"></i>
                                    </div>
                                </el-image>
                            </div>
                        </el-col>
                    </el-row>
                    <el-button-group style="margin-top:15px;">
                        <el-button type="danger" size="mini" style="padding:5px 10px;" icon="el-icon-delete" @click="handleDelete(item)" v-hasPermi="['iot:device:remove']">删除 </el-button>
                        <el-button type="primary" size="mini" style="padding:5px 15px;" icon="el-icon-edit" @click="handleEdit(item)" v-hasPermi="['iot:device:edit']">编辑 </el-button>
                        <el-button type="success" size="mini" style="padding:5px 15px;" icon="el-icon-odometer" @click="handDeviceView(item)" v-hasPermi="['iot:device:view']">运行状态 </el-button>
                    </el-button-group>
                </el-card>
            </el-col>
        </el-row>

        <el-empty description="暂无数据，请添加设备" v-if="total==0"></el-empty>
        <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    </el-card>

    <el-dialog :title="title" :visible.sync="open" width="60%" @close="open=false">
        <device-form v-if="open" :deviceId="deviceId" @success="handleSuccess" @close="open=false"></device-form>
    </el-dialog>

</div>
</template>

<script>
import {
    listDeviceShort,
    delDevice,
} from "@/api/iot/device";

import DeviceForm from "./device-form.vue"

export default {
    name: "Device",
    dicts: ['iot_device_status', 'iot_device_type'],
    components: {DeviceForm},
    data() {
        return {
            loading: true,
            total: 0,
            deviceList: [],
            open:false,
            title:'',
            queryParams: {
                pageNum: 1,
                pageSize: 12,
                deviceName: null,
                serialNumber: null,
                status: null,
                deviceType:null
            },
            deviceId:0,
        };
    },
    created() {
        this.getList();
        let productId = this.$route.query.productId
        if (productId != null) {
            this.queryParams.productId = Number(productId);
        }
        let groupId = this.$route.query.groupId
        if (groupId != null) {
            this.queryParams.groupId = Number(groupId);
        }
        let sn = this.$route.query.sn
        if (sn != null) {
            this.queryParams.serialNumber = sn;
        }
    },
    methods: {
        getList() {
            this.loading = true;
            listDeviceShort(this.queryParams).then(response => {
                this.deviceList = response.rows;
                this.total = response.total;
                this.loading = false;
            }).catch(() => {
                this.loading = false;
            });
        },
        handleQuery() {
            this.queryParams.pageNum = 1;
            this.getList();
        },
        resetQuery() {
            this.resetForm("queryForm");
            this.handleQuery();
        },
        handleAddDevice() {
            this.deviceId = 0;
            this.title = '新增设备';
            this.open = true;
        },
        handleEdit(item) {
            this.deviceId = item.deviceId;
            this.title = '编辑设备';
            this.open = true;
        },
        handleEditDevice(item) {
            this.handleEdit(item);
        },
        handDeviceView(item) {
            this.$router.push({ path: '/iot/device-view/index', query: { deviceId: item.deviceId }});
        },
        handleDelete(item) {
            this.$modal.confirm('确认删除设备"' + item.deviceName + '"吗？').then(function() {
                return delDevice(item.deviceId);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => {});
        },
        handleSuccess(result) {
            this.open = false;
            this.getList();
            this.$modal.msgSuccess(result || '操作成功');
        }
    }
};
</script>

<style>
.card-item:hover {
    border: 1px solid #409EFF;
    box-shadow: 0 2px 12px 0 rgba(64,158,255,0.3);
}
.el-descriptions--mini .el-descriptions__body .el-descriptions__table .el-descriptions-item__cell {
    padding-bottom: 2px;
}
</style>
