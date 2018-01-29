-- INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
-- 单据系统
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES ( 3000, now(), now(), '0', '1', 'BILL', '单据系统', 'BILL', 'app.bill', null, '\0');

-- 单据系统二级菜单-BILL 3000
-- BILL 3000<总部计划中心-BILL001 3001>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3001, now(), now(),  '0', '2', 'BILL001', '总部计划中心', 'BILL,BILL001', 'app.bill.plan', 3000, '\0');
-- BILL 3000<进货-BILL002 3002>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3002, now(), now(),  '0', '2', 'BILL002', '进货', 'BILL,BILL002', 'app.bill.purchase', 3000 , '\0');
-- BILL 3000<配送-BILL003 3003>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3003, now(), now(),  '0', '2', 'BILL003', '配送', 'BILL,BILL003', 'app.bill.delivery', 3000 , '\0');
-- BILL 3000<调剂-BILL004 3004>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3004, now(), now(),  '0', '2', 'BILL004', '调剂', 'BILL,BILL004', 'app.bill.adjust', 3000 , '\0');
-- BILL 3000<退库-BILL005 3005>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3005, now(), now(),  '0', '2', 'BILL005', '退库', 'BILL,BILL005', 'app.bill.restock', 3000 , '\0');
-- BILL 3000<退货-BILL006 3006>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3006, now(), now(),  '0', '2', 'BILL006', '退货', 'BILL,BILL006', 'app.bill.returned', 3000 , '\0');
-- BILL 3000<其他调拨-BILL007 3007>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3007, now(), now(),  '0', '2', 'BILL007', '其他调拨', 'BILL,BILL007', 'app.bill.otherTransfer', 3000 , '\0');
-- BILL 3000<误差管理-BILL008 3008>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3008, now(), now(),  '0', '2', 'BILL008', '误差管理', 'BILL,BILL008', 'app.bill.mistake', 3000 , '\0');
-- BILL 3000<运单跟踪-BILL009 3009>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3009, now(), now(),  '0', '2', 'BILL009', '运单跟踪', 'BILL,BILL009', 'app.bill.trace', 3000 , '\0');
-- BILL 3000<库存查询-BILL010 3010>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3010, now(), now(),  '0', '2', 'BILL010', '库存查询', 'BILL,BILL010', 'app.bill.stock', 3000 , '\0');
-- BILL 3000<其他出入库-BILL011 3011>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3011, now(), now(),  '0', '2', 'BILL011', '其他出入库', 'BILL,BILL011', 'app.bill.otherStorage', 3000 , '\0');
-- BILL 3000<日结-BILL012 3012>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3012, now(), now(),  '0', '2', 'BILL012', '日结', 'BILL,BILL012', 'app.bill.dayEnd', 3000 , '\0');

-- 三级菜单
-- 总部计划中心-BILL001 3001
-- BILL001 3001<添加总部计划-BILL001001 3020>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3020, now(), now(), '0', '3', 'BILL001001', '添加总部计划', 'BILL,BILL001,BILL001001', 'app.bill.plan.add', 3001, '\0');
-- BILL001 3001<查询总部计划-BILL001002 3021>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3021, now(), now(), '0', '3', 'BILL001002', '查询总部计划', 'BILL,BILL001,BILL001002', 'app.bill.plan.list', 3001, '\0');

-- 进货-BILL002 3002
-- BILL002 3002<进货录单-BILL002001 3022>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3022, now(), now(), '0', '3', 'BILL002001', '进货录单', 'BILL,BILL002,BILL002001', 'app.bill.procurement.add', 3002, '\0');
-- BILL002 3002<查询进货入库单-BILL002002 3023>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3023, now(), now(), '0', '3', 'BILL002002', '查询进货入库单', 'BILL,BILL002,BILL002002', 'app.bill.procurement.list', 3002, '\0');

-- 配送-BILL003 3003
-- BILL003 3003<查询站点配送计划-BILL003001 3024>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3024, now(), now(), '0', '3', 'BILL003001', '查询站点配送计划', 'BILL,BILL003,BILL003001', 'app.bill.delivery.planList', 3003, '\0');
-- BILL003 3003<站点配送拣货-BILL003002 3025自主>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3025, now(), now(), '0', '3', 'BILL003002', '站点配送拣货', 'BILL,BILL003,BILL003002', 'app.bill.delivery.pick', 3003, '\0');
-- BILL003 3003<查询配送出库单-BILL003003 3026>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3026, now(), now(), '0', '3', 'BILL003004', '查询配送出库单', 'BILL,BILL003,BILL003003', 'app.bill.delivery.outStorageList', 3003, '\0');
-- BILL003 3003<配送入库单查询-BILL003004 3027>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3027, now(), now(), '0', '3', 'BILL003005', '配送入库单查询', 'BILL,BILL003,BILL003004', 'app.bill.delivery.inStorageList', 3003, '\0');
-- BILL003 3003<配送调拨单查询-BILL003005 3028>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3028, now(), now(), '0', '3', 'BILL003006', '配送调拨单查询', 'BILL,BILL003,BILL003005', 'app.bill.delivery.transferList', 3003, '\0');

-- 调剂-BILL004 3004
-- BILL004 3004<查询站点调剂计划-BILL004001 3029>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3029, now(), now(), '0', '3', 'BILL004001', '查询站点调剂计划', 'BILL,BILL004,BILL004001', 'app.bill.adjust.planList', 3004, '\0');
-- BILL004 3004<站点调剂拣货-BILL004002 3030自主>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3030, now(), now(), '0', '3', 'BILL004002', '站点调剂拣货', 'BILL,BILL004,BILL004002', 'app.bill.adjust.pick', 3004, '\0');
-- BILL004 3004<查询调剂出库单-BILL004003 3031>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3031, now(), now(), '0', '3', 'BILL004003', '查询调剂出库单', 'BILL,BILL004,BILL004003', 'app.bill.adjust.outStorageList', 3004, '\0');
-- BILL004 3004<调剂入库单查询-BILL004004 3032>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3032, now(), now(), '0', '3', 'BILL004004', '调剂入库单查询', 'BILL,BILL004,BILL004004', 'app.bill.adjust.inStorageList', 3004, '\0');
-- BILL004 3004<调剂调拨单查询-BILL004005 3033>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3033, now(), now(), '0', '3', 'BILL004005', '调剂调拨单查询', 'BILL,BILL004,BILL004005', 'app.bill.adjust.transferList', 3004, '\0');

-- 退库-BILL005 3005
-- BILL005 3005<查询退站点库计划-BILL005001 3034>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3034, now(), now(), '0', '3', 'BILL005001', '查询退站点库计划', 'BILL,BILL005,BILL005001', 'app.bill.restock.planList', 3005, '\0');
-- BILL005 3005<站点退库拣货-BILL005002 3035>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3035, now(), now(), '0', '3', 'BILL005002', '站点退库拣货', 'BILL,BILL005,BILL005002', 'app.bill.restock.pick', 3005, '\0');
-- BILL005 3005<查询退库出库单-BILL005003 3036>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3036, now(), now(), '0', '3', 'BILL005003', '查询退库出库单', 'BILL,BILL005,BILL005003', 'app.bill.restock.outStorageList', 3005, '\0');
-- BILL005 3005<退库入库单查询-BILL005004 3037>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3037, now(), now(), '0', '3', 'BILL005004', '退库入库单查询', 'BILL,BILL005,BILL005004', 'app.bill.restock.inStorageList', 3005, '\0');
-- BILL005 3005<退库调拨单查询-BILL005005 3038>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3038, now(), now(), '0', '3', 'BILL005005', '退库调拨单查询', 'BILL,BILL005,BILL005005', 'app.bill.restock.transferList', 3005, '\0');

-- 退货-BILL006 3006
-- BILL006 3006<查询站点退货计划-BILL006001 3039>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3039, now(), now(), '0', '3', 'BILL006001', '查询站点退货计划', 'BILL,BILL006,BILL006001', 'app.bill.returned.planList', 3006, '\0');
-- BILL006 3006<站点退货拣货-BILL006002 3040>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3040, now(), now(), '0', '3', 'BILL006002', '站点退货拣货', 'BILL,BILL006,BILL006002', 'app.bill.returned.pick', 3006, '\0');
-- BILL006 3006<查询退货出库单-BILL006003 3041>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3041, now(), now(), '0', '3', 'BILL006003', '查询退货出库单', 'BILL,BILL006,BILL006003', 'app.bill.returned.outStorageList', 3006, '\0');

-- 其他调拨-BILL007 3007
-- BILL007 3007<添加其他调拨单-BILL007001 3042>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3042, now(), now(), '0', '3', 'BILL007001', '添加其他调拨单', 'BILL,BILL007,BILL007001', 'app.bill.otherTransfer.add', 3007, '\0');
-- BILL007 3007<查询其他调拨单-BILL007002 3043>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3043, now(), now(), '0', '3', 'BILL007002', '查询其他调拨单', 'BILL,BILL007,BILL007002', 'app.bill.otherTransfer.list', 3007, '\0');

-- 误差管理-BILL008 3008
-- BILL008 3008<添加报溢单-BILL008001 3044>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3044, now(), now(), '0', '3', 'BILL008001', '添加报溢单', 'BILL,BILL008,BILL008001', 'app.bill.mistake.overflowAdd', 3008, '\0');
-- BILL008 3008<查询报溢单-BILL008002 3045>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3045, now(), now(), '0', '3', 'BILL008002', '查询报溢单', 'BILL,BILL008,BILL008002', 'app.bill.mistake.overflowList', 3008, '\0');
-- BILL008 3008<添加报损单-BILL008003 3046>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3046, now(), now(), '0', '3', 'BILL008003', '添加报损单', 'BILL,BILL008,BILL008003', 'app.bill.mistake.lossAdd', 3008, '\0');
-- BILL008 3008<查询报损单-BILL008004 3047>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3047, now(), now(), '0', '3', 'BILL008004', '查询报损单', 'BILL,BILL008,BILL008004', 'app.bill.mistake.lossList', 3008, '\0');
-- BILL008 3008<添加日常误差单-BILL008005 3048>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3048, now(), now(), '0', '3', 'BILL008005', '添加日常误差单', 'BILL,BILL008,BILL008005', 'app.bill.mistake.adyMistakeAdd', 3008, '\0');
-- BILL008 3008<查询日常误差单-BILL008006 3049>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3049, now(), now(), '0', '3', 'BILL008006', '查询日常误差单', 'BILL,BILL008,BILL008006', 'app.bill.mistake.adyMistakeList', 3008, '\0');
-- BILL008 3008<查询流转误差单-BILL008007 3050>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3050, now(), now(), '0', '3', 'BILL008007', '查询流转误差单', 'BILL,BILL008,BILL008008', 'app.bill.mistake.list', 3008, '\0');

-- 运单跟踪-BILL009 3009
-- BILL009 3009<添加运单-BILL009001 3051>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3051, now(), now(), '0', '3', 'BILL009001', '添加运单', 'BILL,BILL009,BILL009001', 'app.bill.trace.add', 3009, '\0');
-- BILL009 3009<查询运单-BILL009002 3052>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3052, now(), now(), '0', '3', 'BILL009002', '查询运单', 'BILL,BILL009,BILL009002', 'app.bill.trace.list', 3009, '\0');

-- 库存查询-BILL010 3010
-- BILL010 3010<库存查询-BILL010001 3053>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3053, now(), now(), '0', '3', 'BILL010001', '库存查询', 'BILL,BILL010,BILL010001', 'app.bill.stock.list', 3010, '\0');
-- BILL010 3010<库存流水查询-BILL010002 3054>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3054, now(), now(), '0', '3', 'BILL010002', '库存流水查询', 'BILL,BILL010,BILL010002', 'app.bill.stock.accountList', 3010, '\0');

-- 其他出入库-BILL011 3011
-- BILL011 3011<其他出库拣货-BILL011001 3055自主>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3055, now(), now(), '0', '3', 'BILL011001', '其他出库拣货', 'BILL,BILL011,BILL011001', 'app.bill.otherOutStorage.pick', 3011, '\0');
-- BILL011 3011<查询其他出库单-BILL011002 3056>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3056, now(), now(), '0', '3', 'BILL011002', '查询其他出库单', 'BILL,BILL011,BILL011002', 'app.bill.otherOutStorage.list', 3011, '\0');
-- BILL011 3011<查询其他入库单-BILL011003 3057>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3057, now(), now(), '0', '3', 'BILL011003', '查询其他入库单', 'BILL,BILL011,BILL011003', 'app.bill.otherOutStorage.otherList', 3011, '\0');

-- 日结-BILL012 3012
-- BILL012 3012<查询日结-BILL012001 3058>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3058, now(), now(), '0', '3', 'BILL012001', '查询日结', 'BILL,BILL012,BILL012001', 'app.bill.dayEnd.list', 3012, '\0');

-- **************************************************************************************************需要补充/修改部分*******************************************************************************************

-- 四级（操作）
-- 总部计划中心BILL001 3001【添加总部计划-BILL001001 3020】
-- BILL001001 3020<总部计划暂存-BILL001001001 3100>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3100, now(), now(), '0', '4', 'BILL001001001', '总部计划暂存', 'BILL,BILL001,BILL001001,BILL001001001', '/coffeeBill/api/bill/plan/save', 3020, '\0');
-- BILL001001 3020<总部计划提交-BILL001001002 3101>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3101, now(), now(), '0', '4', 'BILL001001002', '总部计划提交', 'BILL,BILL001,BILL001001,BILL001001002', '/coffeeBill/api/bill/plan/submit', 3020, '\0');
-- 总部计划中心BILL001 3001【查询总部计划-BILL001002 3021】
-- BILL001002 3021<条件筛选查询-BILL001002001 3102>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3102, now(), now(), '0', '4', 'BILL001002001', '总部计划条件筛选查询', 'BILL,BILL001,BILL001002,BILL001002001', '/coffeeBill/api/bill/plan/hq/findByConditions', 3021, '\0');
-- BILL001002 3021<查看-BILL001002002 3103>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3103, now(), now(), '0', '4', 'BILL001002002', '总部计划详情查询', 'BILL,BILL001,BILL001002,BILL001002002', '/coffeeBill/api/bill/plan/hq/findByBillCode', 3021, '\0');
-- BILL001002 3021<打开-BILL001002003 3104>
-- INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
--   VALUES (3104, now(), now(), '0', '4', 'BILL001002003', '打开总部计划', 'BILL,BILL001,BILL001002,BILL001002003', '/coffeeBill/api/bill/plan/open', 3021, '\0');
-- BILL001002 3021<审核不通过-BILL001002004 3105>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3105, now(), now(), '0', '4', 'BILL001002004', '总部计划审核不通过', 'BILL,BILL001,BILL001002,BILL001002004', '/coffeeBill/api/bill/plan/auditFailure', 3021, '\0');
-- BILL001002 3021<审核通过-BILL001002005 3106>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3106, now(), now(), '0', '4', 'BILL001002005', '总部计划审核通过', 'BILL,BILL001,BILL001002,BILL001002005', '/coffeeBill/api/bill/plan/auditSuccess', 3021, '\0');
-- BILL001002 3021<删除-BILL001002006 3107>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3107, now(), now(), '0', '4', 'BILL001002006', '删除总部计划', 'BILL,BILL001,BILL001002,BILL001002006', '/coffeeBill/api/bill/plan/delete', 3021, '\0');
-- BILL001002 3021<导出-BILL001002007 3108>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3108, now(), now(), '0', '4', 'BILL001002007', '导出总部计划', 'BILL,BILL001,BILL001002,BILL001002007', '/coffeeBill/api/bill/plan/downLoad', 3021, '\0');

-- 进货BILL002 3002【进货录单-BILL002001 3022】
-- BILL002001 3022<暂存-BILL002001001 3110>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3109, now(), now(), '0', '4', 'BILL002001001', '进货单暂存', 'BILL,BILL002,BILL002001,BILL002001001', '/coffeeBill/api/bill/purchase/save', 3022, '\0');
-- BILL002001 3022<提交-BILL002001002 3111>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3110, now(), now(), '0', '4', 'BILL002001002', '进货单提交', 'BILL,BILL002,BILL002001,BILL002001002', '/coffeeBill/api/bill/purchase/submit', 3022, '\0');

-- 进货BILL002 3002【查询进货入库单-BILL002002 3023】
-- BILL002002 3023<条件筛选查询-BILL002002001 3112>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3111, now(), now(), '0', '4', 'BILL002002001', '进货单条件筛选查询', 'BILL,BILL002,BILL002002,BILL002002001', '/coffeeBill/api/bill/purchase/findPurchaseByConditions', 2023, '\0');
-- BILL002002 3023<导出-BILL002002002 3113>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3112, now(), now(), '0', '4', 'BILL002002002', '进货单导出', 'BILL,BILL002,BILL002002,BILL002002002', '/coffeeBill/api/bill/purchase/downLoad', 2023, '\0');
-- BILL002002 3023<查看-BILL002002003 3114>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3113, now(), now(), '0', '4', 'BILL002002003', '查看进货单详情', 'BILL,BILL002,BILL002002,BILL002002003', '/coffeeBill/api/bill/purchase/findPurchaseBill', 2023, '\0');
-- BILL002002 3023<打开-BILL002002004 3115>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3114, now(), now(), '0', '4', 'BILL002002004', '打开进货单', 'BILL,BILL002,BILL002002,BILL002002004', '/coffeeBill/api/bill/purchase/open', 2023, '\0');
-- BILL002002 3023<审核不通过-BILL002002005 3116>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3115, now(), now(), '0', '4', 'BILL002002005', '进货单审核不通过', 'BILL,BILL002,BILL002002,BILL002002005', '/coffeeBill/api/bill/purchase/auditFailure', 2023, '\0');
-- BILL002002 3023<通过-BILL002002006 3117>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3116, now(), now(), '0', '4', 'BILL002002006', '进货单审核通过', 'BILL,BILL002,BILL002002,BILL002002006', '/coffeeBill/api/bill/purchase/auditSuccess', 2023, '\0');


--  配送-BILL003 3003【查询站点配送计划-BILL003001 3024】
-- BILL003001 3024<条件查询-BILL003001001 3118>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3117, now(), now(), '0', '4', 'BILL003001001', '条件筛选查询站点配送计划', 'BILL,BILL003,BILL003001,BILL003001001', '/coffeeBill/api/bill/delivery/findPlanByConditions', 3024, '\0');
-- BILL003001 3024<拣货-BILL003001002 3119>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3118, now(), now(), '0', '4', 'BILL003001002', '计划界面_查看配送计划单_根据单号查询', 'BILL,BILL003,BILL003001,BILL003001002', '/coffeeBill/api/bill/delivery/findPlanByBillCode', 3024, '\0');
-- BILL003001 3024<根据站点计划号查询-BILL003001003 3120>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3119, now(), now(), '0', '4', 'BILL003001003', '计划界面_查看配送出库单_列表中拣货按钮消失后的查看按钮', 'BILL,BILL003,BILL003001,BILL003001003', '/coffeeBill/api/bill/delivery/findBySourceCode', 3024, '\0');
-- BILL003001 3024<导出-BILL003001004 3121>
-- INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
--   VALUES (3120, now(), now(), '0', '4', 'BILL003001004', '导出', 'BILL,BILL003,BILL003001,BILL003001004', '补充内容4', 3024, '\0');

-- BILL003001 3024<拣货保存>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3121, now(), now(), '0', '4', 'BILL003001004', '计划配送拣货保存', 'BILL,BILL003,BILL003001,BILL003001004', '/coffeeBill/api/bill/delivery/save', 3024, '\0');
-- BILL003001 3024<拣货提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3122, now(), now(), '0', '4', 'BILL003001005', '计划配送拣货提交', 'BILL,BILL003,BILL003001,BILL003001005', '/coffeeBill/api/bill/delivery/submit', 3024, '\0');



-- 配送-BILL003 3003【站点配送拣货-BILL003002 3025自主】
-- BILL003002 3025<自主拣货保存>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3123, now(), now(), '0', '4', 'BILL003002001', '站点自主拣货保存', 'BILL,BILL003,BILL003002,BILL003002001', '/coffeeBill/api/bill/delivery/saveBySelf', 3025, '\0');
-- BILL003002 3025<自主拣货提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3124, now(), now(), '0', '4', 'BILL003002002', '站点自主拣货提交', 'BILL,BILL003,BILL003002,BILL003002002', '/coffeeBill/api/bill/delivery/submitBySelf', 3025, '\0');


-- 配送-BILL003 3003【查询配送出库单-BILL003003 3026】
-- BILL003003 3026<条件查询-BILL003003001 3127>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3125, now(), now(), '0', '4', 'BILL003003001', '查询出库单_配送出库单条件筛选查询', 'BILL,BILL003,BILL003003,BILL003003001', '/coffeeBill/api/bill/delivery/findOutStorageByConditions', 3026, '\0');
-- BILL003003 3026<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3126, now(), now(), '0', '4', 'BILL003003002', '查询出库单_查看', 'BILL,BILL003,BILL003003,BILL003003002', '/coffeeBill/api/bill/delivery/findOutStorageByBillCode', 3026, '\0');
-- BILL003003 3026<修改保存>
-- INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
--   VALUES (3127, now(), now(), '0', '4', 'BILL003003005', '查询出库单_修改保存', 'BILL,BILL003,BILL003003,BILL003003005', '/coffeeBill/api/bill/delivery/save', 3026, '\0');
-- BILL003003 3026<修改提交>
-- INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
--   VALUES (3128, now(), now(), '0', '4', 'BILL003003006', '查询出库单_修改提交', 'BILL,BILL003,BILL003003,BILL003003006', '/coffeeBill/api/bill/delivery/submit', 3026, '\0');
-- BILL003003 3026<审核通过>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3129, now(), now(), '0', '4', 'BILL003003007', '查询出库单_审核通过', 'BILL,BILL003,BILL003003,BILL003003007', '/coffeeBill/api/bill/delivery/auditSuccess', 3026, '\0');
-- BILL003003 3026<审核不通过>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3130, now(), now(), '0', '4', 'BILL003003008', '查询出库单_审核不通过', 'BILL,BILL003,BILL003003,BILL003003008', '/coffeeBill/api/bill/delivery/auditFailure', 3026, '\0');
-- BILL003003 3026<审核打开>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3131, now(), now(), '0', '4', 'BILL003003009', '查询出库单_审核打开', 'BILL,BILL003,BILL003003,BILL003003009', '/coffeeBill/api/bill/delivery/open', 3026, '\0');

-- 配送-BILL003 3003【配送入库单查询-BILL003004 3027】
-- BILL003005 3027<条件查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3132, now(), now(), '0', '4', 'BILL003004001', '查询入库单_多条件查询', 'BILL,BILL003,BILL003004,BILL003004001', '/coffeeBill/api/bill/delivery/findInStorageByConditions', 3027, '\0');
-- BILL005004 3027<调拨>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3133, now(), now(), '0', '4', 'BILL003004002', '查询入库单_调拨', 'BILL,BILL003,BILL003004,BILL003004002', '/coffeeBill/api/bill/delivery/allotSave', 3027, '\0');
-- BILL005004 3027<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3134, now(), now(), '0', '4', 'BILL003004003', '查询入库单_查看', 'BILL,BILL003,BILL003004,BILL003004003', '/coffeeBill/api/bill/delivery/findInStorageByBillCode', 3027, '\0');


-- 配送-BILL003 3003【配送调拨单查询-BILL003005 3028】
-- BILL003006 3028<条件查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3135, now(), now(), '0', '4', 'BILL003005001', '查询调 拨单_多条件查询', 'BILL,BILL003,BILL003005,BILL003005001', '/coffeeBill/api/bill/delivery/findAllotByConditions', 3028, '\0');
-- BILL005005 3028<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3136, now(), now(), '0', '4', 'BILL003005002', '查询调拨单_查看', 'BILL,BILL003,BILL003005,BILL003005002', '/coffeeBill/api/bill/delivery/findAllotByBillCode', 3028, '\0');
-- BILL003006 3028<导出>


-- 调剂-BILL004 3004【查询站点调剂计划-BILL004001 3029】
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3137, now(), now(), '0', '4', 'BILL004001001', '调剂计划条件查询', 'BILL,BILL004,BILL004001,BILL004001001', '/coffeeBill/api/bill/adjust/findPlanByConditions', 3029, '\0');
-- BILL004001 3029<根据站点计划好查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3138, now(), now(), '0', '4', 'BILL004001002', '查看单个调剂计划', 'BILL,BILL004,BILL004001,BILL004001002', '/coffeeBill/api/bill/adjust/findPlanByBillCode', 3029, '\0');
 -- BILL004001 3029<拣货保存>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3139, now(), now(), '0', '4', 'BILL004001003', '保存拣货', 'BILL,BILL004,BILL004001,BILL004001003', '/coffeeBill/api/bill/adjust/save', 3029, '\0');
 -- BILL004001 3029<拣货保存>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3140, now(), now(), '0', '4', 'BILL004001004', '提交拣货', 'BILL,BILL004,BILL004001,BILL004001004', '/coffeeBill/api/bill/adjust/submit', 3029, '\0');
-- BILL004001 3029<导出>
-- 补充内容 3141
-- 调剂-BILL004 3004【站点调剂拣货-BILL004002 3030自主】
-- BILL004002 3030<保存>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3142, now(), now(), '0', '4', 'BILL004002001', '自主拣货保存', 'BILL,BILL004,BILL004002,BILL004002001', '/coffeeBill/api/bill/adjust/saveBySelf', 3030, '\0');
-- BILL004002 3030<提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3143, now(), now(), '0', '4', 'BILL004002002', '自主拣货提交', 'BILL,BILL004,BILL004002,BILL004002002', '/coffeeBill/api/bill/adjust/submitBySelf', 3030, '\0');
-- 调剂-BILL004 3004【查询调剂出库单-BILL004003 3031】
-- BILL004003 3031<件筛选查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3144, now(), now(), '0', '4', 'BILL004003001', '调剂出库单查询', 'BILL,BILL004,BILL004003,BILL004003001', '/coffeeBill/api/bill/adjust/findOutStorageByConditions', 3031, '\0');
-- BILL004003 3031<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3145, now(), now(), '0', '4', 'BILL004003002', '调剂出库单单个查看', 'BILL,BILL004,BILL004003,BILL004003002', '/coffeeBill/api/bill/adjust/findOutStorageByBillCode', 3031, '\0');
-- BILL004003 3031<保存>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3146, now(), now(), '0', '4', 'BILL004003003', '保存调剂出库单', 'BILL,BILL004,BILL004003,BILL004003003', '/coffeeBill/coffeeBill/api/bill/adjust/save', 3031, '\0');
-- BILL004003 3031<提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3147, now(), now(), '0', '4', 'BILL004003004', '提交调剂出库单', 'BILL,BILL004,BILL004003,BILL004003004', '/coffeeBill/coffeeBill/api/bill/adjust/submit', 3031, '\0');
-- BILL004003 3031<审核通过>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3148, now(), now(), '0', '4', 'BILL004003005', '审核通过', 'BILL,BILL004,BILL004003,BILL004003005', '/coffeeBill/api/bill/adjust/auditSuccess', 3031, '\0');
-- BILL004003 3031<审核不通过>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3149, now(), now(), '0', '4', 'BILL004003006', '审核不通过', 'BILL,BILL004,BILL004003,BILL004003006', '/coffeeBill/api/bill/adjust/auditFailure', 3031, '\0');
-- BILL004003 3031<OPEN>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3150, now(), now(), '0', '4', 'BILL004003007', '调剂出库单open', 'BILL,BILL004,BILL004003,BILL004003007', '/coffeeBill/api/bill/adjust/open', 3031, '\0');
-- 调剂-BILL004 3004【调剂入库单查询-BILL004004 3032】
-- BILL004004 3032<件筛选查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3151, now(), now(), '0', '4', 'BILL004004001', '查询入库单', 'BILL,BILL004,BILL004004,BILL004004001', '/coffeeBill/api/bill/adjust/findInStorageByConditions', 3032, '\0');
-- BILL004004 3032<调拨>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3152, now(), now(), '0', '4', 'BILL004004002', '调拨', 'BILL,BILL004,BILL004004,BILL004004002', '/coffeeBill/api/bill/allot/save', 3032, '\0');
-- BILL004004 3032<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3153, now(), now(), '0', '4', 'BILL004004003', '查看入库单', 'BILL,BILL004,BILL004004,BILL004004003', '/coffeeBill/api/bill/adjust/findInStorageByBillCode', 3032, '\0');
-- 调剂-BILL004 3004【调剂调拨单查询-BILL004005 3033】
-- BILL004005 3033<件筛选查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3154, now(), now(), '0', '4', 'BILL004005001', '查询调拨单', 'BILL,BILL004,BILL004005,BILL004005001', '/coffeeBill/api/bill/adjust/findAllotByConditions', 3033, '\0');
-- BILL004005 3033<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3155, now(), now(), '0', '4', 'BILL004005002', '查看调拨单', 'BILL,BILL004,BILL004005,BILL004005002', '/coffeeBill/api/bill/adjust/findAllotByBillCode', 3033, '\0');


-- 退库-BILL005 3005【查询退站点库计划-BILL005001 3034】
-- BILL005001 3034<条件筛选查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3156, now(), now(), '0', '4', 'BILL005001001', '计划界面_多条件查询', 'BILL,BILL005,BILL005001,BILL005001001', '/coffeeBill/api/bill/restock/findPlanByConditions', 3034, '\0');
-- BILL005001 3034<站点计划号>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3157, now(), now(), '0', '4', 'BILL005001002', '计划界面_查看退库计划单_根据单号查询', 'BILL,BILL005,BILL005001,BILL005001002', '/coffeeBill/api/bill/restock/findPlanByBillCode', 3034, '\0');
-- BILL005001 3034<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3158, now(), now(), '0', '4', 'BILL005001003', '计划界面_查看退库出库单_列表中拣货按钮消失后的查看按钮', 'BILL,BILL005,BILL005001,BILL005001003', '/coffeeBill/api/bill/restock/findBySourceCode', 3034, '\0');

-- BILL005001 3034<拣货保存>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3159, now(), now(), '0', '4', 'BILL005001004', '计划退库拣货保存', 'BILL,BILL005,BILL005001,BILL005001004', '/coffeeBill/api/bill/restock/save', 3034, '\0');
-- BILL005001 3034<拣货提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3160, now(), now(), '0', '4', 'BILL005001005', '计划退库拣货提交', 'BILL,BILL005,BILL005001,BILL005001005', '/coffeeBill/api/bill/restock/submit', 3034, '\0');
-- 退库-BILL005 3005【站点退库拣货-BILL005002 3035】
-- BILL005002 3035<自主拣货保存>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3161, now(), now(), '0', '4', 'BILL005002001', '站点自主拣货保存', 'BILL,BILL005,BILL005002,BILL005002001', '/coffeeBill/api/bill/restock/saveBySelf', 3035, '\0');
-- BILL005002 3035<自主拣货提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3162, now(), now(), '0', '4', 'BILL005002002', '站点自主拣货提交', 'BILL,BILL005,BILL005002,BILL005002002', '/coffeeBill/api/bill/restock/submitBySelf', 3035, '\0');
-- 退库-BILL005 3005【查询退库出库单-BILL005003 3036】
-- BILL005003 3036<条件筛选查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3163, now(), now(), '0', '4', 'BILL005003001', '查询出库单_退库出库单条件筛选查询', 'BILL,BILL005,BILL005003,BILL005003001', '/coffeeBill/api/bill/restock/findOutStorageByConditions', 3036, '\0');
-- BILL005003 3036<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3164, now(), now(), '0', '4', 'BILL005003002', '查询出库单_查看', 'BILL,BILL005,BILL005003,BILL005003002', '/coffeeBill/api/bill/restock/findOutStorageByBillCode', 3036, '\0');
-- BILL005003 3036<修改保存>
-- INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
--   VALUES (3165, now(), now(), '0', '4', 'BILL005003005', '查询出库单_修改保存', 'BILL,BILL005,BILL005003,BILL005003005', '/coffeeBill/api/bill/restock/save', 3036, '\0');
-- BILL005003 3036<修改提交>
-- INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
--   VALUES (3166, now(), now(), '0', '4', 'BILL005003006', '查询出库单_修改提交', 'BILL,BILL005,BILL005003,BILL005003006', '/coffeeBill/api/bill/restock/submit', 3036, '\0');
-- BILL005003 3036<审核通过>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3167, now(), now(), '0', '4', 'BILL005003007', '查询出库单_审核通过', 'BILL,BILL005,BILL005003,BILL005003007', '/coffeeBill/api/bill/restock/auditSuccess', 3036, '\0');
-- BILL005003 3036<审核不通过>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3168, now(), now(), '0', '4', 'BILL005003008', '查询出库单_审核不通过', 'BILL,BILL005,BILL005003,BILL005003008', '/coffeeBill/api/bill/restock/auditFailure', 3036, '\0');
-- BILL005003 3036<审核打开>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3169, now(), now(), '0', '4', 'BILL005003009', '查询出库单_审核打开', 'BILL,BILL005,BILL005003,BILL005003009', '/coffeeBill/api/bill/restock/open', 3036, '\0');
-- 退库-BILL005 3005【退库入库单查询-BILL005004 3037】
-- BILL005004 3037<条件查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3170, now(), now(), '0', '4', 'BILL005004001', '查询入库单_多条件查询', 'BILL,BILL005,BILL005004,BILL005004001', '/coffeeBill/api/bill/restock/findInStorageByConditions', 3037, '\0');
-- BILL005004 3037<调拨>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3171, now(), now(), '0', '4', 'BILL005004002', '查询入库单_调拨', 'BILL,BILL005,BILL005004,BILL005004002', '/coffeeBill/api/bill/restock/allotSave', 3037, '\0');
-- BILL005004 3037<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3172, now(), now(), '0', '4', 'BILL005004003', '查询入库单_查看', 'BILL,BILL005,BILL005004,BILL005004003', '/coffeeBill/api/bill/restock/findInStorageByBillCode', 3037, '\0');
-- 退库-BILL005 3005【退库调拨单查询-BILL005005 3038】
-- BILL005005 3038<多条件查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3173, now(), now(), '0', '4', 'BILL005005001', '查询调拨单_多条件查询', 'BILL,BILL005,BILL005005,BILL005005001', '/coffeeBill/api/bill/restock/findAllotByConditions', 3038, '\0');
-- BILL005005 3038<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3174, now(), now(), '0', '4', 'BILL005005002', '查询调拨单_查看', 'BILL,BILL005,BILL005005,BILL005005002', '/coffeeBill/api/bill/restock/findAllotByBillCode', 3038, '\0');


-- 退货-BILL006 3006【查询站点退货计划-BILL006001 3039】
-- BILL006001 3039<条件筛选查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3175, now(), now(), '0', '4', 'BILL006001001', '计划界面_多条件查询', 'BILL,BILL006,BILL006001,BILL006001001', '/coffeeBill/api/bill/returned/findPlanByConditions', 3039, '\0');
-- BILL006001 3039<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3176, now(), now(), '0', '4', 'BILL006001002', '计划界面_查看退货计划单_根据单号查询', 'BILL,BILL006,BILL006001,BILL006001002', '/coffeeBill/api/bill/returned/findPlanByBillCode', 3039, '\0');
-- BILL006001 3039<根据站点计划号查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3177, now(), now(), '0', '4', 'BILL006001003', '计划界面_查看退货出库单_列表中拣货按钮消失后的查看按钮', 'BILL,BILL006,BILL006001,BILL006001003', '/coffeeBill/api/bill/returned/findBySourceCode', 3039, '\0');

-- BILL006001 3039<拣货保存>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3178, now(), now(), '0', '4', 'BILL006001004', '计划退货拣货保存', 'BILL,BILL006,BILL006001,BILL006001004', '/coffeeBill/api/bill/returned/save', 3039, '\0');
-- BILL006001 3039<拣货提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3179, now(), now(), '0', '4', 'BILL006001005', '计划退货拣货提交', 'BILL,BILL006,BILL006001,BILL006001005', '/coffeeBill/api/bill/returned/submit', 3039, '\0');

-- 退货-BILL006 3006【站点退货拣货-BILL006002 3040】
-- BILL006002 3040<保存>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3180, now(), now(), '0', '4', 'BILL006002001', '退货拣货保存', 'BILL,BILL006,BILL006002,BILL006002001', '/coffeeBill/api/bill/returned/selfSave', 3040, '\0');
-- BILL006002 3040<提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3181, now(), now(), '0', '4', 'BILL006002002', '退货拣货保存', 'BILL,BILL006,BILL006002,BILL006002002', '/coffeeBill/api/bill/returned/selfSubmit', 3040, '\0');

-- 退货-BILL006 3006【查询退货出库单-BILL006003 3041】
-- BILL006003 3041<条件筛选查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3182, now(), now(), '0', '4', 'BILL006003001', '查询出库单_退库出库单条件筛选查询', 'BILL,BILL006,BILL006003,BILL006003001', '/coffeeBill/api/bill/returned/findOutStorageByConditions', 3041, '\0');
-- BILL006003 3041<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3183, now(), now(), '0', '4', 'BILL006003002', '查询出库单_查看', 'BILL,BILL006,BILL006003,BILL006003002', '/coffeeBill/api/bill/returned/findOutStorageByBillCode', 3041, '\0');

-- BILL006003 3041<审核通过>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3184, now(), now(), '0', '4', 'BILL006003003', '查询出库单_审核通过', 'BILL,BILL006,BILL006003,BILL006003003', '/coffeeBill/api/bill/returned/auditSuccess', 3041, '\0');
-- BILL006003 3041<审核不通过>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3185, now(), now(), '0', '4', 'BILL006003004', '查询出库单_审核不通过', 'BILL,BILL006,BILL006003,BILL006003004', '/coffeeBill/api/bill/returned/auditFailure', 3041, '\0');
-- BILL006003 3041<审核打开>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3186, now(), now(), '0', '4', 'BILL006003005', '查询出库单_审核打开', 'BILL,BILL006,BILL006003,BILL006003005', '/coffeeBill/api/bill/returned/open', 3041, '\0');



-- 其他调拨
-- 其他调拨-BILL007 3007【添加其他调拨单-BILL007001 3042】
-- BILL007001 3042<提交>
-- 补充内容 3187
-- 其他调拨-BILL007 3007【查询其他调拨单-BILL007002 3043】
-- BILL007002 3043<条件筛选>
-- 补充内容 3188
-- BILL007002 3043<详情>
-- 补充内容3189
-- BILL007002 3043<导出>
-- 补充内容3190


-- 误差管理-BILL008 3008【添加报溢单-BILL008001 3044】
-- BILL008001 3044<提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3191, now(), now(), '0', '4', 'BILL008001001', '提交报溢单', 'BILL,BILL008,BILL008001,BILL008001001', '/coffeeBill/api/bill/mistake/submitOverFlow', 3044, '\0');
-- 误差管理-BILL008 3008【查询报溢单-BILL008002 3045】
-- BILL008002 3045<条件筛选查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3192, now(), now(), '0', '4', 'BILL008002001', '条件筛选报溢单', 'BILL,BILL008,BILL008002,BILL008002001', '/coffeeBill/api/bill/mistake/findOverFlowByConditions', 3045, '\0');
-- BILL008002 3045<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3193, now(), now(), '0', '4', 'BILL008002002', '查看报溢单详情', 'BILL,BILL008,BILL008002,BILL008002002', '/coffeeBill/api/bill/mistake/findOverFlowByBillCode', 3045, '\0');
-- 误差管理-BILL008 3008【添加报损单-BILL008003 3046】
-- BILL008003 3046<提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3194, now(), now(), '0', '4', 'BILL008003001', '提交报损单', 'BILL,BILL008,BILL008003,BILL008003001', '/coffeeBill/api/bill/mistake/submitLoss', 3046, '\0');
-- 误差管理-BILL008 3008【查询报损单-BILL008004 3047】
-- BILL008004 3047<条件查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3195, now(), now(), '0', '4', 'BILL008004001', '条件筛选报损单', 'BILL,BILL008,BILL008004,BILL008004001', '/coffeeBill/api/bill/mistake/findLossByConditions', 3047, '\0');
-- BILL008004 3047<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3196, now(), now(), '0', '4', 'BILL008004002', '查看报损单详情', 'BILL,BILL008,BILL008004,BILL008004001', '/coffeeBill/api/bill/mistake/findLossByBillCode', 3047, '\0');
-- 误差管理-BILL008 3008【添加日常误差单-BILL008005 3048】
-- BILL008005 3048<提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3197, now(), now(), '0', '4', 'BILL008005001', '提交日常误差单', 'BILL,BILL008,BILL008005,BILL008005001', '/coffeeBill/api/bill/mistake/submitDayMistake', 3048, '\0');
-- 误差管理-BILL008 3008【查询日常误差单-BILL008006 3049】
-- BILL008006 3049<条件筛选查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3198, now(), now(), '0', '4', 'BILL008006001', '条件筛选日常误差单', 'BILL,BILL008,BILL008006,BILL008006001', '/coffeeBill/api/bill/mistake/findDayMistakeByConditions', 3049, '\0');
-- BILL008006 3049<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3199, now(), now(), '0', '4', 'BILL008006002', '查看日常误差单详情', 'BILL,BILL008,BILL008006,BILL008006002', '/coffeeBill/api/bill/mistake/findDayMistakeByBillCode', 3049, '\0');
-- 误差管理-BILL008 3008【查询流转误差单-BILL008007 3050】
-- BILL008007 3050<条件筛选查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3200, now(), now(), '0', '4', 'BILL008007001', '条件筛选流转误差单', 'BILL,BILL008,BILL008007,BILL008007001', '/coffeeBill/api/bill/mistake/findAllotByConditions', 3050, '\0');
-- BILL008007 3050<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (3201, now(), now(), '0', '4', 'BILL008007002', '查看流转误差单详情', 'BILL,BILL008,BILL008007,BILL008007002', '/coffeeBill/api/bill/mistake/findAllotByBillCode', 3050, '\0');

-- 运单跟踪-BILL009 3009【添加运单-BILL009001 3051】
-- BILL009001 3051<提交>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3202, now(), now(), '0', '4', 'BILL009001001', '运单添加', 'BILL,BILL009,BILL009001,BILL009001001', '/coffeeBill/api/waybill/createWayBill', 3051, '\0');
-- 运单跟踪-BILL009 3009【查询运单-BILL009002 3052】
-- BILL009002 3052<修改>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3203, now(), now(), '0', '4', 'BILL009001002', '运单修改', 'BILL,BILL009,BILL009001,BILL009001002', '/coffeeBill/api/waybill/updateWayBill', 3051, '\0');
-- BILL009002 3052<条件筛选查询>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3204, now(), now(), '0', '4', 'BILL009002001', '运单条件筛选查询', 'BILL,BILL009,BILL009002,BILL009002001', '/coffeeBill/api/waybill/findWayBillByConditions', 3052, '\0');
-- BILL009002 3052<查看>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3205, now(), now(), '0', '4', 'BILL009002002', '运单详情查询', 'BILL,BILL009,BILL009002,BILL009002002', '/coffeeBill/api/waybill/findOneWayBill', 3052, '\0');
-- BILL009002 3052<收货>
INSERT INTO oauth_database.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
   VALUES (3206, now(), now(), '0', '4', 'BILL009002003', '运单确认收货', 'BILL,BILL009,BILL009002,BILL009002003', '/coffeeBill/api/waybill/confirmReceiptBill', 3052, '\0');

-- 库存查询-BILL010 3010【库存查询-BILL010001 3053】
-- BILL010001 3053<条件筛选查询>
-- 补充内容3207
-- 库存查询-BILL010 3010【库存流水查询-BILL010002 3054】
-- BILL010002 3054<条件筛选查询>
-- 补充内容3208
-- 其他出入库-BILL011 3011【其他出库拣货-BILL011001 3055自主】
-- BILL011001 3055<提交>
-- 补充内容3209
-- BILL011001 3055<暂存>
-- 补充内容3210
-- 其他出入库-BILL011 3011【查询其他出库单-BILL011002 3056】
-- BILL011002 3056<条件筛选查询>
-- 补充内容3211
-- BILL011002 3056<查看>
-- 补充内容3212
-- 其他出入库-BILL011 3011【查询其他入库单-BILL011003 3057】
-- BILL011003 3057<条件筛选查询>
-- 补充内容3213
-- BILL011003 3057<查看>
-- 补充内容3214

-- 日结-BILL012 3012【查询日结-BILL012001 3058】
-- BILL012001 3058<日结>
-- 补充内容3215
-- BILL012001 3058<零售日结详情>
-- 补充内容3216
-- BILL012001 3058<库存日结详情>
-- 补充内容3217


-- 库位设置（待定）
-- 补充内容3218


