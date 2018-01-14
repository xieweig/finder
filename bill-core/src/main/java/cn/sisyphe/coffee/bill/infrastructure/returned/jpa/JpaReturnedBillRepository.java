package cn.sisyphe.coffee.bill.infrastructure.returned.jpa;

import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ncmao
 * @Date 2017/12/27 16:01
 * @description
 */
@Repository
public interface JpaReturnedBillRepository extends JpaBillRepository<ReturnedBill> {

}
