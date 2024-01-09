package com.board.project.security;

import com.board.project.mapper.MemberMapper;
import com.board.project.domain.entity.Member;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Setter(onMethod_ = @Autowired)
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userDetailsService");
        Member vo = memberMapper.read(username);

        log.info("details vo : {}", vo);

        return vo == null ? null : new CustomUser(vo);
    }
}
