package lemon.api.repository;

import lemon.api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> findByUser(Long userId, Pageable pageable);
    public List<Transaction> findByUserAndType(Long userId, String type, Pageable pageable);
    public List<Transaction> findByUserAndWallet(Long userId, String wallet, Pageable pageable);
    public List<Transaction> findByUserAndTypeAndWallet(Long userId, String type, String wallet, Pageable pageable);
}
