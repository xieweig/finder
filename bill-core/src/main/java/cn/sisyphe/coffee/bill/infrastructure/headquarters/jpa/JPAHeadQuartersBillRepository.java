package cn.sisyphe.coffee.bill.infrastructure.headquarters.jpa;

import cn.sisyphe.coffee.bill.domain.headquarters.HeadQuarterBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ncmao
 * @Date 2017/12/25 17:09
 * @description
 */

@Repository
public interface JPAHeadQuartersBillRepository extends JpaRepository<HeadQuarterBill, Long>, JpaSpecificationExecutor<HeadQuarterBill> {
}
