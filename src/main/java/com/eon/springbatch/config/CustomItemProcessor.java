package com.eon.springbatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.eon.springbatch.entity.Product;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {
	 private Logger logger= LoggerFactory.getLogger(CustomItemProcessor.class);
	@Override
	public Product process(Product item) throws Exception {
		// TODO Auto-generated method stub
		// transform ka logic
		// calculate discounted price
		// original price
		// discount price

		logger.info("item id: "+item.getProductId());
		double discountPer = Double.parseDouble(item.getDiscount());
		double originalPrice = Double.parseDouble(item.getPrice());
		double discount = (discountPer/100)*originalPrice;
		double finalPrice = originalPrice-discount;
		logger.info("discountPer: "+discountPer+
					" originalPrice: "+originalPrice+
					" discount: "+discount+
					" finalPrice: "+finalPrice);
		item.setDiscountedPrice(String.valueOf(finalPrice));
		return item;
	}

}
