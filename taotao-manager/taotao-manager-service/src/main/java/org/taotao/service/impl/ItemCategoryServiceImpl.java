package org.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taotao.common.pojo.EasyUICategoryTreeNode;
import org.taotao.mapper.TbItemCatMapper;
import org.taotao.pojo.TbItemCat;
import org.taotao.pojo.TbItemCatExample;
import org.taotao.pojo.TbItemCatExample.Criteria;
import org.taotao.service.ItemCategoryService; 

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUICategoryTreeNode> getItemCategoryList(long parentId) {
		// PageHelper.startPage(page, rows);
		TbItemCatExample example = new TbItemCatExample();
		// 组装查询条件
		Criteria criteria = example.createCriteria();
		// parent_id =:parentId
		criteria.andParentIdEqualTo(parentId);

		List<TbItemCat> itemList = itemCatMapper.selectByExample(example);
		List<EasyUICategoryTreeNode> nodeList = new ArrayList<EasyUICategoryTreeNode>();
		for (TbItemCat itemCat : itemList) {
			EasyUICategoryTreeNode node = new EasyUICategoryTreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent() ? "closed" : "open");
			nodeList.add(node);
		}
		return nodeList;
	}

}
