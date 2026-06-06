package com.ruoyi.iot.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class AlertLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long alertLogId;

    @Excel(name = "alertName")
    private String alertName;

    @Excel(name = "alertLevel")
    private Long alertLevel;

    @Excel(name = "status")
    private Integer status;

    private Long productId;

    private String serialNumber;

    private String detail;

    private Long userId;

    private String userName;

    private String remark;

    public void setAlertLogId(Long alertLogId) { this.alertLogId = alertLogId; }
    public Long getAlertLogId() { return alertLogId; }
    public void setAlertName(String alertName) { this.alertName = alertName; }
    public String getAlertName() { return alertName; }
    public void setAlertLevel(Long alertLevel) { this.alertLevel = alertLevel; }
    public Long getAlertLevel() { return alertLevel; }
    public void setStatus(Integer status) { this.status = status; }
    public Integer getStatus() { return status; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Long getProductId() { return productId; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public String getSerialNumber() { return serialNumber; }
    public void setDetail(String detail) { this.detail = detail; }
    public String getDetail() { return detail; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getUserId() { return userId; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserName() { return userName; }
    public void setRemark(String remark) { this.remark = remark; }
    public String getRemark() { return remark; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("alertLogId", getAlertLogId())
            .append("alertName", getAlertName())
            .append("alertLevel", getAlertLevel())
            .append("status", getStatus())
            .append("productId", getProductId())
            .append("serialNumber", getSerialNumber())
            .append("detail", getDetail())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("remark", getRemark())
            .toString();
    }
}
