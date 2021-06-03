package lemon.api.service;

import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.BtcWallet;

import java.math.BigDecimal;

public interface IBtcWalletService {
    BtcWallet saveWallet(BtcWallet btcWallet) throws Exception;
    BtcWallet updateBalance(Long userId, BigDecimal balance, String txType) throws Exception, ResourceNotFoundException;
}
