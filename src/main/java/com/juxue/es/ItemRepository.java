package com.juxue.es;

import com.juxue.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

/**
 * Description: common_web
 * Created by yhsh on 2020/5/30 16:07
 * version 2.0
 * 方法说明
 */
public interface ItemRepository extends ElasticsearchCrudRepository<Item,Long> {

	List<Item> findByPriceBetween(double price1, double price2);

	List<Item> findItemByTitleIsNotIn(String title,Pageable pageable);


}
