package com.ruoyi.iot.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.iot.domain.Group;
import com.ruoyi.iot.mapper.GroupMapper;
import com.ruoyi.iot.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public Group selectGroupByGroupId(Long groupId) {
        return groupMapper.selectGroupByGroupId(groupId);
    }

    @Override
    public List<Group> selectGroupList(Group group) {
        return groupMapper.selectGroupList(group);
    }

    @Override
    public int insertGroup(Group group) {
        group.setCreateTime(DateUtils.getNowDate());
        return groupMapper.insertGroup(group);
    }

    @Override
    public int updateGroup(Group group) {
        group.setUpdateTime(DateUtils.getNowDate());
        return groupMapper.updateGroup(group);
    }

    @Override
    public int deleteGroupByGroupId(Long groupId) {
        return groupMapper.deleteGroupByGroupId(groupId);
    }

    @Override
    public int deleteGroupByGroupIds(Long[] groupIds) {
        return groupMapper.deleteGroupByGroupIds(groupIds);
    }
}
