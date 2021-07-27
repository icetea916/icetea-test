package life.icetea.test.lambda.domain;

import lombok.Data;

import java.util.List;

/**
 * 专辑,由若干曲目组成
 */
@Data
public class Album {

    /**
     * 专辑名称
     */
    private String name;
    /**
     * 曲目列表
     */
    private List<Track> tracks;
    /**
     * 参与制作的艺术家列表
     */
    private List<Artist> musicians;

}
