package lemon.api.service;

import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.*;
import lemon.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class TransactionService implements ITransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
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

	public List<Transaction> findByUser(Long userId) {
		return transactionRepository.findByUser(userId);
	}

	public Transaction createTransaction(Transaction transaction) throws Exception, ResourceNotFoundException {
		try {
			String wallet = transaction.getWallet();
			Long userId = transaction.getUser();
			BigDecimal amount = transaction.getAmount();
			String txType = transaction.getType();

			if (wallet.equalsIgnoreCase("ARS")) {
				arsWalletService.updateBalance(userId, amount, txType);
				int decimals = this.getNumberOfDecimalPlaces(amount);
				if (decimals != 2) {
					throw new Exception("Invalid amount format");
				}
				return transactionRepository.save(transaction);
			} else if (wallet.equalsIgnoreCase("USDT")) {
				usdtWalletService.updateBalance(userId, amount, txType);
				int decimals = this.getNumberOfDecimalPlaces(amount);
				if (decimals != 2) {
					throw new Exception("Invalid amount format");
				}
				return transactionRepository.save(transaction);
			} else if (wallet.equalsIgnoreCase("BTC")) {
				btcWalletService.updateBalance(userId, amount, txType);
				int decimals = this.getNumberOfDecimalPlaces(amount);
				if (decimals != 8) {
					throw new Exception("Invalid amount format");
				}
				return transactionRepository.save(transaction);
			}
			throw new Exception("Unsupported wallet given: " + wallet);
		} catch (Exception e) {
			throw e;
		}
	}

	private int getNumberOfDecimalPlaces(BigDecimal amount) {
		return Math.max(0, amount.scale());
	}
}
