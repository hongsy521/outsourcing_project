package com.sparta.easyspring.postlike.repository;

import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.auth.repository.UserRepository;
import com.sparta.easyspring.post.entity.Post;
import com.sparta.easyspring.post.repository.PostRepository;
import com.sparta.easyspring.config.MockTestDataSetup;
import com.sparta.easyspring.postlike.entity.PostLike;
import com.sparta.easyspring.postlike.repository.inf.PostLikeRepositoryInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PostLikeRepositoryInterfaceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostLikeRepositoryInterface postLikeRepositoryInterface;

    @Test
    @DisplayName("성공: 포스트, 사용자로 좋아요한 포스트 찾기")
    void findByUserAndPost() {
        // given
        User TEST_USER = MockTestDataSetup.mockTestUserSetup();
        userRepository.save(TEST_USER);

        Post TEST_POST = MockTestDataSetup.mockTestPostSetup(TEST_USER);
        postRepository.save(TEST_POST);

        PostLike TEST_POSTLIKE = new PostLike(TEST_USER, TEST_POST);
        postLikeRepositoryInterface.save(TEST_POSTLIKE);

        // when
        PostLike resultPostLike = postLikeRepositoryInterface.findByUserAndPost(TEST_USER, TEST_POST);

        // then
        assertThat(resultPostLike).isEqualTo(TEST_POSTLIKE);
    }

}