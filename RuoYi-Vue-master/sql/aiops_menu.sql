-- 可观测性菜单 SQL
-- 父菜单：可观测性
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('可观测性', 0, 5, 'aiops', NULL, 1, 0, 'M', '0', '0', NULL, 'monitor', 'admin', sysdate(), '', NULL, '可观测性监控预警与根因分析');

-- 获取刚插入的父菜单 ID
SET @parent_id = LAST_INSERT_ID();

-- 监控大屏
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('监控大屏', @parent_id, 1, 'dashboard', 'monitor/dashboard', 1, 0, 'C', '0', '0', 'aiops:dashboard:list', 'dashboard', 'admin', sysdate(), '', NULL, '监控大屏');

-- PromQL 查询
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('PromQL查询', @parent_id, 2, 'prometheus', 'monitor/prometheus/index', 1, 0, 'C', '0', '0', 'aiops:prometheus:query', 'chart', 'admin', sysdate(), '', NULL, 'PromQL查询');

-- Targets 状态
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('Targets状态', @parent_id, 3, 'prometheus/targets', 'monitor/prometheus/targets', 1, 0, 'C', '0', '0', 'aiops:prometheus:list', 'server', 'admin', sysdate(), '', NULL, 'Targets状态');

-- 规则管理
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('规则管理', @parent_id, 4, 'prometheus/rules', 'monitor/prometheus/rules', 1, 0, 'C', '0', '0', 'aiops:prometheus:edit', 'edit', 'admin', sysdate(), '', NULL, 'Prometheus规则管理');

-- 告警列表
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('告警列表', @parent_id, 5, 'alert/alerts', 'monitor/alertmanager/alerts', 1, 0, 'C', '0', '0', 'aiops:alert:list', 'bell', 'admin', sysdate(), '', NULL, '告警列表');

-- 静默管理
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('静默管理', @parent_id, 6, 'alert/silences', 'monitor/alertmanager/silences', 1, 0, 'C', '0', '0', 'aiops:alert:manage', 'time', 'admin', sysdate(), '', NULL, '静默管理');

-- 日志检索
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('日志检索', @parent_id, 7, 'loki', 'monitor/loki/index', 1, 0, 'C', '0', '0', 'aiops:loki:query', 'log', 'admin', sysdate(), '', NULL, 'Loki日志检索');

-- RCA 分析
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('RCA分析', @parent_id, 8, 'rca/analysis', 'monitor/rca/analysis', 1, 0, 'C', '0', '0', 'aiops:rca:analyze', 'bug', 'admin', sysdate(), '', NULL, 'RCA根因分析');

-- RCA 知识库
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('RCA知识库', @parent_id, 9, 'rca/knowledge', 'monitor/rca/knowledge', 1, 0, 'C', '0', '0', 'aiops:rca:list', 'education', 'admin', sysdate(), '', NULL, 'RCA知识库');

-- AI 配置
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('AI配置', @parent_id, 10, 'ai-config', 'monitor/ai-config', 1, 0, 'C', '0', '0', 'aiops:ai:config', 'tool', 'admin', sysdate(), '', NULL, 'AI模型配置');

-- 按钮权限：告警管理（使用变量存储菜单ID）
SET @alert_menu_id = (SELECT menu_id FROM sys_menu WHERE menu_name = '告警列表' AND parent_id = @parent_id);
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('告警查询', @alert_menu_id, 1, '#', '', 1, 0, 'F', '0', '0', 'aiops:alert:query', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('告警新增', @alert_menu_id, 2, '#', '', 1, 0, 'F', '0', '0', 'aiops:alert:add', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('告警修改', @alert_menu_id, 3, '#', '', 1, 0, 'F', '0', '0', 'aiops:alert:edit', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('告警删除', @alert_menu_id, 4, '#', '', 1, 0, 'F', '0', '0', 'aiops:alert:remove', '#', 'admin', sysdate(), '', NULL, '');

-- 按钮权限：RCA 分析
SET @rca_menu_id = (SELECT menu_id FROM sys_menu WHERE menu_name = 'RCA分析' AND parent_id = @parent_id);
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('RCA查询', @rca_menu_id, 1, '#', '', 1, 0, 'F', '0', '0', 'aiops:rca:query', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('RCA新增', @rca_menu_id, 2, '#', '', 1, 0, 'F', '0', '0', 'aiops:rca:add', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('RCA修改', @rca_menu_id, 3, '#', '', 1, 0, 'F', '0', '0', 'aiops:rca:edit', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('RCA删除', @rca_menu_id, 4, '#', '', 1, 0, 'F', '0', '0', 'aiops:rca:remove', '#', 'admin', sysdate(), '', NULL, '');

-- 按钮权限：RCA 知识库
SET @knowledge_menu_id = (SELECT menu_id FROM sys_menu WHERE menu_name = 'RCA知识库' AND parent_id = @parent_id);
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('知识库查询', @knowledge_menu_id, 1, '#', '', 1, 0, 'F', '0', '0', 'aiops:rca:knowledge:query', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('知识库新增', @knowledge_menu_id, 2, '#', '', 1, 0, 'F', '0', '0', 'aiops:rca:knowledge:add', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('知识库修改', @knowledge_menu_id, 3, '#', '', 1, 0, 'F', '0', '0', 'aiops:rca:knowledge:edit', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES ('知识库删除', @knowledge_menu_id, 4, '#', '', 1, 0, 'F', '0', '0', 'aiops:rca:knowledge:remove', '#', 'admin', sysdate(), '', NULL, '');
