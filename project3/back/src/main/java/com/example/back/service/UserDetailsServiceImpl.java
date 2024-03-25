//package com.example.back.service;
//
//import com.example.back.domain.User;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
//        User user = userService.getUser(id);
//        if (user == null){
//            throw new UsernameNotFoundException("User not found" + id);
//        }
//        HttpSession session = getCurrentSession();
////        String role = (String) session.getAttribute("role");
//        String role = (session != null) ? (String) session.getAttribute("role") : null;
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
//
//        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPass(), authorities);
//    }
//
//
//}
