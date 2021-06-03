package lemon.api.service;
import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.Transaction;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ITransactionService {
    List<Transaction> findByUser(Long userId, Pageable pageable);
    Transaction createTransaction(Transaction transaction) throws Exception, ResourceNotFoundException;
}
