//Nguyễn Hải Anh

//Lớp định nghĩa đơn hàng Đường bộ

package BackEnd;

public class RoadOrder extends AbsOrder {

    @Override
    protected void calculateShippingCost() {
        this.shippingCost = distance*20000 + weight*5000;
        if(shippingCost >= 10000000) {
            shippingCost = 9999999;
        }
    }

    //Constructor
    public RoadOrder(String senderName, String senderPhone, String receiverName, String receiverPhone,
                     String address, String date, double weight, double distance) {

        super(senderName, senderPhone, receiverName, receiverPhone, address, date, weight, distance);
        shippingMethod = 0; // 0 la duong bo, 1 la hang khong.
        calculateShippingCost(); // shipping coost duoc tinh.
    }
}
