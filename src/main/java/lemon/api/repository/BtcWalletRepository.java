package lemon.api.repository;

import lemon.api.model.BtcWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BtcWalletRepository extends JpaRepository<BtcWallet, Long> {
    BtcWallet findFirstByUser(Long userId);
}
