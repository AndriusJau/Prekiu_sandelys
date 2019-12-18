package sandelys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	
	public static String csvFile = "C:\\Users\\I\\Desktop\\JAVA_uzduotis\\sample.csv";
	public static ArrayList<String> aList = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
		
		readAllLinesFromFile(csvFile);	    
	    ArrayList<Goods> goods = sumUpDublicates(convertToGoods(aList));	    	    
	    
	    ArrayList<Goods> filteredByQuantity = filterByQuantity(goods);
	    System.out.println("Prekiu, kuriu likutis mazesnis uz norodyta kieki, sarasas:\n");
	    if(!filteredByQuantity.isEmpty()) {
	    	System.out.println("Item Name, Code, Quantity, Expiration Date");
	    	for(Goods item: filteredByQuantity){
	    	System.out.println(item.toString());
	    	}	 
	    }
	    else {
	    	System.out.println("Tokiu prekiu nera");
	    }	    
	    
	    ArrayList<Goods> filteredByDate = filterByDate(goods);	    
	    System.out.println("Prekiu, kuriu galiojimo laikas pasibaiges, sarasas:\n");	    
	    if(!filteredByDate.isEmpty()) {
	    	System.out.println("Item Name, Code, Quantity, Expiration Date");	    	
	    	for(Goods item: filteredByDate){
	    	System.out.println(item.toString());
	    	}
	    }
	    else {
	    	System.out.println("Tokiu prekiu nera");
	    }	
	}
	
	public static ArrayList<String> readAllLinesFromFile(String cvsFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(cvsFile));
		String line = "";
		while ((line = br.readLine()) != null) {
			aList.add(line);
		}
		br.close();
	    return aList;
	}
	
	public static ArrayList<Goods> convertToGoods(ArrayList<String> aList) {
	    ArrayList<Goods> goods = new ArrayList<Goods>();
	    aList.remove(0);
	    for(String list : aList) {
	        String[] data = list.split(",");
	        String itemName = data[0];
	        long code = Long.parseLong(data[1]);
	        int quantity = Integer.parseInt(data[2]);
	        String expirationDate = data[3];	        
	        goods.add(new Goods(itemName, code, quantity, expirationDate));
	    }
	    
	    Collections.sort(goods, new Comparator<Goods>() {
	        @Override
	        public int compare(Goods object1, Goods object2) {
	            return object1.itemName.compareTo(object2.itemName);
	        } 
	    });
		return goods;
	}
	
	public static ArrayList<Goods> filterByQuantity(ArrayList<Goods> goods) {
		ArrayList<Goods> filteredList = new ArrayList<Goods>();
		Scanner myScanner = new Scanner(System.in);
		System.out.print("Iveskite prekiu kieki: ");
		int amount = myScanner.nextInt();
		for(Goods item: goods){
	        if (amount > item.quantity) {
	        	filteredList.add(item);	        	
	        }    	   
	    }		
		return filteredList;
	} 
	
	public static ArrayList<Goods> filterByDate(ArrayList<Goods> goods) {
		ArrayList<Goods> filteredList = new ArrayList<Goods>();
		Scanner myScanner = new Scanner(System.in);		
		System.out.print("\nIveskite data (yyyy-mm-dd): ");		
		String date = myScanner.nextLine();
		for(Goods item: goods){
	        if (date.compareTo(item.expirationDate) > 0) {
	        	filteredList.add(item);	        	
	        } 	        
	    }		
		return filteredList;
	}
	
	public static ArrayList<Goods> sumUpDublicates(ArrayList<Goods> goods) {
		ArrayList<Goods> filteredList = new ArrayList<Goods>();
		for (int i = 0; i < goods.size(); i++){
			if (i == 0) {
				filteredList.add(goods.get(i));
			}
			else if (goods.get(i-1).compareTo(goods.get(i)) == 0) {
				int updateQuantity = goods.get(i-1).quantity + goods.get(i).quantity;
				goods.get(i).setQuantity(updateQuantity);			
				filteredList.add(goods.get(i));
				filteredList.remove(goods.get(i-1));				
			}
			else {
				filteredList.add(goods.get(i));	
			}
		}
		return filteredList;
	}
}