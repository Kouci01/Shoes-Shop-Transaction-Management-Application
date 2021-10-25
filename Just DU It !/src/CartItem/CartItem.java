package CartItem;

public class CartItem implements Comparable<CartItem>{
	private int productID;
	private int quantity;
	
	public CartItem(int productID, int quantity) {
		this.productID = productID;
		this.quantity = quantity;
	}
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public int compareTo(CartItem o) {
		return this.getProductID() - o.getProductID();
	}
	
	

}
