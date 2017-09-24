package com.example.sachinpc.styleomegav10;

/**
 * Created by SachinPC on 7/23/2017.
 */

public class Cart_Items {
  //  private String Name;
    private String ID;
    private String Quantity;
    private String price;
    private String pro_id;
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public Cart_Items(String pro_id, String ID, String quantity,String Price) {
        this.pro_id = pro_id;
        this.ID = ID;
        this.Quantity = quantity;
        this.price=Price;

    }
/*
    public String get() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
*/
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
public double calTotal(){
    double total= Double.parseDouble(this.getPrice())*Double.parseDouble(this.getQuantity());

    return total;
}
    @Override
    public String toString() {
        return "Cart_Items{" +
                "Item_ID='" + pro_id + '\'' +
                ", ID='" + ID + '\'' +
                ", Quantity='" + Quantity + '\'' +
                '}';
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }
}
