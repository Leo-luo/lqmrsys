package com.lqmrSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.DictDto;
import com.lqmrSys.bean.dto.MenuTreeDto;
import com.lqmrSys.bean.pagination.DictDataTablesReq;
import com.lqmrSys.bean.req.DictReq;
import com.lqmrSys.dao.DictDao;
import com.lqmrSys.service.DictService;

@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private DictDao dictDao;
	
	@Override
	public Res<Integer> addDict(DictReq req) {
		int result = dictDao.addDict(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyDict(DictReq req) {
		int result = dictDao.modifyDict(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delDict(int dictId) {
		int result = dictDao.delDict(dictId);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<DictDto> dictDetailById(int dictId) {
		DictDto dto = dictDao.dictDetailById(dictId);
		if(dto!=null){
			return new Res<DictDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<DictDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<DictDto> dictList(DictDataTablesReq req) {
		return dictDao.dictList(req);
	}

	@Override
	public int dictCount(DictDataTablesReq req) {
		return dictDao.dictCount(req);
	}
	
	@Override
	public List<List<MenuTreeDto>> dictMenuTreeList(String dictCodes) {
		String[] dictCode = dictCodes.split(",");
		List<List<MenuTreeDto>> returnList = new ArrayList<List<MenuTreeDto>>();
		for (int i = 0; i < dictCode.length; i++) {
			List<MenuTreeDto> dtoList = buildDcitTree(dictCode[i]);
			if(dtoList!=null && dtoList.size()>0){
				returnList.add(dtoList);
			}
		}
		return returnList;
	}
	public List<MenuTreeDto> buildDcitTree(String dictCode) {
    	List<MenuTreeDto> menulist = new ArrayList<MenuTreeDto>();
    	List<DictDto> lastList = new ArrayList<DictDto>();
    	List<DictDto> list = dictDao.dictListByDictCodeAndParentId(dictCode, 0);
        for (DictDto menu : list) {  
            if ("0".equals(menu.getParentDictId()+"")) {  
            	MenuTreeDto tree = new MenuTreeDto();
            	tree.setId(menu.getDictId()+"");
            	tree.setParent("#");
            	tree.setChildren(1);
            	tree.setLabel(menu.getContent());
            	menulist.add(tree);
                build(menu,menulist,lastList);  
            }  
        } 
        List<MenuTreeDto> listTree = new ArrayList<MenuTreeDto>();
        if(!menulist.isEmpty()){
        	for (MenuTreeDto menuTreeDto : menulist) {
        		if(!lastList.isEmpty()){
        			for (DictDto treeDto : lastList) {
						if((menuTreeDto.getId()+"").equals(treeDto.getDictId()+"")){
							menuTreeDto.setChildren(0);
						}
					}
        		}
				listTree.add(menuTreeDto);
			}
        }
        return listTree;  
    }
	private void build(DictDto menu,List<MenuTreeDto> menulist,List<DictDto> lastList){  
    	List<DictDto> children = getChildren(menu); 
        if (!children.isEmpty()) {  
        	for (int i = 0; i < children.size(); i++) {
        		DictDto sysmenu = children.get(i);
        		MenuTreeDto tree = new MenuTreeDto();
            	tree.setId(sysmenu.getDictId()+"");
            	tree.setParent(sysmenu.getParentDictId()+"");
            	tree.setLabel(sysmenu.getContent());
            	tree.setChildren(1);
            	menulist.add(tree);
            	build(sysmenu,menulist,lastList);
			}
        }else{
        	lastList.add(menu);
        }   
    }  
    private List<DictDto> getChildren(DictDto menu){  
        List<DictDto> children = new ArrayList<DictDto>(); 
        String menucode = menu.getDictId()+""; 
    	List<DictDto> list = dictDao.dictListByDictCodeAndParentId(menu.getDictCode(), menu.getDictId());
    	for (DictDto sysmenu : list) {  
            if (menucode.equals(sysmenu.getParentDictId()+"")) {  
                children.add(sysmenu);  
            }  
        } 
        return children;  
    }
}
