package life.icetea.test.lambda.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 专辑中的一支曲目
 */
@Data
@AllArgsConstructor
public class Track {

    /**
     * 专辑名称
     */
    private String name;
    /**
     * 时长单位s
     */
    private int length;

}
