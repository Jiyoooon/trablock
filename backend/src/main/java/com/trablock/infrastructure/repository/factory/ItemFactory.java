package com.trablock.infrastructure.repository.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.trablock.domain.Item;

public class ItemFactory
{
	public static Item create(ResultSet rs) throws SQLException
	{
		if (rs == null) return null;
		Item item = new Item();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setCategory(rs.getString("category"));
		item.setExplanation(rs.getString("explanation"));
		item.setAvailable(rs.getBoolean("available"));
		item.setSeller(rs.getLong("seller"));
		item.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
		item.setImage(rs.getString("image"));

		return item;
	}
}
