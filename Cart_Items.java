package com.example.sachinpc.styleomegav10;

/**
 * Created by SachinPC on 7/23/2017.
 */

public class Cart_Items {
    private String Name;
    private String ID;
    private String Quantity;
    private String price;
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public Cart_Items(String name, String ID, String quantity,String Price) {
        Name = name;
        this.ID = ID;
        Quantity = quantity;
        this.price=Price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

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
                "Name='" + Name + '\'' +
                ", ID='" + ID + '\'' +
                ", Quantity='" + Quantity + '\'' +
                '}';
    }
}
