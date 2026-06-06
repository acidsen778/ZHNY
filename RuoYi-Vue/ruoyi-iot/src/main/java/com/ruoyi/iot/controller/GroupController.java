package com.ruoyi.iot.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.iot.domain.Group;
import com.ruoyi.iot.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/iot/group")
public class GroupController extends BaseController {

    @Autowired
    private IGroupService groupService;

    @PreAuthorize("@ss.hasPermi('iot:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(Group group) {
        startPage();
        List<Group> list = groupService.selectGroupList(group);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('iot:group:query')")
    @GetMapping(value = "/{groupId}")
    public AjaxResult getInfo(@PathVariable("groupId") Long groupId) {
        return success(groupService.selectGroupByGroupId(groupId));
    }

    @PreAuthorize("@ss.hasPermi('iot:group:add')")
    @Log(title = "iot.group", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Group group) {
        return toAjax(groupService.insertGroup(group));
    }

    @PreAuthorize("@ss.hasPermi('iot:group:edit')")
    @Log(title = "iot.group", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Group group) {
        return toAjax(groupService.updateGroup(group));
    }

    @PreAuthorize("@ss.hasPermi('iot:group:remove')")
    @Log(title = "iot.group", businessType = BusinessType.DELETE)
    @DeleteMapping("/{groupIds}")
    public AjaxResult remove(@PathVariable Long[] groupIds) {
        return toAjax(groupService.deleteGroupByGroupIds(groupIds));
    }

    @Log(title = "iot.group", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('iot:group:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, Group group) {
        List<Group> list = groupService.selectGroupList(group);
        ExcelUtil<Group> util = new ExcelUtil<>(Group.class);
        util.exportExcel(response, list, "group");
    }
}
