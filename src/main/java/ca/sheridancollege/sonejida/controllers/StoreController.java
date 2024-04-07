package ca.sheridancollege.sonejida.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.sonejida.beans.Item;
import ca.sheridancollege.sonejida.beans.Store;
import ca.sheridancollege.sonejida.repositories.StoreRepository;
import ca.sheridancollege.sonejida.repositories.ItemRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class StoreController {
	
	private StoreRepository storeRepo;
	private ItemRepository itemRepo;
	
	@GetMapping("/addStore")
	public String goAdd(Model model) {
		model.addAttribute("store", new Store());
		return "addStore.html";
	}
	@PostMapping("/addStore")
	public String processAdd(@ModelAttribute Store store) {
		storeRepo.addStore(store);
		return "redirect:/addStore";  
	}
	
	@GetMapping("/editStore/{id}")
	public String editItem(Model model, @PathVariable int id) {
		Store store = storeRepo.getStoreById(id);
		model.addAttribute("store", store);
		return "editStore.html";
	}
	
	@PostMapping("/editStore")
	public String processEdit(@ModelAttribute Store store) {
		storeRepo.editStore(store);
		return "redirect:/view";  
	}
	
	@GetMapping("/deleteStore/{id}")
	public String deleteStore(Model model, @PathVariable int id) {
		storeRepo.deleteStoreById(id);
		return "redirect:/view";
	}
	
}
