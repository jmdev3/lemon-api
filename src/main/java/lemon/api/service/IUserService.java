package lemon.api.service;

import java.util.List;
import java.util.Map;

import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.User;

public interface IUserService {

List<User> getAllUsers();

User getUserById(Long userId) throws ResourceNotFoundException;

List<User> getByAliasOrEmail(String alias, String email);

User saveUser(User user) throws Exception;

User updateUser(Long userId, User user) throws Exception, ResourceNotFoundException;

Map<String, Boolean> deleteUser(Long userId);
}
