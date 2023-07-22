package org.acme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.acme.AccountTransaction.Status;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class TransactionRepository implements PanacheRepository <AccountTransaction> {


    // public List<AccountTransaction> findByAccountIdAndStatus(String accountId, String status) {
    //     return find( "acc_id=?1 AND status=?2", accountId, status).list();
    // }

    // public List<AccountTransaction> findByAccountIdAndFromDate(String accountId, String status, 
    //     LocalDateTime fromDate) {
    //     return find("acc_id=?1 AND status=?2 AND tx_ts >= ?3", accountId, status, fromDate).list();
    // }

    // public List<AccountTransaction> findByAccountIdAndFromToDate(String accountId, String status,
    //         LocalDateTime fromDate, LocalDateTime toDate) {
    //     return find("acc_id=?1 AND status=?2 AND tx_ts BETWEEN ?3 AND ?4",
    //      accountId, status, fromDate, toDate).list();
    // }

    
    public List<AccountTransaction> findByAccountIdStatusAndDatesOptional(
        String accountId, Optional<Status> statusOpt, Optional<String> fromDateTimeOpt, 
        Optional<String> toDateTimeOpt) {
        
            
        Map<String, Object> params = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
        StringBuilder queryBuilder = new StringBuilder("accountId=:accountId");
        params.put("accountId", accountId);
        if (statusOpt.isPresent()) {
            queryBuilder.append(" AND status=:status");
            params.put("status", statusOpt.get());
        }
        if (fromDateTimeOpt.isPresent()) {
            LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeOpt.get(), formatter);
            queryBuilder.append(" AND transactionDateTime >= :fromDateTime");
            params.put("fromDateTime", fromDateTime);
        }
        if (toDateTimeOpt.isPresent()) {
            LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeOpt.get(), formatter);
            queryBuilder.append(" AND transactionDateTime <=:toDateTime");
            params.put("toDateTime", toDateTime);
        }
        System.out.println(queryBuilder.toString());
        return find(queryBuilder.toString(), params).list();
    }




}    