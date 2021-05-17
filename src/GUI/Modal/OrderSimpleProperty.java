package GUI.Modal;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderSimpleProperty {
    private SimpleStringProperty senderNameProperty, senderPhoneProperty, receiverNameProperty, receiverPhoneProperty,
            addressProperty, dateProperty;
    private SimpleIntegerProperty weightProperty, distanceProperty, costProperty, wayProperty;

    public OrderSimpleProperty() {
    }

    public OrderSimpleProperty(Order order) {
        this.senderNameProperty.set(order.getSenderName());
        this.senderPhoneProperty.set(order.getSenderPhone());
        this.receiverNameProperty.set(order.getReceiverName());
        this.receiverPhoneProperty.set(order.getReceiverPhone());
        this.addressProperty.set(order.getAddress());
        this.weightProperty.set(order.getWeight());
        this.distanceProperty.set(order.getDistance());
        this.costProperty.set(order.getCost());
        this.wayProperty.set(order.getWay());
        this.dateProperty.set(order.getDate());
    }

    public static OrderSimpleProperty convertNorToProp(Order order) {
        return new OrderSimpleProperty(order);
    }

    public String getSenderNameProperty() {
        return senderNameProperty.get();
    }

    public SimpleStringProperty senderNamePropertyProperty() {
        return senderNameProperty;
    }

    public void setSenderNameProperty(String senderNameProperty) {
        this.senderNameProperty.set(senderNameProperty);
    }

    public String getSenderPhoneProperty() {
        return senderPhoneProperty.get();
    }

    public SimpleStringProperty senderPhonePropertyProperty() {
        return senderPhoneProperty;
    }

    public void setSenderPhoneProperty(String senderPhoneProperty) {
        this.senderPhoneProperty.set(senderPhoneProperty);
    }

    public String getReceiverNameProperty() {
        return receiverNameProperty.get();
    }

    public SimpleStringProperty receiverNamePropertyProperty() {
        return receiverNameProperty;
    }

    public void setReceiverNameProperty(String receiverNameProperty) {
        this.receiverNameProperty.set(receiverNameProperty);
    }

    public String getReceiverPhoneProperty() {
        return receiverPhoneProperty.get();
    }

    public SimpleStringProperty receiverPhonePropertyProperty() {
        return receiverPhoneProperty;
    }

    public void setReceiverPhoneProperty(String receiverPhoneProperty) {
        this.receiverPhoneProperty.set(receiverPhoneProperty);
    }

    public String getAddressProperty() {
        return addressProperty.get();
    }

    public SimpleStringProperty addressPropertyProperty() {
        return addressProperty;
    }

    public void setAddressProperty(String addressProperty) {
        this.addressProperty.set(addressProperty);
    }

    public String getDateProperty() {
        return dateProperty.get();
    }

    public SimpleStringProperty datePropertyProperty() {
        return dateProperty;
    }

    public void setDateProperty(String dateProperty) {
        this.dateProperty.set(dateProperty);
    }

    public int getWeightProperty() {
        return weightProperty.get();
    }

    public SimpleIntegerProperty weightPropertyProperty() {
        return weightProperty;
    }

    public void setWeightProperty(int weightProperty) {
        this.weightProperty.set(weightProperty);
    }

    public int getDistanceProperty() {
        return distanceProperty.get();
    }

    public SimpleIntegerProperty distancePropertyProperty() {
        return distanceProperty;
    }

    public void setDistanceProperty(int distanceProperty) {
        this.distanceProperty.set(distanceProperty);
    }

    public int getCostProperty() {
        return costProperty.get();
    }

    public SimpleIntegerProperty costPropertyProperty() {
        return costProperty;
    }

    public void setCostProperty(int costProperty) {
        this.costProperty.set(costProperty);
    }

    public int getWayProperty() {
        return wayProperty.get();
    }

    public SimpleIntegerProperty wayPropertyProperty() {
        return wayProperty;
    }

    public void setWayProperty(int wayProperty) {
        this.wayProperty.set(wayProperty);
    }
}
