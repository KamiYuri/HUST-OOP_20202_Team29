package GUI.View.OrderInformation;

import GUI.Modal.Order;

public class OrderInfHelper {

    private static InfController infController;

    public static Order makeNewOrder() {
        infController = new InfController();
        infController.showScene();
        return infController.getOrder();
    }

    public static Order showOrEditOrder(Order order) {
        infController = new InfController(order);
        infController.showScene();
        return infController.getOrder();
    }
}
