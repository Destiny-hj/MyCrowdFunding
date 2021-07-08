package org.study.crowd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.crowd.entity.Menu;
import org.study.crowd.entity.MenuExample;
import org.study.crowd.mapper.MenuMapper;
import org.study.crowd.service.api.MenuService;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> getAll() {
		return menuMapper.selectByExample(new MenuExample());
	}

	@Override
	public void saveMenu(Menu menu) {
		menuMapper.insert(menu);
	}

	@Override
	public void updateMenu(Menu menu) {
		
		// 由于pid没有传入，一定要使用有选择的更新，保证“pid”字段不会被置空
		menuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public void removeMenu(Integer id) {
		menuMapper.deleteByPrimaryKey(id);
	}

}
