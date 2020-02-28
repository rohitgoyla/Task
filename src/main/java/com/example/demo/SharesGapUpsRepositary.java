package com.example.demo;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "shares", viewName = "all")
interface SharesGapUpsRepositary extends CouchbaseRepository<SharesGapUps, String> {
}
