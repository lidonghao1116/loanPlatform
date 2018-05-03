package com.platform.loan.controller;

/**
* Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
*/

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.platform.loan.result.IndexResult;

/**
 *
 * @author caogu.wyp
 * @version $Id: IndexController.java, v 0.1 2018-03-15 下午3:55 caogu.wyp Exp $$
 */
@RestController
public class IndexController {

    @RequestMapping("/hello")
    public IndexResult hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        IndexResult indexResult = new IndexResult();

        indexResult.setContent("hello world");
        indexResult.setId("001");

        //do something

        return indexResult;
    }

}
