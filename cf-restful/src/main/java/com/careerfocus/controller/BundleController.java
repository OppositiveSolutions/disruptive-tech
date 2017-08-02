package com.careerfocus.controller;

import com.careerfocus.entity.Bundle;
import com.careerfocus.service.BundleService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Response editBundle(@RequestBody Bundle bundle) throws Exception {
        return Response.ok(service.editBundle(bundle)).build();
    }

    @RequestMapping(value = "/list/{coachingType}", method = RequestMethod.GET)
    public Response getQPBundleList(@PathVariable("coachingType") Integer coachingType) throws Exception {
        return Response.ok(service.getQPBundleList(coachingType)).build();
    }
    
    @RequestMapping(value = "/{bundleId}", method = RequestMethod.GET)
    public Response getQPBundle(@PathVariable("bundleId") Integer bundleId) throws Exception {
        return Response.ok(service.getQPBundle(bundleId)).build();
    }
    
    @RequestMapping(value = "/qps/{bundleId}", method = RequestMethod.GET)
    public Response getQPBundleQPs(@PathVariable("bundleId") Integer bundleId) throws Exception {
        return Response.ok(service.getQPBundleQPs(bundleId)).build();
    }
    
    @RequestMapping(value = "/{bundleId}/addqp/{qpId}", method = RequestMethod.GET)
    public Response addQptoBundle(@PathVariable("bundleId") Integer bundleId, @PathVariable("qpId") Integer qpId) throws Exception {
        return Response.ok(service.addQptoBundle(bundleId, qpId)).build();
    }
    
    @RequestMapping(value = "/{bundleId}/removeqp/{qpId}", method = RequestMethod.DELETE)
    public Response removeQpFromBundle(@PathVariable("bundleId") Integer bundleId, @PathVariable("qpId") Integer qpId) throws Exception {
        return Response.ok(service.removeQpFromBundle(bundleId, qpId)).build();
    }
    
    @RequestMapping(value = "/delete/{bundleId}", method = RequestMethod.DELETE)
    public Response deleteBundle(@PathVariable("bundleId") Integer bundleId) throws Exception {
        return Response.ok(service.deleteBundle(bundleId)).build();
    }
    
    @RequestMapping(value = "/{userId}/purchase/{bundleId}", method = RequestMethod.GET)
    public Response purchaseBundle(@PathVariable("bundleId") Integer bundleId, @PathVariable("userId") Integer userId) throws Exception {
        return Response.ok(service.purchaseBundle(userId, bundleId)).build();
    }
}
