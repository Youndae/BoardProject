package com.board.project.domain.entity;

import lombok.*;

//@Data
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Auth {

    private int authNo;
    private String userId;
    private String auth;

}
