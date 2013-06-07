package com.johnathanmarksmith.mvc.web.controller;

import com.johnathanmarksmith.mvc.web.exception.ResourceNotFoundException;
import com.johnathanmarksmith.mvc.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
    @ResponseBody
    public User getDisplayDefault(ModelMap model)
    {

        /*

            you did not enter a name so the default is going to run

         */

        return new User("Johnathan Mark Smith", "JohnathanMarkSmith");

    }

    @ExceptionHandler(ResourceNotFoundException.class)
 //   @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException ex)
    {
        logger.warn("user requested a resource which didn't exist", ex);
        return new ModelAndView( jsonView, "error", "user requested a resource which didn't exist");


    }
}
