package ca.sheridancollege.sonejida.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
	private int ItemId;
	private String itemName;
	private String storeName;
	private double price;
	private double size;
	private String brand;
}
