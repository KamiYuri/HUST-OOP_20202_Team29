package BackEnd;

public class Order {
    private String senderName, senderPhone, receiverName, receiverPhone, address, date;
    private int weight, distance, shipping, shippingCost;

    public Order() {
        this.senderName = "";
        this.senderPhone = "";
        this.receiverName = "";
        this.receiverPhone = "";
        this.address = "";
        this.weight = 0;
        this.distance = 0;
        this.shippingCost = 0;
        this.shipping = 0;
        this.date = "";
    }

    public Order(String senderName, String senderPhone, String receiverName, String receiverPhone,
                 String address, int weight, int distance, int shipping, String date) {
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.address = address;
        this.weight = weight;
        this.distance = distance;
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

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getCost() {
        return shippingCost;
    }

    public void setCost(int cost) {
        this.shippingCost = cost;
    }

    public int getWay() {
        return shipping;
    }

    public void setWay(int way) {
        this.shipping = way;
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
        System.out.println(getWay());
        System.out.println(getDate());
    }
}
