package com.ruoyi.iot.mapper;

import com.ruoyi.iot.domain.AlertLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertLogMapper {

    public AlertLog selectAlertLogByAlertLogId(Long alertLogId);

    public List<AlertLog> selectAlertLogList(AlertLog alertLog);

    public int insertAlertLog(AlertLog alertLog);

    public int updateAlertLog(AlertLog alertLog);

    public int deleteAlertLogByAlertLogId(Long alertLogId);

    public int deleteAlertLogByAlertLogIds(Long[] alertLogIds);
}
