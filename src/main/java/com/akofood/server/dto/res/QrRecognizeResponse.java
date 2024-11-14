package com.akofood.server.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QrRecognizeResponse {
    private Boolean use;
    private String info;
}
