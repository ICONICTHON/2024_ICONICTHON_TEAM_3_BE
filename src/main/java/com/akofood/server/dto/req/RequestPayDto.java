package com.akofood.server.dto.req;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class RequestPayDto {
    private String orderUid;
    private String itemName;
    private String buyerName;
    private Long paymentPrice;
//    private String buyerEmail;
//    private String buyerAddress;

//    @Builder
//    public RequestPayDto(String orderUid, String itemName, String buyerName, Long paymentPrice, String buyerEmail, String buyerAddress) {
//        this.orderUid = orderUid;
//        this.itemName = itemName;
//        this.buyerName = buyerName;
//        this.paymentPrice = paymentPrice;
//        this.buyerEmail = buyerEmail;
//        this.buyerAddress = buyerAddress;
//    }

    @Builder
    public RequestPayDto(String orderUid, String itemName, String buyerName, Long paymentPrice) {
        this.orderUid = orderUid;
        this.itemName = itemName;
        this.buyerName = buyerName;
        this.paymentPrice = paymentPrice;
//        this.buyerEmail = buyerEmail;
//        this.buyerAddress = buyerAddress;
    }
}

