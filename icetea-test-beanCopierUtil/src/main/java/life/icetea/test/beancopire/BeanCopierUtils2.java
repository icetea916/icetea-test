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
        copyProperties(source, target, null);
    }

    /**
     * 将source对象的属性拷贝到target对象中去
     *
     * @param source source对象
     * @param target target对象
     */
    public static void copyProperties(Object source, Object target, Converter converter) {
        String cacheKey = generateKey(source.getClass(), target.getClass());
        // 查看concurrentHashMap的实现方法介绍可以得知该方法为原子方法,保证了线程安全
        BeanCopier beanCopier = BEAN_COPIER_MAP.computeIfAbsent(cacheKey, k -> BeanCopier.create(source.getClass(), target.getClass(), converter != null));
        beanCopier.copy(source, target, converter);
    }

    /**
     * 将source对象的属性拷贝到target对象中去
     *
     * @param source      source对象
     * @param targetClazz target对象class
     */
    public static <T> T copyPropertiesWithNew(Object source, Class<T> targetClazz) {
        return copyPropertiesWithNew(source, targetClazz, null);
    }

    /**
     * 将source对象的属性拷贝到target对象中去
     *
     * @param source      source对象
     * @param targetClazz target对象class
     */
    public static <T> T copyPropertiesWithNew(Object source, Class<T> targetClazz, Converter converter) {
        T target;
        try {
            target = (T) targetClazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("copy属性异常", e);
        }
        copyProperties(source, target, converter);
        return target;
    }

    /**
     * 生成key
     */
    private static String generateKey(Class<?> srcClazz, Class<?> tarClazz) {
        return srcClazz.getName() + tarClazz.getName();
    }

}
