package org.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后台管理首页
 * 
 * @author hydra
 *
 */
@Controller
public class IndexController {
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String showIndex() {
		return "index";
	}
	
	@RequestMapping(value="/{page}",method=RequestMethod.GET)
	public String showPage(@PathVariable String page) {
		return page;
	}
}
