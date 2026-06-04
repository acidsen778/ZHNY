package com.ruoyi.iot.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.iot.mapper.IotThingsModelTemplateMapper;
import com.ruoyi.iot.domain.IotThingsModelTemplate;
import com.ruoyi.iot.service.IIotThingsModelTemplateService;

/**
 * 物模型模板Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-06-04
 */
@Service
public class IotThingsModelTemplateServiceImpl implements IIotThingsModelTemplateService 
{
    @Autowired
    private IotThingsModelTemplateMapper iotThingsModelTemplateMapper;

    /**
     * 查询物模型模板
     * 
     * @param templateId 物模型模板主键
     * @return 物模型模板
     */
    @Override
    public IotThingsModelTemplate selectIotThingsModelTemplateByTemplateId(Long templateId)
    {
        return iotThingsModelTemplateMapper.selectIotThingsModelTemplateByTemplateId(templateId);
    }

    /**
     * 查询物模型模板列表
     * 
     * @param iotThingsModelTemplate 物模型模板
     * @return 物模型模板
     */
    @Override
    public List<IotThingsModelTemplate> selectIotThingsModelTemplateList(IotThingsModelTemplate iotThingsModelTemplate)
    {
        return iotThingsModelTemplateMapper.selectIotThingsModelTemplateList(iotThingsModelTemplate);
    }

    /**
     * 新增物模型模板
     * 
     * @param iotThingsModelTemplate 物模型模板
     * @return 结果
     */
    @Override
    public int insertIotThingsModelTemplate(IotThingsModelTemplate iotThingsModelTemplate)
    {
        iotThingsModelTemplate.setCreateTime(DateUtils.getNowDate());
        return iotThingsModelTemplateMapper.insertIotThingsModelTemplate(iotThingsModelTemplate);
    }

    /**
     * 修改物模型模板
     * 
     * @param iotThingsModelTemplate 物模型模板
     * @return 结果
     */
    @Override
    public int updateIotThingsModelTemplate(IotThingsModelTemplate iotThingsModelTemplate)
    {
        iotThingsModelTemplate.setUpdateTime(DateUtils.getNowDate());
        return iotThingsModelTemplateMapper.updateIotThingsModelTemplate(iotThingsModelTemplate);
    }

    /**
     * 批量删除物模型模板
     * 
     * @param templateIds 需要删除的物模型模板主键
     * @return 结果
     */
    @Override
    public int deleteIotThingsModelTemplateByTemplateIds(Long[] templateIds)
    {
        return iotThingsModelTemplateMapper.deleteIotThingsModelTemplateByTemplateIds(templateIds);
    }

    /**
     * 删除物模型模板信息
     * 
     * @param templateId 物模型模板主键
     * @return 结果
     */
    @Override
    public int deleteIotThingsModelTemplateByTemplateId(Long templateId)
    {
        return iotThingsModelTemplateMapper.deleteIotThingsModelTemplateByTemplateId(templateId);
    }
}
