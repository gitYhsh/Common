package com.juxue.web.controller;

import com.juxue.demo.ItemRepository;
import com.juxue.entity.Item;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: common_web
 * Created by yhsh on 2020/5/30 16:09
 * version 2.0
 * 方法说明
 */
@RestController
public class TestController {


	@Autowired
	private ItemRepository itemRepository;

	@GetMapping(value = "Demo")
	public Object insert() {
		Item item = new Item(8L, "杨洪升", "手机", "小米", 2999.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg");
		Item i = itemRepository.save(item);

		return i;
		//elasticsearchOperations.
		//reactive.save(item);
	}
	@GetMapping(value = "Demo1")
	public void insertList() {
		List<Item> list = new ArrayList<>();
		list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3999.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg"));
		list.add(new Item(3L, "华为META20", "手机", "华为", 4999.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg"));
		list.add(new Item(4L, "iPhone X", "手机", "iPhone", 5100.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg"));
		list.add(new Item(5L, "iPhone XS", "手机", "iPhone", 5999.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg"));
		// 接收对象集合，实现批量新增
		itemRepository.saveAll(list);
	}
	@GetMapping(value = "Demo2")
	public void query() {

		// 查询全部，并按照价格降序排序
		Iterable<Item> items = itemRepository.findAll(Sort.by("price").descending());
		items.forEach(item -> System.out.println("item = " + item));
	}

	@GetMapping(value = "Demo3")
	public void queryByPriceBetween() {
		// 根据价格区间查询
		List<Item> list = itemRepository.findByPriceBetween(5000.00, 6000.00);
		list.forEach(item -> System.out.println("item = " + item));
	}
	@GetMapping(value = "Demo4")
	public void queryByNoy() {
		// 根据价格区间查询
		Pageable page = PageRequest.of(0,1);
		List<Item> list = itemRepository.findItemByTitleIsNotIn("杨洪升",page);
		list.forEach(item -> System.out.println("item = " + item));

	}








}
