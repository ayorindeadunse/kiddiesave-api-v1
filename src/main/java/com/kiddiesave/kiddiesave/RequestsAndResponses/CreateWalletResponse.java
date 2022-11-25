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
    /*"wallet_id": "637fb336bd97535242302452",
        "customer_email": "adunses3@gmail.com",
        "customer_phone": "",
        "created_at": "2022-11-24T18:08:54.791131248Z",
        "updated_at": "2022-11-24T18:08:54.791131317Z",
        "deleted_at": "0001-01-01T00:00:00Z",
        "status": "active",
        "environment": "test",
        "organization_id": "63578d6198bf7dc2408bc227",
        "currency": "NGN",
        "balance": 0,
        "deleted": false,
        "balances": [
            {
                "currency": "NGN",
                "balance": 0
            },
            {
                "currency": "USD",
                "balance": 0
            }
        ],
        "allow_withdraw": true,
        "locked": false,
        "meta_data": null,
        "allow_fund": true,
        "allow_wallet_transfer": true,
        "allow_bank_transfer": true*/
}

class BalanceInfo
{
    public String currency;
    public long balance;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}