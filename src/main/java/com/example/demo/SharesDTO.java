package com.example.demo;


import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@Document
public class SharesDTO {
    //"Date ","series ","OPEN ","HIGH ","LOW ","PREV. CLOSE ","ltp ","close ","vwap ","52W H ","52W L ","VOLUME ","VALUE ","No of trades "
    @Id
    private String SHARENAME;
    @Field
    private List <ShareDetails> shareDetails;
}
