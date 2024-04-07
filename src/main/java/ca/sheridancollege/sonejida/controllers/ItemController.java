package ca.sheridancollege.sonejida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.sonejida.beans.Item;
import ca.sheridancollege.sonejida.beans.Store;
import ca.sheridancollege.sonejida.repositories.ItemRepository;
import ca.sheridancollege.sonejida.repositories.StoreRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ItemController {
	
	private ItemRepository itemRepo;
	private StoreRepository storeRepo;
	@GetMapping("/")
	public String home() {
	return "home.html";
	} 
	
	@GetMapping("/addItem")
	public String goAdd(Model model) {
		model.addAttribute("item", new Item());
		return "addItem.html";
	}
	@PostMapping("/addItem")
	public String processAdd(@ModelAttribute Item item) {
		itemRepo.addItem(item);
		return "redirect:/addItem";  
	}
	
	@GetMapping("/view")
	public String viewStore(Model model) {
		
		model.addAttribute("myitems", itemRepo.getItems2());
		model.addAttribute("mystores", storeRepo.getStores2());
		return "viewPage.html";
	}
	
	@GetMapping("/editItem/{id}")
	public String editItem(Model model, @PathVariable int id) {
		Item item = itemRepo.getItemById(id);
		model.addAttribute("item", item);
		return "editItem.html";
	}
	
	@PostMapping("/editItem")
	public String processEdit(@ModelAttribute Item item) {
		itemRepo.editItem(item);
		return "redirect:/view";  
	}
	
	@GetMapping("/deleteItem/{id}")
	public String deleteItem(Model model, @PathVariable int id) {
		itemRepo.deleteItemById(id);
		return "redirect:/view";
	}
}
