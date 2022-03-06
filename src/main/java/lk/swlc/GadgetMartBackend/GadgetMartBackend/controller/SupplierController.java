

package lk.swlc.GadgetMartBackend.GadgetMartBackend.controller;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.Supplier;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@CrossOrigin
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity getallSupplierData(){
        return new ResponseEntity(supplierService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity searchSupplierData(@PathVariable(value = "id") Long id){
        Supplier search = supplierService.search(id);
        if (search!=null){
            return new ResponseEntity(search, HttpStatus.OK);
        }else{
            return new ResponseEntity("data not found", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateSupplier(@PathVariable(value = "id") long id, @RequestBody Supplier supplier) {
        Supplier update = supplierService.update(id, supplier);
        if (update == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(update, HttpStatus.OK);
        }
    }

}
