package ca.sheridancollege.sonejida.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.sonejida.beans.Item;
import ca.sheridancollege.sonejida.beans.Store;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ItemRepository {
	
	private NamedParameterJdbcTemplate jdbc;
	
	public void addItem(Item item) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO items "
				+ "(item_name, store_name, price, size, brand) VALUES"
				+ "(:ina, :sna, :pr, :si, :br)";
		parameters.addValue("ina", item.getItemName());
		parameters.addValue("sna", item.getStoreName());
		parameters.addValue("pr", item.getPrice());
		parameters.addValue("si", item.getSize());
		parameters.addValue("br", item.getBrand());
		jdbc.update(query,parameters);
		
	}
	
	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM items";
		List<Map<String, Object>> rows =
				jdbc.queryForList(query, parameters);
		for(Map<String, Object> row : rows) {
			Item i = new Item();
			i.setItemName((String)row.get("item_name"));
			i.setStoreName((String)row.get("store_name"));
			i.setPrice((double)row.get("price"));
			i.setSize((double)row.get("size"));
			i.setBrand((String)row.get("brand"));
			
			items.add(i); 
		}
		return items;
	}
	
	public ArrayList<Item> getItems2() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM items";
		ArrayList<Item> items = 
				(ArrayList<Item>) jdbc.query(query, parameters,
		new BeanPropertyRowMapper<Item>(Item.class));
		return items;
		}
	
	public Item getItemById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM items WHERE itemId=:dars";
		parameters.addValue("dars", id);
		ArrayList<Item> items = 
				(ArrayList<Item>) jdbc.query(query, parameters,
		new BeanPropertyRowMapper<Item>(Item.class));
		
		if(items.size()>0)
			return items.get(0);
		else
			return null;
	}
	
	public void editItem(Item item) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE items SET item_name=:ina, store_name=:sna, price=:pr, size=:si, brand=:br WHERE	 itemId=:id";
		parameters.addValue("id", item.getItemId());
		parameters.addValue("ina", item.getItemName());
		parameters.addValue("sna", item.getStoreName());
		parameters.addValue("pr", item.getPrice());
		parameters.addValue("si", item.getSize());
		parameters.addValue("br", item.getBrand());
		jdbc.update(query, parameters);
	}

	public void deleteItemById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM items WHERE itemId=:dars";
		parameters.addValue("dars", id);
		jdbc.update(query, parameters);
	}
}
