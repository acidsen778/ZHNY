package com.ruoyi.iot.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class Device extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long deviceId;

    @Excel(name = "deviceName")
    private String deviceName;

    private Long productId;

    @Excel(name = "productName")
    private String productName;

    private Long landId;

    private String landName;

    private Long userId;

    private String userName;

    @Excel(name = "serialNumber")
    private String serialNumber;

    @Excel(name = "firmwareVersion")
    private java.math.BigDecimal firmwareVersion;

    private Integer deviceType;

    @Excel(name = "status")
    private Integer status;

    private Integer rssi;

    private Integer isShadow;

    @Excel(name = "networkAddress")
    private String networkAddress;

    @Excel(name = "networkIp")
    private String networkIp;

    @Excel(name = "longitude")
    private java.math.BigDecimal longitude;

    @Excel(name = "latitude")
    private java.math.BigDecimal latitude;

    private Date activeTime;

    private String thingsModelValue;

    private String imgUrl;

    private Integer locationWay;

    private String tenantId;

    private String tenantName;

    private Long groupId;

    private String groupName;

    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }
    public Long getDeviceId() { return deviceId; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }
    public String getDeviceName() { return deviceName; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Long getProductId() { return productId; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductName() { return productName; }
    public void setLandId(Long landId) { this.landId = landId; }
    public Long getLandId() { return landId; }
    public void setLandName(String landName) { this.landName = landName; }
    public String getLandName() { return landName; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getUserId() { return userId; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserName() { return userName; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public String getSerialNumber() { return serialNumber; }
    public void setFirmwareVersion(java.math.BigDecimal firmwareVersion) { this.firmwareVersion = firmwareVersion; }
    public java.math.BigDecimal getFirmwareVersion() { return firmwareVersion; }
    public void setDeviceType(Integer deviceType) { this.deviceType = deviceType; }
    public Integer getDeviceType() { return deviceType; }
    public void setStatus(Integer status) { this.status = status; }
    public Integer getStatus() { return status; }
    public void setRssi(Integer rssi) { this.rssi = rssi; }
    public Integer getRssi() { return rssi; }
    public void setIsShadow(Integer isShadow) { this.isShadow = isShadow; }
    public Integer getIsShadow() { return isShadow; }
    public void setNetworkAddress(String networkAddress) { this.networkAddress = networkAddress; }
    public String getNetworkAddress() { return networkAddress; }
    public void setNetworkIp(String networkIp) { this.networkIp = networkIp; }
    public String getNetworkIp() { return networkIp; }
    public void setLongitude(java.math.BigDecimal longitude) { this.longitude = longitude; }
    public java.math.BigDecimal getLongitude() { return longitude; }
    public void setLatitude(java.math.BigDecimal latitude) { this.latitude = latitude; }
    public java.math.BigDecimal getLatitude() { return latitude; }
    public void setActiveTime(Date activeTime) { this.activeTime = activeTime; }
    public Date getActiveTime() { return activeTime; }
    public void setThingsModelValue(String thingsModelValue) { this.thingsModelValue = thingsModelValue; }
    public String getThingsModelValue() { return thingsModelValue; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
    public String getImgUrl() { return imgUrl; }
    public void setLocationWay(Integer locationWay) { this.locationWay = locationWay; }
    public Integer getLocationWay() { return locationWay; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getTenantId() { return tenantId; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }
    public String getTenantName() { return tenantName; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }
    public Long getGroupId() { return groupId; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public String getGroupName() { return groupName; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("deviceId", getDeviceId())
            .append("deviceName", getDeviceName())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("landId", getLandId())
            .append("landName", getLandName())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("serialNumber", getSerialNumber())
            .append("firmwareVersion", getFirmwareVersion())
            .append("deviceType", getDeviceType())
            .append("status", getStatus())
            .append("rssi", getRssi())
            .append("isShadow", getIsShadow())
            .append("networkAddress", getNetworkAddress())
            .append("networkIp", getNetworkIp())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("activeTime", getActiveTime())
            .append("thingsModelValue", getThingsModelValue())
            .append("imgUrl", getImgUrl())
            .append("locationWay", getLocationWay())
            .append("tenantId", getTenantId())
            .append("tenantName", getTenantName())
            .toString();
    }
}
