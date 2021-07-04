package org.study.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.crowd.entity.Role;
import org.study.crowd.entity.RoleExample;
import org.study.crowd.mapper.RoleMapper;
import org.study.crowd.service.api.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
		
		// 1.开启分页功能
		PageHelper.startPage(pageNum, pageSize);
		
		// 2.执行查询
		List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
		
		// 3.封装为PageInfo对象返回
		return new PageInfo<>(roleList);
	}

	@Override
	public void saveRole(Role role) {
		roleMapper.insert(role);
	}

	@Override
	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKey(role);
	}

	@Override
	public void removeRole(List<Integer> roleIdList) {
		
		RoleExample example = new RoleExample();
		
		RoleExample.Criteria criteria = example.createCriteria();
		
		//delete from t_role where id in (5,8,12)
		criteria.andIdIn(roleIdList);
		
		roleMapper.deleteByExample(example);
	}

}
