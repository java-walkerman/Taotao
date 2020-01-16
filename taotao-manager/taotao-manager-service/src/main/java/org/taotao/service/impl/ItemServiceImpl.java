package org.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taotao.common.helper.CommonHelper.CommonHelper;
import org.taotao.common.pojo.EasyUIDataGridResult;
import org.taotao.common.result.JsonResult;
import org.taotao.mapper.TbItemDescMapper;
import org.taotao.mapper.TbItemMapper;
import org.taotao.pojo.TbItem;
import org.taotao.pojo.TbItemDesc;
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

	@Autowired
	private TbItemDescMapper itemDescMapper;

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

	@Override
	public JsonResult<String> createItem(TbItem item, String desc) {
		// 生成商品ID
		long itemId = CommonHelper.generateId();
		// 组装item的属性
		item.setId(itemId);
		// '商品状态，1-正常，2-下架，3-删除',
		item.setStatus((byte) 1);
		// item.setCreated(new Date());
		// item.setUpdated(new Date());
		// 向商品列表插入数据
		int itemInsert = itemMapper.insert(item);
		if (itemInsert <= 0) {
			return JsonResult.failure("商品创建失败");
		}

		// 创建一个商品描述表对应的pojo
		TbItemDesc itemDesc = new TbItemDesc();
		// 组装pojo的属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		// 向商品描述表插入数据
		int descInsert = itemDescMapper.insert(itemDesc);
		if (descInsert <= 0) {
			return JsonResult.failure("商品描述创建失败");
		}
		// return 结果
		return JsonResult.success("商品创建成功！");
	}

}
