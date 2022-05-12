package com.academyinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.academyinfo.member.service.MemberService;

import lombok.AllArgsConstructor;

// 스프링 시큐리티 설정 클래스
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정(관리자,일반회원,기업)
                .antMatchers("/admin/**").hasRole("ADMIN")			// ADMIN 권한을 가진 사용자만 해당 페이지 접속가능
                .antMatchers("/user/myinfo").hasRole("MEMBER")		// MEMBER 권한을 가진 사용자만 해당 페이지 접속가능
                .antMatchers("/user/cpninfo").hasRole("COMPANY")	// COMPANY 권한을 가진 사용자만 해당 페이지 접속가능
                .antMatchers("/**").permitAll()						// 이외의 모든 페이지는 권한 없이 접속가능
                .antMatchers("/freeboard/post").hasAnyRole("MEMBER","COMPANY")
            .and() // 로그인 설정
                .formLogin()
                .loginPage("/user/login")	// 로그인 양식이 있는 페이지 지정
                .usernameParameter("id")	// 로그인 폼에 지정한 양식과 일치해야함
                .passwordParameter("pwd")	
                .defaultSuccessUrl("/user/login/result")	// 로그인이 성공했을 시 나올 결과창
                .permitAll()
            .and() // POST 사용으로 security 무시해야 하는 영역
    			//다른 부분은 무시해주시고 이 부분만 잘 사용 해주시면 됩니다.
    			.csrf()
    			.ignoringAntMatchers("/freeboard/post")
    		    	 //
            .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/logout/result")	// 로그아웃 성공했을 시 나올 결과창
                .invalidateHttpSession(true)
            .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/user/denied");	// 접근권한이 없는 페이지 접근 시 나올 페이지
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
