package com.sparta.boardlv3.user.service;

import com.sparta.boardlv3.user.dto.AuthRequestDto;
import com.sparta.boardlv3.user.entity.User;
import com.sparta.boardlv3.user.entity.UserRoleEnum;
import com.sparta.boardlv3.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void signup(AuthRequestDto authRequestDto) {
        String username = authRequestDto.getUsername();
        String password = passwordEncoder.encode(authRequestDto.getPassword());
        UserRoleEnum role = authRequestDto.getRole();

        if (userRepository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        User user = new User(username, password, role);
        userRepository.save(user);
    }

    public void login(AuthRequestDto authRequestDto) {
        String username = authRequestDto.getUsername();
        String password = authRequestDto.getPassword();

        //Id 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("Id가 틀렸습니다.")
        );

        //패스워드 확인
        if (passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
