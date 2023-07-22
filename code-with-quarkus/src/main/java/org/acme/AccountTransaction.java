package org.acme;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions", schema="transaction")
public class AccountTransaction extends PanacheEntityBase {
    public enum Status {
        pending,
        rejected,
        complete
    }
    public enum TransactionType {
        pos,
        atm,
        transfer,
        online,
        dd
    }
/* 
    tx_id        | character varying(40)       |           | not null |         | extended |              |
    acc_id       | character varying(40)       |           | not null |         | extended |              |
    tx_ts        | timestamp without time zone |           | not null |         | plain    |              |
    status       | character varying(40)       |           | not null |         | extended |              |
    amount       | integer                     |           | not null |         | plain    |              |
    merchantname | text                        |           |          |         | extended |              |
    merchant_id  | character varying(40)       |           |          |         | extended |              |
    tx_type      | character varying(40)       |           |          |         | extended |              |
    tx_details   | text                        |           |          |         | extended |              |
   */
    @Id
    //TODO: Convert to type UUID
    @Column(name="tx_id", length=40, nullable = false)
    public String transactionId;
    
    @Column(name="acc_id", length=40, nullable = false)
    public String accountId;

    @Column(name="tx_ts", nullable = false)
    public LocalDateTime transactionDateTime;

    @Enumerated(EnumType.STRING )
    @Column (nullable = false, length = 40)
    public Status status;

    @Column (nullable = false)
    public Integer amount;
    
    @Column(name="merchantname")
    public String merchantName;

    @Column(name="merchant_id")
    public String merchantId;

    @Column(name="tx_type")
    @Enumerated(EnumType.STRING)
    public TransactionType transactionType;

    @Column(name="tx_details")
    public String transactionDetails;
}
