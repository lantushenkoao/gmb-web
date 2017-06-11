package com.lantushenko.experimental.stub.web.controllers.api.user;

import com.lantushenko.experimental.stub.dao.User;
import com.lantushenko.experimental.stub.services.UsersService;
import com.lantushenko.experimental.stub.web.serialize.json.AjaxStatusDto;
import com.lantushenko.experimental.stub.web.serialize.json.CurrentUserDto;
import com.lantushenko.experimental.stub.web.serialize.json.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * This controller performs CRUD operations on User domain object
 * It is also responsible for providing front end with the current session information
 */
@RestController
@RequestMapping(value = {"/api/user"})
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "current", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public CurrentUserDto loadCurrent() {
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return new CurrentUserDto(usersService.findUserById(id));
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS')")
    public UserDto get(@RequestParam Long id) {
        return new UserDto(usersService.findUserById(id));
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS') " +
            "or (hasRole('ROLE_USER') && null == #userId )")
    public UserDto save(
                        @RequestParam(required = false) Long id,
                        @RequestParam String login,
                        @RequestParam String fullName,
                        @RequestParam(required = false) String password,
                        @RequestParam Integer expectedCalories){
        User user = usersService.findUserById(resolveUserId(id));
        user.setLogin(login);
        user.setFullName(fullName);
        user.setExpectedCalories(expectedCalories);

        if(StringUtils.hasText(password)){
            usersService.setPasswordNoUpdate(user, password);
        }
        usersService.save(user);

        return new UserDto(user);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS')")
    public List<UserDto> list() {
        List<UserDto> users = new ArrayList<>();
        for(User user: usersService.listUsers()){
            users.add(new UserDto(user));
        }
        return users;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS')")
    public UserDto add(@RequestParam String login,
                                @RequestParam String fullName,
                                @RequestParam String password,
                                @RequestParam Integer expectedCalories){
        User user = usersService.add(login, fullName, password, expectedCalories);
        return new UserDto(user);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS')")
    public AjaxStatusDto delete(@RequestParam Long id) throws Exception {
        User user = usersService.findUserById(id);
        usersService.delete(user);
        return new AjaxStatusDto(true, "deleted");
    }

    private Long resolveUserId(Long userId){
        if(null == userId) {
            return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        }else{
            return userId;
        }
    }
}
