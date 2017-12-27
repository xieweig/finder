package cn.sisyphe.bill.infrastructure.plan.jpa;

import cn.sisyphe.bill.domain.plan.CargoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ncmao
 * @Date 2017/12/26 9:29
 * @description
 */

@Repository
public interface CargoDetailRepository extends JpaRepository<CargoDetail, Long>, JpaSpecificationExecutor<CargoDetail> {
}
