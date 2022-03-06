

package lk.swlc.GadgetMartBackend.GadgetMartBackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.api.BrandAPIManagement;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.exception.*;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.brand.BrandModel;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.brand.CommonBrandModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@Controller
@CrossOrigin
@RequestMapping(value = "/brand")
public class BrandController {

    @Value("${singerurl}")
    private String singerurl;
    @Value("${abnasurl}")
    private String abnasurl;

    @Autowired
    private BrandAPIManagement brandAPIManagement;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBrandAlldata() {
        return new ResponseEntity(brandAPIManagement.getalldataAPI(), HttpStatus.OK);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchbrand(@PathVariable(value = "id") String id) {
        CommonBrandModel commonBrandModel = brandAPIManagement.searchspringnodeAPI(id);
            if (commonBrandModel != null) {
                return new ResponseEntity(commonBrandModel, HttpStatus.OK);
            } else {
                return new ResponseEntity(0, HttpStatus.NOT_FOUND);
            }

    }
}
