package ca.sheridancollege.sonejida.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.sonejida.beans.Store;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class StoreRepository {

	private NamedParameterJdbcTemplate jdbc;
	

	public void addStore(Store store) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO stores "
				+ "(store_name, address, email, website) VALUES"
				+ "(:sna, :ad, :em, :wb)";
		parameters.addValue("sna", store.getStoreName());
		parameters.addValue("ad", store.getAddress());
		parameters.addValue("em", store.getEmail());
		parameters.addValue("wb", store.getWebsite());
		jdbc.update(query,parameters);
		
	}
	
	public ArrayList<Store> getStores() {
		ArrayList<Store> stores = new ArrayList<Store>();
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM stores";
		List<Map<String, Object>> rows =
				jdbc.queryForList(query, parameters);
		for(Map<String, Object> row : rows) {
			Store s = new Store();
			s.setStoreName((String)row.get("store_name"));
			s.setAddress((String)row.get("address"));
			s.setEmail((String)row.get("email"));
			s.setWebsite((String)row.get("website"));
			
			stores.add(s); 
		}
		return stores;
	}
	
	public ArrayList<Store> getStores2() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM stores";
		ArrayList<Store> stores = 
				(ArrayList<Store>) jdbc.query(query, parameters,
		new BeanPropertyRowMapper<Store>(Store.class));
		return stores;
		}
	public Store getStoreById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM stores WHERE storeId=:dars";
		parameters.addValue("dars", id);
		ArrayList<Store> stores = 
				(ArrayList<Store>) jdbc.query(query, parameters,
		new BeanPropertyRowMapper<Store>(Store.class));
		
		if(stores.size()>0)
			return stores.get(0);
		else
			return null;
	}
	
	public void editStore(Store store) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE stores SET store_name=:sna, address=:ad, email=:em, website=:wb WHERE storeId=:id";
		parameters.addValue("id", store.getStoreId());
		parameters.addValue("sna", store.getStoreName());
		parameters.addValue("ad", store.getAddress());
		parameters.addValue("em", store.getEmail());
		parameters.addValue("wb", store.getWebsite());
		jdbc.update(query, parameters);
	}

	public void deleteStoreById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM stores WHERE storeId=:dars";
		parameters.addValue("dars", id);
		jdbc.update(query, parameters);
	}
	
}
