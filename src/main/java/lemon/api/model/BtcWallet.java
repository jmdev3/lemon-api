package lemon.api.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.persistence.*;

@Entity(name = "btc_wallet")
@Table(name = "btc_wallet")
@EntityListeners(AuditingEntityListener.class)
public class BtcWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long walletid;

    @Column(name = "user", nullable = false)
    private long user;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance = new BigDecimal(0.00000000);

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

    public String getBalance() { return new DecimalFormat("#0.00000000").format(balance); }
    public BigDecimal getBalanceUnFormatted() { return balance; }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "btc_wallet{" +
                "walletid=" + walletid +
                ", user='" + user + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
