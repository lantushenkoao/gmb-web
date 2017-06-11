package com.lantushenko.experimental.stub.web.controllers.api.unauthorised;

import com.lantushenko.experimental.stub.dao.User;
import com.lantushenko.experimental.stub.services.UsersService;
import com.lantushenko.experimental.stub.web.serialize.json.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller exposes actions available for unauthorised users.
 */
@RestController
@RequestMapping(value = {"/api/unauthorised"})
public class UnauthorisedController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public UserDto registerUser(@RequestParam String login,
                                @RequestParam String fullName,
                                @RequestParam String password,
                                @RequestParam Integer expectedCalories){
        User user = usersService.add(login, fullName, password, expectedCalories);
        return new UserDto(user);
    }
}
