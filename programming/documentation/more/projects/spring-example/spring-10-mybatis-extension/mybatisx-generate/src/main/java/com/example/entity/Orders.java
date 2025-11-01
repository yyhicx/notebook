package com.example.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName orders
 */
@Data
public class Orders implements Serializable {
    private Integer orderId;

    private String orderName;

    private Integer customerId;

    private static final long serialVersionUID = 1L;
}