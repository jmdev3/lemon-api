package lemon.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.ArsWallet;
import lemon.api.model.BtcWallet;
import lemon.api.model.User;
import lemon.api.repository.ArsWalletRepository;
import lemon.api.repository.BtcWalletRepository;
import lemon.api.repository.UserRepository;
import lemon.api.dto.UserWithWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BtcWalletRepository btcWalletRepository;
	@Autowired
	private IBtcWalletService btcWalletService;
	@Autowired
	private ArsWalletRepository arsWalletRepository;
	@Autowired
	private IArsWalletService arsWalletService;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserWithWallet getUserById(Long userId) throws ResourceNotFoundException {
		User user = new User();
		BtcWallet btcWallet = new BtcWallet();
		ArsWallet arsWallet = new ArsWallet();
		try {
			user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found given id: " + userId));
			btcWallet = btcWalletRepository.getWalletByUserId(user.getId());
			arsWallet = arsWalletRepository.getWalletByUserId(user.getId());
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return new UserWithWallet(user, btcWallet, arsWallet);
	}

	@Override
	public List<User> getByAliasOrEmail(String alias, String email) {
		return userRepository.getUsersByAliasOrEmail(alias, email);
	}

	@Override
	public User saveUser(User user) throws Exception {
		String alias = user.getAlias();
		String email = user.getEmail();

		List<User> exists = userRepository.getUsersByAliasOrEmail(alias, email);

		if (exists.size() > 0) {
			throw new Exception("Alias and/or email are in use");
		}

		User newUser = userRepository.save(user);

		BtcWallet btcWallet = new BtcWallet();
		btcWallet.setUser(newUser.getId());
		btcWalletService.saveBtcWallet(btcWallet);

		ArsWallet arsWallet = new ArsWallet();
		arsWallet.setUser(newUser.getId());
		arsWalletService.saveArsWallet(arsWallet);

		return newUser;
	}

	@Override
	public User updateUser(Long userId, User userDet) throws Exception, ResourceNotFoundException {
		User updatedUser = new User();
		String alias = userDet.getAlias();
		String email = userDet.getEmail();

		List<User> exists = userRepository.getUsersByAliasOrEmail(alias, email);

		if (exists.size() > 0) {
			throw new Exception("Alias and/or email are in use");
		}

		try {
			User user = new User();
			user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
			user.setEmail(userDet.getEmail());
			user.setLastName(userDet.getLastName());
			user.setFirstName(userDet.getFirstName());
			user.setAlias(userDet.getAlias());
			updatedUser = userRepository.save(user);
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return updatedUser;
	}

	@Override
	public Map<String, Boolean> deleteUser(Long userId) {
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		try {
			User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
			userRepository.delete(user);
			response.put("deleted", Boolean.TRUE);
		} catch (ResourceNotFoundException e) {
			response.put("deleted", Boolean.FALSE);
		}
		return response;
	}

}
