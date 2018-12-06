package com.lqmrSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.MenuTreeDto;
import com.lqmrSys.bean.dto.RelationModuleDto;
import com.lqmrSys.bean.req.ModuleReq;
import com.lqmrSys.bean.req.RelationModuleReq;
import com.lqmrSys.dao.ModuleDao;
import com.lqmrSys.service.ModuleService;
@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleDao moduleDao;

	@Override
	public List<MenuTreeDto> buildModuleTree(int parentModuleId) {
		List<MenuTreeDto> menulist = new ArrayList<MenuTreeDto>();
    	List<RelationModuleDto> lastList = new ArrayList<RelationModuleDto>();
    	List<RelationModuleDto> list = moduleDao.moduleList(parentModuleId);
        for (RelationModuleDto menu : list) {  
            if ("0".equals(menu.getParentModuleId()+"")) {  
            	MenuTreeDto tree = new MenuTreeDto();
            	tree.setId(menu.getModuleId()+"");
            	tree.setParent("#");
            	tree.setChildren(1);
            	tree.setLabel(menu.getModuleName());
            	menulist.add(tree);
            	buildMod(menu,menulist,lastList);  
            }  
        } 
        List<MenuTreeDto> listTree = new ArrayList<MenuTreeDto>();
        if(!menulist.isEmpty()){
        	for (MenuTreeDto menuTreeDto : menulist) {
        		if(!lastList.isEmpty()){
        			for (RelationModuleDto treeDto : lastList) {
						if((menuTreeDto.getId()+"").equals(treeDto.getModuleId()+"")){
							menuTreeDto.setChildren(0);
						}
					}
        		}
				listTree.add(menuTreeDto);
			}
        }
        return listTree;  
	}
	private void buildMod(RelationModuleDto menu,List<MenuTreeDto> menulist,List<RelationModuleDto> lastList){  
    	List<RelationModuleDto> children = geModChildren(menu); 
        if (!children.isEmpty()) {  
        	for (int i = 0; i < children.size(); i++) {
        		RelationModuleDto sysmenu = children.get(i);
        		MenuTreeDto tree = new MenuTreeDto();
            	tree.setId(sysmenu.getModuleId()+"");
            	tree.setParent(sysmenu.getParentModuleId()+"");
            	tree.setLabel(sysmenu.getModuleName());
            	tree.setChildren(1);
            	menulist.add(tree);
            	buildMod(sysmenu,menulist,lastList);
			}
        }else{
        	lastList.add(menu);
        }   
    }  
    private List<RelationModuleDto> geModChildren(RelationModuleDto menu){  
        List<RelationModuleDto> children = new ArrayList<RelationModuleDto>(); 
        String menucode = menu.getModuleId()+""; 
    	List<RelationModuleDto> list = moduleDao.moduleList(menu.getModuleId());
    	for (RelationModuleDto sysmenu : list) {  
            if (menucode.equals(sysmenu.getParentModuleId()+"")) {  
                children.add(sysmenu);  
            }  
        } 
        return children;  
    }

	@Override
	public List<MenuTreeDto> buildRoleModuleTree(int roleId, int parentModuleId) {
		List<MenuTreeDto> menulist = new ArrayList<MenuTreeDto>();
    	List<RelationModuleDto> lastList = new ArrayList<RelationModuleDto>();
    	List<RelationModuleDto> list = moduleDao.roleModuleList(roleId, parentModuleId);
        for (RelationModuleDto menu : list) {  
            if ("0".equals(menu.getParentModuleId()+"")) {  
            	MenuTreeDto tree = new MenuTreeDto();
            	tree.setId(menu.getModuleId()+"");
            	tree.setParent("#");
            	tree.setChildren(1);
            	tree.setLabel(menu.getModuleName());
            	menulist.add(tree);
            	buildRoleMod(menu,menulist,lastList,roleId);  
            }  
        } 
        List<MenuTreeDto> listTree = new ArrayList<MenuTreeDto>();
        if(!menulist.isEmpty()){
        	for (MenuTreeDto menuTreeDto : menulist) {
        		if(!lastList.isEmpty()){
        			for (RelationModuleDto treeDto : lastList) {
						if((menuTreeDto.getId()+"").equals(treeDto.getModuleId()+"")){
							menuTreeDto.setChildren(0);
						}
					}
        		}
				listTree.add(menuTreeDto);
			}
        }
        return listTree;  
	}
	private void buildRoleMod(RelationModuleDto menu,List<MenuTreeDto> menulist,List<RelationModuleDto> lastList, int roleId){  
    	List<RelationModuleDto> children = getRoleModChildren(menu, roleId); 
        if (!children.isEmpty()) {  
        	for (int i = 0; i < children.size(); i++) {
        		RelationModuleDto sysmenu = children.get(i);
        		MenuTreeDto tree = new MenuTreeDto();
            	tree.setId(sysmenu.getModuleId()+"");
            	tree.setParent(sysmenu.getParentModuleId()+"");
            	tree.setLabel(sysmenu.getModuleName());
            	tree.setChildren(1);
            	menulist.add(tree);
            	buildRoleMod(sysmenu,menulist,lastList,roleId);
			}
        }else{
        	lastList.add(menu);
        }   
    }  
    private List<RelationModuleDto> getRoleModChildren(RelationModuleDto menu, int roleId){  
        List<RelationModuleDto> children = new ArrayList<RelationModuleDto>(); 
        String menucode = menu.getModuleId()+""; 
    	List<RelationModuleDto> list = moduleDao.roleModuleList(roleId, menu.getModuleId());
    	for (RelationModuleDto sysmenu : list) {  
            if (menucode.equals(sysmenu.getParentModuleId()+"")) {  
                children.add(sysmenu);  
            }  
        } 
        return children;  
    }

	@Override
	public Res<Integer> modifyUserMod(ModuleReq req) {
		moduleDao.delUserMod(req.getDataId());
		List<RelationModuleReq> modList = new ArrayList<RelationModuleReq>();
		String[] moduleId = req.getModuleIds().split(",");
		for (String string : moduleId) {
			RelationModuleReq mod = new RelationModuleReq();
			mod.setDataId(req.getDataId());
			mod.setModuleId(Integer.valueOf(string));
			modList.add(mod);
		}
		if(moduleDao.addUserModList(modList)){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), modList.size());
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), 0);
	}

	@Override
	public List<MenuTreeDto> buildUserModuleTree(int userId, int parentModuleId) {
		List<MenuTreeDto> menulist = new ArrayList<MenuTreeDto>();
    	List<RelationModuleDto> lastList = new ArrayList<RelationModuleDto>();
    	List<RelationModuleDto> list = moduleDao.userModuleList(userId, parentModuleId);
        for (RelationModuleDto menu : list) {  
            if ("0".equals(menu.getParentModuleId()+"")) {  
            	MenuTreeDto tree = new MenuTreeDto();
            	tree.setId(menu.getModuleId()+"");
            	tree.setParent("#");
            	tree.setChildren(1);
            	tree.setLabel(menu.getModuleName());
            	menulist.add(tree);
            	buildUserMod(menu,menulist,lastList,userId);  
            }  
        } 
        List<MenuTreeDto> listTree = new ArrayList<MenuTreeDto>();
        if(!menulist.isEmpty()){
        	for (MenuTreeDto menuTreeDto : menulist) {
        		if(!lastList.isEmpty()){
        			for (RelationModuleDto treeDto : lastList) {
						if((menuTreeDto.getId()+"").equals(treeDto.getModuleId()+"")){
							menuTreeDto.setChildren(0);
						}
					}
        		}
				listTree.add(menuTreeDto);
			}
        }
        return listTree;  
	}
	private void buildUserMod(RelationModuleDto menu,List<MenuTreeDto> menulist,List<RelationModuleDto> lastList, int userId){  
    	List<RelationModuleDto> children = getUserModChildren(menu, userId); 
        if (!children.isEmpty()) {  
        	for (int i = 0; i < children.size(); i++) {
        		RelationModuleDto sysmenu = children.get(i);
        		MenuTreeDto tree = new MenuTreeDto();
            	tree.setId(sysmenu.getModuleId()+"");
            	tree.setParent(sysmenu.getParentModuleId()+"");
            	tree.setLabel(sysmenu.getModuleName());
            	tree.setChildren(1);
            	menulist.add(tree);
            	buildUserMod(sysmenu,menulist,lastList,userId);
			}
        }else{
        	lastList.add(menu);
        }   
    }  
    private List<RelationModuleDto> getUserModChildren(RelationModuleDto menu, int roleId){  
        List<RelationModuleDto> children = new ArrayList<RelationModuleDto>(); 
        String menucode = menu.getModuleId()+""; 
    	List<RelationModuleDto> list = moduleDao.roleModuleList(roleId, menu.getModuleId());
    	for (RelationModuleDto sysmenu : list) {  
            if (menucode.equals(sysmenu.getParentModuleId()+"")) {  
                children.add(sysmenu);  
            }  
        } 
        return children;  
    }

	@Override
	public Res<String> userDataStr(int userId) {
		return new Res<String>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), moduleDao.userDataStr(userId));
	}

	@Override
	public Res<Integer> modifyUserData(ModuleReq req) {
		moduleDao.delUserData(req.getDataId());
		String[] moduleIds = req.getModuleIds().split(",");
		List<RelationModuleReq> list = new ArrayList<RelationModuleReq>();
		for (String string : moduleIds) {
			RelationModuleReq re = new RelationModuleReq();
			re.setDataId(req.getDataId());
			re.setModuleId(Integer.valueOf(string));
			list.add(re);
		}
		if(moduleDao.addUserDataList(list)){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), list.size());
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), 0);
	}
	@Override
	public List<RelationModuleDto> moduleList(int parentModuleId) {
		return moduleDao.moduleList(parentModuleId);
	}
}
