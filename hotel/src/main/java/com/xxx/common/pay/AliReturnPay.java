package com.xxx.common.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AliReturnPay implements Serializable {
    private static final long serialVersionUID = 8683949075381016639L;

    private String app_id;

    private String out_trade_no;

    private String sign;

    private String trade_status;

    private String trade_no;

    private String total_amount;

    private String real_name;

    private String subject;

    private String store_id;

    private String timeout_express;

    private Map<String,Object> goods_detail;

    public static enum Action {
        goods_id,
        goods_name,
        STATE,
    }

}
