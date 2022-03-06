
package lk.swlc.GadgetMartBackend.GadgetMartBackend.controller;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.api.ItemManagement;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.brand.CommonBrandModel;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.item.CommonItemModel;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.item.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@CrossOrigin
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemManagement itemManagement;
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getItemAlldata() {
        return new ResponseEntity(itemManagement.getalldataAPI(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchItem(@PathVariable(value = "id") String id) {
        CommonItemModel commonItemModel = itemManagement.searchspringnodeAPI(id);
        if (commonItemModel != null) {
            return new ResponseEntity(commonItemModel, HttpStatus.OK);
        } else {
            return new ResponseEntity(0, HttpStatus.NOT_FOUND);
        }

    }
}
