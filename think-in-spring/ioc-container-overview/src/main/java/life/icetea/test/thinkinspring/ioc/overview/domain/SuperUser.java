package life.icetea.test.thinkinspring.ioc.overview.domain;

import life.icetea.test.thinkinspring.ioc.overview.annotation.Super;
import lombok.Data;

@Data
@Super
public class SuperUser extends User {

    private String address;

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                '}' + super.toString();
    }
}
