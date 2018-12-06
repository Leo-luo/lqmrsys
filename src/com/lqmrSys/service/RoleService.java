package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.RoleDto;
import com.lqmrSys.bean.pagination.RoleDataTablesReq;
import com.lqmrSys.bean.req.RoleReq;

public interface RoleService {
	//新增角色
	public Res<Integer> addRole(RoleReq req);
	//修改角色
	public Res<Integer> modifyRole(RoleReq req);
	//逻辑删除角色
	public Res<Integer> delRole(int roleId);
	//角色信息
	public Res<RoleDto> roleDetailById(int roleId);
	//角色列表
	public List<RoleDto> roleList(RoleDataTablesReq req);
	//角色列表长度
	public int roleCount(RoleDataTablesReq req);
}
