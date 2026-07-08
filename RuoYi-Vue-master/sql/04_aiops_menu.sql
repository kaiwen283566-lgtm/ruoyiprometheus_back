-- AIOps 菜单和权限
DELETE FROM sys_menu WHERE menu_id BETWEEN 3000 AND 3099;

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES
(3000, 'AIOps智能运维', 0, 10, 'aiops', NULL, '', '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', NOW(), '', NULL, 'AIOps智能运维目录'),
(3001, 'Prometheus配置', 3000, 1, 'prometheus', 'monitor/prometheus/index', '', '', 1, 0, 'C', '0', '0', 'monitor:prometheus:list', 'server', 'admin', NOW(), '', NULL, 'Prometheus配置菜单'),
(3002, '日志查询', 3000, 2, 'logs', 'logs/index', '', '', 1, 0, 'C', '0', '0', 'logs:query:list', 'log', 'admin', NOW(), '', NULL, '日志查询菜单'),
(3003, '智能对话', 3000, 3, 'chat', 'ai/index', '', '', 1, 0, 'C', '0', '0', 'ai:chat:list', 'message', 'admin', NOW(), '', NULL, '智能对话菜单'),
(3011, 'Prometheus查询', 3001, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'monitor:prometheus:query', '#', 'admin', NOW(), '', NULL, ''),
(3012, 'Prometheus新增', 3001, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'monitor:prometheus:add', '#', 'admin', NOW(), '', NULL, ''),
(3013, 'Prometheus修改', 3001, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'monitor:prometheus:edit', '#', 'admin', NOW(), '', NULL, ''),
(3014, 'Prometheus删除', 3001, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'monitor:prometheus:remove', '#', 'admin', NOW(), '', NULL, ''),
(3021, '日志查询执行', 3002, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'logs:query:execute', '#', 'admin', NOW(), '', NULL, ''),
(3022, '日志查询删除', 3002, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'logs:query:remove', '#', 'admin', NOW(), '', NULL, ''),
(3031, '智能对话发送', 3003, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:send', '#', 'admin', NOW(), '', NULL, ''),
(3032, '智能对话清空', 3003, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'ai:chat:clear', '#', 'admin', NOW(), '', NULL, '');
