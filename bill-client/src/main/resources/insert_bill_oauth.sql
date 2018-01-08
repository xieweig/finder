-- 单据系统
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES ( 3000, now(), now(), '0', '1', 'BILL', '单据系统', 'BILL', 'app.bill', null, '\0');

-- 单据系统二级菜单
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3001, now(), now(),  '0', '2', 'BILL001', '中部计划中心', 'BILL,BILL001', 'app.bill.plan', 3000, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3002, now(), now(),  '0', '2', 'BILL002', '进货', 'BILL,BILL002', 'app.bill.purchase', 3000 , '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3003, now(), now(),  '0', '2', 'BILL003', '配送', 'BILL,BILL003', 'app.bill.purchase', 3000 , '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3004, now(), now(),  '0', '2', 'BILL004', '调剂', 'BILL,BILL004', 'app.bill.purchase', 3000 , '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3005, now(), now(),  '0', '2', 'BILL005', '退库', 'BILL,BILL005', 'app.bill.restock', 3000 , '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3006, now(), now(),  '0', '2', 'BILL006', '退货', 'BILL,BILL006', 'app.bill.purchase', 3000 , '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3007, now(), now(),  '0', '2', 'BILL007', '其他调拨', 'BILL,BILL007', 'app.bill.purchase', 3000 , '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3008, now(), now(),  '0', '2', 'BILL008', '误差管理', 'BILL,BILL008', 'app.bill.purchase', 3000 , '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3009, now(), now(),  '0', '2', 'BILL009', '运单跟踪', 'BILL,BILL009', 'app.bill.purchase', 3000 , '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3010, now(), now(),  '0', '2', 'BILL010', '库存查询（待确认）', 'BILL,BILL010', 'app.bill.purchase', 3000 , '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3011, now(), now(),  '0', '2', 'BILL011', '其他出入库', 'BILL,BILL011', 'app.bill.purchase', 3000 , '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3012, now(), now(),  '0', '2', 'BILL012', '日结', 'BILL,BILL012', 'app.bill.purchase', 3000 , '\0');

-- 三级菜单
-- 总部计划
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES ( 3020, now(), now(), '0', '3', 'BILL001001', '添加总部计划', 'BILL,BILL001,BILL001001', 'app.bill.plan.add', 3001, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES ( 3021, now(), now(), '0', '3', 'BILL001002', '条件筛选查询总部计划', 'BILL,BILL001,BILL001002', 'app.bill.plan.list', 3001, '\0');
-- 进货
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3022, now(), now(), '0', '3', 'BILL002001', '进货录单', 'BILL,BILL002,BILL002001', 'app.bill.procurement.add', 3002, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3023, now(), now(), '0', '3', 'BILL002002', '查询进货入库单', 'BILL,BILL002,BILL002002', 'app.bill.procurement.list', 3002, '\0');
-- 配送
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3024, now(), now(), '0', '3', 'BILL003001', '查询站点配送计划', 'BILL,BILL003,BILL003001', 'app.bill.delivery.planList', 3003, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3025, now(), now(), '0', '3', 'BILL003002', '站点配送拣货', 'BILL,BILL003,BILL003002', 'app.bill.delivery.pick', 3003, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3026, now(), now(), '0', '3', 'BILL003003', '站点配送计划拣货', 'BILL,BILL003,BILL003003', 'app.bill.delivery.planPick', 3003, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3027, now(), now(), '0', '3', 'BILL003004', '查询配送出库单', 'BILL,BILL003,BILL003004', 'app.bill.delivery.outStorageList', 3003, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3028, now(), now(), '0', '3', 'BILL003005', '配送入库单查询', 'BILL,BILL003,BILL003005', 'app.bill.delivery.inStorageList', 3003, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3029, now(), now(), '0', '3', 'BILL003006', '配送调拨单查询', 'BILL,BILL003,BILL003006', 'app.bill.delivery.transferList', 3003, '\0');
-- 调剂
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3030, now(), now(), '0', '3', 'BILL004001', '查询站点调剂计划', 'BILL,BILL004,BILL004001', 'app.bill.adjust.planList', 3004, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3031, now(), now(), '0', '3', 'BILL004002', '站点调剂拣货', 'BILL,BILL004,BILL004002', 'app.bill.adjust.pick', 3004, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3032, now(), now(), '0', '3', 'BILL004003', '查询调剂出库单', 'BILL,BILL004,BILL004003', 'app.bill.adjust.outStorageList', 3004, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3033, now(), now(), '0', '3', 'BILL004004', '调剂入库单查询', 'BILL,BILL004,BILL004004', 'app.bill.adjust.inStorageList', 3004, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3034, now(), now(), '0', '3', 'BILL004005', '调剂调拨单查询', 'BILL,BILL004,BILL004005', 'app.bill.adjust.transferList', 3004, '\0');
-- 退库
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3035, now(), now(), '0', '3', 'BILL005001', '查询站点退库计划', 'BILL,BILL005,BILL005001', 'app.bill.restock.planList', 3005, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3036, now(), now(), '0', '3', 'BILL005002', '站点退库拣货', 'BILL,BILL005,BILL005002', 'app.bill.restock.pick', 3005, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3037, now(), now(), '0', '3', 'BILL005003', '查询退库出库单', 'BILL,BILL005,BILL005003', 'app.bill.restock.outStorageList', 3005, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3038, now(), now(), '0', '3', 'BILL005004', '退库入库单查询', 'BILL,BILL005,BILL005004', 'app.bill.restock.inStorageList', 3005, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3039, now(), now(), '0', '3', 'BILL005005', '退库调拨单查询', 'BILL,BILL005,BILL005005', 'app.bill.restock.transferList', 3005, '\0');
-- 退货
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3040, now(), now(), '0', '3', 'BILL006001', '查询站点退货计划', 'BILL,BILL006,BILL006001', 'app.bill.returned.planList', 3006, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3041, now(), now(), '0', '3', 'BILL006002', '站点退货拣货', 'BILL,BILL006,BILL006002', 'app.bill.returned.pick', 3006, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3042, now(), now(), '0', '3', 'BILL006002', '查询退货出库单', 'BILL,BILL006,BILL006003', 'app.bill.returned.outStorageList', 3006, '\0');
-- 其他调拨
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3043, now(), now(), '0', '3', 'BILL007001', '添加其他调拨单', 'BILL,BILL007,BILL007001', 'app.bill.otherTransfer.add', 3007, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3045, now(), now(), '0', '3', 'BILL007002', '查询其他调拨单', 'BILL,BILL007,BILL007002', 'app.bill.otherTransfer.list', 3007, '\0');
-- 误差管理
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3046, now(), now(), '0', '3', 'BILL008001', '添加报溢单', 'BILL,BILL008,BILL008001', 'app.bill.overflow.add', 3008, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3047, now(), now(), '0', '3', 'BILL008002', '查询报溢单', 'BILL,BILL008,BILL008002', 'app.bill.overflow.list', 3008, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3048, now(), now(), '0', '3', 'BILL008003', '添加报损单', 'BILL,BILL008,BILL008003', 'app.bill.loss.add', 3008, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3049, now(), now(), '0', '3', 'BILL008004', '查询报损单', 'BILL,BILL008,BILL008004', 'app.bill.loss.list', 3008, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3050, now(), now(), '0', '3', 'BILL008005', '添加流转误差单', 'BILL,BILL008,BILL008005', 'app.bill.mistake.add', 3008, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3051, now(), now(), '0', '3', 'BILL008006', '查询流转误差单', 'BILL,BILL008,BILL008006', 'app.bill.mistake.list', 3008, '\0');
-- 运单跟踪
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3052, now(), now(), '0', '3', 'BILL009001', '添加运单', 'BILL,BILL009,BILL009001', 'app.bill.trace.add', 3009, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3053, now(), now(), '0', '3', 'BILL009002', '查询运单', 'BILL,BILL009,BILL009002', 'app.bill.trace.list', 3009, '\0');
-- 库存查询
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3054, now(), now(), '0', '3', 'BILL010001', '库存查询', 'BILL,BILL010,BILL010001', 'app.bill.stock.list', 3010, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3055, now(), now(), '0', '3', 'BILL010002', '库存流水查询', 'BILL,BILL010,BILL010002', 'app.bill.stockAccount.list', 3010, '\0');
--其他出入库
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3056, now(), now(), '0', '3', 'BILL011001', '其他出库拣货', 'BILL,BILL011,BILL011001', 'app.bill.otherOutStorage.pick', 3011, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3057, now(), now(), '0', '3', 'BILL011002', '查询其他出库单', 'BILL,BILL011,BILL011002', 'app.bill.otherOutStorage.list', 3011, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3058, now(), now(), '0', '3', 'BILL011003', '查询其他入库单', 'BILL,BILL011,BILL011003', 'app.bill.storageOtherTransfer.list', 3011, '\0');
-- 日结
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3059, now(), now(), '0', '3', 'BILL012001', '查询日结', 'BILL,BILL012,BILL012001', 'app.bill.dayEnd.list', 3012, '\0');

-- 四级（操作）
-- 总部计划
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3100, now(), now(), '0', '4', 'BILL001001001', '暂存总部计划', 'BILL,BILL001,BILL001001,BILL001001001', '/coffeeBill/api/bill/planbill/create', 3020, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3101, now(), now(), '0', '4', 'BILL001001002', '提交总部计划', 'BILL,BILL001,BILL001001,BILL001001002', '/coffeeBill/api/bill/planbill/submit', 3020, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3102, now(), now(), '0', '4', 'BILL001002001', '条件筛选查询操作', 'BILL,BILL001,BILL001002,BILL001002001', '/coffeeBill/api/bill/planbill/findPlanBillByConditions', 3021, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3103, now(), now(), '0', '4', 'BILL001002002', '查询总部计划详情', 'BILL,BILL001,BILL001002,BILL001002002', '/coffeeBill/api/bill/planbill/findByBillCode', 3021, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3104, now(), now(), '0', '4', 'BILL001002003', '打开总部计划', 'BILL,BILL001,BILL001002,BILL001002003', '/coffeeBill/api/bill/planbill/open', 3021, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3105, now(), now(), '0', '4', 'BILL001002004', '总部计划审核不通过', 'BILL,BILL001,BILL001002,BILL001002004', '/coffeeBill/api/bill/planbill/unpass', 3021, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3106, now(), now(), '0', '4', 'BILL001002005', '总部计划审核通过', 'BILL,BILL001,BILL001002,BILL001002005', '/coffeeBill/api/bill/planbill/pass', 3021, '\0');
-- 进货
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3110, now(), now(), '0', '4', 'BILL002001001', '保存', 'BILL,BILL002,BILL002001,BILL002001001', '/coffeeBill/api/bill/purchase/savePurchaseBill', 3022, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3111, now(), now(), '0', '4', 'BILL002001002', '提交审核', 'BILL,BILL002,BILL002001,BILL002001002', '/coffeeBill/api/bill/purchase/submitPurchaseBill', 3022, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3112, now(), now(), '0', '4', 'BILL002002001', '高级分页查询', 'BILL,BILL002,BILL002002,BILL002002001', '/coffeeBill/api/bill/purchase/findByConditions', 2023, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3113, now(), now(), '0', '4', 'BILL002002002', '导出进货单', 'BILL,BILL002,BILL002002,BILL002002002', '/coffeeBill/api/bill/purchase/downLoad', 2023, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3114, now(), now(), '0', '4', 'BILL002002003', '保存', 'BILL,BILL002,BILL002002,BILL002002003', '/coffeeBill/api/bill/purchase/updatePurchaseBillToSave', 2023, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3115, now(), now(), '0', '4', 'BILL002002004', '提交审核', 'BILL,BILL002,BILL002002,BILL002002004', '/coffeeBill/api/bill/purchase/updatePurchaseBillToSubmit', 2023, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3116, now(), now(), '0', '4', 'BILL002002005', '审核不通过', 'BILL,BILL002,BILL002002,BILL002002005', '/coffeeBill/api/bill/purchase/auditFailure', 2023, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3117, now(), now(), '0', '4', 'BILL002002006', '审核通过', 'BILL,BILL002,BILL002002,BILL002002006', '/coffeeBill/api/bill/purchase/auditSuccess', 2023, '\0');
-- 运单
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3120, now(), now(), '0', '9', 'BILL009001001', '添加', 'BILL,BILL009,BILL009001,BILL009001001', '/coffeeBill/api/waybill/createWayBill', 3052, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3121, now(), now(), '0', '9', 'BILL009001002', '修改', 'BILL,BILL009,BILL009001,BILL009001002', '/coffeeBill/api/waybill/updateWayBill', 3052, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3122, now(), now(), '0', '9', 'BILL009002001', '查询', 'BILL,BILL009,BILL009002,BILL009002001', '/coffeeBill/api/waybill/findWayBillByConditions', 3053, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3123, now(), now(), '0', '9', 'BILL009002002', '查看', 'BILL,BILL009,BILL009002,BILL009002002', '/coffeeBill/api/waybill/findOneWayBill', 3053, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3124, now(), now(), '0', '9', 'BILL009002003', '运单确认收货', 'BILL,BILL009,BILL009002,BILL009002003', '/coffeeBill/api/waybill/confirmReceiptBill', 3053, '\0');
-- -- 退库
-- INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3130, now(), now(), '0', '4', 'BILL005002001', '站点拣货保存', 'BILL,BILL005,BILL005002,BILL005002001', '/coffeeBill/api/bill/restock/saveRestockBill', 3036, '\0');
-- INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3131, now(), now(), '0', '4', 'BILL005002002', '站点拣货提交', 'BILL,BILL005,BILL005002,BILL005002002', '/coffeeBill/api/bill/restock/submitRestockBill', 3036, '\0');
-- INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3132, now(), now(), '0', '4', 'BILL005003001', '多条件查询退库出库单', 'BILL,BILL005,BILL005003,BILL005003001', '/coffeeBill/api/bill/restock/findByConditions', 3037, '\0');
-- INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3133, now(), now(), '0', '4', 'BILL005003001', '查看出库单详情', 'BILL,BILL005,BILL005003,BILL005003001', '/coffeeBill/api/bill/restock/findByRestockBillCode', 3037, '\0');                                                                                                                                   ----problems here
-- INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3134, now(), now(), '0', '4', 'BILL005003002', '审核出库单', 'BILL,BILL005,BILL005003,BILL005003002', '/coffeeBill/api/bill/restock/findByRestockBillCode', 3037, '\0');
-- INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3135, now(), now(), '0', '4', 'BILL005003003', '退库出库单修改保存', 'BILL,BILL005,BILL005003,BILL005003003', '/coffeeBill/api/bill/restock/updatePurchaseBillToSave', 3037, '\0');
-- INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3136, now(), now(), '0', '4', 'BILL005003004', '退库出库单修改提交', 'BILL,BILL005,BILL005003,BILL005003004', '/coffeeBill/api/bill/restock/updateRestockBillToSubmit', 3037, '\0');
-- INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3137, now(), now(), '0', '4', 'BILL005003005', '审核通过退库出库单', 'BILL,BILL005,BILL005003,BILL005003005', '/coffeeBill/api/bill/restock/auditSuccess', 3037, '\0');
-- INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3138, now(), now(), '0', '4', 'BILL005003006', '审核不通过退库出库单', 'BILL,BILL005,BILL005003,BILL005003006', '/coffeeBill/api/bill/restock/auditFailure', 3037, '\0');
-- 退库改 by xie_wei_guang
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3130, now(), now(), '0', '4', 'BILL005001001', '子计划多条件查询', 'BILL,BILL005,BILL005001,BILL005001001', '/coffeeBill/api/bill/restock/findPlanBillByConditions', 3035, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3131, now(), now(), '0', '4', 'BILL005001002', '单据编码子计划单个查询', 'BILL,BILL005,BILL005001,BILL005001002', '/coffeeBill/api/bill/restock/findPlanBillByBillCode', 3035, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3132, now(), now(), '0', '4', 'BILL005001003', '拣货子计划单个查询', 'BILL,BILL005,BILL005001,BILL005001003', '/coffeeBill/api/bill/restock/findPlanBillByBillCode', 3035, '\0');

INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3133, now(), now(), '0', '4', 'BILL005001004', '站点计划拣货保存', 'BILL,BILL005,BILL005001,BILL005001004', '/coffeeBill/api/bill/restock/saveRestockBill', 3035, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3134, now(), now(), '0', '4', 'BILL005001005', '站点计划拣货提交', 'BILL,BILL005,BILL005001,BILL005001005', '/coffeeBill/api/bill/restock/submitRestockBill', 3035, '\0');

INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3135, now(), now(), '0', '4', 'BILL005002001', '站点自主拣货保存', 'BILL,BILL005,BILL005002,BILL005002001', '/coffeeBill/api/bill/restock/saveRestockBill', 3036, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3136, now(), now(), '0', '4', 'BILL005002002', '站点自主拣货提交', 'BILL,BILL005,BILL005002,BILL005002002', '/coffeeBill/api/bill/restock/submitRestockBill', 3036, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3137, now(), now(), '0', '4', 'BILL005003001', '多条件查询退库出库单', 'BILL,BILL005,BILL005003,BILL005003001', '/coffeeBill/api/bill/restock/findByConditions', 3037, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3138, now(), now(), '0', '4', 'BILL005003002', '查看出库单详情', 'BILL,BILL005,BILL005003,BILL005003002', '/coffeeBill/api/bill/restock/findByRestockBillCode', 3037, '\0');                                                                                                                                   ----problems here
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3139, now(), now(), '0', '4', 'BILL005003003', '修改出库单详情', 'BILL,BILL005,BILL005003,BILL005003003', '/coffeeBill/api/bill/restock/findByRestockBillCode', 3037, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3140, now(), now(), '0', '4', 'BILL005003004', '审核出库单详情', 'BILL,BILL005,BILL005003,BILL005003004', '/coffeeBill/api/bill/restock/findByRestockBillCode', 3037, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3141, now(), now(), '0', '4', 'BILL005003005', '退库出库单修改保存', 'BILL,BILL005,BILL005003,BILL005003005', '/coffeeBill/api/bill/restock/updatePurchaseBillToSave', 3037, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3142, now(), now(), '0', '4', 'BILL005003006', '退库出库单修改提交', 'BILL,BILL005,BILL005003,BILL005003006', '/coffeeBill/api/bill/restock/updateRestockBillToSubmit', 3037, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3143, now(), now(), '0', '4', 'BILL005003007', '审核通过退库出库单', 'BILL,BILL005,BILL005003,BILL005003007', '/coffeeBill/api/bill/restock/auditSuccess', 3037, '\0');
INSERT INTO oauth_database20170828.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (3144, now(), now(), '0', '4', 'BILL005003008', '审核不通过退库出库单', 'BILL,BILL005,BILL005003,BILL005003008', '/coffeeBill/api/bill/restock/auditFailure', 3037, '\0');









