package sxt.loader;

/**
 * Created by zzf on 17/10/25.
 */
public class Demo03 {

    public static void main(String[] args) throws ClassNotFoundException {
        FileSystemClassLoader loader = new FileSystemClassLoader("/Users/zzf/myproject/druid/target/classes");
        FileSystemClassLoader loader1 = new FileSystemClassLoader("/Users/zzf/myproject/druid/target/classes");


        Class<?> c = loader.loadClass("com.alibaba.druid.filter.AutoLoad");
        Class<?> c2 = loader.loadClass("com.alibaba.druid.filter.AutoLoad");
        Class<?> c3 = loader1.loadClass("com.alibaba.druid.filter.AutoLoad");

        Class<?> c4 = loader1.loadClass("java.lang.String");
        Class<?> c5 = loader1.loadClass("sxt.loader.Demo02");//当前项目

        System.out.println(c.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());//同一个类，被不同的类加载器加载, 是不同的类

        System.out.println(c3.getClassLoader());//自定义加载器
        System.out.println(c4.getClassLoader());//引导类加载器
        System.out.println(c.getClassLoader());//自定义加载器
        System.out.println(c5.getClassLoader());//系统默认加载器
    }
}
