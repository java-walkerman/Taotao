package org.taotao.service;

import org.taotao.common.pojo.EasyUIDataGridResult;
import org.taotao.pojo.TbItem;

public interface ItemService {
	
	TbItem getItemById(long itemId);
	
	EasyUIDataGridResult  getItemList(int page, int rows);
}
