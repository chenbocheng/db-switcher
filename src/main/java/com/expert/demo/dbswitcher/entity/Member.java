package com.expert.demo.dbswitcher.entity;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member extends AbstractEntity {
    private String username;
    private String password;
    private String email;
    private boolean enabled = false;
}
