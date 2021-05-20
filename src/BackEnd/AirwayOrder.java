//Nguyễn Hải Anh

//Lớp định nghĩa đơn hàng Hàng không

package BackEnd;

public class AirwayOrder extends AbsOrder {

    @Override
    protected void calculateShippingCost() {
        shippingCost = distance*100000 + weight*100000 + 200000;
        if(shippingCost >= 10000000) {
            shippingCost = 9999999;
        }
    }

    //Constructor
    public AirwayOrder(String senderName, String senderPhone, String receiverName, String receiverPhone,
                       String address, String date, double weight, double distance) {
        super(senderName, senderPhone, receiverName, receiverPhone, address, date, weight, distance);
        shippingMethod = 1; // 1 la hang khong, 0 la duong bo.
        calculateShippingCost();
    }

    public AirwayOrder() {
    }
}
