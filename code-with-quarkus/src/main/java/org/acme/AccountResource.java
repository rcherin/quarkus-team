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
import jakarta.ws.rs.core.Response;

import org.acme.AccountTransaction.Status;


@Path("transactions")
public class AccountResource {

    @Inject TransactionRepository transactionRepository;

    @GET
    @Path("/account/{acc_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountTransactions(
        @PathParam("acc_id") String accountId,
        @QueryParam("status") Optional<Status> statusOpt, 
        @QueryParam("fromDateTime") Optional<String> fromDateTimeStrOpt,
        @QueryParam("toDateTime") Optional<String> toDateTimeStrOpt) {

        return Response.ok(transactionRepository.findByAccountIdStatusAndDatesOptional(
            accountId, statusOpt, fromDateTimeStrOpt, toDateTimeStrOpt)).build();
    }


    @GET
    @Path("{tx_id}")
    public Response getByTransactionId(@PathParam("tx_id") String transactionId) {
        return Response.ok(AccountTransaction.findById(transactionId)).build();
    }
    
}