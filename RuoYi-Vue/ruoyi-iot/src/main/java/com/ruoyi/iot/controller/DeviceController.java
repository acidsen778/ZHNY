package com.ruoyi.iot.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.iot.domain.Device;
import com.ruoyi.iot.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/iot/device")
public class DeviceController extends BaseController {

    @Autowired
    private IDeviceService deviceService;

    @PreAuthorize("@ss.hasPermi('iot:device:list')")
    @GetMapping("/list")
    public TableDataInfo list(Device device) {
        startPage();
        List<Device> list = deviceService.selectDeviceList(device);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('iot:device:list')")
    @GetMapping("/shortList")
    public TableDataInfo shortList(Device device) {
        startPage();
        List<Device> list = deviceService.selectDeviceList(device);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('iot:device:query')")
    @GetMapping(value = "/{deviceId}")
    public AjaxResult getInfo(@PathVariable("deviceId") Long deviceId) {
        return success(deviceService.selectDeviceByDeviceId(deviceId));
    }

    @PreAuthorize("@ss.hasPermi('iot:device:add')")
    @Log(title = "iot.device", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Device device) {
        return toAjax(deviceService.insertDevice(device));
    }

    @PreAuthorize("@ss.hasPermi('iot:device:edit')")
    @Log(title = "iot.device", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Device device) {
        return toAjax(deviceService.updateDevice(device));
    }

    @PreAuthorize("@ss.hasPermi('iot:device:edit')")
    @Log(title = "iot.device", businessType = BusinessType.UPDATE)
    @GetMapping("/generator")
    public AjaxResult generator() {
        return success("DEV-" + System.currentTimeMillis());
    }

    @PreAuthorize("@ss.hasPermi('iot:device:remove')")
    @Log(title = "iot.device", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deviceIds}")
    public AjaxResult remove(@PathVariable Long[] deviceIds) {
        return toAjax(deviceService.deleteDeviceByDeviceIds(deviceIds));
    }

    @Log(title = "iot.device", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('iot:device:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, Device device) {
        List<Device> list = deviceService.selectDeviceList(device);
        ExcelUtil<Device> util = new ExcelUtil<>(Device.class);
        util.exportExcel(response, list, "device");
    }
}
