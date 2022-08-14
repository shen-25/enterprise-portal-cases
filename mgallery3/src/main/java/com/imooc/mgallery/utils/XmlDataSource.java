package com.imooc.mgallery.utils;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
/**
 * 数据类，将xml文件解析为java对象
 */
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.catalina.startup.SetAllPropertiesRule;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.imooc.mgallery.entity.Painting;

public class XmlDataSource {
  
	private static List<Painting> data = new ArrayList<>();
	private static String dataFile;
	
	private static void reload() {
		URLDecoder decoder = new URLDecoder();
		try {
			dataFile = decoder.decode(dataFile, "UTF-8");
			//利用dom4j对XML进行解析
			SAXReader reader = new SAXReader();
			//获取document对象
			Document document = (Document)reader.read(dataFile);
			List<Node> nodes = ((Node) document).selectNodes("/root/painting");
			data.clear();
			for(Node node: nodes) {
				Element element = (Element)node;
				Painting painting = new Painting();
				String id = element.attributeValue("id");
				painting.setId(Integer.parseInt(id));
				String pname = element.elementText("pname");
				painting.setpname(pname);
				painting.setCategroy(Integer.parseInt(element.elementText("category")));
				painting.setPrice(Integer.parseInt(element.elementText("price")));
				painting.setPreview(element.elementText("preview"));
				painting.setDescription(element.elementText("description"));
				//System.out.println(id + "--" + pname);
				data.add(painting);
			}
			 	
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static {
		//获取最原始的painting.xml的磁盘位置
		dataFile = XmlDataSource.class.getResource("/painting.xml").getPath();
		//dataFile = "F:\\eclipse\\eclipse-workspace3\\mgallery\\src\\main\\java\\painting.xml";
		//System.out.println("磁盘" + dataFile);
		//将特殊的字符解析
		System.out.println("file" + dataFile);
		reload();		
	}
	
	public static List<Painting> getRawData(){
		return data;
	}
	/**
	public static void append(Painting painting) throws DocumentException {
		//读取xml文档，得到Document对象
		SAXReader reader = new SAXReader();
		Writer writer = null;
		try {
			Document document = reader.read(dataFile);
			
			Element root = document.getRootElement();
			Element p = root.addElement("painting");
			p.addAttribute("id", String.valueOf(data.size() + 1));
			p.addElement("pname").setText(painting.getpname());
			p.addElement("category").setText(painting.getCategory().toString());
			p.addElement("price").setText(painting.getPrice().toString());
			p.addElement("preview").setText(painting.getPreview());
			p.addElement("description").setText(painting.getDescription());
			writer = new OutputStreamWriter(new FileOutputStream(dataFile), "UTF-8");
			try {
				   document.write(writer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			reload();
			
		}
	}
	**/

	public static void append(Painting painting) {
		//1.读取XML文档得到Document对象
		SAXReader reader = new SAXReader();
		Writer writer = null;
		try {
			Document document = reader.read(dataFile);
			//2.创建新的painting节点
			Element root = document.getRootElement();
			Element p = root.addElement("painting");
			//3.创建painting节点的各个子节点
			p.addAttribute("id", String.valueOf(data.size()+1));
			p.addElement("pname").setText(painting.getPname());
			p.addElement("category").setText(painting.getCategory().toString());
			
			p.addElement("price").setText(painting.getPrice().toString());
		    p.addElement("preview").setText(painting.getPreview());
		    p.addElement("description").setText(painting.getDescription());
		    //4.写入XML，完成追加操作
		    writer = new OutputStreamWriter(new FileOutputStream(dataFile),"UTF-8");
		    document.write(writer);
		    System.out.println(dataFile);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(writer != null) {
				try {
					writer.close();	
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			reload();	//内存与文件顺序一致
		}
	}
    
	public static void update(Painting painting) {
		SAXReader reader = new SAXReader();
		Writer writer = null;
		try {
			Document document = reader.read(dataFile);
			//节点路径[@属性名=属性值]
			//root/painting[@id=x]
		List<Node> nodes = document.selectNodes("/root/painting[@id=" + painting.getId() + "]");
		if(nodes.size() == 0) {
			throw new RuntimeException("id=" + painting.getId() + "编号不存在");
		}
		Element p = (Element)nodes.get(0);
		p.selectSingleNode("pname").setText(painting.getpname());
		p.selectSingleNode("category").setText(painting.getCategory().toString());
		p.selectSingleNode("price").setText(painting.getPrice().toString());
		p.selectSingleNode("preview").setText(painting.getPreview().toString());
		p.selectSingleNode("description").setText(painting.getDescription().toString());
		 writer = new OutputStreamWriter(new FileOutputStream(dataFile));
		document.write(writer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			reload();
		}
		
	}
	
	public static void delete(Integer id)
	{
		SAXReader reader = new SAXReader();
		Writer  writer = null;
		Document document;
		try {
			document = reader.read(dataFile);
			List<Node> nodes = document.selectNodes("/root/painting[@id=" + id + "]");
			if(nodes.size() == 0) {
				throw new RuntimeException("id=" + id + "编号油画不存在");
			}
			Element p = (Element)nodes.get(0);
			p.getParent().remove(p);
			writer = new OutputStreamWriter(new FileOutputStream(dataFile),"UTF-8");
			document.write(writer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		   if(writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   reload();
		}
	
		
	}
	public static void main(String[] args) throws DocumentException  {
         List<Painting> ps = XmlDataSource.getRawData();
      /** Painting painting = new Painting();
         painting.setpname("dd");
         painting.setCategory(1);
         painting.setPrice(23423);
         painting.setPreview("/upload/1.png");
         painting.setDescription("测试用的");
         XmlDataSource.append(painting);
         System.out.println(ps);**/
         //这步还没有完，因为内存和外存不一致，还需要重新读取
	}
	
}
