package com.foodordering.food;

import java.util.List;
import com.foodordering.model.FoodItem;

public interface IMenuService {
	void viewMenu();

	void addItem();

	void removeItem();

	List<FoodItem> getMenu();
	
	void setDiscountPercentage(double percent);
	
	
}
