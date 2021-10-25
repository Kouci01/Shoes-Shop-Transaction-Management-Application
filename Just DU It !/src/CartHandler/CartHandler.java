package CartHandler;

import java.util.Collections;
import java.util.Vector;

import CartItem.CartItem;
import CartView.viewManageCartForm;
import Product.Product;
import ProductHandler.ProductHandler;


public class CartHandler {
	private static Vector<CartItem> listCartItem = new Vector<CartItem>();

	public static Vector<CartItem> getListCartItem() {
		return listCartItem;
	}

	public static void setListCartItem(Vector<CartItem> listCartItem) {
		CartHandler.listCartItem = listCartItem;
	}
	
	public static CartItem addToCart(int productID,int quantity) {
		// Disini musti validasi buat ngeccek productnya ada ato nggak
		if(productID<1)return null;
		if(quantity <1) return null;
	
		Product item = ProductHandler.getProduct(productID);
		
//		// Buat ngecek Datanya ada di database Product ato nggak
		if(item == null) {
			return null;
		}
		
		for(int i=0;i<listCartItem.size();i++) {
			if(listCartItem.get(i).getProductID() == productID) {
				// Kalo ternyata ud pernah masuk Cart
				return updateStock(productID, quantity);
			}
		}
		// Disini Kalo misalnya quantity valid ato nggak dan belom ada di Cart
		if(item.getStock() < quantity) {
			return null;
		}
		CartHandler.listCartItem.add(new CartItem(productID, quantity));
		Collections.sort(listCartItem);
		return new CartItem(productID, quantity);
		
	}
	
	public static int CalculateTotal() {
		int total = 0;
		for(int i=0;i<listCartItem.size();i++) {
			// Musti cocokin ama wishnu
			Product items = ProductHandler.getProduct(listCartItem.get(i).getProductID());
			total +=(listCartItem.get(i).getQuantity() * items.getPrice());
		}
		
		return total;
	}
	
	public static Boolean deleteItem(int productID) {
		for(int i=0;i<listCartItem.size();i++) {
			if(listCartItem.get(i).getProductID() == productID) {
				listCartItem.remove(i);
				return false;
			}
		}
		return true;
	}
	
	public static void viewAddToCartForm(int employeeID) {
		new CartView.viewAddToCartForm(employeeID);
	}
	
	public static void viewManageCartForm(int employeeID) {
		new viewManageCartForm(employeeID);
	}
	public static void viewPaymentForm(int employeeID) {
		new CartView.viewPaymentForm(employeeID);
	}
	public static CartItem updateStock(int productID,int stock) {
		Product item = ProductHandler.getProduct(productID);
		if(stock==0)return null;
		for(int i=0;i<listCartItem.size();i++) {
			if(listCartItem.get(i).getProductID() == productID) {
				if(item.getStock() < stock) {
					return null;
				}else {
					listCartItem.get(i).setQuantity(stock);
					return new CartItem(productID,stock);
				}		
			}
		}
		return null;
	}
	
	public static void clearCartItemList() {
		listCartItem.clear();
	}

}
