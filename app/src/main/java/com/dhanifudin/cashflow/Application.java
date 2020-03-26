package com.dhanifudin.cashflow;

import com.dhanifudin.cashflow.models.Account;
import com.dhanifudin.cashflow.models.Session;

public class Application extends android.app.Application {

    private static Account account;
    private static Session session;

    @Override
    public void onCreate() {
        super.onCreate();
        session = new Session(this);
        account = new Account("Nadia Nadine Bunga");

    }
    public static Session getSession(){

        return session;
    }

    public static Account getAccount() {
        return account;
    }
}
