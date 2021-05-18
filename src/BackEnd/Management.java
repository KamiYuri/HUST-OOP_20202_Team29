package BackEnd;


import GUI.Modal.Modal;

import java.util.ArrayList;

public class Management {
    private ArrayList<AbsOrder> OrderList = new ArrayList<>(); // luu tru order.

    private Management() {

    }

    private static class ManegementHelper{
        private static final Management INSTANCE = new Management();
    }

    public static Management getInstance() {
        return ManegementHelper.INSTANCE;
    }

    public AbsOrder convertToOrder(Modal modal) {
        AbsOrder absOrder = new AirwayOrder();

        if(modal.getShipping() == 0) {
            absOrder = new RoadOrder(modal.getSenderName(), modal.getSenderPhone(), modal.getReceiverName(), modal.getReceiverPhone(),
                    modal.getAddress(), modal.getDate(), modal.getWeight(), modal.getDistance());
        }
        else if(modal.getShipping() == 1) {
            absOrder = new AirwayOrder(modal.getSenderName(), modal.getSenderPhone(), modal.getReceiverName(), modal.getReceiverPhone(),
                    modal.getAddress(), modal.getDate(), modal.getWeight(), modal.getDistance());
        }
        return absOrder;
    }

    public ArrayList<AbsOrder> convertToOrder(Modal[] modals) {
        ArrayList<AbsOrder> orders = new ArrayList<>();
        for(int i = 0; i < modals.length; i++) {
            orders.add(convertToOrder(modals[i]));
        }
        return orders;
    }

    public Modal convertToModal(AbsOrder absOrder) {
        return new Modal(absOrder.getSenderName(), absOrder.getSenderPhone(), absOrder.getReceiverName(),
                absOrder.receiverPhone, absOrder.address, absOrder.weight, absOrder.getDistance(),
                absOrder.getShippingCost(), absOrder.getShippingMethod(), absOrder.getDate());
    }

    public Modal[] convertToModal(ArrayList<AbsOrder> absOrders) {
        Modal[] modals = new Modal[absOrders.size()];
        for(int i = 0; i < absOrders.size(); i++) {
            modals[i] = convertToModal(absOrders.get(i));
        }
        return modals;
    }

    public void addOrder(AbsOrder absOrder) {
        OrderList.add(absOrder);
    }

    public void updateFromController(Modal[] modal) {
        this.OrderList = new ArrayList<>();
        for(Modal o : modal) {
            addOrder(convertToOrder(o));
        }
    }

    public double calcCost(Modal modal) {
        return convertToModal(convertToOrder(modal)).getCost();
    }

//    //flag nhan tin hieu 0-1 tu nut chon kieu van chuyen trong GUI.
//    public static AbsOrder CreateOrder(int flag, String senderName, String senderPhone, String receiverName,
//                                       String receiverPhone, String address, String date, double weight, double distance){
//
//        AbsOrder newOrder;
//        if(flag == 0){
//            newOrder = new RoadOrder(senderName, senderPhone, receiverName, receiverPhone, address, date, weight, distance);
//        }
//        else {
//           newOrder = new AirwayOrder(senderName, senderPhone, receiverName, receiverPhone, address, date, weight, distance);
//        }
//        return newOrder;
//    }

//    //submit thi goi ham Create va AddOrder de tao va them Modal vao danh sach.
//    public static void AddOrder (AbsOrder order){
//        OrderList.add(order);
//    }
//
//    public static void DeleteOrder (AbsOrder order){
//        OrderList.remove(order);
//    }

    //Thong ke cac Modal trong 1 thang ra 1 Arraytlist.
    public  Modal[] OrdersInAmonth(int month){
        ArrayList<AbsOrder> list = new ArrayList<>();
        String Month;

        if(month < 10)
            Month = "/0" + month + "/";
        else
            Month = "/" + month + "/";

        for (AbsOrder o : OrderList){
            if(o.getDate().contains(Month))
                list.add(o);
        }
        return convertToModal(list);
    }

    //Tong doanh thu trong 1 thang.
    public double incomeInAmonth(int month){
        double income = 0;
        String Month;

        if(month < 10)
            Month = "/0" + month + "/";
        else
            Month = "/" + month + "/";

        for (AbsOrder o : OrderList){
            if(o.getDate().contains(Month))
                income += o.getShippingCost();
        }
        return income;
    }

    // tim kiem don hang theo nguoi gui
    public Modal[] SearchOrder(String name){
        ArrayList<AbsOrder> nameList = new ArrayList<>();

        for (AbsOrder o : OrderList){
            if(o.getSenderName().contains(name))
                nameList.add(o);
        }
        return convertToModal(nameList);
    }

    // Tim kiem don hang co chi phi lon hon 1 so nhap vao
    public Modal[] SearchOrder(double shippingCost){
        ArrayList<AbsOrder> costList = new ArrayList<>();

        for (AbsOrder o : OrderList){
            if(o.getShippingCost() >= shippingCost)
                costList.add(o);
        }

        return convertToModal(costList);
    }

    //tim kiem don hang theo dia chi
    public Modal[] SearchByAddress(String address){


        ArrayList<AbsOrder> addList = new ArrayList<>();

        for (AbsOrder o : OrderList){
            if (o.getAddress().contains(address)) {
                addList.add(o);
            }
        }
        if(addList.size() == 0)
            return null;
        return convertToModal(addList);
    }

//    //ham sua don hang.
//    public static void ModifyOrder(AbsOrder order,int flag, String senderName, String senderPhone, String receiverName,
//                                       String receiverPhone, String address, String date, double weight, double distance ){
//        //Tao 1 order moi voi tham so duoc chinh sua
//        AbsOrder newOrder = CreateOrder(flag, senderName, senderPhone, receiverName, receiverPhone, address, date, weight, distance);
//
//        // Them Modal moi vao danh sach
//        AddOrder(newOrder);
//        //Xoa order cu.
//        DeleteOrder(order);
//    }

}
