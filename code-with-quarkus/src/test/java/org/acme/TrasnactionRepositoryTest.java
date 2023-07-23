package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.acme.AccountTransaction.Status;
import org.acme.AccountTransaction.TransactionType;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class TrasnactionRepositoryTest {

    @Inject TransactionRepository transactionRepository;

    @Test
    @Transactional
    public void testTransactionRepository() {
        AccountTransaction t = new AccountTransaction();
        t.transactionId =  "bcb6cce0-17e9-4cd8-958d-2552f9c3fd41";
        t.accountId = "ADUG99161510903217";
        t.transactionDateTime = LocalDateTime.parse("2023-01-15T10:54:46");
        t.status = Status.rejected;
        t.amount= 2563;
        t.merchantName = "Rose, Stewart and Peterson";
        t.merchantId = "620-82965-1";
        t.transactionType = TransactionType.atm;
        t.transactionDetails = "Agent follow about.";

        t.persist();

        AccountTransaction t1 = AccountTransaction.findById(t.transactionId);
        assertNotNull(t1);

        AccountTransaction t2 = transactionRepository.findByAccountIdStatusAndDatesOptional(t.accountId, Optional.empty(), 
        Optional.empty(), Optional.empty()).get(0);
        assertEquals(t.accountId, t2.accountId);
        assertEquals(t.status, t2.status);
    }
}
