package lemon.api.service;
import lemon.api.exception.ResourceNotFoundException;
import lemon.api.model.Transaction;
import java.util.List;

public interface ITransactionService {
    List<Transaction> findByUser(Long userId);
    Transaction createTransaction(Transaction transaction) throws Exception, ResourceNotFoundException;
}
