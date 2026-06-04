-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块', '3', '1', 'land', 'system/land/index', 1, 0, 'C', '0', '0', 'system:land:list', '#', 'admin', sysdate(), '', null, '地块菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:land:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:land:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:land:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:land:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('地块导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:land:export',       '#', 'admin', sysdate(), '', null, '');