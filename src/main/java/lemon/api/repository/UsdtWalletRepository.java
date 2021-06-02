package lemon.api.repository;

import lemon.api.model.UsdtWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsdtWalletRepository extends JpaRepository<UsdtWallet, Long> {
    UsdtWallet findFirstByUser(Long userId);
}
