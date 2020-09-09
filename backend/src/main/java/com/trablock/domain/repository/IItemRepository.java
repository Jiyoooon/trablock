package com.trablock.domain.repository;

import org.springframework.transaction.annotation.Transactional;

import com.trablock.domain.Item;

import java.util.List;

public interface IItemRepository
{
	List<Item> list();
	List<Item> getByUser(final long userId);
	Item get(long id);

	@Transactional
	long create(Item item);

	@Transactional
	int update(Item item);

	@Transactional
	int delete(long id);
}
