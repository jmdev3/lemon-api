package lemon.api.repository;

import lemon.api.model.ArsWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArsWalletRepository extends JpaRepository<ArsWallet, Long> {

    @Query("select w from ars_wallet w where w.user = ?1")
    public ArsWallet getWalletByUserId(Long userId);
}
