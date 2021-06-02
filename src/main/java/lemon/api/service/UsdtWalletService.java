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
	public UsdtWallet updateBalance(Long walletId, BigDecimal balance) throws Exception, ResourceNotFoundException {
		UsdtWallet usdtWallet = new UsdtWallet();
		try {
			usdtWallet = usdtWalletRepository.findById(walletId).orElseThrow(() -> new ResourceNotFoundException("Wallet not found given: " + walletId));
			usdtWallet.setBalance(usdtWallet.getBalanceUnformatted().add(balance));
			usdtWallet = usdtWalletRepository.save(usdtWallet);
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return usdtWallet;
	}
}
