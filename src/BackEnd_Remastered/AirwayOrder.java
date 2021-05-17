package BackEnd_Remastered;
import java.util.ArrayList;

public class AirwayOrder extends AbsOrder {

    @Override
    protected void calculateShippingCost() {
        shippingCost = distance*100000 + weight*100000 + 200000;
    }

    //Constructor
    public AirwayOrder(String senderName, String senderPhone, String receiverName, String receiverPhone,
                       String address, String date, double weight, double distance) {
        super(senderName, senderPhone, receiverName, receiverPhone, address, date, weight, distance);
        shippingMethod = 1; // 1 la hang khong, 0 la duong bo.
        calculateShippingCost();
    }
}
