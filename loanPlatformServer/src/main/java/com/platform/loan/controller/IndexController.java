package com.platform.loan.controller;

import com.platform.loan.dao.UserRepository;
import com.platform.loan.modle.User;
import com.platform.loan.result.IndexResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 *
 * @author caogu.wyp
 * @version $Id: IndexController.java, v 0.1 2018-03-15 下午3:55 caogu.wyp Exp $$
 */
@RestController
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/hello")
    public IndexResult hello(@RequestParam(value = "name", defaultValue = "World") String name) {

        IndexResult indexResult = new IndexResult();

        indexResult.setContent("hello world");
        indexResult.setId("001");

        return indexResult;
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public IndexResult add(User user) {

        User savedUser = userRepository.save(user);

        IndexResult indexResult = new IndexResult();
        indexResult.setContent("增加user成功" + savedUser.getId());
        return indexResult;
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public IndexResult queryById(@PathVariable Integer id) {

        Optional<User> user = userRepository.findById(id);

        IndexResult indexResult = new IndexResult();

        indexResult.setContent(user.get().getName());

        return indexResult;
    }

}
