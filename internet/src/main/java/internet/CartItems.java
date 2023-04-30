package internet;

import java.text.DecimalFormat;

public class CartItems{
    private String itemName;
    private float itemprice;
    private int itemQuantity;
    private float itemTotal;
    private int card_id;

    public CartItems(String itemName, float itemprice, int itemQuantity, int card_id) {
        this.itemName = itemName;
        this.itemprice = itemprice;
        this.itemQuantity = itemQuantity;
        this.itemTotal = itemprice*itemQuantity;
        this.card_id = card_id;
    }
    
    public String getItemName() {
        return itemName;
    }
    public float getItemprice() {
        return itemprice;
    }
    public int getItemQuantity() {
        return itemQuantity;
    }
    public float getItemTotal() {
        return itemTotal;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setItemprice(int itemprice) {
        this.itemprice = itemprice;
    }

    public int getCardId() {
        return card_id;
    }


    public void setItemQuantity(int itemQuantity) throws ClassNotFoundException {
    	System.out.println(this.card_id);
        DbConnection db = new DbConnection();
        db.updateQuantity(this.card_id, itemQuantity);
        this.itemQuantity = itemQuantity;
        setItemTotal(itemprice*itemQuantity);
    }
    public void setItemTotal(float f) {
        this.itemTotal = f;
        
    }

    public String formated() {
        DecimalFormat df = new DecimalFormat("#.##");
        String formatHolder = (df.format(itemTotal));
        return formatHolder;
    }
    
}