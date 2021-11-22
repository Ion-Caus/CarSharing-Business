package com.sep.carsharingbusiness.logic.loginImpl;

import com.sep.carsharingbusiness.graphQLServices.IAccountService;
import com.sep.carsharingbusiness.logic.IUserLogic;
import com.sep.carsharingbusiness.model.Account;
import com.sep.carsharingbusiness.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class UserLogic implements IUserLogic {
    private final IAccountService accountService;

    @Autowired
    public UserLogic(IAccountService accountService) {
        this.accountService = accountService;
    }

    @SessionScope
    public Customer login(Account account) throws IOException, InterruptedException, IllegalAccessException, NoSuchAlgorithmException {
        Account toCompare = accountService.getAccount(account.getUsername());
        if (toCompare == null) {
            throw new IllegalAccessException("No such username.");
        }
//        account.setPassword(
//                hash(account.getPassword())
//        );

        if (account.getPassword().equals(toCompare.getPassword())) {
            return toCompare.customer;
        }
        throw new IllegalAccessException("Incorrect password.");
    }

    @SessionScope
    public Customer register(Account account) throws IOException, InterruptedException, NoSuchAlgorithmException {
//        account.setPassword(
//                hash(account.getPassword())
//        );
        Account registered = accountService.addAccount(account);
        return registered.customer;
    }

    private String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] passBytes = password.getBytes();
        byte[] passHash = sha256.digest(passBytes);
        return Arrays.toString(passHash);
    }
}
