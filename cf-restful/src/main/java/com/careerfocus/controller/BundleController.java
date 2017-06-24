package com.careerfocus.controller;

import com.careerfocus.entity.Bundle;
import com.careerfocus.service.BundleService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sarath on 04/06/17.
 */

@RestController
@RequestMapping("/bundle")
public class BundleController {

    @Autowired
    BundleService service;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response saveBundle(@RequestBody Bundle bundle) throws Exception {
        return Response.ok(service.saveBundle(bundle)).build();
    }

}
