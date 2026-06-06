package com.ruoyi.iot.service;

import com.ruoyi.iot.domain.Group;

import java.util.List;

public interface IGroupService {

    public Group selectGroupByGroupId(Long groupId);

    public List<Group> selectGroupList(Group group);

    public int insertGroup(Group group);

    public int updateGroup(Group group);

    public int deleteGroupByGroupId(Long groupId);

    public int deleteGroupByGroupIds(Long[] groupIds);
}
