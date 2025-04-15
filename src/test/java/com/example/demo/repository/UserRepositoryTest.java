package com.example.demo.repository;

import com.example.demo.model.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class)
//@TestPropertySource("classpath:test-application.properties")
@DataJpaTest(showSql = true)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void UserRepository_가_제대로_연결되었다() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("th@gmail.com");
        userEntity.setAddress("hk");
        userEntity.setNickname("thkim");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("aaa");

        //when
        UserEntity result = userRepository.save(userEntity);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    void findByIdAndStatus_로_유저_데이터를_찾아올_수_있다() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("th@gmail.com");
        userEntity.setAddress("hk");
        userEntity.setNickname("thkim");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("aaa");

        // when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.ACTIVE);

        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void findByIdAndStatus_는_데이터가_없으면_Optional_empty_를_내려준다() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("th@gmail.com");
        userEntity.setAddress("hk");
        userEntity.setNickname("thkim");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("aaa");

        // when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.PENDING);

        // then
//        assertThat(result.isPresent()).isFalse();
        assertThat(result.isEmpty()).isTrue(); // better

    }


    @Test
    void findByEmailAndStatus_로_유저_데이터를_찾아올_수_있다() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("th@gmail.com");
        userEntity.setAddress("hk");
        userEntity.setNickname("thkim");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("aaa");

        // when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("th@gmail.com", UserStatus.ACTIVE);

        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void findByEmailAndStatus_는_데이터가_없으면_Optional_empty_를_내려준다() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("th@gmail.com");
        userEntity.setAddress("hk");
        userEntity.setNickname("thkim");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("aaa");

        // when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("th@gmail.com", UserStatus.PENDING);

        // then
//        assertThat(result.isPresent()).isFalse();
        assertThat(result.isEmpty()).isTrue(); // better

    }







}
