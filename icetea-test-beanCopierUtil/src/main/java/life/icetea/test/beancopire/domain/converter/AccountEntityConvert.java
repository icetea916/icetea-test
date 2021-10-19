package life.icetea.test.beancopire.domain.converter;

import life.icetea.test.beancopire.BeanCopierUtils;
import life.icetea.test.beancopire.domain.AccountEntity;
import net.sf.cglib.core.Converter;

/**
 * 要实现深copy需要自定义converter
 *
 * @author icetea
 */
public class AccountEntityConvert implements Converter {

    /**
     * 自定义属性转换
     *
     * @param value   源对象中属性的对象
     * @param target  目标对象属性类
     * @param context 目标对象中属性对应set方法名,eg.setId
     * @return
     */
    @Override
    public Object convert(Object value, Class target, Object context) {
        if (value instanceof AccountEntity) {
            AccountEntity accountEntity = new AccountEntity();
            BeanCopierUtils.copyProperties(value, accountEntity);
            return accountEntity;
        }
        // 更多类型转换请自定义
        return value;
    }

}
