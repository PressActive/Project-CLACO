package com.academyinfo.member.dto;

import java.time.LocalDateTime;

import com.academyinfo.member.domain.MemberEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
	// 테이블(DB)과 뷰 사이의 데이터를 주고 받기 위한 클래스
	 private Long mindex;
	 
	 private String id;
	 private String pwd;
	 private String mname;
	 private String phone;
	 private String email;
	 private String companynum;
	 private String address;
	 private String role;
	 private LocalDateTime createdDate;
	
	 @Builder
	 public MemberDto(Long mindex, String id, String pwd, String mname, String phone, String email, String companynum, String address, String role) {
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
	 
	 public MemberEntity toEntity() {
		 return MemberEntity.builder()
				 .mindex(mindex)
	             .id(id)
	             .pwd(pwd)
	             .mname(mname)
	             .phone(phone)
	             .email(email)
	             .companynum(companynum)
	             .address(address) 
	             .role(role)
	             .build();
		 
	 }
}