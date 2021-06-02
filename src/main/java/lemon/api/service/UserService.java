package lemon.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.ArsWallet;
import lemon.api.model.BtcWallet;
import lemon.api.model.UsdtWallet;
import lemon.api.model.User;
import lemon.api.repository.ArsWalletRepository;
import lemon.api.repository.BtcWalletRepository;
import lemon.api.repository.UsdtWalletRepository;
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
	@Autowired
	private UsdtWalletRepository usdtWalletRepository;
	@Autowired
	private UsdtWalletService usdtWalletService;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserWithWallet getUserById(Long userId) throws ResourceNotFoundException {
		User user = new User();
		BtcWallet btcWallet = new BtcWallet();
		ArsWallet arsWallet = new ArsWallet();
		UsdtWallet usdtWallet = new UsdtWallet();
		try {
			user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found given id: " + userId));
			Long userIdVar = user.getId();
			btcWallet = btcWalletRepository.findFirstByUser(userIdVar);
			arsWallet = arsWalletRepository.findFirstByUser(userIdVar);
			usdtWallet = usdtWalletRepository.findFirstByUser(userIdVar);
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return new UserWithWallet(user, btcWallet.getBalance(), arsWallet.getBalance(), usdtWallet.getBalance());
	}

	@Override
	public List<User> findByAliasOrEmail(String alias, String email) {
		return userRepository.findByAliasOrEmail(alias, email);
	}

	@Override
	public User saveUser(User user) throws Exception {
		String alias = user.getAlias();
		String email = user.getEmail();

		List<User> exists = userRepository.findByAliasOrEmail(alias, email);

		if (exists.size() > 0) {
			throw new Exception("Alias and/or email are in use");
		}

		User newUser = userRepository.save(user);
		Long userIdVar = user.getId();

		BtcWallet btcWallet = new BtcWallet();
		btcWallet.setUser(userIdVar);
		btcWalletService.saveWallet(btcWallet);

		ArsWallet arsWallet = new ArsWallet();
		arsWallet.setUser(userIdVar);
		arsWalletService.saveWallet(arsWallet);

		UsdtWallet usdtWallet = new UsdtWallet();
		usdtWallet.setUser(userIdVar);
		usdtWalletService.saveWallet(usdtWallet);

		return newUser;
	}

	@Override
	public User updateUser(Long userId, User userDet) throws Exception, ResourceNotFoundException {
		User updatedUser = new User();
		String alias = userDet.getAlias();
		String email = userDet.getEmail();

		List<User> exists = userRepository.findByAliasOrEmail(alias, email);

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
