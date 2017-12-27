package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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


    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseResult createProduct() throws Exception {
        return null;
    }
}
