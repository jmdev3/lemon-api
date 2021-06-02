package lemon.api.dto;

import lemon.api.model.ArsWallet;
import lemon.api.model.UsdtWallet;
import lemon.api.model.User;
import lemon.api.model.BtcWallet;

public class UserWithWallet {

    private User user;
    private BtcWallet btcWallet;
    private ArsWallet arsWallet;
    private UsdtWallet usdtWallet;

    public UserWithWallet(User user, BtcWallet btcWallet, ArsWallet arsWallet, UsdtWallet usdtWallet) {
        this.user = user;
        this.btcWallet = btcWallet;
        this.arsWallet = arsWallet;
        this.usdtWallet = usdtWallet;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public BtcWallet getBtcWallet() { return btcWallet; }
    public void setBtcWallet(BtcWallet btcWallet) { this.btcWallet = btcWallet; }

    public ArsWallet getArsWallet() {
        return arsWallet;
    }
    public void setArsWallet(ArsWallet arsWallet) {
        this.arsWallet = arsWallet;
    }

    public UsdtWallet getUsdtWallet() {
        return usdtWallet;
    }
    public void setUsdtWallet(UsdtWallet usdtWallet) {
        this.usdtWallet = usdtWallet;
    }
}
