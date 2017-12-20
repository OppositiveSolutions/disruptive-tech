package com.careerfocus.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.careerfocus.model.request.PaymentVO;
import com.careerfocus.util.PaymentGatewayUtil;

@Repository
public class CartDAO {

	@Autowired
	private JdbcTemplate template;

	public boolean saveCartItems(List<Integer> itemList, int userId) {
		try {
			String query = "INSERT INTO cart (`bundle_id`,`user_id`,`added_date`) VALUES (?,?,now())";
			for (int bundleId : itemList)
				template.update(query, bundleId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteCartItems(int bundleId, int userId) {
		try {
			String query = "UPDATE cart SET is_complete = 3 WHERE bundle_id = ? AND user_id = ?";
			template.update(query, bundleId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public PaymentVO getPurchaseDetails(List<Integer> itemList, int userId) {
		PaymentVO paymentVO = new PaymentVO();
		String productInfo = "";
		float amount = (float) 0.00;
		for (int bundleId : itemList) {
			String query = "select * from bundle WHERE bundle_id = ?";
			Map<String, Object> bundle = template.queryForMap(query, bundleId);
			amount += (float) bundle.get("selling_price");
			productInfo += "bId:" + bundleId + ";";
		}
		paymentVO.setAmount(amount);
		paymentVO.setProductinfo(productInfo);;
		paymentVO.setTxnid(PaymentGatewayUtil.randomAlphaNumeric(27));
		paymentVO.setService_provider("payu_paisa");
		paymentVO.setFurl("");
		paymentVO.setSurl("");
		paymentVO.setKey("");
		String query = "select * from user WHERE user_id = ?";
		Map<String, Object> bundle = template.queryForMap(query, userId);
//		paymentVO.setEmail(email);
//		paymentVO.setFirstname(firstname);
//		paymentVO.setPhone(phone);
		return paymentVO;
	}

	public List<Map<String, Object>> getCartItems(int userId) {
		String query = "SELECT * FROM cart WHERE user_id = ?";
		return template.queryForList(query, userId);
	}

	public boolean getPurchaseStatus(int userId) {
		String query = "SELECT * FROM cart WHERE user_id = ? AND (is_complete = 0 OR is_complete = 2)";
		return template.queryForList(query, userId).size() > 0 ? false : true;
	}

	public void saveTransaction(PaymentVO paymentVO) {
		// TODO Auto-generated method stub

	}

}
