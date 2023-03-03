package com.ecolapp.core.models.api_request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductStateRequest {
    private Long id;
    private String state;
}
