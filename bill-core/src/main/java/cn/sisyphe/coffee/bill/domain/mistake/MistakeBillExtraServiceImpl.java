package cn.sisyphe.coffee.bill.domain.mistake;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.cargo.repo.CargoRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.rawmaterial.RawMaterialRepository;
import cn.sisyphe.coffee.bill.viewmodel.mistake.ConditionQueryMistakeBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Amy on 2018/1/18.
 *         describe：
 */
@Service
public class MistakeBillExtraServiceImpl extends AbstractBillExtraService<MistakeBill, ConditionQueryMistakeBill> implements MistakeBillExtraService {
    @Autowired
    public MistakeBillExtraServiceImpl(BillRepository<MistakeBill> billRepository) {
        super(billRepository);
    }

    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @Override
    protected void addExtraExpression(ConditionQueryMistakeBill conditionQuery, List<Expression<Boolean>> expressions, Root<MistakeBill> root, CriteriaBuilder criteriaBuilder) {
        //追加误差单类型的条件
        expressions.add(criteriaBuilder.equal(root.get("specificBillType").as(BillTypeEnum.class), BillTypeEnum.NO_PLAN));
        //目标匹配
        if (conditionQuery.obtainTarget(conditionQuery.getTargetEnumSet())) {
            //都匹配
            if (!StringUtils.isEmpty(conditionQuery.getTargetCode())) {
                //当目标编号不为空时-匹配原料的编号和货物编号
                expressions.add(
                        criteriaBuilder.or(
                                criteriaBuilder.equal(root.get("dbGoods").get("rawMaterialCode").as(String.class), conditionQuery.getTargetCode()),
                                criteriaBuilder.equal(root.get("dbGoods").get("cargoCode").as(String.class), conditionQuery.getTargetCode())
                        )
                );
            }
            if (!StringUtils.isEmpty(conditionQuery.getTargetName())) {
                //当目标名称不为空时-匹配原料编号集合和货物编号集合
                List<String> cargoCodeList = cargoRepository.findCargoCodesByCargoName(conditionQuery.getTargetName());
                List<String> rawMaterialCodeList = rawMaterialRepository.findRawMaterialCodesByRawMaterialName(conditionQuery.getTargetName());
                expressions.add(
                        criteriaBuilder.or(
                                root.get("dbGoods").get("cargoCode").as(String.class).in(cargoCodeList),
                                root.get("dbGoods").get("rawMaterialCode").as(String.class).in(rawMaterialCodeList)
                        )
                );
            }
        } else {
            //匹配其中一个
            for (BasicEnum basicEnum : conditionQuery.getTargetEnumSet()) {
                if (BasicEnum.BY_CARGO.equals(basicEnum)) {
                    if (!StringUtils.isEmpty(conditionQuery.getTargetCode())) {
                        expressions.add(criteriaBuilder.equal(root.get("dbGoods").get("cargoCode").as(String.class), conditionQuery.getTargetCode()));
                    }
                    if (!StringUtils.isEmpty(conditionQuery.getTargetName())) {
                        List<String> cargoCodeList = cargoRepository.findCargoCodesByCargoName(conditionQuery.getTargetName());
                        expressions.add(root.get("dbGoods").get("cargoCode").as(String.class).in(cargoCodeList));
                    }
                    break;
                } else if (BasicEnum.BY_MATERIAL.equals(basicEnum)) {
                    if (!StringUtils.isEmpty(conditionQuery.getTargetCode())) {
                        expressions.add(criteriaBuilder.equal(root.get("dbGoods").get("rawMaterialCode").as(String.class), conditionQuery.getTargetCode()));
                    }
                    if (!StringUtils.isEmpty(conditionQuery.getTargetName())) {
                        List<String> rawMaterialCodeList = rawMaterialRepository.findRawMaterialCodesByRawMaterialName(conditionQuery.getTargetName());
                        expressions.add(root.get("dbGoods").get("rawMaterialCode").as(String.class).in(rawMaterialCodeList));
                    }
                    break;
                }
            }
        }

        /**
         * 出库库位条件
         */
        if (!StringUtils.isEmpty(conditionQuery.getOutStorageCode())) {
            expressions.add(criteriaBuilder.equal(root.get("dbStation").get("outStorageCode").as(String.class), conditionQuery.getOutStorageCode()));
        }
        /**
         * 入库库位条件
         */
        if (!StringUtils.isEmpty(conditionQuery.getInStorageCode())) {
            expressions.add(criteriaBuilder.equal(root.get("dbStation").get("inStorageCode").as(String.class), conditionQuery.getInStorageCode()));
        }
    }
}
