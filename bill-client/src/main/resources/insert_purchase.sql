--二级菜单
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (2001, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '2', 'BILL002', '进货', 'BILL,BILL002', 'app.bill.purchase', 2000 , '\0');
--三级菜单
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (2002, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL002001', '进货录单', 'BILL,BILL002,BILL002001', 'app.bill.procurement.add', 2001, '\0');
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (2003, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL002002', '查询进货入库单', 'BILL,BILL002,BILL002002', 'app.bill.procurement.list', 2001, '\0');
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (2004, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL002003', '查看进货入库单', 'BILL,BILL002,BILL002003', 'app.bill.procurement.look', 2001, '\0');
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (2005, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL002004', '修改进货入库单', 'BILL,BILL002,BILL002004', 'app.bill.procurement.edit', 2001, '\0');
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (2006, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL002005', '审核进货入库单', 'BILL,BILL002,BILL002005', 'app.bill.procurement.audit', 2001, '\0');
--四级菜单权限
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (2007, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL002001001', '保存', 'BILL,BILL002,BILL002001,BILL002001001', '/api/bill/purchase/savePurchaseBill', 2002, '\0');
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`) VALUES (2008, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL002001002', '提交审核', 'BILL,BILL002,BILL002001,BILL002001002', '/api/bill/purchase/submitPurchaseBill', 2002, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (2009, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL002002001', '高级分页查询', 'BILL,BILL002,BILL002002,BILL002002001', '/api/bill/purchase/findByConditions', 2003, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (2010, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL002003001', '导出进货单', 'BILL,BILL002,BILL002003,BILL002003001', '/api/bill/purchase/downLoad', 2004, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (2011, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL002004001', '保存', 'BILL,BILL002,BILL002004,BILL002004001', '/api/bill/purchase/updatePurchaseBillToSave', 2005, '\0');
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (2012, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL002004002', '提交审核', 'BILL,BILL002,BILL002004,BILL002004002', '/api/bill/purchase/updatePurchaseBillToSubmit', 2005, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (2013, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL002005001', '审核不通过', 'BILL,BILL002,BILL002005,BILL002005001', '/api/bill/purchase/auditFailure', 2006, '\0');
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (2014, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL002005002', '审核通过', 'BILL,BILL002,BILL002005,BILL002005002', '/api/bill/purchase/auditSuccess', 2006, '\0');


---二级菜单(运单)
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (9001, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '9', 'BILL009', '运单跟踪', 'BILL,BILL009', 'app.bill.trace', 9000 , '\0');

--三级菜单(运单)
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (9002, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '9', 'BILL009001', '运单列表', 'BILL,BILL009,BILL009001', 'app.bill.trace.list', 9001, '\0');


INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (9003, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '9', 'BILL009002', '添加运单', 'BILL,BILL009,BILL009002', 'app.bill.trace.add', 9001, '\0');

--四级菜单权限(运单)
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (9004, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '9', 'BILL009001001', '查询', 'BILL,BILL009,BILL009001,BILL009001001', '/api/waybill/findWayBillByConditions', 9002, '\0');


INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (9005, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '9', 'BILL009001002', '添加', 'BILL,BILL009,BILL009001,BILL009001002', '/api/waybill/createWayBill', 9003, '\0');


INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (9006, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '9', 'BILL009001003', '修改', 'BILL,BILL009,BILL009001,BILL009001003', '/api/waybill/updateWayBill', 9002, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (9007, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '9', 'BILL009001004', '查看', 'BILL,BILL009,BILL009001,BILL009001004', '/api/waybill/findOneWayBill', 9002, '\0');


INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (9008, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '9', 'BILL009001005', '运单确认收货', 'BILL,BILL009,BILL009001,BILL009001005', '/api/waybill/confirmReceiptBill', 9002, '\0');
