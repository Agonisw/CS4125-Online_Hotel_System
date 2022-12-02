package com.xxx.common.pay;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.xxx.common.entity.Car;
import com.xxx.common.entity.Order;
import com.xxx.common.entity.Product;
import com.xxx.common.mapper.CarMapper;
import com.xxx.common.mapper.OrderMapper;
import com.xxx.common.mapper.ProductMapper;
import com.xxx.common.utils.TimeUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class PayController {

    private final Map<String , List<Car>> carMap = new HashMap<>();

    @Autowired
    private WebSocket webSocket;
    private static final String URL = "https://openapi.alipaydev.com/gateway.do";

    private static final String APP_ID = "2021000117631561";

    private static final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCRMNLTogEJedSNApyvwnb/8v82QSQq9U4vhNrEiqcr9g3cFc+vX5DV2+99yvjPzJGRWoVpRImA1ayQHzzMi9MUIzYlUI+NYCYYL4yw5Z7oYCs/blWYLo3Dvjxiu/XL/+8zqe4vgF25Ceo+qda/Qt2cFwDgZqa9qIDllZnVXj9oDopdmIduclO3jSlca8Z1uKYu8FxtFOpyvqLnauVNrs/CsBl6hG0wZr9/odUEDffesEk7M4pfrytvvdxkaiUBtkhMVRjUdT3OhN5chiK7g4UyPNXcnm/MSb6GFVWWmpRn8PmtOBukTMrk0WGh8trZgOIxeaKs2kRxGERlFXEYHlLBAgMBAAECggEAMNIfAkTGXn5V53iKYVtdkmEOShsLY1qLCNV+jnolDlY1gNzgshLLa+4BkOXwVkduZ1bt52DBb83psaifVBRsTKgua8u0CZDcHmPWxiDgFzlHMZQTOhHlX32XZofXddpAf7nTOqvKWA8qchm/+R4t0G+3vr0lsj534Ts1uBZx8bXzzmfpVOnzmkWwbqdbUFctyN2lRr1HTHUNmxusu1MdvcM4e5icqSlA5W71gCZ1NX/tbr+xLunGlq4t6RTrP8Z5zwWBbkh4OHQEcMLexCsNxfJZX3B3CqR5BraBISJmvRMteJuViv117vjK8CvB1kNwUGQ8vy3Pml2lU/7y9B4gAQKBgQD6u3GkA6HyzzQp7XEio1z3rXoYym+5Zt8sGKTDHafbu0WIlBHuDNMQ4lUxCQ6U8JAANPX6jNXRlE4VS4ZBqX6dn1LMPtyZZOlo7RASeDjnAoxojAw+Pc3wn67fYIcDZrApsQ8yXRSWx0/rntuS2D4YZYGsVKvcZc3LEcSYXt/6gQKBgQCUPbpMHDAhSpCEnc8WvSbV1AGST8aowsaXe+VxWDwZdbT1Ev8mcne+Mj2KGM6Oh1PFlXCMgXw5hbIOdNPmQ5PZ3oQojSnhrjmfgKOMhBeb7r/rjqc82M1VxKuhU1JJgmj3KNByhjqqz5tAWrhfKDKBoo9Q+V6wQJMMPGpzXbO4QQKBgQDg53ZeKqwAAwDFgyjyaxsfR8q3pg5lffM9uLVCF73UCb2rbzZOE87X3E2SeI9N5iMlLFxgbCf3oFISI/a/u2vJs3ovn0t9hNkht1OtXY2dGdochVMOGZ468uGHmSxCTsBCBYKeKIQpY4s6zPokVDgO5i5VCHZIckEQoFV6X9AFAQKBgQCM+vOM5JXlY1tzs/052+PuIbTo59O/tZVWha7cj5SAhg/1rv5XGy/EA1XBWvw+UncaBphGF/MDZaHD29mWOJiI7/Tx9+ZnyeDlT1q7DPjDBFkjdcSYunr1YZf0UiCE5nBa9kbfU8bBobU9i8miPcFixi3UTYR+zLrMPTzjwK5lwQKBgFTjombsVk0gcwiBRxLef2DAkonh4Jg/Nlv3utxnnCCO//i6ExeHfY/pdQB1p4t5LL+ZqC0N0QHevd8OK95zwRMx8Omp0fmAkphRIAFU7PYum4P3BWn/pxe4mpgtmr9ukCEtfwp61/a3VNNPUsGDsSw4Ij3cYViNcBrDe3Uq6Vkk";

    private static final String FORMAT = "json";

    private static final String CHARSET = "UTF-8";

    private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0c20RtglybVKXV5oLdIMxwyMcj/OuMhveudgH9Y0BP1nUcxxUk4nExQpsajBVyX8KTtOzczsKN5q4WFojX1wkRaWGK2TJ0PxFC7jcEsugaXvSGv77gGeCZDQ6v8a1tzzoD5Mf/81TqdlfZ10sOaXSjNKMBdBPq3lJ0MD7GXE9V+8+3zNhAtWno9AKA7DO9etTxHboJDnfjtRgn+VDJyPZ44s8bQaNjdZbZnahbOl0/AKjBL687k6iqX3FY7FbrdaCDalwCVstiK4AQyhZXSXbDgsleM6dN/eWYh2P6ip+X/H1Uab6HOlfGu2W/2xgKM2cppG9dUp7ySVnwEeOTII1wIDAQAB";

    private static final String SIGN_TYPE = "RSA2";

    @ApiOperation(value = "sandboxPay")
    @PostMapping("/sandboxPay")
    public String sandboxPay(@RequestBody Car car) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(URL,APP_ID,APP_PRIVATE_KEY,FORMAT,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
        System.out.println(alipayClient.toString());

        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();

        alipayRequest.setNotifyUrl("http://panshenwu.natapp1.cc/common/call");
        long startTs = System.currentTimeMillis();
        JSONObject bizContent = new JSONObject();
        long l = System.currentTimeMillis();
        bizContent.put("out_trade_no", car.getOrderNum());
        assert false;

        bizContent.put("total_amount", car.getTotalPrice());
        bizContent.put("subject", "testProduct");


// Detailed information of goods, as required
        JSONArray goodsDetail = new JSONArray();
        JSONObject goods1 = new JSONObject();
        goods1.put("goods_id", "goodsNo1");
        goods1.put("goods_name", "子商品1");
        goods1.put("quantity", 1);
        goods1.put("price", 0.01);
        goodsDetail.add(goods1);
        bizContent.put("goods_detail", goodsDetail);
        alipayRequest.setBizContent(bizContent.toJSONString());
        AlipayTradePrecreateResponse response = alipayClient.execute (alipayRequest);
        System.out.println(response.getOutTradeNo());
        carMap.put(response.getOutTradeNo(),car.getCarList());
        return response.getQrCode();
    }

    @Autowired
    private CarMapper carMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;

    @ApiOperation(value = "call")
    @PostMapping("/call")
    public void call(HttpServletRequest request, HttpServletResponse response, AliReturnPay aliReturnPay) throws IOException {

        response.setContentType("type=text/html;charset=UTF-8");
        String orderNo = aliReturnPay.getOut_trade_no(); // Get the order number and modify the data
        System.out.println(aliReturnPay);
        System.out.println(orderNo);
        System.out.println(carMap);
        List<Car> carList = carMap.get(orderNo);
        if (("TRADE_SUCCESS").equals(aliReturnPay.getTrade_status())){
            for (Car car:carList){
                //Insert order table
                Order order = new Order();
                order.setOrderNum(UUID.randomUUID().toString().replace("-", ""));
                order.setProductId(car.getProductId());
                order.setUserId(car.getUserId());
                order.setIsDelivery("1");
                order.setCreateTime(TimeUtil.getCurrentTime());
                order.setTotalPrice(car.getTotalPrice());
                order.setSpecName(car.getSpecName());
                order.setCount(car.getCount());
                orderMapper.insert(order);

                Product product = productMapper.selectById(car.getProductId());
                product.setCount(product.getCount() + car.getCount());
                productMapper.updateById(product);

                carMapper.deleteById(car.getId());

                //Delete Shopping cart List
            }
            webSocket.sendMessage("true");
        }
    }



}
