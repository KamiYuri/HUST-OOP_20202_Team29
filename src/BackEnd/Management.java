package BackEnd;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

public class Management {
    static ArrayList<Order> danhSach = new ArrayList<>();

    //add don hang vao danh sach va cap nhat so tien.
    public static void add(Order order){
        if(order.getShipping() == 0)
            order.setCost(order.getDistance()*20000 + order.getWeight() * 5000);
        if(order.getShipping() == 1)
            order.setCost(order.getDistance()*100000 + order.getWeight()*100000 + 200000);

        danhSach.add(order);
    }

    public static void delete(Order order){
        danhSach.remove(order);
    }

    public static ArrayList<Order> ordersInAMonth(int Month){
        ArrayList<Order> ordersList = new ArrayList<>();
        String month;
        if(Month < 10)
            month = "/0"+ String.valueOf(Month) +"/";
        else
            month = "/"+ String.valueOf(Month)+"/";
        for (Order o : danhSach){
            if(o.getDate().contains(month))
                ordersList.add(o);
        }
        return ordersList;
    }

    public static double IncomeInAMonth(int Month){
        String month;
        double income = 0;
        if(Month < 10)
            month = "/0"+ String.valueOf(Month) +"/";
        else
            month = "/"+ String.valueOf(Month)+"/";
        for (Order o : danhSach) {
            if (o.getDate().contains(month))
                income += o.getCost();
        }
        return income;
    }

    public static ArrayList<Order> search(String name) {
        ArrayList<Order> temp = new ArrayList<>();
        for (Order o : danhSach){
            if (o.getSenderName().contains(name))
                temp.add(o);
        }
        return temp;
    }
    // method overloading
    public static ArrayList<Order> search(double cost){
        ArrayList<Order> temp = new ArrayList<>();
        for(Order o: danhSach){
            if(o.getCost() >= cost)
                temp.add(o);
        }
        return temp;
    }

    public static ArrayList<Order> addressList(String address){
        ArrayList<Order> temp = new ArrayList<>();
        for (Order o: danhSach){
            if(o.getAddress().contains(address))
                temp.add(o);
        }
        return temp;
    }

    public static Order modifyOrder(Order order,String senderName, String senderPhone, String receiverName, String receiverPhone,
                                    String address, double weight, double distance, int shipping, String date){
        order.setAddress(address);
        order.setReceiverName(receiverName);
        order.setShipping(shipping);
        order.setSenderName(senderName);
        order.setReceiverPhone(receiverPhone);
        order.setDate(date);
        order.setDistance(distance);
        order.setWeight(weight);
        if(order.getShipping() == 0)
            order.setCost(order.getDistance()*20000 + order.getWeight() * 5000);
        if(order.getShipping() == 1)
            order.setCost(order.getDistance()*100000 + order.getWeight()*100000 + 200000);
        return order;
    }


}
