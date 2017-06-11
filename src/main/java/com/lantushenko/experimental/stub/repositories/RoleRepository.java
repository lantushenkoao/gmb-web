package com.lantushenko.experimental.stub.repositories;

import java.util.List;

import com.lantushenko.experimental.stub.dao.Role;

public interface RoleRepository extends AbstractRepository {

    List<Role> getUserRoles(Integer userId);
}
