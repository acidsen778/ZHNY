package com.ruoyi.iot.mapper;

import com.ruoyi.iot.domain.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMapper {

    public Group selectGroupByGroupId(Long groupId);

    public List<Group> selectGroupList(Group group);

    public int insertGroup(Group group);

    public int updateGroup(Group group);

    public int deleteGroupByGroupId(Long groupId);

    public int deleteGroupByGroupIds(Long[] groupIds);
}
