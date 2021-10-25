package ProductHandler;

import java.util.Vector;

import Product.Product;
import ProductView.viewDeleteProductForm;
import ProductView.viewInsertProductForm;
import ProductView.viewUpdateProductForm;

public class ProductHandler {
	
	public static void viewManageProductForm() {
		new ProductView.viewManageProductForm();
	}
	
	public static void viewInsertProductForm() {
		new viewInsertProductForm();
	}
	
	public static void viewUpdateProductForm() {
		new viewUpdateProductForm();
	}

	public static void viewDeleteProductForm() {
		new viewDeleteProductForm();
	}

	public static Vector<Product> getAllProduct(){
		return Product.getAllProduct();
	}
	
	public static Product addProduct(String name, String description, int price, int stock) {
		Product pro = new Product(0, name, description, price, stock);
		pro.insertProduct();
		return pro;
	}
	
	public static Product updateProduct(int ID, String name, String description, int price, int stock) {
		Product pro = new Product(ID, name, description, price, stock);
		return pro.updateProduct();
	}
	
	public static boolean deleteProduct(int ID) {
		Product pro = new Product(ID, null, null, 0, 0);
		return pro.deleteProduct();
	}
	
	public static void reduceProductStock(int ID, int quantity) {
		Product pro = Product.getProduct(ID);
		int stock = pro.getStock();
		int temp = stock - quantity;
		Product.updateProductStock(ID, temp);
	}
	
	public static Product getProduct(int ID) {
		return Product.getProduct(ID);
	}
	
}
