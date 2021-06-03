package lemon.api.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "transaction")
@Table(name = "transaction")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionid;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "wallet", nullable = false)
    private String wallet;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "user", nullable = false)
    private Long user;

    public long getTransactionId() { return transactionid; }
    public void setTransactionId(long transactionid) { this.transactionid = transactionid; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getWallet() { return wallet; }
    public void setWallet(String wallet) { this.wallet = wallet; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getUser() { return user; }
    public void setUser(Long user) { this.user = user; }
}
