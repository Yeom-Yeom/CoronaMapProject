package com.sku.CoronaMap.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

// 테이블 가져오기: 로그인 기능에 쓰일 변수를 선언
//@Entity
//@Getter
//@Setter
//@Table(name = "member2")
@Data
public class MemberDTO {
    //@Id
    private String userid;
    private String passwd;
    private String name;
    private String email;
    //private Date join_date;

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
/*    public Date getJoin_date() {
        return join_date;
    }
    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }*/

    @Override
    public String toString() {
        return "MemberDTO [userid=" + userid + ", passwd=" + passwd + ", name=" + name + ", email=" + email + "]";
    }
}