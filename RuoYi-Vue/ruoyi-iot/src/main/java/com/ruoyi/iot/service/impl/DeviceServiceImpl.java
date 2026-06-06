package com.ruoyi.iot.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.iot.domain.Device;
import com.ruoyi.iot.mapper.DeviceMapper;
import com.ruoyi.iot.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements IDeviceService {

    private static final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public Device selectDeviceByDeviceId(Long deviceId) {
        return deviceMapper.selectDeviceByDeviceId(deviceId);
    }

    @Override
    public List<Device> selectDeviceList(Device device) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (user != null) {
            device.setUserId(user.getUserId());
        }
        return deviceMapper.selectDeviceList(device);
    }

    @Override
    public List<Device> selectDeviceListByGroup(Device device) {
        return deviceMapper.selectDeviceListByGroup(device);
    }

    @Override
    public int insertDevice(Device device) {
        device.setCreateTime(DateUtils.getNowDate());
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (user != null) {
            device.setUserId(user.getUserId());
            device.setUserName(user.getUserName());
        }
        device.setStatus(3); // online by default
        return deviceMapper.insertDevice(device);
    }

    @Override
    public int updateDevice(Device device) {
        device.setUpdateTime(DateUtils.getNowDate());
        return deviceMapper.updateDevice(device);
    }

    @Override
    public int deleteDeviceByDeviceId(Long deviceId) {
        return deviceMapper.deleteDeviceByDeviceId(deviceId);
    }

    @Override
    public int deleteDeviceByDeviceIds(Long[] deviceIds) {
        return deviceMapper.deleteDeviceByDeviceIds(deviceIds);
    }
}
