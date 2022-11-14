package org.tinger.demo.entity;

import lombok.*;
import org.tinger.core.data.annotation.PrimaryKey;

import java.util.Date;

/**
 * Created by tinger on 2022-11-07
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @PrimaryKey
    private String id;
    private String name;
    private String avatar;
    private Integer gender;
    private Date birthday;
    private String summary;
    private Date createTime;
    private Date updateTime;
    private Integer version;
}
