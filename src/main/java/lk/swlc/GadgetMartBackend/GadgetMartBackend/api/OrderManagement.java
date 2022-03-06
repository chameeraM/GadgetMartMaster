

package lk.swlc.GadgetMartBackend.GadgetMartBackend.api;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.Order;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order.CommonOrderModel;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order.NodeOrderReturnModel;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.orderdetail.FrontReturnOrderModel;

import java.util.List;

public interface OrderManagement {

    public Order addOrderAPI(CommonOrderModel commonOrderModel);
    public NodeOrderReturnModel checkfunction(CommonOrderModel commonOrderModel);
    public List<FrontReturnOrderModel> searchorder(String username);
    public List<FrontReturnOrderModel> getallOrders();
}
