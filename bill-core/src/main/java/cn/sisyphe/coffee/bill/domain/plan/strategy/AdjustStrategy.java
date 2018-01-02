package cn.sisyphe.coffee.bill.domain.plan.strategy;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ncmao
 *         计划单转成调剂单策略
 */

@Service
public class AdjustStrategy extends AbstractCastableStrategy {
    @Override
    public void cast(PlanBillPayload planBillPayload, BillRepository billRepository) {
        StationType outStationType = obtainStationTypeByLocation(planBillPayload.getOutLocation());
        StationType inStationType = obtainStationTypeByLocation(planBillPayload.getInLocation());
        getAdjustSpecStrategy(outStationType, inStationType);
    }

    /**
     * 计划单-调剂转为相应的计划单
     * @param outStationType
     * @param inStationType
     * @return
     */
    private AbstractCastableStrategy getAdjustSpecStrategy(StationType outStationType, StationType inStationType) {
        Switcher<AbstractCastableStrategy> switcher = new Switcher<AbstractCastableStrategy>()
                .addCase(obtainAdjustStoreType(), new AdjustToAdjustStrategy())
                .addCase(obtainAdjustLogisticsType(), new AdjustToAdjustStrategy())
                .addCase(obtainDeliveryType(), new AdjustToDeliveryStrategy())
                .addCase(obtainRestockType(), new AdjustToRestockStrategy());

        return switcher.exec(outStationType, inStationType);
    }

    private List<StationType> obtainAdjustStoreType() {
        List<StationType> stationTypeList = new ArrayList<>();
        stationTypeList.add(StationType.STORE);
        stationTypeList.add(StationType.STORE);
        return stationTypeList;
    }

    private List<StationType> obtainAdjustLogisticsType() {
        List<StationType> stationTypeList = new ArrayList<>();
        stationTypeList.add(StationType.LOGISTICS);
        stationTypeList.add(StationType.LOGISTICS);
        return stationTypeList;
    }

    private List<StationType> obtainDeliveryType() {
        List<StationType> stationTypeList = new ArrayList<>();
        stationTypeList.add(StationType.LOGISTICS);
        stationTypeList.add(StationType.STORE);
        return stationTypeList;
    }

    private List<StationType> obtainRestockType() {
        List<StationType> stationTypeList = new ArrayList<>();
        stationTypeList.add(StationType.STORE);
        stationTypeList.add(StationType.LOGISTICS);
        return stationTypeList;
    }

    /**
     * 获取出入站点的类型
     *
     * @param location
     * @return
     */
    private StationType obtainStationTypeByLocation(AbstractLocation location) {
        if (location instanceof Station && StationType.LOGISTICS.equals(((Station) location).getStationType())) {
            return StationType.LOGISTICS;
        } else if (location instanceof Station && StationType.STORE.equals(((Station) location).getStationType())) {
            return StationType.STORE;
        } else if (location instanceof Supplier) {
            return StationType.SUPPLIER;
        }
        return null;
    }
}
