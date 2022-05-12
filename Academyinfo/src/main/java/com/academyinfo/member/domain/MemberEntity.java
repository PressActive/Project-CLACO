package com.academyinfo.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class MemberEntity {
	// 실제 테이블에 저장되있는 회원 데이터
    @Id 
    @Column(name = "MINDEX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mindex;
    
    @Column(length = 20, nullable = false)
    private String id;
    
    @Column(length = 100, nullable = false)
    private String pwd;
    
    @Column(length = 20, nullable = false)
    private String mname;
    
    @Column(length = 20, nullable = false)
    private String phone;
    
    @Column(length = 30, nullable = false)
    private String email;
    
    @Column(length = 20)
    private String companynum;
    
    @Column(length = 50)
    private String address;
    
    @Column(length = 20)
    private String role;
    

    @Builder
    public MemberEntity(Long mindex, String id, String pwd, String mname, String phone, String email, String companynum, String address, String role) {
        this.mindex = mindex;
        this.id = id;
        this.pwd = pwd;
        this.mname = mname;
        this.phone = phone;
        this.email = email;
        this.companynum = companynum;
        this.address = address;
        this.role = role;
    }
}