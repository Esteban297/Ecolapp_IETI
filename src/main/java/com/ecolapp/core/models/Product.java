package com.ecolapp.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document(collection = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id private Long id;
    @JsonProperty("product_name") @Field("product_name") private String productName;
    private String state;
}
