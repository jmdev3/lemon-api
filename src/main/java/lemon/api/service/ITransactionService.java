package lemon.api.service;
import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.Transaction;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ITransactionService {
    List<Transaction> findByUser(Long userId, Pageable pageable);
    List<Transaction> findByUserAndType(Long userId, String type, Pageable pageable);
    List<Transaction> findByUserAndWallet(Long userId, String wallet, Pageable pageable);
    List<Transaction> findByUserAndTypeAndWallet(Long userId, String type, String wallet, Pageable pageable);
    Transaction createTransaction(Transaction transaction) throws Exception, ResourceNotFoundException;
}
