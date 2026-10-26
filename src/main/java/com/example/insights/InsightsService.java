package com.example.insights;
import org.springframework.stereotype.Service; import java.time.Instant;
@Service public class InsightsService {
 private final AggregateRepository repository;
 public InsightsService(AggregateRepository repository) { this.repository=repository; }
 public MetricResponse getMetric(String tenantId,String campaignId,MetricType metric,Instant start,Instant end) {
  if(tenantId==null || tenantId.isBlank()) throw new IllegalArgumentException("Tenant identity is required");
  if(start==null || end==null || !start.isBefore(end)) throw new IllegalArgumentException("startTime must be earlier than endTime");
  return new MetricResponse(tenantId,campaignId,metric.name(),start,end,repository.sum(tenantId,campaignId,metric,start,end));
 }
}
