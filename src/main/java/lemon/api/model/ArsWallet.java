package lemon.api.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "ars_wallet")
@Table(name = "ars_wallet")
@EntityListeners(AuditingEntityListener.class)
public class ArsWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long walletid;

    @Column(name = "user", nullable = false)
    private long user;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance = new BigDecimal(0.00);

    public long getWalletId() {
        return walletid;
    }
    public void setWalletId(long id) {
        this.walletid = walletid;
    }

    public long getUser() {
        return user;
    }
    public void setUser(long user) {
        this.user = user;
    }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
