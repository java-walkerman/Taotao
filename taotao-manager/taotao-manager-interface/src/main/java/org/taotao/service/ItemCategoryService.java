package org.taotao.service;

import java.util.List;

import org.taotao.common.pojo.EasyUICategoryTreeNode;

public interface ItemCategoryService {
	
	List<EasyUICategoryTreeNode> getItemCategoryList(long parentId);

}
