package lemon.api.dto;

import lemon.api.model.User;
import lemon.api.model.BtcWallet;

public class UserWithWallet {

    private User user;
    private BtcWallet btcWallet;

    public UserWithWallet(User user, BtcWallet btcWallet) {
        this.user = user;
        this.btcWallet = btcWallet;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public BtcWallet getBtcWallet() { return btcWallet; }
    public void setBtcWallet(BtcWallet btcWallet) { this.btcWallet = btcWallet; }
}
