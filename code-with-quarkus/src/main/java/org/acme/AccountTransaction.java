package org.acme;

import java.sql.Timestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions", schema="transaction")
public class AccountTransaction extends PanacheEntityBase {
    enum Status {
        pending,
        rejected,
        complete
    }
    enum TransactionType {
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
    public String tx_id;
    public String acc_id;
    public Timestamp tx_ts;

    // TODO: Convert to enum
    public String status;
    public Integer amount;
    public String merchantname;
    public String merchant_id;
    // TODO: Convert to enum
    public String tx_type;
    public String tx_details;

}
