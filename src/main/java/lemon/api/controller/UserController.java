package lemon.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import lemon.api.dto.UserWithWallet;
import lemon.api.model.User;
import lemon.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lemon.api.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private IUserService userService;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<UserWithWallet> getUsersById(@PathVariable(value = "id") Long userId)
      throws ResourceNotFoundException {
    UserWithWallet user = userService.getUserById(userId);
    return ResponseEntity.ok().body(user);
  }

  @PostMapping("/users")
  public User createUser(
          @Valid @RequestBody User user)
          throws Exception {
      return userService.saveUser(user);
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(
      @PathVariable(value = "id") Long userId, @Valid @RequestBody User user)
      throws ResourceNotFoundException, Exception {
    User updatedUser = userService.updateUser(userId, user);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/user/{id}")
  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
    return userService.deleteUser(userId);
  }
}
