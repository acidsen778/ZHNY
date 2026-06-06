package com.ruoyi.iot.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.iot.domain.AlertLog;
import com.ruoyi.iot.mapper.AlertLogMapper;
import com.ruoyi.iot.service.IAlertLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertLogServiceImpl implements IAlertLogService {

    @Autowired
    private AlertLogMapper alertLogMapper;

    @Override
    public AlertLog selectAlertLogByAlertLogId(Long alertLogId) {
        return alertLogMapper.selectAlertLogByAlertLogId(alertLogId);
    }

    @Override
    public List<AlertLog> selectAlertLogList(AlertLog alertLog) {
        return alertLogMapper.selectAlertLogList(alertLog);
    }

    @Override
    public int insertAlertLog(AlertLog alertLog) {
        alertLog.setCreateTime(DateUtils.getNowDate());
        return alertLogMapper.insertAlertLog(alertLog);
    }

    @Override
    public int updateAlertLog(AlertLog alertLog) {
        alertLog.setUpdateTime(DateUtils.getNowDate());
        return alertLogMapper.updateAlertLog(alertLog);
    }

    @Override
    public int deleteAlertLogByAlertLogId(Long alertLogId) {
        return alertLogMapper.deleteAlertLogByAlertLogId(alertLogId);
    }

    @Override
    public int deleteAlertLogByAlertLogIds(Long[] alertLogIds) {
        return alertLogMapper.deleteAlertLogByAlertLogIds(alertLogIds);
    }
}
