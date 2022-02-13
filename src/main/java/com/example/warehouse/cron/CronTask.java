package com.example.warehouse.cron;

import com.example.warehouse.dto.ProductDto;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CronTask {
    private final DashboardService dashboardService;

    // CronTask
    @Scheduled(fixedDelay = 86_400_000L, initialDelay = 5000L)
    private void checkExpirationDateProducts() {
        Response response = dashboardService.getAllExpireDateSoonProducts();
        List<ProductDto> productDtos = (List<ProductDto>) response.getData();
        for (ProductDto productDto : productDtos) {
            /*

                PushNotification push = new PushNotification();
                push.sendMessage(dto.productName);

             */
        }


    }


}
