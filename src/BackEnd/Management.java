package BackEnd;

import GUI.Modal.Modal;

import java.util.ArrayList;

public class Management {
    private ArrayList<AbsOrder> OrderList = new ArrayList<>(); // luu tru order.

    //<editor-fold desc="Make Singleton">
    private Management() {

    }

    private static class ManegementHelper{
        private static final Management INSTANCE = new Management();
    }

    public static Management getInstance() {
        return ManegementHelper.INSTANCE;
    }
    //</editor-fold>

    //<editor-fold desc="Modify data">
    /** Polymorphism */

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
    //</editor-fold>

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
        if(nameList.isEmpty())
            return null;
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
}
