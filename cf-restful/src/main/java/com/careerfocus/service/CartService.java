package com.careerfocus.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.careerfocus.dao.CartDAO;
import com.careerfocus.model.request.PaymentVO;

public class CartService {

	@Autowired
	CartDAO cartDAO;

	@Autowired
	PaymentGatewayService service;

	public boolean saveCartItems(List<Integer> itemList, int userId) {
		return cartDAO.saveCartItems(itemList, userId);
	}

	public boolean deleteCartItems(Integer bundleId, int userId) {
		return cartDAO.deleteCartItems(bundleId, userId);
	}

	public boolean purchaseBundles(HttpServletRequest request, HttpServletResponse response, List<Integer> itemList,
			Integer isCart) {
		PaymentVO paymentVO = null;
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		if (isCart == 1)
			if (cartDAO.saveCartItems(itemList, userId))
				paymentVO = cartDAO.getPurchaseDetails(itemList, userId);
		if (paymentVO != null)
			try {
				cartDAO.saveTransaction(paymentVO);
				service.doPost(request, response, paymentVO);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}

	public List<Map<String,Object>> getCartItems(int userId) {
		return cartDAO.getCartItems(userId);
	}

	public boolean getPurchaseStatus(int userId) {
		return cartDAO.getPurchaseStatus(userId);
	}

}
