package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.planbill.PlanBillManager;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ncmao
 * @Date 2017/12/27 11:31
 * @description
 */
@RestController
@RequestMapping("/api/bill/planbill")
@CrossOrigin(origins = "*")
public class PlanBillController {


    @Autowired
    private PlanBillManager planBillManager;


    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseResult createProduct(@RequestBody PlanBillDTO planBillDTO) throws Exception {
        planBillManager.create(planBillDTO);
        return null;
    }
}
