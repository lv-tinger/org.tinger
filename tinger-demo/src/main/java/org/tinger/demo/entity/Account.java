package org.tinger.demo.entity;

import lombok.*;
import org.tinger.core.data.annotation.PrimaryKey;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @PrimaryKey
    private String id;
    private String username;
    private String password;
    private Integer status;
}