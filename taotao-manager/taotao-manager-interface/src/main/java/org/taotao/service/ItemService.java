package org.taotao.service;

import org.taotao.common.pojo.EasyUIDataGridResult;
import org.taotao.common.result.JsonResult;
import org.taotao.pojo.TbItem;

public interface ItemService {

	/**
	 * 根据Id查询商品
	 * 
	 * @param itemId
	 * @return
	 */
	TbItem getItemById(long itemId);

	/**
	 * 查询商品记录，列表显示
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGridResult getItemList(int page, int rows);

	/**
	 * 添加新商品
	 * @param item
	 * @param desc
	 * @return
	 */
	JsonResult<String> createItem(TbItem item, String desc);
}
