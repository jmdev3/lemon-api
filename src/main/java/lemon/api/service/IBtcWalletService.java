package lemon.api.service;

import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.BtcWallet;

import java.math.BigDecimal;

public interface IBtcWalletService {

BtcWallet saveBtcWallet(BtcWallet btcWallet) throws Exception;

BtcWallet updateBalance(Long btcWalletId, BigDecimal balance) throws Exception, ResourceNotFoundException;

}
