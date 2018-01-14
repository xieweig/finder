package cn.sisyphe.coffee.bill.application.base;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * heyong
 * manager 工厂
 */
@Component
public class BillManagerFactory implements ApplicationContextAware {

    private static Map<BillTypeEnum, AbstractBillManager> managerMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, AbstractBillManager> map = applicationContext.getBeansOfType(AbstractBillManager.class);
        managerMap = new HashMap<>(10);

        map.forEach((key, value) -> managerMap.put(value.billType(), value));
    }

    @SuppressWarnings("unchecked")
    public static <T extends AbstractBillManager> T getManager(BillTypeEnum billTypeEnum){
        AbstractBillManager manager = managerMap.get(billTypeEnum);

        if (manager == null){
            throw new DataException("001", "对象创建失败");
        }

        return (T) manager;
    }
}
