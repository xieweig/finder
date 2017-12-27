package cn.sisyphe.bill.infrastructure.plan.jpa;

import cn.sisyphe.bill.domain.plan.RawMaterialDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ncmao
 * @Date 2017/12/26 9:30
 * @description
 */

@Repository
public interface RawMaterialDetailRepository extends JpaRepository<RawMaterialDetail, Long>, JpaSpecificationExecutor<RawMaterialDetail> {
}
