package life.icetea.test.beancopire;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BeanCopier工具类, 使用ConcurrentHashMap
 * <p>
 * 参考链接:
 * https://www.lifengdi.com/archives/article/500
 * https://blog.csdn.net/u012500848/article/details/100412631
 * 这篇文章的concurrentHashMap使用有问题
 *
 * @author icetea
 */
public class BeanCopierUtils2 {

    /**
     * BeanCopier缓存(享元模式)
     */
    private static final Map<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();

    /**
     * 将source对象的属性拷贝到target对象中去
     *
     * @param source source对象
     * @param target target对象
     */
    public static void copyProperties(Object source, Object target) {
        String cacheKey = source.getClass().toString() + target.getClass().toString();

        // 查看concurrentHashMap的实现方法介绍可以得知该方法为原子方法,保证了线程安全
        BeanCopier beanCopier = BEAN_COPIER_MAP.computeIfAbsent(cacheKey, k -> BeanCopier.create(source.getClass(), target.getClass(), false));
        beanCopier.copy(source, target, null);
    }

    /**
     * 将source对象的属性拷贝到target对象中去
     *
     * @param source source对象
     * @param target target对象
     */
    public static void copyProperties(Object source, Object target, Converter converter) {
        String cacheKey = source.getClass().toString() + target.getClass().toString();

        // 查看concurrentHashMap的实现方法介绍可以得知该方法为原子方法,保证了线程安全
        BeanCopier beanCopier = BEAN_COPIER_MAP.computeIfAbsent(cacheKey, k -> BeanCopier.create(source.getClass(), target.getClass(), true));
        beanCopier.copy(source, target, converter);
    }

}
