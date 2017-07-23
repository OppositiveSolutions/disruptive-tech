package com.careerfocus.controller;

import com.careerfocus.service.TestService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "/{userId}/all/{type}", method = RequestMethod.GET)
    public Response getAllTests(@PathVariable("userId") int userId, @PathVariable("type") int type)
            throws Exception {
        return Response.ok(testService.getAllTests(userId, type)).build();
    }
    
}
