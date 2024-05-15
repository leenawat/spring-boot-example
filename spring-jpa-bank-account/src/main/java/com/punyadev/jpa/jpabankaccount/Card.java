package com.punyadev.jpa.jpabankaccount;

import jakarta.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue
    private Long id;

    private String number;
    private String cvv;
    private String expirationDate;
    @OneToOne(optional = false)
    @JoinColumn(name = "account_id",
            foreignKey = @ForeignKey(name = "fk_account_id"), unique = true)
    private Account account;

    @ManyToOne(optional = false)
    private BankUser bankUser;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BankUser getBankUser() {
        return bankUser;
    }

    public void setBankUser(BankUser bankUser) {
        this.bankUser = bankUser;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
