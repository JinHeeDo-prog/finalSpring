package com.example.fianltest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor //매개변수 없는 생성자
@AllArgsConstructor //전체 매개변수를 받는 생성자
@Builder
@Table
public class User implements UserDetails {

    @Id //Id값으로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 키값으로 설정
    private Long id;


    @Column(nullable = false, unique = true) // null 방지, 중복 방지
    private String uid;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // 패스워드는 쓰기만 하고 읽어오진 않기때문에 WRITE_ONLY로 설정
    @Column(nullable = false) // null 방지
    private String password;

    @Column(nullable = false) // null 방지
    private String name;

    @Column(nullable = false) // null 방지
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.uid;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return false;
    }

}
