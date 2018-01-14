package cn.sisyphe.coffee.bill.infrastructure.base.jpa;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaBillRepository<T extends Bill> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    /**
     * 根据单号查询
     * @param billCode
     * @return
     */
    T findOneByBillCode(String billCode);

    /**
     * 根据来源单号查询
     * @param sourceCode
     * @return
     */
    T findOneBySourceCode(String sourceCode);

    /**
     * 根据发起单号发查
     * @param rootCode
     * @return
     */
    T findOneByRootCode(String rootCode);

    /**
     * 复杂查询
     * @param ta
     * @param pageable
     * @return
     */
    Page<T> findAll(Specification<T> ta, Pageable pageable );
}
