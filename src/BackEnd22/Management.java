package BackEnd22;

import GUI.Modal.Modal;

import java.util.ArrayList;

public class Management {
    static ArrayList<Modal> danhSach = new ArrayList<>();

    //add don hang vao danh sach va cap nhat so tien.
    public static void add(Modal modal){
        if(modal.getShipping() == 0)
            modal.setCost(modal.getDistance()*20000 + modal.getWeight() * 5000);
        if(modal.getShipping() == 1)
            modal.setCost(modal.getDistance()*100000 + modal.getWeight()*100000 + 200000);

        danhSach.add(modal);
    }

    public static void delete(Modal modal){
        danhSach.remove(modal);
    }

    public static ArrayList<Modal> ordersInAMonth(int Month){
        ArrayList<Modal> ordersList = new ArrayList<>();
        String month;
        if(Month < 10)
            month = "/0"+ Month +"/";
        else
            month = "/"+ Month +"/";
        for (Modal o : danhSach){
            if(o.getDate().contains(month))
                ordersList.add(o);
        }
        return ordersList;
    }

    public static double IncomeInAMonth(int Month){
        String month;
        double income = 0;
        if(Month < 10)
            month = "/0"+ Month +"/";
        else
            month = "/"+ Month +"/";
        for (Modal o : danhSach) {
            if (o.getDate().contains(month))
                income += o.getCost();
        }
        return income;
    }

    public static ArrayList<Modal> search(String name) {
        ArrayList<Modal> temp = new ArrayList<>();
        for (Modal o : danhSach){
            if (o.getSenderName().contains(name))
                temp.add(o);
        }
        return temp;
    }
    // method overloading
    public static ArrayList<Modal> search(double cost){
        ArrayList<Modal> temp = new ArrayList<>();
        for(Modal o: danhSach){
            if(o.getCost() >= cost)
                temp.add(o);
        }
        return temp;
    }

    public static ArrayList<Modal> addressList(String address){
        ArrayList<Modal> temp = new ArrayList<>();
        for (Modal o: danhSach){
            if(o.getAddress().contains(address))
                temp.add(o);
        }
        return temp;
    }

    public static Modal modifyOrder(Modal modal, String senderName, String senderPhone, String receiverName, String receiverPhone,
                                    String address, double weight, double distance, int shipping, String date){
        modal.setAddress(address);
        modal.setReceiverName(receiverName);
        modal.setShipping(shipping);
        modal.setSenderName(senderName);
        modal.setReceiverPhone(receiverPhone);
        modal.setDate(date);
        modal.setDistance(distance);
        modal.setWeight(weight);
        if(modal.getShipping() == 0)
            modal.setCost(modal.getDistance()*20000 + modal.getWeight() * 5000);
        if(modal.getShipping() == 1)
            modal.setCost(modal.getDistance()*100000 + modal.getWeight()*100000 + 200000);
        return modal;
    }


}
