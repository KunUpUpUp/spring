package com.seasugar.bean;


import com.seasugar.anno.DI;
import com.seasugar.anno.MyService;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class AnnotationApplicationContext implements ApplicationContext {

    // 设置一个容器
    private static Map<Class, Object> beanFactory = new HashMap<Class, Object>();

    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }

    // 根据包路径，设置包扫描规则
    // 当前包及其子包，哪个类有@MyService注解，把这个类通过反射实例化
    public AnnotationApplicationContext(String basePackage) {
        // 类注入容器
        // 1 把.替换成 \
        String packagePath = basePackage.replaceAll("\\.", "\\\\");
        // 2 获取包的绝对路径
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                // 3
                contextTest(basePackage);
            }

            // 容器中的对象是否含有注入对象
            // 如果注入对象没有加入容器，也不应注入，而是报错
            for (Map.Entry<Class, Object> entry : beanFactory.entrySet()) {
                // 这里为什么不直接使用key呢？因为有key有接口
                Class clazz = entry.getValue().getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(DI.class)) {
                        field.setAccessible(true);
                        try {
                            // 就算扫到注释了@DI的属性，如果没有加入IoC中，field也无法set，结果就是不注入IoC的属性无法自动注入
                            // set(Object obj, Object value)
                            // e.g. set(person, "Alice") obj：field所属对象，value：想要赋的值
                            // field.getType()是获取field的class，用beanFactory获取其对象
                            // 如果注入的对象不在IoC容器中，那么就会赋空值，导致NLP，所以想要依赖注入就必须将相应的属性注入容器
                            field.set(entry.getValue(), beanFactory.get(field.getType()));
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void contextTest(String basePackage) {
        // 1 把.替换成 \
        String packagePath = basePackage.replaceAll("\\.", "\\\\");
        // 2 获取包的绝对路径
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                // 3
                loadFiles(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadFiles(String filePath) {
        File file = new File(filePath);
        // 存在才执行操作，不存在跳出递归
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File file1 : files) {
                    loadFiles(file1.getAbsolutePath());
                }
            } else {
                // 是文件的就进行反射操作
                // 查找带@MyService注解的类
                // 设置项目的根目录路径
                String baseDir = "com" + System.getProperty("file.separator");
                // 1. 找到 "com/" 部分
                String relativePath = file.getPath().substring(file.getPath().indexOf(baseDir));
                // 2. 替换目录分隔符为 '.'，并去掉 ".class" 后缀，获得全限类名
                String className = relativePath.replace(File.separator, ".").replace(".class", "");
                // 3. 扫描@MyService
                Class clazz = null;
                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (clazz != null) {
                    if (clazz.isAnnotationPresent(MyService.class)) {
                        try {
                            // 判断是否有接口
                            Object instance = clazz.newInstance();
                            if (clazz.getInterfaces().length > 0) {
                                beanFactory.put(clazz.getInterfaces()[0], instance);
                            } else {
                                beanFactory.put(clazz, instance);
                            }
                        } catch (InstantiationException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // 现在找的是类中注释了@DI的属性，因此不能再用class判断
//                    else if (clazz.isAnnotationPresent(DI.class)) {
//                        // 判断是否在IoC中
//                        if (beanFactory.containsKey(clazz)) {
//
//                            clazz.newInstance()
//                        }
//                    }
                }
            }
        }
    }
}
