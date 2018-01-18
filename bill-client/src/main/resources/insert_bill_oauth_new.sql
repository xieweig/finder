-- INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
-- 单据系统
 VALUES ( 3000, now(), now(), '0', '1', 'BILL', '单据系统', 'BILL', 'app.bill', null, '\0');

-- 单据系统二级菜单-BILL 3000
-- BILL 3000<总部计划中心-BILL001 3001>
 VALUES (3001, now(), now(),  '0', '2', 'BILL001', '总部计划中心', 'BILL,BILL001', 'app.bill.plan', 3000, '\0');
-- BILL 3000<进货-BILL002 3002>
 VALUES (3002, now(), now(),  '0', '2', 'BILL002', '进货', 'BILL,BILL002', 'app.bill.purchase', 3000 , '\0');
-- BILL 3000<配送-BILL003 3003>
 VALUES (3003, now(), now(),  '0', '2', 'BILL003', '配送', 'BILL,BILL003', 'app.bill.delivery', 3000 , '\0');
-- BILL 3000<调剂-BILL004 3004>
 VALUES (3004, now(), now(),  '0', '2', 'BILL004', '调剂', 'BILL,BILL004', 'app.bill.adjust', 3000 , '\0');
-- BILL 3000<退库-BILL005 3005>
 VALUES (3005, now(), now(),  '0', '2', 'BILL005', '退库', 'BILL,BILL005', 'app.bill.restock', 3000 , '\0');
-- BILL 3000<退货-BILL006 3006>
 VALUES (3006, now(), now(),  '0', '2', 'BILL006', '退货', 'BILL,BILL006', 'app.bill.returned', 3000 , '\0');
-- BILL 3000<其他调拨-BILL007 3007>
 VALUES (3007, now(), now(),  '0', '2', 'BILL007', '其他调拨', 'BILL,BILL007', 'app.bill.otherTransfer', 3000 , '\0');
-- BILL 3000<误差管理-BILL008 3008>
 VALUES (3008, now(), now(),  '0', '2', 'BILL008', '误差管理', 'BILL,BILL008', 'app.bill.mistake', 3000 , '\0');
-- BILL 3000<运单跟踪-BILL009 3009>
 VALUES (3009, now(), now(),  '0', '2', 'BILL009', '运单跟踪', 'BILL,BILL009', 'app.bill.trace', 3000 , '\0');
-- BILL 3000<库存查询-BILL010 3010>
 VALUES (3010, now(), now(),  '0', '2', 'BILL010', '库存查询', 'BILL,BILL010', 'app.bill.stock', 3000 , '\0');
-- BILL 3000<其他出入库-BILL011 3011>
 VALUES (3011, now(), now(),  '0', '2', 'BILL011', '其他出入库', 'BILL,BILL011', 'app.bill.otherStorage', 3000 , '\0');
-- BILL 3000<日结-BILL012 3012>
 VALUES (3012, now(), now(),  '0', '2', 'BILL012', '日结', 'BILL,BILL012', 'app.bill.dayEnd', 3000 , '\0');

-- 三级菜单
-- 总部计划中心-BILL001 3001
-- BILL001 3001<添加总部计划-BILL001001 3020>
 VALUES (3020, now(), now(), '0', '3', 'BILL001001', '添加总部计划', 'BILL,BILL001,BILL001001', 'app.bill.plan.add', 3001, '\0');
-- BILL001 3001<查询总部计划-BILL001002 3021>
 VALUES (3021, now(), now(), '0', '3', 'BILL001002', '查询总部计划', 'BILL,BILL001,BILL001002', 'app.bill.plan.list', 3001, '\0');

-- 进货-BILL002 3002
-- BILL002 3002<进货录单-BILL002001 3022>
 VALUES (3022, now(), now(), '0', '3', 'BILL002001', '进货录单', 'BILL,BILL002,BILL002001', 'app.bill.procurement.add', 3002, '\0');
-- BILL002 3002<查询进货入库单-BILL002002 3023>
 VALUES (3023, now(), now(), '0', '3', 'BILL002002', '查询进货入库单', 'BILL,BILL002,BILL002002', 'app.bill.procurement.list', 3002, '\0');

-- 配送-BILL003 3003
-- BILL003 3003<查询站点配送计划-BILL003001 3024>
 VALUES (3024, now(), now(), '0', '3', 'BILL003001', '查询站点配送计划', 'BILL,BILL003,BILL003001', 'app.bill.delivery.planList', 3003, '\0');
-- BILL003 3003<站点配送拣货-BILL003002 3025自主>
 VALUES (3025, now(), now(), '0', '3', 'BILL003002', '站点配送拣货', 'BILL,BILL003,BILL003002', 'app.bill.delivery.pick', 3003, '\0');
-- BILL003 3003<查询配送出库单-BILL003003 3026>
 VALUES (3026, now(), now(), '0', '3', 'BILL003004', '查询配送出库单', 'BILL,BILL003,BILL003003', 'app.bill.delivery.outStorageList', 3003, '\0');
-- BILL003 3003<配送入库单查询-BILL003004 3027>
 VALUES (3027, now(), now(), '0', '3', 'BILL003005', '配送入库单查询', 'BILL,BILL003,BILL003004', 'app.bill.delivery.inStorageList', 3003, '\0');
-- BILL003 3003<配送调拨单查询-BILL003005 3028>
 VALUES (3028, now(), now(), '0', '3', 'BILL003006', '配送调拨单查询', 'BILL,BILL003,BILL003005', 'app.bill.delivery.transferList', 3003, '\0');

-- 调剂-BILL004 3004
-- BILL004 3004<查询站点调剂计划-BILL004001 3029>
 VALUES (3029, now(), now(), '0', '3', 'BILL004001', '查询站点调剂计划', 'BILL,BILL004,BILL004001', 'app.bill.adjust.planList', 3004, '\0');
-- BILL004 3004<站点调剂拣货-BILL004002 3030自主>
 VALUES (3030, now(), now(), '0', '3', 'BILL004002', '站点调剂拣货', 'BILL,BILL004,BILL004002', 'app.bill.adjust.pick', 3004, '\0');
-- BILL004 3004<查询调剂出库单-BILL004003 3031>
 VALUES (3031, now(), now(), '0', '3', 'BILL004003', '查询调剂出库单', 'BILL,BILL004,BILL004003', 'app.bill.adjust.outStorageList', 3004, '\0');
-- BILL004 3004<调剂入库单查询-BILL004004 3032>
 VALUES (3032, now(), now(), '0', '3', 'BILL004004', '调剂入库单查询', 'BILL,BILL004,BILL004004', 'app.bill.adjust.inStorageList', 3004, '\0');
-- BILL004 3004<调剂调拨单查询-BILL004005 3033>
 VALUES (3033, now(), now(), '0', '3', 'BILL004005', '调剂调拨单查询', 'BILL,BILL004,BILL004005', 'app.bill.adjust.transferList', 3004, '\0');

-- 退库-BILL005 3005
-- BILL005 3005<查询退站点库计划-BILL005001 3034>
 VALUES (3034, now(), now(), '0', '3', 'BILL005001', '查询退站点库计划', 'BILL,BILL005,BILL005001', 'app.bill.restock.planList', 3005, '\0');
-- BILL005 3005<站点退库拣货-BILL005002 3035>
 VALUES (3035, now(), now(), '0', '3', 'BILL005002', '站点退库拣货', 'BILL,BILL005,BILL005002', 'app.bill.restock.pick', 3005, '\0');
-- BILL005 3005<查询退库出库单-BILL005003 3036>
 VALUES (3036, now(), now(), '0', '3', 'BILL005003', '查询退库出库单', 'BILL,BILL005,BILL005003', 'app.bill.restock.outStorageList', 3005, '\0');
-- BILL005 3005<退库入库单查询-BILL005004 3037>
 VALUES (3037, now(), now(), '0', '3', 'BILL005004', '退库入库单查询', 'BILL,BILL005,BILL005004', 'app.bill.restock.inStorageList', 3005, '\0');
-- BILL005 3005<退库调拨单查询-BILL005005 3038>
 VALUES (3038, now(), now(), '0', '3', 'BILL005005', '退库调拨单查询', 'BILL,BILL005,BILL005005', 'app.bill.restock.transferList', 3005, '\0');

-- 退货-BILL006 3006
-- BILL006 3006<查询站点退货计划-BILL006001 3039>
 VALUES (3039, now(), now(), '0', '3', 'BILL006001', '查询站点退货计划', 'BILL,BILL006,BILL006001', 'app.bill.returned.planList', 3006, '\0');
-- BILL006 3006<站点退货拣货-BILL006002 3040>
 VALUES (3040, now(), now(), '0', '3', 'BILL006002', '站点退货拣货', 'BILL,BILL006,BILL006002', 'app.bill.returned.pick', 3006, '\0');
-- BILL006 3006<查询退货出库单-BILL006003 3041>
 VALUES (3041, now(), now(), '0', '3', 'BILL006003', '查询退货出库单', 'BILL,BILL006,BILL006003', 'app.bill.returned.outStorageList', 3006, '\0');

-- 其他调拨-BILL007 3007
-- BILL007 3007<添加其他调拨单-BILL007001 3042>
 VALUES (3042, now(), now(), '0', '3', 'BILL007001', '添加其他调拨单', 'BILL,BILL007,BILL007001', 'app.bill.otherTransfer.add', 3007, '\0');
-- BILL007 3007<查询其他调拨单-BILL007002 3043>
 VALUES (3043, now(), now(), '0', '3', 'BILL007002', '查询其他调拨单', 'BILL,BILL007,BILL007002', 'app.bill.otherTransfer.list', 3007, '\0');

-- 误差管理-BILL008 3008
-- BILL008 3008<添加报溢单-BILL008001 3044>
 VALUES (3044, now(), now(), '0', '3', 'BILL008001', '添加报溢单', 'BILL,BILL008,BILL008001', 'app.bill.mistake.overflowAdd', 3008, '\0');
-- BILL008 3008<查询报溢单-BILL008002 3045>
 VALUES (3045, now(), now(), '0', '3', 'BILL008002', '查询报溢单', 'BILL,BILL008,BILL008002', 'app.bill.mistake.overflowList', 3008, '\0');
-- BILL008 3008<添加报损单-BILL008003 3046>
 VALUES (3046, now(), now(), '0', '3', 'BILL008003', '添加报损单', 'BILL,BILL008,BILL008003', 'app.bill.mistake.lossAdd', 3008, '\0');
-- BILL008 3008<查询报损单-BILL008004 3047>
 VALUES (3047, now(), now(), '0', '3', 'BILL008004', '查询报损单', 'BILL,BILL008,BILL008004', 'app.bill.mistake.lossList', 3008, '\0');
-- BILL008 3008<添加日常误差单-BILL008005 3048>
 VALUES (3048, now(), now(), '0', '3', 'BILL008005', '添加日常误差单', 'BILL,BILL008,BILL008005', 'app.bill.mistake.adyMistakeAdd', 3008, '\0');
-- BILL008 3008<查询日常误差单-BILL008006 3049>
 VALUES (3049, now(), now(), '0', '3', 'BILL008006', '查询日常误差单', 'BILL,BILL008,BILL008006', 'app.bill.mistake.adyMistakeList', 3008, '\0');
-- BILL008 3008<查询流转误差单-BILL008006 3050>
 VALUES (3050, now(), now(), '0', '3', 'BILL008006', '查询流转误差单', 'BILL,BILL008,BILL008008', 'app.bill.mistake.list', 3008, '\0');

-- 运单跟踪-BILL009 3009
-- BILL009 3009<添加运单-BILL009001 3051>
 VALUES (3051, now(), now(), '0', '3', 'BILL009001', '添加运单', 'BILL,BILL009,BILL009001', 'app.bill.trace.add', 3009, '\0');
-- BILL009 3009<查询运单-BILL009002 3052>
 VALUES (3052, now(), now(), '0', '3', 'BILL009002', '查询运单', 'BILL,BILL009,BILL009002', 'app.bill.trace.list', 3009, '\0');

-- 库存查询-BILL010 3010
-- BILL010 3010<库存查询-BILL010001 3053>
 VALUES (3053, now(), now(), '0', '3', 'BILL010001', '库存查询', 'BILL,BILL010,BILL010001', 'app.bill.stock.list', 3010, '\0');
-- BILL010 3010<库存流水查询-BILL010002 3054>
 VALUES (3054, now(), now(), '0', '3', 'BILL010002', '库存流水查询', 'BILL,BILL010,BILL010002', 'app.bill.stock.accountList', 3010, '\0');

-- 其他出入库-BILL011 3011
-- BILL011 3011<其他出库拣货-BILL011001 3055自主>
 VALUES (3055, now(), now(), '0', '3', 'BILL011001', '其他出库拣货', 'BILL,BILL011,BILL011001', 'app.bill.otherOutStorage.pick', 3011, '\0');
-- BILL011 3011<查询其他出库单-BILL011002 3056>
 VALUES (3056, now(), now(), '0', '3', 'BILL011002', '查询其他出库单', 'BILL,BILL011,BILL011002', 'app.bill.otherOutStorage.list', 3011, '\0');
-- BILL011 3011<查询其他入库单-BILL011003 3057>
 VALUES (3057, now(), now(), '0', '3', 'BILL011003', '查询其他入库单', 'BILL,BILL011,BILL011003', 'app.bill.otherOutStorage.otherList', 3011, '\0');

-- 日结-BILL012 3012
-- BILL012 3012<查询日结-BILL012001 3058>
 VALUES (3058, now(), now(), '0', '3', 'BILL012001', '查询日结', 'BILL,BILL012,BILL012001', 'app.bill.dayEnd.list', 3012, '\0');

-- **************************************************************************************************需要补充/修改部分*******************************************************************************************

-- 四级（操作）
-- 总部计划中心BILL001 3001【添加总部计划-BILL001001 3020】
-- BILL001001 3020<总部计划暂存-BILL001001001 3100>
 VALUES (3100, now(), now(), '0', '4', 'BILL001001001', '总部计划暂存', 'BILL,BILL001,BILL001001,BILL001001001', '/coffeeBill/api/bill/planBill/save', 3020, '\0');
-- BILL001001 3020<总部计划提交-BILL001001002 3101>
 VALUES (3101, now(), now(), '0', '4', 'BILL001001002', '总部计划提交', 'BILL,BILL001,BILL001001,BILL001001002', '/coffeeBill/api/bill/planBill/submit', 3020, '\0');
-- 总部计划中心BILL001 3001【查询总部计划-BILL001002 3021】
-- BILL001002 3021<条件筛选查询-BILL001002001 3102>
 VALUES (3102, now(), now(), '0', '4', 'BILL001002001', '总部计划条件筛选查询', 'BILL,BILL001,BILL001002,BILL001002001', '/coffeeBill/api/bill/planBill/findPlanByConditions', 3021, '\0');
-- BILL001002 3021<查看-BILL001002002 3103>
 VALUES (3103, now(), now(), '0', '4', 'BILL001002002', '总部计划详情查询', 'BILL,BILL001,BILL001002,BILL001002002', '/coffeeBill/api/bill/planBill/findByBillCode', 3021, '\0');
-- BILL001002 3021<打开-BILL001002003 3104>
--  VALUES (3104, now(), now(), '0', '4', 'BILL001002003', '打开总部计划', 'BILL,BILL001,BILL001002,BILL001002003', '/coffeeBill/api/bill/planBill/open', 3021, '\0');
-- BILL001002 3021<审核不通过-BILL001002004 3105>
 VALUES (3105, now(), now(), '0', '4', 'BILL001002004', '总部计划审核不通过', 'BILL,BILL001,BILL001002,BILL001002004', '/coffeeBill/api/bill/planBill/auditFailure', 3021, '\0');
-- BILL001002 3021<审核通过-BILL001002005 3106>
 VALUES (3106, now(), now(), '0', '4', 'BILL001002005', '总部计划审核通过', 'BILL,BILL001,BILL001002,BILL001002005', '/coffeeBill/api/bill/planBill/auditSuccess', 3021, '\0');
-- BILL001002 3021<删除-BILL001002006 3107>
 VALUES (3107, now(), now(), '0', '4', 'BILL001002006', '删除总部计划', 'BILL,BILL001,BILL001002,BILL001002006', '/coffeeBill/api/bill/planBill/delete', 3021, '\0');
-- BILL001002 3021<导出-BILL001002007 3108>
 VALUES (3108, now(), now(), '0', '4', 'BILL001002007', '导出总部计划', 'BILL,BILL001,BILL001002,BILL001002007', '/coffeeBill/api/bill/planBill/downLoad', 3021, '\0');

-- 进货BILL002 3002【进货录单-BILL002001 3022】
-- BILL002001 3022<暂存-BILL002001001 3110>
 VALUES (3110, now(), now(), '0', '4', 'BILL002001001', '进货单暂存', 'BILL,BILL002,BILL002001,BILL002001001', '/coffeeBill/api/bill/purchase/save', 3022, '\0');
-- BILL002001 3022<提交-BILL002001002 3111>
 VALUES (3111, now(), now(), '0', '4', 'BILL002001002', '进货单提交', 'BILL,BILL002,BILL002001,BILL002001002', '/coffeeBill/api/bill/purchase/submit', 3022, '\0');

-- 进货BILL002 3002【查询进货入库单-BILL002002 3023】
-- BILL002002 3023<条件筛选查询-BILL002002001 3112>
 VALUES (3112, now(), now(), '0', '4', 'BILL002002001', '进货单条件筛选查询', 'BILL,BILL002,BILL002002,BILL002002001', '/coffeeBill/api/bill/purchase/findByConditions', 2023, '\0');
-- BILL002002 3023<导出-BILL002002002 3113>
 VALUES (3113, now(), now(), '0', '4', 'BILL002002002', '进货单导出', 'BILL,BILL002,BILL002002,BILL002002002', '/coffeeBill/api/bill/purchase/downLoad', 2023, '\0');
-- BILL002002 3023<查看-BILL002002003 3114>
 VALUES (3114, now(), now(), '0', '4', 'BILL002002003', '查看进货单详情', 'BILL,BILL002,BILL002002,BILL002002003', '/coffeeBill/api/bill/purchase/updatePurchaseBillToSave', 2023, '\0');
-- BILL002002 3023<打开-BILL002002004 3115>
--  VALUES (3115, now(), now(), '0', '4', 'BILL002002004', '打开进货单', 'BILL,BILL002,BILL002002,BILL002002004', '/coffeeBill/api/bill/purchase/open', 2023, '\0');
-- BILL002002 3023<审核不通过-BILL002002005 3116>
 VALUES (3116, now(), now(), '0', '4', 'BILL002002005', '进货单审核不通过', 'BILL,BILL002,BILL002002,BILL002002005', '/coffeeBill/api/bill/purchase/auditFailure', 2023, '\0');
-- BILL002002 3023<通过-BILL002002006 3117>
 VALUES (3117, now(), now(), '0', '4', 'BILL002002006', '进货单审核通过', 'BILL,BILL002,BILL002002,BILL002002006', '/coffeeBill/api/bill/purchase/auditSuccess', 2023, '\0');

--  配送-BILL003 3003【查询站点配送计划-BILL003001 3024】
-- BILL003001 3024<条件查询-BILL003001001 3118>
 VALUES (3118, now(), now(), '0', '4', 'BILL003001001', '条件筛选查询站点配送计划', 'BILL,BILL003,BILL003001,BILL003001001', '补充内容1', 3024, '\0');
-- BILL003001 3024<拣货-BILL003001002 3119>
 VALUES (3119, now(), now(), '0', '4', 'BILL003001002', '拣货', 'BILL,BILL003,BILL003001,BILL003001002', '补充内容2', 3024, '\0');
-- BILL003001 3024<根据站点计划号查询-BILL003001003 3120>
 VALUES (3120, now(), now(), '0', '4', 'BILL003001003', '根据站点计划号查询', 'BILL,BILL003,BILL003001,BILL003001003', '补充内容3', 3024, '\0');
-- BILL003001 3024<导出-BILL003001004 3121>
 VALUES (3121, now(), now(), '0', '4', 'BILL003001004', '导出', 'BILL,BILL003,BILL003001,BILL003001004', '补充内容4', 3024, '\0');

-- 配送-BILL003 3003【站点配送拣货-BILL003002 3025自主】
-- BILL003002 3025<提交-BILL003002001 3122>
 VALUES (3122, now(), now(), '0', '4', 'BILL003002001', '提交', 'BILL,BILL003,BILL003002,BILL003002001', '补充内容5', 3025, '\0');
-- 配送-BILL003 3003【查询配送出库单-BILL003003 3026】
-- BILL003004 3026<条件查询-BILL003003001 3123>
 VALUES (3123, now(), now(), '0', '4', 'BILL003003001', '提交', 'BILL,BILL003,BILL003003,BILL003003001', '补充内容6', 3026, '\0');
-- BILL003004 3026<查看-BILL003003002 3124>
 VALUES (3124, now(), now(), '0', '4', 'BILL003003002', '查看', 'BILL,BILL003,BILL003003,BILL003003002', '补充内容7', 3026, '\0');
-- BILL003004 3026<修改-BILL003003003 3125>
 VALUES (3125, now(), now(), '0', '4', 'BILL003003003', '修改', 'BILL,BILL003,BILL003003,BILL003003003', '补充内容8', 3026, '\0');
-- BILL003004 3026<审核通过>
 VALUES (3126, now(), now(), '0', '4', 'BILL003003004', '审核通过', 'BILL,BILL003,BILL003003,BILL003003004', '补充内容9', 3026, '\0');
-- BILL003004 3026<审核不通过>
 VALUES (3127, now(), now(), '0', '4', 'BILL003003005', '审核不通过', 'BILL,BILL003,BILL003003,BILL003003005', '补充内容10', 3026, '\0');
-- BILL003004 3026<导出>
 VALUES (3128, now(), now(), '0', '4', 'BILL003003006', '导出', 'BILL,BILL003,BILL003003,BILL003003006', '补充内容11', 3026, '\0');

--配送-BILL003 3003【配送入库单查询-BILL003004 3027】
--BILL003005 3027<条件查询>
--补充内容
--BILL003005 3027<查看>
--补充内容
--BILL003005 3027<调拨>
--补充内容
--BILL003005 3027<导出>
--补充内容

-- 配送-BILL003 3003【配送调拨单查询-BILL003005 3028】
-- BILL003006 3028<条件查询>
--补充内容
-- BILL003006 3028<查看>
--补充内容
-- BILL003006 3028<导出>
--补充内容

-- 调剂-BILL004 3004【查询站点调剂计划-BILL004001 3029】
-- BILL004001 3029<拣货>
--补充内容
-- BILL004001 3029<根据站点计划好查询>
--补充内容
-- BILL004001 3029<导出>
--补充内容
-- 调剂-BILL004 3004【站点调剂拣货-BILL004002 3030自主】
-- BILL004002 3030<提交>
--补充内容
-- 调剂-BILL004 3004【查询调剂出库单-BILL004003 3031】
-- BILL004003 3031<件筛选查询>
--补充内容
-- BILL004003 3031<查看>
--补充内容
-- BILL004003 3031<修改>
--补充内容
-- BILL004003 3031<审核通过>
--补充内容
-- BILL004003 3031<审核不通过>
--补充内容
-- 调剂-BILL004 3004【调剂入库单查询-BILL004004 3032】
-- BILL004004 3032<件筛选查询>
--补充内容
-- BILL004004 3032<调拨>
--补充内容
-- BILL004004 3032<查看>
--补充内容
-- 调剂-BILL004 3004【调剂调拨单查询-BILL004005 3033】
-- BILL004005 3033<件筛选查询>
--补充内容
-- BILL004005 3033<查看>
--补充内容


-- 退库-BILL005 3005【查询退站点库计划-BILL005001 3034】
-- BILL005001 3034<条件筛选查询>
 VALUES (3130, now(), now(), '0', '4', 'BILL005001001', '退库计划条件筛选查询', 'BILL,BILL005,BILL005001,BILL005001001', '/coffeeBill/api/bill/restock/findPlanBillByConditions', 3034, '\0');
-- BILL005001 3034<站点计划号>
--  VALUES (3131, now(), now(), '0', '4', 'BILL005001002', '根据源单号查询单据详细信息', 'BILL,BILL005,BILL005001,BILL005001002', '/coffeeBill/api/bill/restock/findBySourceCode', 3034, '\0');
-- BILL005001 3034<查看>
 VALUES (3132, now(), now(), '0', '4', 'BILL005001003', '根据单号查询关联计划单据详细信息', 'BILL,BILL005,BILL005001,BILL005001003', '/coffeeBill/api/bill/restock/findPlanBillByBillCode', 3034, '\0');
-- BILL005001 3034<拣货保存>
 VALUES (3133, now(), now(), '0', '4', 'BILL005001004', '退库拣货保存', 'BILL,BILL005,BILL005001,BILL005001004', '/coffeeBill/api/bill/restock/save', 3034, '\0');
-- BILL005001 3034<拣货提交>
 VALUES (3134, now(), now(), '0', '4', 'BILL005001005', '退库拣货提交', 'BILL,BILL005,BILL005001,BILL005001005', '/coffeeBill/api/bill/restock/submit', 3034, '\0');
-- 退库-BILL005 3005【站点退库拣货-BILL005002 3035】
-- BILL005002 3035<自主拣货保存>
 VALUES (3135, now(), now(), '0', '4', 'BILL005002001', '站点自主拣货保存', 'BILL,BILL005,BILL005002,BILL005002001', '/coffeeBill/api/bill/restock/saveRestockBillBySelf', 3035, '\0');
-- BILL005002 3035<自主拣货提交>
 VALUES (3136, now(), now(), '0', '4', 'BILL005002002', '站点自主拣货提交', 'BILL,BILL005,BILL005002,BILL005002002', '/coffeeBill/api/bill/restock/submitRestockBillBySelf', 3035, '\0');
-- 退库-BILL005 3005【查询退库出库单-BILL005003 3036】
-- BILL005003 3036<条件筛选查询>
 VALUES (3137, now(), now(), '0', '4', 'BILL005003001', '退库出库单条件筛选查询', 'BILL,BILL005,BILL005003,BILL005003001', '/coffeeBill/api/bill/restock/findOutStorageByConditions', 3036, '\0');
-- BILL005003 3036<查看>
 VALUES (3138, now(), now(), '0', '4', 'BILL005003002', '查看出库单详情', 'BILL,BILL005,BILL005003,BILL005003002', '/coffeeBill/api/bill/restock/findOutStorageByBillCode', 3036, '\0');
-- BILL005003 3036<修改保存>
 VALUES (3141, now(), now(), '0', '4', 'BILL005003005', '退库出库单修改保存', 'BILL,BILL005,BILL005003,BILL005003005', '/coffeeBill/api/bill/restock/updateRestockBillToSave', 3036, '\0');
-- BILL005003 3036<修改提交>
 VALUES (3142, now(), now(), '0', '4', 'BILL005003006', '退库出库单修改提交', 'BILL,BILL005,BILL005003,BILL005003006', '/coffeeBill/api/bill/restock/updateRestockBillToSubmit', 3036, '\0');
-- BILL005003 3036<审核通过>
 VALUES (3143, now(), now(), '0', '4', 'BILL005003007', '审核通过退库出库单', 'BILL,BILL005,BILL005003,BILL005003007', '/coffeeBill/api/bill/restock/auditSuccess', 3036, '\0');
-- BILL005003 3036<审核不通过>
 VALUES (3144, now(), now(), '0', '4', 'BILL005003008', '审核不通过退库出库单', 'BILL,BILL005,BILL005003,BILL005003008', '/coffeeBill/api/bill/restock/auditFailure', 3036, '\0');

--退库-BILL005 3005【退库入库单查询-BILL005004 3037】
--BILL005004 3037<条件查询>
--补充内容
--BILL005004 3037<调拨>
--补充内容
--BILL005004 3037<查看>
--补充内容
--退库-BILL005 3005【退库调拨单查询-BILL005005 3038】
--BILL005005 3038<查看>
--补充内容

--退货-BILL006 3006【查询站点退货计划-BILL006001 3039】
--BILL006001 3039<条件筛选查询>
--补充内容
--BILL006001 3039<拣货>
--补充内容
--BILL006001 3039<根据站点计划号查询>
--补充内容
--退货-BILL006 3006【站点退货拣货-BILL006002 3040】
--BILL006002 3040<提交>
--补充内容
--退货-BILL006 3006【查询退货出库单-BILL006003 3041】
--BILL006003 3041<条件筛选查询>
--补充内容
--BILL006003 3041<查看>
--补充内容
--BILL006003 3041<审核通过>
--补充内容
--BILL006003 3041<审核不通过>
--补充内容



-- 其他调拨
-- 其他调拨-BILL007 3007【添加其他调拨单-BILL007001 3042】
-- BILL007001 3042<提交>
--补充内容
-- 其他调拨-BILL007 3007【查询其他调拨单-BILL007002 3043】
-- BILL007002 3043<条件筛选>
--补充内容
-- BILL007002 3043<详情>
--补充内容
-- BILL007002 3043<导出>
--补充内容


-- 误差管理-BILL008 3008【添加报溢单-BILL008001 3044】
-- BILL008001 3044<提交>
--补充内容
-- 误差管理-BILL008 3008【查询报溢单-BILL008002 3045】
-- BILL008002 3045<条件筛选查询>
--补充内容
-- BILL008002 3045<查看>
--补充内容
-- 误差管理-BILL008 3008【添加报损单-BILL008003 3046】
-- BILL008003 3046<提交>
--补充内容
-- 误差管理-BILL008 3008【查询报损单-BILL008004 3047】
-- BILL008004 3047<条件查询>
--补充内容
-- BILL008004 3047<查看>
--补充内容
-- 误差管理-BILL008 3008【添加日常误差单-BILL008005 3048】
-- BILL008005 3048<提交>
--补充内容
-- 误差管理-BILL008 3008【查询日常误差单-BILL008006 3049】
-- BILL008006 3049<条件筛选查询>
--补充内容
-- BILL008006 3049<查看>
--补充内容
-- 误差管理-BILL008 3008【查询流转误差单-BILL008006 3050】
-- BILL008006 3050<条件筛选查询>
--补充内容
-- BILL008006 3050<查看>
--补充内容

-- 运单跟踪-BILL009 3009【添加运单-BILL009001 3051】
-- BILL009001 3051<提交>
 VALUES (3120, now(), now(), '0', '4', 'BILL009001001', '运单添加', 'BILL,BILL009,BILL009001,BILL009001001', '/coffeeBill/api/waybill/createWayBill', 3051, '\0');
-- 运单跟踪-BILL009 3009【查询运单-BILL009002 3052】
-- BILL009002 3052<修改>
 VALUES (3121, now(), now(), '0', '4', 'BILL009001002', '运单修改', 'BILL,BILL009,BILL009001,BILL009001002', '/coffeeBill/api/waybill/updateWayBill', 3051, '\0');
-- BILL009002 3052<条件筛选查询>
 VALUES (3122, now(), now(), '0', '4', 'BILL009002001', '运单条件筛选查询', 'BILL,BILL009,BILL009002,BILL009002001', '/coffeeBill/api/waybill/findWayBillByConditions', 3052, '\0');
-- BILL009002 3052<查看>
 VALUES (3123, now(), now(), '0', '4', 'BILL009002002', '运单详情查询', 'BILL,BILL009,BILL009002,BILL009002002', '/coffeeBill/api/waybill/findOneWayBill', 3052, '\0');
-- BILL009002 3052<收货>
 VALUES (3124, now(), now(), '0', '4', 'BILL009002003', '运单确认收货', 'BILL,BILL009,BILL009002,BILL009002003', '/coffeeBill/api/waybill/confirmReceiptBill', 3052, '\0');

-- 库存查询-BILL010 3010【库存查询-BILL010001 3053】
-- BILL010001 3053<条件筛选查询>
--补充内容
-- 库存查询-BILL010 3010【库存流水查询-BILL010002 3054】
-- BILL010002 3054<条件筛选查询>
--补充内容
-- 其他出入库-BILL011 3011【其他出库拣货-BILL011001 3055自主】
-- BILL011001 3055<提交>
--补充内容
-- BILL011001 3055<暂存>
--补充内容
-- 其他出入库-BILL011 3011【查询其他出库单-BILL011002 3056】
-- BILL011002 3056<条件筛选查询>
--补充内容
-- BILL011002 3056<查看>
--补充内容
-- 其他出入库-BILL011 3011【查询其他入库单-BILL011003 3057】
-- BILL011003 3057<条件筛选查询>
--补充内容
-- BILL011003 3057<查看>
--补充内容

-- 日结-BILL012 3012【查询日结-BILL012001 3058】
-- BILL012001 3058<日结>
--补充内容
-- BILL012001 3058<零售日结详情>
--补充内容
-- BILL012001 3058<库存日结详情>
--补充内容


-- 库位设置（待定）
--补充内容

