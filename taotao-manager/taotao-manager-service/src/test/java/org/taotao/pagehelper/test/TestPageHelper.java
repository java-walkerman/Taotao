package org.taotao.pagehelper.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.taotao.mapper.TbItemMapper;
import org.taotao.pojo.TbItem;
import org.taotao.pojo.TbItemExample;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TestPageHelper {

	@Test
	public void testPageHelper() {
		// 1 在mybatis配置文件中配置分页插件
		// 2 执行查询之前配置分页条件，使用pageHelper静态方法
		PageHelper.startPage(1, 10);
		// 3 执行 查询
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbItemMapper mapper = app.getBean(TbItemMapper.class);
		TbItemExample example = new TbItemExample();
		List<TbItem> itemList = mapper.selectByExample(example);
		// 4 使用PageInfo，获取分页信息
		PageInfo<TbItem> page = new PageInfo<TbItem>(itemList);
		System.out.println("=========== page total:" + page.getTotal());
		System.out.println("=========== page pages:" + page.getPages());
		System.out.println("=========== page pageSize:" + page.getPageSize());
	}
}
