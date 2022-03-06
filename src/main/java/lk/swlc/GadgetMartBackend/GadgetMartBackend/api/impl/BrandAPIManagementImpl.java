

package lk.swlc.GadgetMartBackend.GadgetMartBackend.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.api.BrandAPIManagement;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.exception.DownstreamApi;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.exception.MyRestTemplateException;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.brand.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandAPIManagementImpl implements BrandAPIManagement {

    @Value("${singerurl}")
    private String singerurl;
    @Value("${abnasurl}")
    private String abnasurl;

    private HttpEntity<String> response;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @return
     */
    @Override
    public List<CommonBrandModel> getalldataAPI() {
        String brandurl = singerurl + "brand";
        String brandurl2 = abnasurl + "brand/get_all";
        List<BrandModel> brandModel = null;
        BrandNodeModel brandNodeModelreturn = null;

        /**
         * Brand NodeJS API Call
         */

        response = restTemplate.exchange(brandurl2, HttpMethod.GET, null, String.class);
        String resultBrandNodeModelString = response.getBody();

        /**
         * Brand Spring Boot API Call
         */

        ResponseEntity<List<BrandModel>> responseEntity = restTemplate.exchange(
                brandurl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BrandModel>>() {
                });
        brandModel = responseEntity.getBody();

        try {
            brandNodeModelreturn = mapper.readValue(resultBrandNodeModelString, BrandNodeModel.class);
            return convertBrands(brandNodeModelreturn, brandModel);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param s
     * @return
     */

    @Override
    public BrandNodeModel searchspringAPI(String s) {
        return null;
    }

    /**
     * @param s
     * @return
     */
    @Override
    public CommonBrandModel searchspringnodeAPI(String s) {
        String brandurl = singerurl + "brand/" + s;
        String brandurl2 = abnasurl + "brand/get/" + s;
        String resultString = null;
        String resultString2 = null;

        BrandModel brandModel = searchSpringModelAPI(s, brandurl);
        BrandSingleModel brand1 = null;
        if (brandModel == null) {
            brand1 = searchNodeModelAPI(s, brandurl2);
        }


        if (brandModel != null) {
            return new CommonBrandModel(brandModel.getId() + "", brandModel.getName());
        } else if (brand1 != null) {
            return new CommonBrandModel(brand1.getBrand().getId() + "", brand1.getBrand().getName());
        } else {
            throw new MyRestTemplateException(DownstreamApi.MY_API_1, HttpStatus.NOT_FOUND, "Data Not Found");
        }

    }

    private BrandModel searchSpringModelAPI(String id, String brandurl) {
        BrandModel brandModel = null;

        /**
         * Brand Spring Boot API Call
         */

        try {
            ResponseEntity<BrandModel> responseEntity1 = restTemplate.exchange(
                    brandurl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<BrandModel>() {
                    });
            brandModel = responseEntity1.getBody();

            return brandModel;
        } catch (final HttpClientErrorException httpClientErrorException) {
            return brandModel;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return brandModel;
        } catch (final Exception exception) {
            return brandModel;
        }

    }

    private BrandSingleModel searchNodeModelAPI(String id, String brandurl2) {
        BrandSingleModel brand = null;
        try {
            /**
             * Brand NodeJS API Call
             */
            ResponseEntity<BrandSingleModel> responseEntity = restTemplate.exchange(
                    brandurl2,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<BrandSingleModel>() {
                    });
            brand = responseEntity.getBody();

            return brand;

        } catch (final HttpClientErrorException httpClientErrorException) {
            return brand;
        } catch (HttpServerErrorException httpServerErrorException) {
            return brand;
        } catch (Exception exception) {
            return brand;
        }
    }

    /**
     * @param s
     * @return
     */
    @Override
    public BrandNodeModel searchnodeAPI(String s) {
        return null;
    }

    /**
     * @param s
     * @return
     */
    @Override
    public boolean deleteSpringAPI(String s) {
        return false;
    }

    /**
     * @param s
     * @return
     */
    @Override
    public boolean deleteNodeAPI(String s) {
        return false;
    }

    /**
     * @param brandNodeModel
     * @return
     */
    @Override
    public BrandNodeModel postAPI(BrandNodeModel brandNodeModel) {
        return null;
    }

    /**
     * @param s
     * @param brandNodeModel
     * @return
     */
    @Override
    public BrandNodeModel updateAPI(String s, BrandNodeModel brandNodeModel) {
        return null;
    }


    /**
     * Convert Brand Spring and Brand Node model to one
     *
     * @param brandNodeModel
     * @param brandModel2
     * @return
     */

    private List<CommonBrandModel> convertBrands(BrandNodeModel brandNodeModel, List<BrandModel> brandModel2) {
        List<CommonBrandModel> commonBrandModel = new ArrayList<>();
        List<Brand> brands = brandNodeModel.getBrands();
        for (Brand brand : brands) {
            commonBrandModel.add(new CommonBrandModel(brand.getId(), brand.getName()));
        }
        for (BrandModel brandModel1 : brandModel2) {
            commonBrandModel.add(new CommonBrandModel(brandModel1.getId() + "", brandModel1.getName()));
        }
        return commonBrandModel;
    }
}
