package com.ruoyi.iot.service;

import com.ruoyi.iot.domain.AlertLog;

import java.util.List;

public interface IAlertLogService {

    public AlertLog selectAlertLogByAlertLogId(Long alertLogId);

    public List<AlertLog> selectAlertLogList(AlertLog alertLog);

    public int insertAlertLog(AlertLog alertLog);

    public int updateAlertLog(AlertLog alertLog);

    public int deleteAlertLogByAlertLogId(Long alertLogId);

    public int deleteAlertLogByAlertLogIds(Long[] alertLogIds);
}
