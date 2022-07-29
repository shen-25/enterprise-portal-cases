package com.imooc.freemarker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class FreemarkerSample1 {

	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		//1.加载模板
		//创建核心配置对象
		Configuration config = new Configuration(Configuration.VERSION_2_3_31);
		//""空字符串表示当前包
		config.setClassForTemplateLoading(FreemarkerSample1.class, "");
		//得到模板对象
		 Template t = config.getTemplate("sample1.ftl");	
		//2.创建数据
		 Map<String, Object> data = new HashMap<>();
//		 data.put("site", "百度");
//		 data.put("url", "http://www.baidu.com");
//		 data.put("site", "新浪");
//		 data.put("url", "http://www.sina.com");
		// data.put("time", new Date());
		 //data.put("number", 1000000000000.12345678910);
		 Map info = new HashMap<>();
		 info.put("cpu", "i5");
		 Computer computer = new Computer("33dd33", "huawei", new Date(), 10000.8f, info);
		 data.put("computer", computer);
		 List<Integer> list = new ArrayList<>();
		 list.add(1111);
		 list.add(2222);
		 list.add(3333);
		 data.put("list", list);
		 Map<String, String> map = new HashMap<>();
		 map.put("第一个", "first");
		 map.put("第二个", "second");
		 map.put("第三个", "third");
		 data.put("m", map);
		//3.产生输出
		 t.process(data, new OutputStreamWriter(System.out));
	

	}

}
