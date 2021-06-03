package lemon.api.service;

import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.ArsWallet;

import java.math.BigDecimal;

public interface IArsWalletService {
    ArsWallet saveWallet(ArsWallet btcWallet) throws Exception;
    ArsWallet updateBalance(Long userId, BigDecimal ammount, String txType) throws Exception, ResourceNotFoundException;
}
