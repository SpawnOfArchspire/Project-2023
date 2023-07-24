package com.ascendingdc.learnrestapi.service;

import com.ascendingdc.learnrestapi.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto getRoleByName(String name);

    List<RoleDto> getAllRoles();
}
