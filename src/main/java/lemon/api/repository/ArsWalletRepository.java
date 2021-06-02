package lemon.api.repository;

import lemon.api.model.ArsWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArsWalletRepository extends JpaRepository<ArsWallet, Long> {
    ArsWallet findFirstByUser(Long userId);
}
