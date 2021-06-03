package lemon.api.service;

import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.UsdtWallet;
import lemon.api.repository.UsdtWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Transactional
@Service
public class UsdtWalletService implements IUsdtWalletService {
	@Autowired
	private UsdtWalletRepository usdtWalletRepository;

	@Override
	public UsdtWallet saveWallet(UsdtWallet usdtWallet) throws Exception {
		return usdtWalletRepository.save(usdtWallet);
	}

	@Override
	public UsdtWallet updateBalance(Long userId, BigDecimal balance, String txType) throws Exception, ResourceNotFoundException {
		try {
			UsdtWallet usdtWallet = usdtWalletRepository.findFirstByUser(userId);

			if (usdtWallet == null) {
				throw new ResourceNotFoundException("User not found given: " + userId);
			}

			if (txType.equalsIgnoreCase("DEPOSIT")) {
				usdtWallet.setBalance(usdtWallet.getBalance().add(balance));
				usdtWallet = usdtWalletRepository.save(usdtWallet);
				return usdtWallet;
			} else if (txType.equalsIgnoreCase("EXTRACTION")) {
				if (usdtWallet.getBalance().compareTo(balance) < 0) {
					throw new Exception("Insufficient found");
				}
				usdtWallet.setBalance(usdtWallet.getBalance().add(balance));
				usdtWallet = usdtWalletRepository.save(usdtWallet);
				return usdtWallet;
			}

			throw new Exception("Unsupported transaction");
		} catch (ResourceNotFoundException e) {
			throw e;
		}
	}
}
