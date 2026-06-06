package com.ruoyi.iot.mapper;

import com.ruoyi.iot.domain.Device;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceMapper {

    public Device selectDeviceByDeviceId(Long deviceId);

    public List<Device> selectDeviceList(Device device);

    public List<Device> selectDeviceListByGroup(Device device);

    public int insertDevice(Device device);

    public int updateDevice(Device device);

    public int deleteDeviceByDeviceId(Long deviceId);

    public int deleteDeviceByDeviceIds(Long[] deviceIds);
}
