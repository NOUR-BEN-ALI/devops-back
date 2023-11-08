package tn.esprit.rh.achat;

import com.zaxxer.hikari.metrics.prometheus.PrometheusHistogramMetricsTrackerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;


@RestController
@EnableAspectJAutoProxy
@EnableScheduling
@SpringBootApplication
public class AchatApplication {
    final static Logger logger = LoggerFactory.getLogger(PrometheusHistogramMetricsTrackerFactory.class);
    public static void main(String[] args) {
        SpringApplication.run(AchatApplication.class, args);
    }


@GetMapping("/something")
public ResponseEntity<String> createLog() {
    logger.warn("just checking");
    return  ResponseEntity.ok().body("All OK");
}}