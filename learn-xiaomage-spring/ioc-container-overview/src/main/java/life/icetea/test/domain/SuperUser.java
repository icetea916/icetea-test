package life.icetea.test.domain;

import life.icetea.test.annotation.Super;
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
