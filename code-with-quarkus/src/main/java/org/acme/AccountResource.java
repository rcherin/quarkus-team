package org.acme;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jboss.resteasy.reactive.RestQuery;

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
    public List<AccountTransaction> transactions(@PathParam("acc_id") String accountId,
    @RestQuery String fromDateTime, @RestQuery String toDateTime) {
        System.out.println("Entering transactions");
        System.out.println(fromDateTime);
        System.out.println(toDateTime);
       
        List<AccountTransaction> tranList = new ArrayList<AccountTransaction>();
        return tranList;
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