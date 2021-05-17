package BackEnd_Remastered;

import java.util.ArrayList;
import java.util.Date;

abstract public class AbsOrder {
    //Attribute
    protected String senderName;
    protected String senderPhone;
    protected String receiverName;
    protected String receiverPhone;
    protected String address;
    protected String date;

    protected double weight;
    protected double distance;
    protected double shippingCost; // phi van chuyen
    protected int shippingMethod; // cach van chuyen


    // Constructor
    public AbsOrder() {
    }

    public AbsOrder(String senderName, String senderPhone, String receiverName, String receiverPhone,
                    String address, String date, double weight, double distance) {
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.address = address;
        this.date = date;
        this.weight = weight;
        this.distance = distance;
    }

    //Getter setter

    public String getSenderName() {
        return senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }

    public double getDistance() {
        return distance;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public int getShippingMethod() {
        return shippingMethod;
    }

    // Abs Method
    protected abstract void calculateShippingCost();

    //Method
    public void printInfo(){
        System.out.println(getSenderName());
        System.out.println(getSenderPhone());
        System.out.println(getReceiverName());
        System.out.println(getReceiverPhone());
        System.out.println(getAddress());
        System.out.println(getWeight());
        System.out.println(getDistance());
        System.out.println(getShippingCost());
        System.out.println(getShippingMethod());
        System.out.println(getDate());
    }

}
