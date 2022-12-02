package com.xxx.common.command;

import com.xxx.common.entity.User;
import com.xxx.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

//Requester role
public class RemoteControl {

    private Command registerCommand;

    public RemoteControl(Command onTvCommand) {
        this.registerCommand = onTvCommand;
    }

    //Open
    public void register(User user){
        registerCommand.execute(user);
    }


    public static void main(String[] args) {

    }
}