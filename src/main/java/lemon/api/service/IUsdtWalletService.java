package lemon.api.service;

import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.UsdtWallet;

import java.math.BigDecimal;

public interface IUsdtWalletService {
    UsdtWallet saveWallet(UsdtWallet btcWallet) throws Exception;
    UsdtWallet updateBalance(Long walletId, BigDecimal balance) throws Exception, ResourceNotFoundException;
}
