package org.acme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.acme.AccountTransaction.Status;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class TransactionRepository implements PanacheRepository <AccountTransaction> {
    
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
        return find(queryBuilder.toString(), params).list();
    }




}    