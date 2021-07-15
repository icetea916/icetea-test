package life.icetea.test.lambada.domain;

import lombok.Data;

import java.util.List;

/**
 * 艺术家: 创作音乐的个人或团队
 */
@Data
public class Artist {

    /**
     * 艺术家名称
     */
    private String name;
    /**
     * 乐队成员
     */
    private List<String> members;
    /**
     * 乐队来自哪里
     */
    private String origin;

}