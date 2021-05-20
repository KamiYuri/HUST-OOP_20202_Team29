//Hoàng Việt Dũng

package GUI.View.OrderInformation;

import GUI.Modal.Model;

//Class nhận và trả thông tin cho cửa sổ hiển thị Thông tin đơn hàng
public class OrderInfHelper {

    private static InfController infController;

    public static Model makeNewOrder() {
        infController = new InfController();
        infController.showScene();
        return infController.getOrder();
    }

    public static Model showOrEditOrder(Model model) {
        infController = new InfController(model);
        infController.showScene();
        return infController.getOrder();
    }
}
