package lemon.api.dto;

import lemon.api.model.ArsWallet;
import lemon.api.model.User;
import lemon.api.model.BtcWallet;

public class UserWithWallet {

    private User user;
    private BtcWallet btcWallet;
    private ArsWallet arsWallet;

    public UserWithWallet(User user, BtcWallet btcWallet, ArsWallet arsWallet) {
        this.user = user;
        this.btcWallet = btcWallet;
        this.arsWallet = arsWallet;
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
}
