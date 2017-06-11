package com.lantushenko.experimental.stub.web.controllers;

import static org.junit.Assert.*;


import com.lantushenko.experimental.stub.dbunit.utils.DataSets;
import com.lantushenko.experimental.stub.dbunit.utils.DbUnitDataSource;
import com.lantushenko.experimental.stub.repositories.AbstractDaoTest;
import com.lantushenko.experimental.stub.web.controllers.api.user.UsersController;
import com.lantushenko.experimental.stub.web.serialize.json.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UsersControllerTests extends AbstractDaoTest {
    @Autowired
    private UsersController usersController;
    @Autowired
    private DbUnitDataSource dbUnitDataSource;

    @Test
    public void testAddUser(){
        UserDto actual = usersController.add(DataSets.User.USER1_LOGIN, "Test User", DataSets.User.USER1_PASSWORD, 100);
        assertNotNull(actual.getId());
    }
}
