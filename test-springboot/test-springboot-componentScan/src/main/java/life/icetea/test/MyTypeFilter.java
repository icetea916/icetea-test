package life.icetea.test;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.awt.*;
import java.io.IOException;

/**
 * 自定义扫描过滤规则
 *
 * @author icetea
 * @date 2023-01-04 10:06
 */
public class MyTypeFilter implements TypeFilter {

    /**
     * @param metadataReader        读取到当前正在扫描的类信息
     * @param metadataReaderFactory 可以获取到其他任何类的信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        System.out.println("======MyTypeFilter Start======");
        // 获取当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        System.out.println("annotationMetadata: " + annotationMetadata);

        // 获取当前正在扫描类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        System.out.println("classMetadata: " + classMetadata);

        // 获取当前类的资源路径
        Resource resource = metadataReader.getResource();
        System.out.println("resource" + resource);

        // 获取类名
        String className = classMetadata.getClassName();
        System.out.println("className" + className);

        Class<?> forName = null;
        try {
            forName = Class.forName(className);
            if (Color.class.isAssignableFrom(forName)) {
                // 如果是Color的子类，就加载到IOC容器
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("======MyTypeFilter End======");
        return false;
    }
}
