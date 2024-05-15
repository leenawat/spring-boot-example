package com.punyadev.jpa.jpabankaccount;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToMany(mappedBy = "accounts")
    private Set<BankUser> bankUsers;

    @OneToOne(mappedBy = "account")
    private Card card;

    private String IBAN;

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Set<BankUser> getBankUsers() {
        return bankUsers;
    }

    public void setBankUsers(Set<BankUser> bankUsers) {
        this.bankUsers = bankUsers;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
