package s19239.mas.teddemo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s19239.mas.teddemo.model.User;
import s19239.mas.teddemo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

}
