package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.LoginDto;
import com.lqmrSys.bean.dto.UserDto;
import com.lqmrSys.bean.pagination.UserDataTablesReq;
import com.lqmrSys.bean.req.UserReq;

public interface UserService {
	//人员登录
	public Res<LoginDto> userLogin(String account, String pwd);
	//新增人员
	public Res<Integer> addUser(UserReq req);
	//修改人员信息
	public Res<Integer> modifyUser(UserReq req);
	//逻辑删除用户信息
	public Res<Integer> delUser(int userId);
	//人员信息
	public Res<UserDto> userDetail(int userId);
	//人员列表
	public List<UserDto> userList(UserDataTablesReq req);
	//人员列表长度
	public int userCount(UserDataTablesReq req);
	//人员权限串
	public Res<String> userMods(int userId);
	//重置用户密码
	public Res<Integer> resetUserPwd(int userId);
	//修改用户密码
	public Res<Integer> modifyUserPwd(int userId, String pwd, String oldPwd);
}
