package com.careerfocus.controller;

import com.careerfocus.entity.Achievers;
import com.careerfocus.entity.Bundle;
import com.careerfocus.service.BundleService;
import com.careerfocus.util.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by sarath on 04/06/17.
 */

@RestController
@RequestMapping("/bundle")
public class BundleController {

	@Autowired
	BundleService service;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response saveBundle(@RequestPart(value = "bundle", required = true) String bundleJson,
			@RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
		Bundle bundle = new ObjectMapper().readValue(bundleJson, Bundle.class);
		return Response.ok(service.saveBundle(bundle, image)).build();
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Response editBundle(@RequestPart(value = "bundle", required = true) String bundleJson,
			@RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
		Bundle bundle = new ObjectMapper().readValue(bundleJson, Bundle.class);
		return Response.ok(service.editBundle(bundle, image)).build();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response getBundles() throws Exception {
		return Response.ok(service.getBundles()).build();
	}
	
	@RequestMapping(value = "/delete/{bundleId}", method = RequestMethod.DELETE)
	public Response removeStudent(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("bundleId") int bundleId) throws Exception {
		return Response.ok(service.deleteBundle(bundleId)).build();
	}

	@RequestMapping(value = "/list/{coachingType}", method = RequestMethod.GET)
	public Response getQPBundleList(@PathVariable("coachingType") Integer coachingType) throws Exception {
		return Response.ok(service.getQPBundleList(coachingType)).build();
	}

	@RequestMapping(value = "/coachingtypes", method = RequestMethod.GET)
	public Response getCoachingTypeList() throws Exception {
		return Response.ok(service.getCoachingTypeList()).build();
	}

	@RequestMapping(value = "/purchased/{userId}", method = RequestMethod.GET)
	public Response getPurchasedQPBundleList(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(service.getPurchasedQPBundleList(userId)).build();
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
	public Response addQptoBundle(@PathVariable("bundleId") Integer bundleId, @PathVariable("qpId") Integer qpId)
			throws Exception {
		return Response.ok(service.addQptoBundle(bundleId, qpId)).build();
	}

	@RequestMapping(value = "/{bundleId}/removeqp/{qpId}", method = RequestMethod.DELETE)
	public Response removeQpFromBundle(@PathVariable("bundleId") Integer bundleId, @PathVariable("qpId") Integer qpId)
			throws Exception {
		return Response.ok(service.removeQpFromBundle(bundleId, qpId)).build();
	}
	
	@RequestMapping(value = "/{bundleId}/changestatus/{status}", method = RequestMethod.GET)
	public Response changeBundleStatus(@PathVariable("bundleId") Integer bundleId, @PathVariable("status") Integer status)
			throws Exception {
		return Response.ok(service.changeBundleStatus(bundleId, status)).build();
	}

	// @RequestMapping(value = "/delete/{bundleId}", method =
	// RequestMethod.DELETE)
	// public Response deleteBundle(@PathVariable("bundleId") Integer bundleId)
	// throws Exception {
	// return Response.ok(service.deleteBundle(bundleId)).build();
	// }

	@RequestMapping(value = "/delete/{bundleId}", method = RequestMethod.PUT)
	public Response deleteBundle(@PathVariable("bundleId") Integer bundleId) throws Exception {
		return Response.ok(service.deleteBundle(bundleId)).build();
	}

	@RequestMapping(value = "/purchase/{bundleId}", method = RequestMethod.GET)
	public Response purchaseBundle(HttpServletRequest request, @PathVariable("bundleId") Integer bundleId)
			throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(service.purchaseBundle(userId, bundleId)).build();
	}
	
	@RequestMapping(value = "/{bundleId}/image", method = RequestMethod.GET)
	public byte[] getAchieverImage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("bundleId") int bundleId) throws Exception {
		return service.getUserImage(bundleId);
	}
}
