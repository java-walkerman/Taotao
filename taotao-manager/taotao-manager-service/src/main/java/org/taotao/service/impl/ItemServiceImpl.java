package org.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taotao.common.pojo.EasyUIDataGridResult;
import org.taotao.mapper.TbItemMapper;
import org.taotao.pojo.TbItem;
import org.taotao.pojo.TbItemExample;
import org.taotao.service.ItemService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 商品管理 Service
 * 
 * @author hydra
 *
 */

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Override
	public TbItem getItemById(long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> itemList = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(itemList);

		EasyUIDataGridResult result = new EasyUIDataGridResult(pageInfo.getTotal(), pageInfo.getList());

		return result;
	}

}
