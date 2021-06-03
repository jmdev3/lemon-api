package lemon.api.service;

import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.BtcWallet;
import lemon.api.repository.BtcWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Transactional
@Service
public class BtcWalletService implements IBtcWalletService {
	@Autowired
	private BtcWalletRepository btcWalletRepository;

	@Override
	public BtcWallet saveWallet(BtcWallet btcWallet) throws Exception {
		return btcWalletRepository.save(btcWallet);
	}

	@Override
	public BtcWallet updateBalance(Long userId, BigDecimal amount, String txType) throws Exception, ResourceNotFoundException {
		try {
			BtcWallet btcWallet = btcWalletRepository.findFirstByUser(userId);
			if (btcWallet == null) {
				throw new ResourceNotFoundException("User not found given: " + userId);
			}
			if (txType.equalsIgnoreCase("DEPOSIT")) {
				btcWallet.setBalance(btcWallet.getBalance().add(amount));
				btcWallet = btcWalletRepository.save(btcWallet);
				return btcWallet;
			} else if (txType.equalsIgnoreCase("EXTRACTION")) {
				if (btcWallet.getBalance().compareTo(amount) < 0) {
					throw new Exception("Insufficient found");
				}
				btcWallet.setBalance(btcWallet.getBalance().subtract(amount));
				btcWallet = btcWalletRepository.save(btcWallet);
				return btcWallet;
			}

			throw new Exception("Unsupported transaction");
		} catch (ResourceNotFoundException e) {
			throw e;
		}
	}
}
