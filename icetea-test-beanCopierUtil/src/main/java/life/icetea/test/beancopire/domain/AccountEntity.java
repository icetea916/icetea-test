package life.icetea.test.beancopire.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author icetea
 */
public class AccountEntity {

    private Integer id;
    private BigDecimal balance;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", balance=" + balance +
                ", createTime=" + createTime +
                '}';
    }
}
