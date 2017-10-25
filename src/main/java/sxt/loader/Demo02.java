package sxt.loader;

/**
 * Created by zzf on 17/10/23.
 */
public class Demo02 {

    public static void main(String[] args) {

        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

        System.out.println(System.getProperty("java.class.path"));

        System.out.println("++++++++++++++");
        String a = "hello";
        //双亲委派机制，    保证java核心库的安全
        System.out.println(a.getClass().getClassLoader());
        System.out.println(a);
    }
}
