//Nguyễn Hải Anh

//Lớp thực hiện các chức năng chính của chương trình

package BackEnd;

import GUI.Modal.Model;

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

    public AbsOrder convertToOrder(Model model) {
        if(model.getShipping() == 0) {
            return new RoadOrder(model.getSenderName(), model.getSenderPhone(), model.getReceiverName(), model.getReceiverPhone(),
                    model.getAddress(), model.getDate(), model.getWeight(), model.getDistance());
        }
        else {
            return new AirwayOrder(model.getSenderName(), model.getSenderPhone(), model.getReceiverName(), model.getReceiverPhone(),
                    model.getAddress(), model.getDate(), model.getWeight(), model.getDistance());
        }
    }

    public ArrayList<AbsOrder> convertToOrder(Model[] models) {
        ArrayList<AbsOrder> orders = new ArrayList<>();
        for(int i = 0; i < models.length; i++) {
            orders.add(convertToOrder(models[i]));
        }
        return orders;
    }

    public Model convertToModal(AbsOrder absOrder) {
        return new Model(absOrder.getSenderName(), absOrder.getSenderPhone(), absOrder.getReceiverName(),
                absOrder.receiverPhone, absOrder.address, absOrder.weight, absOrder.getDistance(),
                absOrder.getShippingCost(), absOrder.getShippingMethod(), absOrder.getDate());
    }

    public Model[] convertToModal(ArrayList<AbsOrder> absOrders) {
        Model[] models = new Model[absOrders.size()];
        for(int i = 0; i < absOrders.size(); i++) {
            models[i] = convertToModal(absOrders.get(i));
        }
        return models;
    }
    //</editor-fold>

    public void addOrder(AbsOrder absOrder) {
        OrderList.add(absOrder);
    }

    public void updateFromController(Model[] model) {
        this.OrderList = new ArrayList<>();
        for(Model o : model) {
            addOrder(convertToOrder(o));
        }
    }

    public double calcCost(Model model) {
        return convertToModal(convertToOrder(model)).getCost();
    }

    //Thong ke cac Model trong 1 thang ra 1 Arraytlist.
    public  Model[] OrdersInAmonth(int month){
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
    public Model[] SearchOrder(String name){
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
    public Model[] SearchOrder(double shippingCost){
        ArrayList<AbsOrder> costList = new ArrayList<>();

        for (AbsOrder o : OrderList){
            if(o.getShippingCost() >= shippingCost)
                costList.add(o);
        }

        return convertToModal(costList);
    }

    //tim kiem don hang theo dia chi
    public Model[] SearchByAddress(String address){


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
