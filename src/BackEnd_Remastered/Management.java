package BackEnd_Remastered;


import java.util.ArrayList;

public class Management {
    static ArrayList<AbsOrder> OrderList = new ArrayList<>(); // luu tru order.


    //flag nhan tin hieu 0-1 tu nut chon kieu van chuyen trong GUI.
    public static AbsOrder CreateOrder(int flag, String senderName, String senderPhone, String receiverName,
                                       String receiverPhone, String address, String date, double weight, double distance){

        AbsOrder newOrder;
        if(flag == 0){
            newOrder = new RoadOrder(senderName, senderPhone, receiverName, receiverPhone, address, date, weight, distance);
        }
        else {
           newOrder = new AirwayOrder(senderName, senderPhone, receiverName, receiverPhone, address, date, weight, distance);
        }
        return newOrder;
    }

    //submit thi goi ham Create va AddOrder de tao va them Order vao danh sach.
    public static void AddOrder (AbsOrder order){
        OrderList.add(order);
    }

    public static void DeleteOrder (AbsOrder order){
        OrderList.remove(order);
    }

    //Thong ke cac Order trong 1 thang ra 1 Arratlist.
    public static ArrayList<AbsOrder> OrdersInAmonth(int month){
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

        return list;
    }

    //Tong doanh thu trong 1 thang.
    public static double IncomeInAmonth(int month){
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
    public static ArrayList<AbsOrder> SearchOrder(String name){
        ArrayList<AbsOrder> alist = new ArrayList<>();

        for (AbsOrder o : OrderList){
            if(o.getSenderName().contains(name))
                alist.add(o);
        }
        return alist;
    }

    // Tim kiem don hang co chi phi lon hon 1 so nhap vao
    public static ArrayList<AbsOrder> SearchOrder(double shippingCost){
        ArrayList<AbsOrder> alist = new ArrayList<>();

        for (AbsOrder o : OrderList){
            if(o.getShippingCost() >= shippingCost)
                alist.add(o);
        }

        return alist;
    }

    //tim kiem don hang theo dia chi
    public static ArrayList<AbsOrder> SearchByAddress(String address){
        ArrayList<AbsOrder> alist = new ArrayList<>();

        for (AbsOrder o : OrderList){
            if (o.getAddress().contains(address))
                alist.add(o);
        }
        return alist;
    }

    //ham sua don hang.
    public static void ModifyOrder(AbsOrder order,int flag, String senderName, String senderPhone, String receiverName,
                                       String receiverPhone, String address, String date, double weight, double distance ){
        //Tao 1 order moi voi tham so duoc chinh sua
        AbsOrder newOrder = CreateOrder(flag, senderName, senderPhone, receiverName, receiverPhone, address, date, weight, distance);

        // Them Order moi vao danh sach
        AddOrder(newOrder);
        //Xoa order cu.
        DeleteOrder(order);
    }



}
