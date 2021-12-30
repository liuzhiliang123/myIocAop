package com.lzl.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工厂类，生产对象
 */
public class BeanFactory {

    /**
     * 1：读取解析xml，通过反射技术实例化对象饼且存储代用（map集合）
     * 2：对外获取实例对象的借口（根据id获取）
     */

    private static Map<String,Object> map = new HashMap<>();//存储对象

    static {
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("bean.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> beanList = rootElement.selectNodes("//bean");
            for (int i = 0; i < beanList.size(); i++) {
                Element o = beanList.get(i);
                String id = o.attributeValue("id");
                String aClass = o.attributeValue("class");
                Class<?> aClass1 = Class.forName(aClass);
                Object o1 = aClass1.newInstance();
                map.put(id,o1);
            }

            // 实例化完成之后维护对象的依赖关系，检查哪些对象需要传值进入，根据它的配置，我们传入相应的值
            // 有property子元素的bean就有传值需求
            List<Element> list = rootElement.selectNodes("//property");
            for (int i = 0; i < list.size(); i++) {
                Element element = list.get(i);
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");
                Element parent = element.getParent();
                String parentId = parent.attributeValue("id");
                Object parentObject = map.get(parentId);
                Method[] methods = parentObject.getClass().getMethods();
                for (Method method : methods) {
                    if(method.getName().equalsIgnoreCase("set"+name)){
                        method.invoke(parentObject,map.get(ref));
                    }
                }
                map.put(parentId,parentObject);
                System.out.println(map.toString());
            }

        } catch (DocumentException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static Object getBean(String id){
        return map.get(id);
    }
}
