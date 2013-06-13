package com.johnathanmarksmith.mvc.web.controller;

import com.johnathanmarksmith.mvc.web.exception.ResourceNotFoundException;
import com.johnathanmarksmith.mvc.web.model.User;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/json")
class JSonController
{

    private static final Logger logger = LoggerFactory.getLogger(JSonController.class);

    private MappingJacksonJsonView  jsonView = new MappingJacksonJsonView();


    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseBody
    public User getName(@PathVariable String name, ModelMap model) throws ResourceNotFoundException
    {

        logger.debug("I am in the controller and got user name: " + name);

        /*

            Simulate a successful lookup for 2 users, this is where your real lookup code would go

         */

        if ("JohnathanMarkSmith".equals(name))
        {
            return new User("Johnathan Mark Smith", name);
        }

        if ("Regan".equals(name))
        {
            return new User("Regan Smith", name);
        }

        throw new ResourceNotFoundException("User Is Not Found");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<User> getDisplayDefault(ModelMap model)
    {
        return new ResponseEntity<User>(new User("Johnathan Mark Smith", "JohnathanMarkSmith"), HttpStatus.OK);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorHolder> handle(ResourceNotFoundException e) {
        logger.warn("The resource was not found", e);
        return new ResponseEntity<ErrorHolder>(new ErrorHolder("Uh oh"), HttpStatus.NOT_FOUND);
    }


    class ErrorHolder {
        public String errorMessage;
        @JsonCreator
        public ErrorHolder(@JsonProperty("errorMessage") String errorMessage)
        {
            this.errorMessage = errorMessage;
        }

    }
}
