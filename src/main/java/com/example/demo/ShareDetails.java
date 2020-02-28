package com.example.demo;

import com.couchbase.client.java.repository.annotation.Field;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@Document
public class ShareDetails {
    @Field
    private Double OPEN;
    @Field
    private Double HIGH;
    @Field
    private Double LOW;
    @Field
    private Double PREVIOUS_CLOSE;
    @Field
    private Double CLOSE;
    @Field
    private Double WEEK_HIGH_52;
    @Field
    private Double WEEK_LOW_52;
    @Field
    private Double GAP_UP;
    @Field
    private Date DATE;
    @Field
    private Double PREVIOUSCLOSE_TO_OPEN;
    @Field
    private Double OPEN_TO_HIGH;
    @Field
    private Double OPEN_TO_LOW;
    @Field
    private Double OPEN_TO_CLOSE;
}
