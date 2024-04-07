package ca.sheridancollege.sonejida.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Store {
	private int StoreId;
	private String storeName;
	private String address;
	private String email;
	private String website;
}
