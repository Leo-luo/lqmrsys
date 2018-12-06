package com.lqmrSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.DeptDto;
import com.lqmrSys.bean.dto.MenuTreeDto;
import com.lqmrSys.bean.pagination.DeptDataTablesReq;
import com.lqmrSys.bean.req.DeptReq;
import com.lqmrSys.dao.DeptDao;
import com.lqmrSys.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptDao deptDao;

	@Override
	public Res<Integer> addDept(DeptReq req) {
		int result = deptDao.addDept(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyDept(DeptReq req) {
		int result = deptDao.modifyDept(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delDept(int deptId) {
		int result = deptDao.delDept(deptId);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<DeptDto> deptDetailById(int deptId) {
		DeptDto dto = deptDao.deptDetailById(deptId);
		if(dto!=null){
			return new Res<DeptDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<DeptDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<DeptDto> deptList(DeptDataTablesReq req) {
		return deptDao.deptList(req);
	}

	@Override
	public int deptCount(DeptDataTablesReq req) {
		return deptDao.deptCount(req);
	}

	@Override
	public List<MenuTreeDto> buildDeptTree(int parentDeptId) {
    	List<MenuTreeDto> menulist = new ArrayList<MenuTreeDto>();
    	List<DeptDto> lastList = new ArrayList<DeptDto>();
    	List<DeptDto> list = deptDao.deptListByParentDeptId(parentDeptId);
        for (DeptDto menu : list) {  
            if ("0".equals(menu.getParentDeptId()+"")) {  
            	MenuTreeDto tree = new MenuTreeDto();
            	tree.setId(menu.getDeptId()+"");
            	tree.setParent("#");
            	tree.setChildren(1);
            	tree.setLabel(menu.getName());
            	menulist.add(tree);
                build(menu,menulist,lastList);  
            }  
        } 
        List<MenuTreeDto> listTree = new ArrayList<MenuTreeDto>();
        if(!menulist.isEmpty()){
        	for (MenuTreeDto menuTreeDto : menulist) {
        		if(!lastList.isEmpty()){
        			for (DeptDto treeDto : lastList) {
						if((menuTreeDto.getId()+"").equals(treeDto.getDeptId()+"")){
							menuTreeDto.setChildren(0);
						}
					}
        		}
				listTree.add(menuTreeDto);
			}
        }
        return listTree;  
    }
	private void build(DeptDto menu,List<MenuTreeDto> menulist,List<DeptDto> lastList){  
    	List<DeptDto> children = getChildren(menu); 
        if (!children.isEmpty()) {  
        	for (int i = 0; i < children.size(); i++) {
        		DeptDto sysmenu = children.get(i);
        		MenuTreeDto tree = new MenuTreeDto();
            	tree.setId(sysmenu.getDeptId()+"");
            	tree.setParent(sysmenu.getParentDeptId()+"");
            	tree.setLabel(sysmenu.getName());
            	tree.setChildren(1);
            	menulist.add(tree);
            	build(sysmenu,menulist,lastList);
			}
        }else{
        	lastList.add(menu);
        }   
    }  
    private List<DeptDto> getChildren(DeptDto menu){  
        List<DeptDto> children = new ArrayList<DeptDto>(); 
        String menucode = menu.getDeptId()+""; 
    	List<DeptDto> list = deptDao.deptListByParentDeptId(menu.getDeptId());
    	for (DeptDto sysmenu : list) {  
            if (menucode.equals(sysmenu.getParentDeptId()+"")) {  
                children.add(sysmenu);  
            }  
        } 
        return children;  
    }

}
