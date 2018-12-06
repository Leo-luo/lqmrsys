package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.MenuTreeDto;
import com.lqmrSys.bean.dto.RelationModuleDto;
import com.lqmrSys.bean.req.ModuleReq;

public interface ModuleService {
	//权限列表树
	public List<MenuTreeDto> buildModuleTree(int parentModuleId);
	//角色权限列表树
	public List<MenuTreeDto> buildRoleModuleTree(int roleId, int parentModuleId);
	//更新人员权限
	public Res<Integer> modifyUserMod(ModuleReq req);
	//人员权限列表树
	public List<MenuTreeDto> buildUserModuleTree(int userId, int parentModuleId);
	//人员数据权限串
	public Res<String> userDataStr(int userId);
	//更新人员数据权限
	public Res<Integer> modifyUserData(ModuleReq req);
	//权限列表
	public List<RelationModuleDto> moduleList(int parentModuleId);
}
