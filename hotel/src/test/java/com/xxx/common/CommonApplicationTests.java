package com.xxx.common;

import com.xxx.common.utils.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class CommonApplicationTests {

    @Test
    void contextLoads(){

    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse("2022-10-12 09:59:51");
        Date date2 = new Date();
        int i = date1.compareTo(date2);
        System.out.println(i);

    }

}
