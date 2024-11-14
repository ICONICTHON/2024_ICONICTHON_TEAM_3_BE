package com.akofood.server.entity;

import com.akofood.server.entity.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String address;

    private String nickname;

//    @Builder
//    public User(String username) {
//        this.username = username;
//    } //유저 id만 받아오게 수정했음!!!

    @Builder
    public User(String username, String email, String address) {
        this.username = username;
        this.email = email;
        this.address = address;
    }
}