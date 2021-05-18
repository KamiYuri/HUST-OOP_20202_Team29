package GUI.View.OrderInformation;

import GUI.Modal.Modal;

public class OrderInfHelper {

    private static InfController infController;

    public static Modal makeNewOrder() {
        infController = new InfController();
        infController.showScene();
        return infController.getOrder();
    }

    public static Modal showOrEditOrder(Modal modal) {
        infController = new InfController(modal);
        infController.showScene();
        return infController.getOrder();
    }
}
