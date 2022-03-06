
package lk.swlc.GadgetMartBackend.GadgetMartBackend.controller;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.api.ItemManagement;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.api.SubCategoryAPIManagement;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.item.CommonItemModel;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.subcategory.CommonSubCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@CrossOrigin
@RequestMapping(value = "/subcategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryAPIManagement subCategoryAPIManagement;
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSubCategoryAlldata() {
        return new ResponseEntity(subCategoryAPIManagement.getalldataAPI(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchSubCategory(@PathVariable(value = "id") String id) {
        CommonSubCategoryModel commonSubCategoryModel = subCategoryAPIManagement.searchspringnodeAPI(id);
        if (commonSubCategoryModel != null) {
            return new ResponseEntity(commonSubCategoryModel, HttpStatus.OK);
        } else {
            return new ResponseEntity(0, HttpStatus.NOT_FOUND);
        }

    }
}
