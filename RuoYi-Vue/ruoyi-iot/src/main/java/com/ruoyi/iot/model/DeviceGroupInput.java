package com.ruoyi.iot.model;

/**
 * 璁惧鍒嗙粍瀵硅薄 iot_device_group
 * 
 * @author kerwincui
 * @date 2021-12-16
 */
public class DeviceGroupInput
{
    private static final long serialVersionUID = 1L;

    /** 鍒嗙粍ID */
    private Long groupId;

    /** 璁惧ID */
    private Long[] deviceIds;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long[] getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(Long[] deviceIds) {
        this.deviceIds = deviceIds;
    }


}

