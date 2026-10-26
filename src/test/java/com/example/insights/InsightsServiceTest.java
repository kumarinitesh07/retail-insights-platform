package com.example.insights;
import org.junit.jupiter.api.Test; import java.time.Instant; import static org.junit.jupiter.api.Assertions.*;
class InsightsServiceTest {
 @Test void sumsBucketsForRangeAndTenant() {
  var service=new InsightsService(new AggregateRepository());
  var result=service.getMetric("retailer-a","123",MetricType.clicks,Instant.parse("2026-10-25T00:00:00Z"),Instant.parse("2026-10-27T00:00:00Z"));
  assertEquals(2000,result.count());
 }
 @Test void tenantIsolationIsAppliedInRepositoryQuery() {
  var service=new InsightsService(new AggregateRepository());
  var result=service.getMetric("retailer-a","123",MetricType.clicks,Instant.parse("2026-10-25T00:00:00Z"),Instant.parse("2026-10-26T00:00:00Z"));
  assertEquals(1200,result.count());
 }
}
