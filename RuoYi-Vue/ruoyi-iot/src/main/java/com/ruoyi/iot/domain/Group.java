package com.ruoyi.iot.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class Group extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long groupId;

    @Excel(name = "groupName")
    private String groupName;

    @Excel(name = "groupOrder")
    private Long groupOrder;

    private Long userId;

    private String userName;

    private String tenantId;

    private String tenantName;

    private String remark;

    public void setGroupId(Long groupId) { this.groupId = groupId; }
    public Long getGroupId() { return groupId; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public String getGroupName() { return groupName; }
    public void setGroupOrder(Long groupOrder) { this.groupOrder = groupOrder; }
    public Long getGroupOrder() { return groupOrder; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getUserId() { return userId; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserName() { return userName; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getTenantId() { return tenantId; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }
    public String getTenantName() { return tenantName; }
    public void setRemark(String remark) { this.remark = remark; }
    public String getRemark() { return remark; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("groupId", getGroupId())
            .append("groupName", getGroupName())
            .append("groupOrder", getGroupOrder())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("tenantId", getTenantId())
            .append("tenantName", getTenantName())
            .append("remark", getRemark())
            .toString();
    }
}
