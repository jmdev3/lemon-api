package lemon.api.dto;
import lemon.api.model.User;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class UserWithWallet {

    private User user;
    private BigDecimal btc;
    private BigDecimal ars;
    private BigDecimal usdt;

    public UserWithWallet(User user, BigDecimal btc, BigDecimal ars, BigDecimal usdt) {
        this.user = user;
        this.btc = btc;
        this.ars = ars;
        this.usdt = usdt;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getBtc() {
        return new DecimalFormat("#0.00000000").format(btc);
    }
    public void setBtc(BigDecimal btc) {
        this.btc = btc;
    }

    public String getArs() {
        return new DecimalFormat("#0.00").format(ars);
    }
    public void setArs(BigDecimal ars) { this.ars = ars; }

    public String getUsdt() {
        return new DecimalFormat("#0.00").format(usdt);
    }
    public void setUsdt(BigDecimal usdt) { this.usdt = usdt; }
}
