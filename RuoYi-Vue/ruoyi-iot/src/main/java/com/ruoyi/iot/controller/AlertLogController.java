package com.ruoyi.iot.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.iot.domain.AlertLog;
import com.ruoyi.iot.service.IAlertLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/iot/alertLog")
public class AlertLogController extends BaseController {

    @Autowired
    private IAlertLogService alertLogService;

    @PreAuthorize("@ss.hasPermi('iot:alertLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(AlertLog alertLog) {
        startPage();
        List<AlertLog> list = alertLogService.selectAlertLogList(alertLog);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('iot:alertLog:query')")
    @GetMapping(value = "/{alertLogId}")
    public AjaxResult getInfo(@PathVariable("alertLogId") Long alertLogId) {
        return success(alertLogService.selectAlertLogByAlertLogId(alertLogId));
    }

    @PreAuthorize("@ss.hasPermi('iot:alertLog:add')")
    @Log(title = "iot.alertLog", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AlertLog alertLog) {
        return toAjax(alertLogService.insertAlertLog(alertLog));
    }

    @PreAuthorize("@ss.hasPermi('iot:alertLog:edit')")
    @Log(title = "iot.alertLog", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AlertLog alertLog) {
        return toAjax(alertLogService.updateAlertLog(alertLog));
    }

    @PreAuthorize("@ss.hasPermi('iot:alertLog:remove')")
    @Log(title = "iot.alertLog", businessType = BusinessType.DELETE)
    @DeleteMapping("/{alertLogIds}")
    public AjaxResult remove(@PathVariable Long[] alertLogIds) {
        return toAjax(alertLogService.deleteAlertLogByAlertLogIds(alertLogIds));
    }

    @Log(title = "iot.alertLog", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('iot:alertLog:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, AlertLog alertLog) {
        List<AlertLog> list = alertLogService.selectAlertLogList(alertLog);
        ExcelUtil<AlertLog> util = new ExcelUtil<>(AlertLog.class);
        util.exportExcel(response, list, "alertLog");
    }
}
