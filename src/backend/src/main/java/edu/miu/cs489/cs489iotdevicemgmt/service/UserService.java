package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User create(User user);
    User update(Long userId, User user);
    boolean delete(Long userId);
}
