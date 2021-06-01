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
	public BtcWallet saveBtcWallet(BtcWallet btcWallet) throws Exception {
		return btcWalletRepository.save(btcWallet);
	}

	@Override
	public BtcWallet updateBalance(Long btcWalletId, BigDecimal balance) throws Exception, ResourceNotFoundException {
		BtcWallet btcWallet = new BtcWallet();
		try {
			btcWallet = btcWalletRepository.findById(btcWalletId).orElseThrow(() -> new ResourceNotFoundException("Wallet not found given: " + btcWalletId));
			btcWallet.setBalance(btcWallet.getBalanceUnFormatted().add(balance));
			btcWallet = btcWalletRepository.save(btcWallet);
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return btcWallet;
	}
}
