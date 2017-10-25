package sxt.loader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 自定义 网络 类加载器
 * Created by zzf on 17/10/24.
 */
public class NetClassLoader extends ClassLoader {

    //
    private String rootUrl;

    public NetClassLoader(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        Class<?> c = findLoadedClass(name);

        //查询有没有加载过
        if (c != null) {
            return c;
        } else {
            ClassLoader parent = this.getParent();
            try {
                c = parent.loadClass(name);//委派给父类加载
            } catch (Exception e) {
//                e.printStackTrace();
            }

            if (c!= null) {
                return c;
            } else {
                byte[] classData = getClassData(name);
                if (classData == null) {
                    throw new ClassNotFoundException();
                } else {
                    c = defineClass(name, classData, 0, classData.length);
                }
            }
        }

        return c;
    }

    private byte[] getClassData(String className) {

        String path = rootUrl + "/" + className.replace(".", "/") + ".class";
        //IOUtils, 将流的数据转成字节数组
        InputStream in = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL url = new URL(path);
            in = url.openStream();

            byte[]  buffer = new byte[1024];
            int temp = 0;
            while ((temp = in.read(buffer)) != -1) {
                baos.write(buffer, 0, temp);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                baos.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
