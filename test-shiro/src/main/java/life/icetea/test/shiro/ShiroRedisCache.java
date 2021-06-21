//package life.icetea.test.shiro;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.cache.CacheException;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
//@Slf4j
//public class ShiroRedisCache<K, V> implements Cache<K, V> {
//
//    private RedisManager redisManager;
//    private static final long expireTime = 10800;
//    public ShiroRedisCache(RedisManager redisManager) {
//        this.redisManager = redisManager;
//    }
//
//    @Override
//    public V get(K k) throws CacheException {
//        log.debug("根据key从redis对象中获取V k = {}", k);
//        try {
//            V value = redisManager.get(k.toString());
//            log.debug("获取的value = {}", value);
//            if(value != null) {
//                return value;
//            }
//            return value;
//        } catch (Throwable t) {
//            throw new CacheException(t);
//        }
//    }
//
//    @Override
//    public V put(K k, V v) throws CacheException {
//        log.debug("根据key存储Cache k = {}", k);
//        try {
//            redisManager.setEx(k.toString(), v, expireTime);
//            return v;
//        } catch (Throwable t) {
//            throw new CacheException(t);
//        }
//    }
//
//    @Override
//    public V remove(K k) throws CacheException {
//        log.debug("根据key从redis中删除 k = {}", k);
//        try {
//            V value = redisManager.get(k.toString());
//            redisManager.del(k.toString());
//            return value;
//        } catch (Throwable t) {
//            throw new CacheException(t);
//        }
//
//    }
//
//    @Override
//    public void clear() throws CacheException {
//
//    }
//
//    @Override
//    public int size() {
//        return 0;
//    }
//
//    @Override
//    public Set<K> keys() {
//        Set<byte[]> set = redisManager.keys("*");
//        return (Set<K>) set;
//    }
//
//    @Override
//    public Collection<V> values() {
//        Set keys = this.keys();
//        List<Object> list = new ArrayList<Object>();
//        for(Object key: keys) {
//            list.add(redisManager.get(key.toString()));
//        }
//        return (Collection<V>) list;
//    }
//}
