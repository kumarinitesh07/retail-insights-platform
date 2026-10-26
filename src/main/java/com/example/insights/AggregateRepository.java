package com.example.insights;
import org.springframework.stereotype.Repository;
import java.time.Instant; 
import java.util.List;

@Repository public class AggregateRepository {
 // replace with MySQL query indexed by tenant_id, campaign_id, bucket_start.
 
 private final List<AggregateBucket> buckets = List.of(
  new AggregateBucket("retailer-a","123",Instant.parse("2026-10-25T00:00:00Z"),1200,9000,180),
  new AggregateBucket("retailer-a","123",Instant.parse("2026-10-26T00:00:00Z"),800,6500,120),
  new AggregateBucket("retailer-b","123",Instant.parse("2026-10-25T00:00:00Z"),500,4000,70));
 
 public long sum(String tenantId,String campaignId,MetricType metric,Instant start,Instant end) {
  
  return buckets.stream().filter(b->b.tenantId().equals(tenantId)).filter(b->b.campaignId().equals(campaignId))
   .filter(b->!b.bucketStart().isBefore(start)).filter(b->b.bucketStart().isBefore(end))
   .mapToLong(b->b.valueFor(metric)).sum();
  
 }
}
