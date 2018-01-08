---wrote by xie_wei_guang
---二级菜单(退库)
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5001, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '2', 'BILL005', '退库', 'BILL,BILL005', 'app.bill.restock', 5000 , '\0');


--三级菜单(退库 14 除去2个添加货物)
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5002, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005001', '查询站点退库计划', 'BILL,BILL005,BILL005001', 'app.bill.restock.planSearch', 5001, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5003, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005002', '站点退库拣货', 'BILL,BILL005,BILL005002', 'app.bill.restock.selfPick', 5001, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5004, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005003', '站点退库计划拣货（按货物或原料）', 'BILL,BILL005,BILL005003', 'app.bill.restock.stationPick', 5001, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5005, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005004', '查询退库出库单', 'BILL,BILL005,BILL005004', 'app.bill.restock.outSearch', 5001, '\0');

--INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
--VALUES (5006, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005005', '查看退库出库单', 'BILL,BILL005,BILL005005', 'app.bill.restock.outView', 5001, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5007, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005006', '修改退库出库单', 'BILL,BILL005,BILL005006', 'app.bill.restock.outEdit', 5001, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5008, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005007', '审核退库出库单', 'BILL,BILL005,BILL005007', 'app.bill.restock.outCheck', 5001, '\0');

--入库
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5009, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005008', '查询退库入库单', 'BILL,BILL005,BILL005008', 'app.bill.restock.inSearch', 5001, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5010, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005009', '查看退库入库单', 'BILL,BILL005,BILL005009', 'app.bill.restock.inView', 5001, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5011, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005010', '退库到货调拨', 'BILL,BILL005,BILL005010', 'app.bill.restock.inAction', 5001, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5012, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005011', '查询退库调拨单', 'BILL,BILL005,BILL005011', 'app.bill.restock.inActionSearch', 5001, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5013, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '3', 'BILL005012', '查看退库调拨单', 'BILL,BILL005,BILL005012', 'app.bill.restock.inActionView', 5001, '\0');


---四级菜单
--第一组 查看计划
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5014, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005001001', '子计划多条件查询', 'BILL,BILL005,BILL005001,BILL005001001', '/api/bill/restock/findPlanBillByConditions', 5002, '\0');
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
VALUES (5015, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005001002', '子计划单个查询', 'BILL,BILL005,BILL005001,BILL005001002', '/api/bill/restock/findPlanBillByBillCode', 5002, '\0');

--第二组 退货拣货
--003-004
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (5016, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005002001', '站点拣货保存', 'BILL,BILL005,BILL005002,BILL005002001', '/api/bill/restock/saveRestockBill', 5003, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (5017, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005002002', '站点拣货提交', 'BILL,BILL005,BILL005002,BILL005002002', '/api/bill/restock/submitRestockBill', 5003, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (5018, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005003001', '计划拣货保存（按货物或原料）', 'BILL,BILL005,BILL005003,BILL005003001', '/api/bill/restock/saveRestockBill', 5004, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (5019, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005003002', '计划拣货提交（按货物或原料）', 'BILL,BILL005,BILL005003,BILL005003002', '/api/bill/restock/submitRestockBill', 5004, '\0');


--第三组
-- 查询-页面 BILL005004 右上多条件查询 列表头三个操作
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (5022, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005004001', '多条件查询退库出库单', 'BILL,BILL005,BILL005004,BILL005004001', '/api/bill/restock/findByConditions', 5005, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (5023, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005004002', '查看-根据入库单编码查询', 'BILL,BILL005,BILL005004,BILL005004002', '/api/bill/restock/findByRestockBillCode', 5005, '\0');



-- BILL005006 修改界面
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (5026, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005006001', '修改进货单据信息--保存', 'BILL,BILL005,BILL005006,BILL005006001', '/api/bill/restock/updateRestockBillToSave', 5007, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (5027, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005006002', '修改出库单据信息--提交审核', 'BILL,BILL005,BILL005006,BILL005006002', '/api/bill/restock/updateRestockBillToSubmit', 5007, '\0');


-- BILL005007 页面审核
INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (5028, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005007001', '审核通过', 'BILL,BILL005,BILL005007,BILL005007001', '/api/bill/restock/auditSuccess', 5008, '\0');

INSERT INTO `oauth_database`.`jurisdiction` (`jurisdiction_id`, create_time, update_time, version, `depth`, `jurisdiction_code`, `jurisdiction_name`, `path`, `url_address`, `parent_id`, `need_token`)
 VALUES (5029, '2017-12-14 18:47:12', '2017-12-14 18:47:14', '0', '4', 'BILL005007002', '审核不通过', 'BILL,BILL005,BILL005007,BILL005007002', '/api/bill/restock/auditFailure', 5008, '\0');
