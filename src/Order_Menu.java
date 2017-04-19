

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Range;

public class Order_Menu {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Range(min=0)
	private int order_id;
	 private String menu_name;
	 private int menu_quantity;
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public int getMenu_quantity() {
		return menu_quantity;
	}
	public void setMenu_quantity(int menu_quantity) {
		this.menu_quantity = menu_quantity;
	}
}
