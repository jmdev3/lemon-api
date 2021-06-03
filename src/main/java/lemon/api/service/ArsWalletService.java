package lemon.api.service;

import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.ArsWallet;
import lemon.api.repository.ArsWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Transactional
@Service
public class ArsWalletService implements IArsWalletService {
	@Autowired
	private ArsWalletRepository arsWalletRepository;

	@Override
	public ArsWallet saveWallet(ArsWallet arsWallet) throws Exception {
		return arsWalletRepository.save(arsWallet);
	}

	@Override
	public ArsWallet updateBalance(Long userId, BigDecimal ammount, String txType) throws Exception, ResourceNotFoundException {
		try {
			ArsWallet arsWallet = arsWalletRepository.findFirstByUser(userId);

			if (arsWallet == null) {
				throw new ResourceNotFoundException("User not found given: " + userId);
			}

			if (txType.equalsIgnoreCase("DEPOSIT")) {
				arsWallet.setBalance(arsWallet.getBalance().add(ammount));
				arsWallet = arsWalletRepository.save(arsWallet);
				return arsWallet;
			} else if (txType.equalsIgnoreCase("EXTRACTION")) {
				if (arsWallet.getBalance().compareTo(ammount) < 0) {
					throw new Exception("Insufficient found");
				}
				arsWallet.setBalance(arsWallet.getBalance().subtract(ammount));
				arsWallet = arsWalletRepository.save(arsWallet);
				return arsWallet;
			}

			throw new Exception("Unsupported transaction");
		} catch (ResourceNotFoundException e) {
			throw e;
		}
	}
}
