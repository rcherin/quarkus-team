package org.acme;

import java.util.Optional;

import org.acme.AccountTransaction.Status;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.quarkus.panache.common.Page;


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

     //example to test pagination http://localhost:8080/transactions/4/50
    @GET
    @Path("{pageNumber}/{pageSize}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionByPage(@PathParam("pageNumber") Integer pageNumber,@PathParam("pageSize") Integer pageSize) {
        return Response.ok(transactionRepository.findByTxnPagination(pageNumber,pageSize)).build();
    }
    
}
