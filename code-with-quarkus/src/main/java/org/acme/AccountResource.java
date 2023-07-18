package org.acme;

import java.util.ArrayList;
import java.util.List;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/transactions")
public class AccountResource {

    @GET
    @Path("/account/{acc_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountTransaction> transactions(@PathParam("acc_id") String accountId) {
        System.out.println("Entering transactions");
        List<AccountTransaction> tranList = new ArrayList<AccountTransaction>();
        AccountTransaction t1 = new AccountTransaction();
        AccountTransaction t2 = new AccountTransaction();
        tranList.add(t1);
        tranList.add(t2);
        return tranList;
    }
}