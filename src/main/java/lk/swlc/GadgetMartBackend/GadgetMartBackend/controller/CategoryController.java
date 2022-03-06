

package lk.swlc.GadgetMartBackend.GadgetMartBackend.controller;


import lk.swlc.GadgetMartBackend.GadgetMartBackend.api.CategoryAPIManagement;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.category.CommonCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@CrossOrigin
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryAPIManagement categoryAPIManagement;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCategoryAlldata() {
        return new ResponseEntity(categoryAPIManagement.getalldataAPI(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchbrand(@PathVariable(value = "id") String id) {
        CommonCategoryModel commonCategoryModel = categoryAPIManagement.searchspringnodeAPI(id);
        if (commonCategoryModel != null) {
            return new ResponseEntity(commonCategoryModel, HttpStatus.OK);
        } else {
            return new ResponseEntity(0, HttpStatus.NOT_FOUND);
        }
    }
}
