/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DO TAN TRUNG
 */
public class NganLuong {
    String nganluong_url = "https://sandbox.nganluong.vn:8088/nl35/checkout.php";
    String merchant_site_code = "47538";	//thay mã merchant site mà b?n dã dang ký vào dây
    String secure_pass = "b915a1c6a150c70e1877030d50fc2c25";
    String return_url = "http://localhost:8080/nl_return";
    String receiver = "dttr278@gmail.com";
    String transaction_info="", order_code, price, currency, order_description="", affiliate_code="", buyer_info="";
    float quantity=1, tax=0, discount=0, fee_cal=0, fee_shipping=0;

    public NganLuong setNganluong_url(String nganluong_url) {
        this.nganluong_url = nganluong_url;
        return this;
    }

    public NganLuong setMerchant_site_code(String merchant_site_code) {
        this.merchant_site_code = merchant_site_code;
        return this;
    }

    public NganLuong setSecure_pass(String secure_pass) {
        this.secure_pass = secure_pass;
        return this;
    }

    public NganLuong setReturn_url(String return_url) {
        this.return_url = return_url;
        return this;
    }

    public NganLuong setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public NganLuong setTransaction_info(String transaction_info) {
        this.transaction_info = transaction_info;
        return this;
    }

    public NganLuong setOrder_code(String order_code) {
        this.order_code = order_code;
        return this;
    }

    public NganLuong setPrice(String price) {
        this.price = price;
        return this;
    }

    public NganLuong setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public NganLuong setOrder_description(String order_description) {
        this.order_description = order_description;
        return this;
    }

    public NganLuong setAffiliate_code(String affiliate_code) {
        this.affiliate_code = affiliate_code;
        return this;
    }

    public NganLuong setBuyer_info(String buyer_info) {
        this.buyer_info = buyer_info;
        return this;
    }

    public NganLuong setQuantity(float quantity) {
        this.quantity = quantity;
        return this;
    }

    public NganLuong setTax(float tax) {
        this.tax = tax;
        return this;
    }

    public NganLuong setDiscount(float discount) {
        this.discount = discount;
        return this;
    }

    public NganLuong setFee_cal(float fee_cal) {
        this.fee_cal = fee_cal;
        return this;
    }

    public NganLuong setFee_shipping(float fee_shipping) {
        this.fee_shipping = fee_shipping;
        return this;
    }
    
    String GetMD5Hash(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            digest = "";
            // Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
            // null, ex);
        } catch (NoSuchAlgorithmException ex) {
            // Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
            // null, ex);
            digest = "";
        }
        return digest;
    }

    public String buildCheckoutUrlNew() throws UnsupportedEncodingException {
        //order_description = "Bình nước & Lock Lock 1.2 lít";
        //buyer_info = "Họ tên người mua *|* Địa chỉ Email *|* Điện thoại *|* Địa chỉ nhận hàng";// "Họ tên người mua *|* Địa chỉ Email *|* Điện thoại *|* Địa chỉ nhận hàng";
        String str_return_url = return_url.toLowerCase();
        String security_code = "";
        security_code += "" + merchant_site_code;
        security_code += " " + str_return_url;
        security_code += " " + receiver;//tài khoản ngân lượng
        security_code += " " + transaction_info;
        security_code += " " + order_code;
        security_code += " " + price;
        security_code += " " + currency;//hỗ trợ 2 loại tiền tệ currency usd,vnd
        security_code += " " + quantity;//số lượng mặc định 1
        security_code += " " + tax;
        security_code += " " + discount;
        security_code += " " + fee_cal;
        security_code += " " + fee_shipping;
        security_code += " " + order_description;
        String payinfo = "";// Convert.ToString("Họ tên người mua *|* Địa chỉ Email *|* Điện thoại *|* Địa chỉ nhận hàng");
        security_code += " " + buyer_info;
        security_code += " " + affiliate_code;
        security_code += " " + secure_pass;
        //return security_code;
        String md5 = GetMD5Hash(security_code);

        //security_code += " " + md5;
        Map<String, Object> ht = new HashMap<String, Object>();

        ht.put("merchant_site_code", merchant_site_code);
        ht.put("return_url", URLEncoder.encode(str_return_url, "UTF-8").toLowerCase());
        ht.put("receiver", URLEncoder.encode(receiver, "UTF-8"));
        ht.put("transaction_info", transaction_info);
        ht.put("order_code", order_code);
        ht.put("price", price);
        ht.put("currency", currency);
        ht.put("quantity", quantity);
        ht.put("tax", tax);
        ht.put("discount", discount);
        ht.put("fee_cal", fee_cal);
        ht.put("fee_shipping", fee_shipping);
        ht.put("order_description", URLEncoder.encode(order_description, "UTF-8"));
        ht.put("buyer_info", URLEncoder.encode(buyer_info, "UTF-8"));// "Họ tên người mua *|* Địa chỉ Email *|* Điện thoại *|* Địa chỉ nhận hàng");// "Họ tên người mua *|* Địa chỉ Email *|* Điện thoại *|* Địa chỉ nhận hàng");
        ht.put("affiliate_code", affiliate_code);
        ht.put("secure_code", md5);
        // T?o url redirect

        String redirect_url = nganluong_url;

        if (redirect_url.indexOf("?") == -1) {
            redirect_url += "?";
        } else if (redirect_url.substring(redirect_url.length() - 1, 1) != "?" && redirect_url.indexOf("&") == -1) {
            redirect_url += "&";
        }

        String url = "";

        // Duy?t các ph?n t? trong m?ng bam ht1 d? t?o redirect url
        List fieldNames = new ArrayList(ht.keySet());
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            if (url == "") {
                url += fieldName + "=" + ht.get(fieldName);
            } else {
                url += "&" + fieldName + "=" + ht.get(fieldName);
            }
        }
        String rdu = redirect_url + url;
        return rdu;
    }

    public  Boolean verifyPaymentUrl(String transaction_info, String order_code, String price, String payment_id, String payment_type, String error_text, String secure_code) throws UnsupportedEncodingException {
        String str = "";

        str += " " + URLEncoder.encode(transaction_info, "UTF-8");

        str += " " + order_code;

        str += " " + price;

        str += " " + payment_id;

        str += " " + payment_type;

        str += " " + URLEncoder.encode(error_text, "UTF-8");

        str += " " + merchant_site_code;

        str += " " + secure_pass;

        // Mã hóa các tham s?
        String verify_secure_code = "";

        verify_secure_code = GetMD5Hash(str);

        // Xác th?c mã c?a ch? web v?i mã tr? v? t? nganluong.vn
        if (verify_secure_code.equals(secure_code)) {
            return true;
        }

        return false;
    }
}
