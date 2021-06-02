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
	public ArsWallet saveArsWallet(ArsWallet arsWallet) throws Exception {
		return arsWalletRepository.save(arsWallet);
	}

	@Override
	public ArsWallet updateBalance(Long btcWalletId, BigDecimal balance) throws Exception, ResourceNotFoundException {
		ArsWallet arsWallet = new ArsWallet();
		try {
			arsWallet = arsWalletRepository.findById(btcWalletId).orElseThrow(() -> new ResourceNotFoundException("Wallet not found given: " + btcWalletId));
			arsWallet.setBalance(arsWallet.getBalanceUnformatted().add(balance));
			arsWallet = arsWalletRepository.save(arsWallet);
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return arsWallet;
	}
}
