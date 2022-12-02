package com.xxx.common.factory;

public class NoVipOrder implements OrderInterface {

    @Override
    public double getMsg(Integer count) {
        return 0.95;
    }

}
