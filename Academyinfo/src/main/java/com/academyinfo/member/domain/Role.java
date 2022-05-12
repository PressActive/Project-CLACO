package com.academyinfo.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	// 권한 구분 (관리자, 일반회원 , 기업회원)
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER"),
	COMPANY("ROLE_COMPANY");
	

    private String value;
}
