

package lk.swlc.GadgetMartBackend.GadgetMartBackend.service.impl;



import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.Supplier;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.searchvo.SupplierVO;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.repository.SupplierRepo;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    @Override
    public List<Supplier> findAll() {
        return supplierRepo.findAll();
    }

    @Override
    public List<Supplier> findAllByObject(SupplierVO supplier) {
        return null;
    }


    @Override
    public Supplier search(long id) {
        Supplier supplier=null;
        try {
            supplier= supplierRepo.findById(id).orElseThrow(() -> new Exception("Supplier Data not Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public Supplier update(long id, Supplier supplier) {
        Supplier supplier1=null;
        try {
            supplier1= supplierRepo.findById(id).orElseThrow(() -> new Exception("Supplier Data not Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(supplier1!=null){
            supplier.setId(supplier1.getId());
            return supplierRepo.save(supplier);
        }else{
            return null;
        }


    }
}
