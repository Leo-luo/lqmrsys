package com.lqmrSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.RelationModuleDto;
import com.lqmrSys.bean.dto.RoleDto;
import com.lqmrSys.bean.pagination.RoleDataTablesReq;
import com.lqmrSys.bean.req.RelationModuleReq;
import com.lqmrSys.bean.req.RoleReq;
import com.lqmrSys.dao.ModuleDao;
import com.lqmrSys.dao.RoleDao;
import com.lqmrSys.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ModuleDao moduleDao;

	@Override
	public Res<Integer> addRole(RoleReq req) {
		int result = roleDao.addRole(req);
		if(result!=0){
//			moduleDao.delRoleMod(req.getRoleId());
			List<RelationModuleReq> modList = new ArrayList<RelationModuleReq>();
			String[] moduleId = req.getModuleIds().split(",");
			for (String string : moduleId) {
				RelationModuleReq mod = new RelationModuleReq();
				mod.setDataId(result);
				mod.setModuleId(Integer.valueOf(string));
				modList.add(mod);
			}
			if(moduleDao.addRoleModList(modList)){
				return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
			}
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyRole(RoleReq req) {
		int result = roleDao.modifyRole(req);
		if(result!=0){
			moduleDao.delRoleMod(req.getRoleId());
			List<RelationModuleReq> modList = new ArrayList<RelationModuleReq>();
			String[] moduleId = req.getModuleIds().split(",");
			for (String string : moduleId) {
				RelationModuleReq mod = new RelationModuleReq();
				mod.setDataId(req.getRoleId());
				mod.setModuleId(Integer.valueOf(string));
				modList.add(mod);
			}
			if(moduleDao.addRoleModList(modList)){
				return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
			}
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delRole(int roleId) {
		int result = roleDao.delRole(roleId);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<RoleDto> roleDetailById(int roleId) {
		RoleDto dto = roleDao.roleDetailById(roleId);
		if(dto!=null){
			String moduleIds = "";
			List<RelationModuleDto> modules = moduleDao.roleModuleList2(roleId);
			if(modules!=null && modules.size()>0){
				for (int i = 0; i < modules.size(); i++) {
					moduleIds = moduleIds+modules.get(i).getModuleId()+",";
				}
				moduleIds = moduleIds.substring(0, moduleIds.length());
			}
			dto.setModuleIds(moduleIds);
			return new Res<RoleDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<RoleDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<RoleDto> roleList(RoleDataTablesReq req) {
		return roleDao.roleList(req);
	}

	@Override
	public int roleCount(RoleDataTablesReq req) {
		return roleDao.roleCount(req);
	}

}
