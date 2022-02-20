package com.sku.CoronaMap.domain;

import com.sku.CoronaMap.domain.enums.BoardType;
import com.sku.CoronaMap.domain.posts.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table
public class User extends BaseTimeEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userid;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Builder
    public User(Long id, String userid, String name, String password, String email){
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
