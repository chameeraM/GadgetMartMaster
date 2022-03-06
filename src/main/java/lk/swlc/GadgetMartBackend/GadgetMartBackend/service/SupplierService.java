
package lk.swlc.GadgetMartBackend.GadgetMartBackend.service;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.Supplier;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.searchvo.SupplierVO;

import java.util.List;

public interface SupplierService {

    public List<Supplier> findAll();

    public List<Supplier> findAllByObject(SupplierVO supplier);

    public Supplier search(long id) ;

    public Supplier update(long id,Supplier supplier) ;
}
