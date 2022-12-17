package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class CreateWalletResponse {
    private String wallet_id;
    private String customer_email;
    private String customer_phone;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;
    private String status;
    private String environment;
    private String organization_id;
    private String currency;
    private long balance;
    private Boolean deleted;
    private List<BalanceInfo> balances;
    private Boolean allow_withdraw;
    private Boolean locked;
    private String meta_data;
    private Boolean allow_fund;
    private Boolean allow_fund_transfer;
}

class BalanceInfo
{
    public String currency;
    public int balance;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}