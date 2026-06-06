package com.ruoyi.iot.service;

import com.ruoyi.iot.domain.Device;

import java.util.List;

public interface IDeviceService {

    public Device selectDeviceByDeviceId(Long deviceId);

    public List<Device> selectDeviceList(Device device);

    public List<Device> selectDeviceListByGroup(Device device);

    public int insertDevice(Device device);

    public int updateDevice(Device device);

    public int deleteDeviceByDeviceId(Long deviceId);

    public int deleteDeviceByDeviceIds(Long[] deviceIds);
}
