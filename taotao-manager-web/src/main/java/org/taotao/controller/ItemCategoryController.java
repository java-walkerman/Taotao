package org.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.taotao.common.pojo.EasyUICategoryTreeNode;
import org.taotao.service.ItemCategoryService;

/**
 * 商品分类管理 Controller
 * 
 * @author hydra
 *
 */
@Controller
public class ItemCategoryController {

	@Autowired
	private ItemCategoryService itemCategoryService;

	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUICategoryTreeNode> getItemCateList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
		return itemCategoryService.getItemCategoryList(parentId);
	}
}
