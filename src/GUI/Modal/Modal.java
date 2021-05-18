package GUI.Modal;

import GUI.Controller.Controller;

public class Modal {
    private String senderName, senderPhone, receiverName, receiverPhone, address, date;
    private double weight, distance, shippingCost;
    private int shipping;

    public Modal() {
        this.senderName = " ";
        this.senderPhone = " ";
        this.receiverName = " ";
        this.receiverPhone = " ";
        this.address = " ";
        this.weight = 0;
        this.distance = 0;
        this.shippingCost = 0;
        this.shipping = 0;
        this.date = " ";
    }

    public Modal(String senderName, String senderPhone, String receiverName, String receiverPhone,
                 String address, double weight, double distance, double shippingCost, int shipping, String date) {
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.address = address;
        this.weight = weight;
        this.distance = distance;
        this.shippingCost = shippingCost;
        this.shipping = shipping;
        this.date = date;
    }

    //region Setter and Getter
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCost() {
        return shippingCost;
    }

    public void setCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
//endregion

    public void showIn4() {
        System.out.println(getSenderName());
        System.out.println(getSenderPhone());
        System.out.println(getReceiverName());
        System.out.println(getReceiverPhone());
        System.out.println(getAddress());
        System.out.println(getWeight());
        System.out.println(getDistance());
        System.out.println(getCost());
        System.out.println(getShipping());
        System.out.println(getDate());
    }
}
