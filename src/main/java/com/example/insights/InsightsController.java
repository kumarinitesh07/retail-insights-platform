package com.example.insights;
import org.springframework.format.annotation.DateTimeFormat; 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*; 
import org.springframework.web.server.ResponseStatusException;
import java.time.Instant;
\
@RestController @RequestMapping("/ad") public class InsightsController {
 private final InsightsService service;
 public InsightsController(InsightsService service) 
 { 
  this.service=service; 
 }
 
 @GetMapping("/{campaignId}/{metric}")
 public MetricResponse getMetric(@RequestHeader("X-Tenant-Id") String tenantId,@PathVariable String campaignId,@PathVariable String metric,
  @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) Instant startTime,
  @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) Instant endTime) {
  final MetricType type;
  
  try { 
   type=MetricType.valueOf(metric); 
  } 
  catch(IllegalArgumentException ex) 
   { 
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unsupported metric"); 
   }
  try { // production must derive tenant from validated JWT/OAuth2 claims.
   return service.getMetric(tenantId,campaignId,type,startTime,endTime);
  } 
  catch(IllegalArgumentException ex) 
   {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage()); 
   }
 }
}
