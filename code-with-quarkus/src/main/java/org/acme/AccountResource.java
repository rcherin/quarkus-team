package org.acme;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;

@Path("/transactions")
public class AccountResource {

    @GET
    @Path("/account/{acc_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountTransaction> getAccountTransactions(@PathParam("acc_id") String accountId) {
        List<AccountTransaction> transactions = AccountTransaction.list("acc_id", accountId);

        return transactions;
    }


    @GET
    @Path("{tx_id}")
    public AccountTransaction getByTransactionId(@PathParam("tx_id") String transactionId) {
        AccountTransaction transaction = AccountTransaction.find("tx_id", transactionId).firstResult();
        if (transaction == null) {
            throw new WebApplicationException("Transaction not found", 404);
        }
        return transaction;

    }

    


}