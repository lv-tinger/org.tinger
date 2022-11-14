package org.tinger.couch;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.UpsertOptions;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Created by tinger on 2022-11-11
 */
public class CouchbaseTest {
    public static void main(String[] args) {
        String username = "Administrator";
        String password = "123456";
        String server = "127.0.0.1";
        String bucketName = "tinger-couch";
        Cluster cluster = Cluster.connect(server, username, password);
        Bucket bucket = cluster.bucket(bucketName);
        Collection collection = bucket.defaultCollection();
        UpsertOptions expiry = UpsertOptions.upsertOptions().expiry(Duration.of(5, ChronoUnit.MINUTES));
        collection.upsert("tinger", "hi", expiry);
        System.out.println(collection.get("tinger").contentAs(String.class));
        cluster.disconnect();
    }
}