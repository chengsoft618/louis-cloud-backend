package com.louis.product.entity;

import com.louis.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Eric
 * @date create in 2019/5/26
 */
@Setter
@Getter
@Entity
@Table(name = "pms_product")
public class PmsProduct extends BaseEntity<Long> {

    private static final long serialVersionUID = -5326322245586530418L;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "product_title")
    private String productTitle;

    @Column(name = "seller_id")
    private long sellerId;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "old_price")
    private BigDecimal oldPrice;

    @Column(name = "product_img")
    private String productImg;

    @Column(name = "publish_time")
    private Date publishTime;

    @Column(name = "summary")
    private String summary;

    @Column(name = "discount_time")
    private Date discountTime;

    @Column(name = "discount_end_time")
    private Date discountEndTime;

    @Column(name = "store_up")
    private int storeUp;

    @Column(name = "audit")
    private boolean audit;




}
