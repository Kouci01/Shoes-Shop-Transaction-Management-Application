package TransactionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import CartHandler.CartHandler;
import ProductHandler.ProductHandler;
import Transaction.Transaction;
import TransactionItem.TransactionItem;

public class TransactionHandler {

	public static Transaction insertTransaction(int employeeID, String paymentType, int totalMoney) {
		// Input to Transaction Database
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatd = DateTimeFormatter.ISO_DATE_TIME;
		formatd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = currentDateTime.format(formatd);
		Transaction tr1 = new Transaction(0, date, employeeID, paymentType);
		tr1.addTransaction();
		
		// Input to Transaction Item Database
		for(int i=0;i<CartHandler.getListCartItem().size();i++) {
			TransactionItem.addTransactionItem(Transaction.GetLastTransactionID(),CartHandler.getListCartItem().get(i).getProductID(),CartHandler.getListCartItem().get(i).getQuantity());
			//Reduce Product Stock
			ProductHandler.reduceProductStock(CartHandler.getListCartItem().get(i).getProductID(),CartHandler.getListCartItem().get(i).getQuantity());
		}
		CartHandler.clearCartItemList();
		return null;
	}
	public static Vector<Transaction> getAllTransaction(){
		return Transaction.getAllTransaction();
	}
	public static Vector<Transaction> getAllTransactionbyMonthYear(int month, int year){
		return Transaction.getTransactionReport(month, year);
	}
	public static Vector<TransactionItem> getAllTransactionItem (int ID) {
		return TransactionItem.getTransactionItem(ID);
	}
	
	public static void viewTransactionReport() {
		new TransactionView.viewTransactionReports();
	}
	public static void viewTodayTransactionReport() {
		new TransactionView.viewTodayTransaction();
	}
	
	public static Vector<Transaction> getTodayTransaction(){
		
		return Transaction.getTodayTransaction();
	}
}
