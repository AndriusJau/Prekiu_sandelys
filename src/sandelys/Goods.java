package sandelys;

public class Goods {
	
	public String itemName;
	public long code;
	public int quantity;
	public String expirationDate;
	
	public Goods (String item, long code, int quantity, String date) {
		this.itemName = item;
		this.code = code;
		this.quantity = quantity;
		this.expirationDate = date;
	}
	
	public void setQuantity(int quantity) {
	    this.quantity = quantity;
	}
	
	@Override
	public String toString() {
	    return itemName + ", " + code + ", " + quantity + ", " + expirationDate;
	}
	
	public int compareTo (Goods item) {
	    if (this.itemName.equals(item.itemName) && this.code == item.code && this.expirationDate.equals(item.expirationDate)) {	    	
	    	return this.itemName.compareTo(item.itemName);
	    }
	    else {
	        return (this.quantity - 1) > 0 ? 1 : -1;
	    }		
	}	
}