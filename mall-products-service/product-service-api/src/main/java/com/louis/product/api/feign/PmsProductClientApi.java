package com.louis.product.api.feign;

import com.louis.product.api.dto.PmsProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2019/9/27
 * Description:
 */
@FeignClient(value = "product-server-A")
public interface PmsProductClientApi  {

    @RequestMapping("product/findByProductId")
    PmsProductDto findByProductId(@RequestParam Long id);

    @RequestMapping("product/findBySellerName")
    List<PmsProductDto> findBySellerName(@RequestParam("sellerName") String sellerName);


}
