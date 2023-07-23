package org.acme;

import java.util.List;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;

import org.acme.AccountTransaction.Status;


@Path("transactions")
public class AccountResource {

    @Inject TransactionRepository transactionRepository;

    @GET
    @Path("/account/{acc_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountTransaction> getAccountTransactions(
        @PathParam("acc_id") String accountId,
        @QueryParam("status") Optional<Status> statusOpt, 
        @QueryParam("fromDateTime") Optional<String> fromDateTimeStrOpt,
        @QueryParam("toDateTime") Optional<String> toDateTimeStrOpt) {

        return transactionRepository.findByAccountIdStatusAndDatesOptional(
            accountId, statusOpt, fromDateTimeStrOpt, toDateTimeStrOpt);
    }


    @GET
    @Path("{tx_id}")
    public AccountTransaction getByTransactionId(@PathParam("tx_id") String transactionId) {
        AccountTransaction transaction = AccountTransaction.findById(transactionId);
        if (transaction == null) {
            throw new WebApplicationException("Transaction not found", 404);
        }
        return transaction;
    }
    
}