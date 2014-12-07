/*   File Name - RequestControler
 *
 *   (C) Copyright 2013-2014 hSenid Software International (Pvt) Limited.
 *   All Rights Reserved.
 *
 *   These materials are unpublished, proprietary, confidential source code of
 *   hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
 *   of hSenid Software International (Pvt) Limited.
 *
 *   hSenid Software International (Pvt) Limited retains all title to and intellectual
 *   property rights in these materials.
 *
 */

import hms.model.User;
import hms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ishara on 12/7/14.
 */
@Controller
@RequestMapping("/request")
public class RequestController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getRequest() {
        Map response = new HashMap();
        try {
            System.out.println("request proceed");
            /*List<User> userList = userService.finAll();
            System.out.println("user list:"+userList);
            response.put("responseContext", userList);*/
        } catch (Exception e) {
            response.put("responseContext", "Internal Error");
        }
        return response;
    }
}
