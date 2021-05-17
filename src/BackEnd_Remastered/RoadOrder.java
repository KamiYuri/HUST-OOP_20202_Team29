package BackEnd_Remastered;

import java.util.ArrayList;

public class RoadOrder extends AbsOrder {

    @Override
    protected void calculateShippingCost() {
        this.shippingCost = distance*20000 + weight*5000;
    }

    //Constructor
    public RoadOrder(String senderName, String senderPhone, String receiverName, String receiverPhone,
                     String address, String date, double weight, double distance) {

        super(senderName, senderPhone, receiverName, receiverPhone, address, date, weight, distance);
        shippingMethod = 0; // 0 la duong bo, 1 la hang khong.
        calculateShippingCost(); // shipping coost duoc tinh.
    }
}
