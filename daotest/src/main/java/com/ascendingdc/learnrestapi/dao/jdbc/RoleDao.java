
package com.ascendingdc.learnrestapi.dao.jdbc;

import com.ascendingdc.learnrestapi.entity.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String name);
    Role save(Role role);
    boolean delete(Role role);
    List<Role> findAllRoles();
}
