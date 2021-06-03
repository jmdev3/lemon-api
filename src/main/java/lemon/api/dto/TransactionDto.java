package lemon.api.dto;

import lemon.api.model.Transaction;

import java.math.BigDecimal;

public class TransactionDto {
    private long transactionid;
    private BigDecimal amount;
    private String wallet;
    private String type;
    private Long user;

    public TransactionDto(Transaction transaction) {
        this.transactionid = transaction.getTransactionId();
        this.amount = transaction.getAmount();
        this.wallet = transaction.getWallet();
        this.type = transaction.getType();
        this.user = transaction.getUser();
    }

    public long getTransactionid() { return transactionid; }
    public void setTransactionid(long transactionid) { this.transactionid = transactionid; }

    public String getAmount() { return amount.toString(); }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getWallet() { return wallet; }
    public void setWallet(String wallet) { this.wallet = wallet; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getUser() { return user; }
    public void setUser(Long user) { this.user = user; }
}
