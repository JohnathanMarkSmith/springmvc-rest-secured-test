package com.johnathanmarksmith.mvc.web.controller;

import com.johnathanmarksmith.mvc.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * Date:   6/5/13 / 7:58 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 * <p/>
 * This is my basic controller for my web app but its going to return JSON data.
 */


@Controller
@RequestMapping("/api")
class APIController
{

    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    private MappingJacksonJsonView  jsonView = new MappingJacksonJsonView();

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public User updateCustomer(@PathVariable("id") String id, @RequestBody User user) {

        logger.debug("I am in the controller and got ID: " + id.toString());
        logger.debug("I am in the controller and got user name: " + user.toString());

        return user;
    }
}
