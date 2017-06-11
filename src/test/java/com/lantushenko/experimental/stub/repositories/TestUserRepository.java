package com.lantushenko.experimental.stub.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lantushenko.experimental.stub.dao.User;
import com.lantushenko.experimental.stub.dao.impl.UserImpl;
import com.lantushenko.experimental.stub.dbunit.utils.DataSets;
import com.lantushenko.experimental.stub.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserRepository extends AbstractDaoTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsertUser() throws Exception {

        UserImpl u = new UserImpl();
        u.setFullName("Test user");
        u.setLogin("login");
        u.setPassword("ab14b");
        u.setExpectedCalories(0);
        userRepository.insert(u);
        assertNotNull(u.getId());
    }

    @Test
    public void testFindUserByLogin() throws Exception {
        getDbUnitDataSource().insert(DataSets.User.DATASET_INSERT_USER1);
        User actual = userRepository.findUserByLogin("TestUser");
        assertEquals(DataSets.User.USER1_LOGIN, actual.getLogin());
    }
}
