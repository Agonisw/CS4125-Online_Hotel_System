package com.xxx.common.command;

import com.xxx.common.entity.User;

public class RegisterCommand implements Command{

    private Register register;

    public RegisterCommand(Register register) {
        this.register = register;
    }

    @Override
    public void execute(User user) {
        register.onRegister(user);
    }
}