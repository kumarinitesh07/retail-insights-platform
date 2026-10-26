package com.example.insights;
import java.time.Instant;
public record MetricResponse(String tenantId, String campaignId, String metric, Instant startTime, Instant endTime, long count) {}
