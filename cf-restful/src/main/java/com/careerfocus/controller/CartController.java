package com.careerfocus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.service.CartService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService service;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response saveCartItems(HttpServletRequest request, @RequestBody List<Integer> itemList) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(service.saveCartItems(itemList, userId)).build();
	}
	
	@RequestMapping(value = "/{bundleId}", method = RequestMethod.DELETE)
	public Response deleteCartItems(HttpServletRequest request, @PathVariable("bundleId") Integer bundleId) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(service.deleteCartItems(bundleId, userId)).build();
	}
	
	@RequestMapping(value = "/purchase/{isCart}", method = RequestMethod.POST)
	public Response saveCartItems(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Integer> itemList, @PathVariable("isCart") Integer isCart) throws Exception {
		return Response.ok(service.purchaseBundles(request, response, itemList, isCart)).build();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Response getCartItems(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(service.getCartItems(userId)).build();
	}
	
	@RequestMapping(value = "/purchase/status", method = RequestMethod.GET)
	public Response getPurchaseStatus(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(service.getPurchaseStatus(userId)).build();
	}

}
