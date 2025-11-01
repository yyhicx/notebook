package com.example.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName customers
 */
@Data
public class Customers implements Serializable {
    private Integer customerId;

    private String customerName;

    private static final long serialVersionUID = 1L;
}