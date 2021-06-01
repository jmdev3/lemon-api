package lemon.api.repository;

import lemon.api.model.BtcWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BtcWalletRepository extends JpaRepository<BtcWallet, Long> {

    @Query("select w from btc_wallet w where w.user = ?1")
    public BtcWallet getWalletByUserId(Long userId);
}
