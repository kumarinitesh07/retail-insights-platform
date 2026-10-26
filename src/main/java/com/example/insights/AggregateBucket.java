package com.example.insights;
import java.time.Instant;
public record AggregateBucket(String tenantId, String campaignId, Instant bucketStart, long clicks, long impressions, long clickToBasket) {
 
 public long valueFor(MetricType metric) { return switch(metric) {
  case clicks -> clicks; case impressions -> impressions; case clickToBasket -> clickToBasket;
 };
                                         }
}
