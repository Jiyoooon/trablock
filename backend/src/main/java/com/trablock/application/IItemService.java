package com.trablock.application;

import org.springframework.transaction.annotation.Transactional;

import com.trablock.domain.Item;

import java.util.List;

public interface IItemService {
	List<Item> list();
	List<Item> getByUser(int uid);
	Item get(long id);

	@Transactional
	Item register(Item item);

	@Transactional
	Item update(Item item);

	@Transactional
	Item delete(long id);
}