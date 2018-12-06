package com.lqmrSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.OperateBean;
import com.lqmrSys.bean.dto.RelationModuleDto;
import com.lqmrSys.bean.dto.UserDto;
import com.lqmrSys.bean.dto.LoginDto;
import com.lqmrSys.bean.pagination.UserDataTablesReq;
import com.lqmrSys.bean.req.RelationModuleReq;
import com.lqmrSys.bean.req.UserReq;
import com.lqmrSys.dao.ModuleDao;
import com.lqmrSys.dao.OperateDao;
import com.lqmrSys.dao.UserDao;
import com.lqmrSys.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private OperateDao operateDao;
	@Autowired
	private ModuleDao moduleDao;
	
	@Override
	public Res<LoginDto> userLogin(String account, String pwd) {
		UserDto userDto = userDao.userDetail(0, account);
		if(userDto!=null){
			if(userDto.getDataStatus()!=1){
				return new Res<LoginDto>(ResultCodeEnum.IS_DELETED.getCode(), "账号已注销", null);
			}
			String userDtoPwd = userDto.getPwd();
			if(userDtoPwd.equals(pwd)){
				LoginDto dto = new LoginDto();
				dto.setAccount(account);
				dto.setUserId(userDto.getUserId());
				dto.setName(userDto.getName());
				
				//登录操作日志
				OperateBean operate = new OperateBean();
				operate.setOperatorId(userDto.getUserId());
				operate.setOperatorName(userDto.getName());
				operate.setOperateInterface("/lqmrsys/user/login");
				operate.setOperateInterfaceName("登录");
				operateDao.addOperate(operate);
				
				return new Res<LoginDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
			}
			return new Res<LoginDto>(ResultCodeEnum.PASSWORD_ERROR.getCode(), ResultCodeEnum.PASSWORD_ERROR.getMessage(), null);
		}
		return new Res<LoginDto>(ResultCodeEnum.ACCOUNT_NOT_EXIST.getCode(), ResultCodeEnum.ACCOUNT_NOT_EXIST.getMessage(), null);
	}

	@Override
	public Res<Integer> addUser(UserReq req) {
		String account = "";
		int allUserNum = userDao.allUserNum()+1;
		if(allUserNum<=9){
			account = "200" + allUserNum;
		}else if(allUserNum<=99){
			account = "20" + allUserNum;
		}else{
			account = "2" + allUserNum;
		}
		int result = userDao.addUser(req, account, "123456");
		if(result!=0){
			//根据角色赋予人员系统权限
			List<RelationModuleDto> roleModList = moduleDao.roleModuleList2(req.getRoleId());
			if(roleModList!=null && roleModList.size()>0){
				List<RelationModuleReq> userModList = new ArrayList<RelationModuleReq>();
				for (RelationModuleDto relationModuleDto : roleModList) {
					RelationModuleReq userMod = new RelationModuleReq();
					userMod.setDataId(result);
					userMod.setModuleId(relationModuleDto.getModuleId());
					userModList.add(userMod);
				}
				moduleDao.addUserModList(userModList);
			}
			//赋予人员本身的数据权限
			List<RelationModuleReq> userDataList = new ArrayList<RelationModuleReq>();
			RelationModuleReq userData = new RelationModuleReq();
			userData.setDataId(result);
			userData.setModuleId(result);
			userDataList.add(userData);
			moduleDao.addUserDataList(userDataList);
			
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyUser(UserReq req) {
		UserDto userDto = userDao.userDetail(req.getUserId(), "");
		int oldRoleId = userDto.getRoleId();
		int result = userDao.modifyUser(req);
		if(result!=0){
			if(req.getRoleId()!=oldRoleId){
				moduleDao.delUserMod(req.getUserId());
				List<RelationModuleDto> roleModList = moduleDao.roleModuleList2(req.getRoleId());
				if(roleModList!=null && roleModList.size()>0){
					List<RelationModuleReq> userModList = new ArrayList<RelationModuleReq>();
					for (RelationModuleDto relationModuleDto : roleModList) {
						RelationModuleReq userMod = new RelationModuleReq();
						userMod.setDataId(req.getUserId());
						userMod.setModuleId(relationModuleDto.getModuleId());
						userModList.add(userMod);
					}
					moduleDao.addUserModList(userModList);
				}
			}
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delUser(int userId) {
		int result = userDao.delUser(userId);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<UserDto> userDetail(int userId) {
		UserDto dto = userDao.userDetail(userId, "");
		if(dto!=null){
			return new Res<UserDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<UserDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<UserDto> userList(UserDataTablesReq req) {
		return userDao.userList(req);
	}

	@Override
	public int userCount(UserDataTablesReq req) {
		return userDao.userCount(req);
	}

	@Override
	public Res<String> userMods(int userId) {
		List<RelationModuleDto> userModList = moduleDao.userModuleList2(userId);
		String mods = "";
		if(userModList!=null && userModList.size()>0){
			for (RelationModuleDto relationModuleDto : userModList) {
				mods = mods + relationModuleDto.getModuleId() + ",";
			}
			mods = mods.substring(0, mods.length()-1);
		}
		return new Res<String>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), mods);
	}

	@Override
	public Res<Integer> resetUserPwd(int userId) {
		int result = userDao.resetUserPwd(userId);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyUserPwd(int userId, String pwd, String oldPwd) {
		if(pwd.equals(oldPwd)){
			return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), "密码重复", 0);
		}
		UserDto dto = userDao.userDetail(userId, "");
		String userPwd = dto.getPwd();
		if(userPwd.equals(oldPwd)){
			int result = userDao.modifyUserPwd(userId, pwd);
			if(result!=0){
				return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
			}else{
				return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), 0);
			}
		}else{
			return new Res<Integer>(ResultCodeEnum.PASSWORD_ERROR.getCode(), ResultCodeEnum.PASSWORD_ERROR.getMessage(), 0);
		}
	}

}
