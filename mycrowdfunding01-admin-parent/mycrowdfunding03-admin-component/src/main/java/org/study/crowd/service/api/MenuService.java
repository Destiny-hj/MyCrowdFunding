package org.study.crowd.service.api;

import org.study.crowd.entity.Menu;

import java.util.List;


public interface MenuService {
	
	List<Menu> getAll();

	void saveMenu(Menu menu);

	void updateMenu(Menu menu);

	void removeMenu(Integer id);

}