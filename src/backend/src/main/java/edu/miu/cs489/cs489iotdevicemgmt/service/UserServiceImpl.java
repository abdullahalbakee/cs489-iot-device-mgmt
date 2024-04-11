package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.model.User;
import edu.miu.cs489.cs489iotdevicemgmt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User user) {
        var existingUser = getById(userId);
        if(existingUser == null) return null;
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        return userRepository.save(existingUser);
    }

    @Override
    public boolean delete(Long userId) {
        var existingUser = getById(userId);
        if(existingUser == null) return false;
        userRepository.deleteById(userId);
        return true;
    }

    private User getById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
