package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.DeptDto;
import com.lqmrSys.bean.dto.MenuTreeDto;
import com.lqmrSys.bean.pagination.DeptDataTablesReq;
import com.lqmrSys.bean.req.DeptReq;

public interface DeptService {
	//新增部门
	public Res<Integer> addDept(DeptReq req);
	//修改部门
	public Res<Integer> modifyDept(DeptReq req);
	//逻辑删除部门
	public Res<Integer> delDept(int deptId);
	//部门信息
	public Res<DeptDto> deptDetailById(int deptId);
	//部门列表
	public List<DeptDto> deptList(DeptDataTablesReq req);
	//部门列表长度
	public int deptCount(DeptDataTablesReq req);
	//部门列表树
	public List<MenuTreeDto> buildDeptTree(int parentDeptId);
}
