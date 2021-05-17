import GUI.View.MainStage.MainStageController;
import javafx.application.Application;
import javafx.stage.Stage;

/*
- đọc dữ liệu từ TextField
- truyền vào back-end cái biến String chứa dữ liệu nhận được
- xử lý trả về list order
- gọi phương thức showOrderList của mainStage
- close address stage

luồng chương trình

    - Đầu tiên là sẽ hiện cửa sổ ch triunhf chinmsh
    - chuc nag ...
    - tao moi -> hienra cua so them tt vao, tra ve 1 instance order -> list cua backend
    - hien thii cac order len cua so chinh
    -hien ra cua so tim kiem lay tt tu nguoi duhng - be tra ve list -> cua so chinh
 */

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        setUpDependencyInjector();

        MainStageController mainStageController = new MainStageController();
        mainStageController.showStage();


    }

//    private void setUpDependencyInjector() {
//        //create factories - here we'll just create one!
//        Callback<Class<?>, Object> controllerFactory = param -> {
//            Order order = new Order("senderName", "senderPhone", "receiverName", "receiverPhone",
//                    "address",1,100, 12, 1, "20/11/2021");
//            return new InfController(order);
//        };
//
//        //save the factory in the injector
//        DependencyInjection.addInjectionMethod(InfController.class, controllerFactory);
//    }
}
