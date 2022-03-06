

package lk.swlc.GadgetMartBackend.GadgetMartBackend.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.api.SubCategoryAPIManagement;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.exception.DownstreamApi;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.exception.MyRestTemplateException;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.subcategory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubCategoryAPIManagementImpl implements SubCategoryAPIManagement {
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
     * Return get all Sub-Category data from Singer & Abans API
     *
     * @return
     */

    @Override
    public List<CommonSubCategoryModel> getalldataAPI() {
        String brandurl = singerurl + "sub-category";
        String brandurl2 = abnasurl + "subCategory/get_all";
        List<SubCategoryModel> subCategoryModelList = null;
        SubCategoryNodeModel subcategoryNodeModelreturn = null;

        /**
         * Brand NodeJS API Call
         */

        response = restTemplate.exchange(brandurl2, HttpMethod.GET, null, String.class);
        String resultBrandNodeModelString = response.getBody();

        /**
         * Brand Spring Boot API Call
         */

        ResponseEntity<List<SubCategoryModel>> responseEntity = restTemplate.exchange(
                brandurl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SubCategoryModel>>() {
                });
        subCategoryModelList = responseEntity.getBody();

        try {
            subcategoryNodeModelreturn = mapper.readValue(resultBrandNodeModelString, SubCategoryNodeModel.class);
            return convertsubCategorys(subcategoryNodeModelreturn, subCategoryModelList);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<CommonSubCategoryModel> convertsubCategorys(SubCategoryNodeModel subcategoryNodeModelreturn, List<SubCategoryModel> subCategoryModelList) {
        List<CommonSubCategoryModel> commonSubCategoryModels = new ArrayList<>();
        List<SubCategory> subCategoryList = subcategoryNodeModelreturn.getSubCategories();
        for (SubCategory subCategory : subCategoryList) {
            commonSubCategoryModels.add(new CommonSubCategoryModel(subCategory.getId(), subCategory.getName(),subCategory.getCategory()));
        }
        for (SubCategoryModel subCategoryModel : subCategoryModelList) {
            commonSubCategoryModels.add(new CommonSubCategoryModel(subCategoryModel.getId() + "", subCategoryModel.getName(),subCategoryModel.getCategory()));
        }
        return commonSubCategoryModels;
    }

    @Override
    public SubCategoryModel searchspringAPI(String s) {
        return null;
    }

    @Override
    public CommonSubCategoryModel searchspringnodeAPI(String s) {

        String brandurl = singerurl + "sub-category/" + s;
        String brandurl2 = abnasurl + "subCategory/get/" + s;
        SubCategoryModel subCategoryModel = null;
        SubCategorySingleModel subCategorySingleModel = null;

        subCategoryModel = getSubCategoryModel(brandurl, subCategoryModel);
        if (subCategoryModel == null) {
            subCategorySingleModel = getSubCategory(brandurl2, subCategorySingleModel);
        }


        if (subCategoryModel != null) {
            return new CommonSubCategoryModel(subCategoryModel.getId() + "", subCategoryModel.getName(),subCategoryModel.getCategory());
        } else if (subCategorySingleModel != null) {
            return new CommonSubCategoryModel(subCategorySingleModel.getSubCategory().getId() + "", subCategorySingleModel.getSubCategory().getName(),subCategorySingleModel.getSubCategory().getCategory());
        } else {
            throw new MyRestTemplateException(DownstreamApi.MY_API_1, HttpStatus.NOT_FOUND, "Data Not Found");
        }
    }

    private SubCategorySingleModel getSubCategory(String brandurl2, SubCategorySingleModel subCategorySingleModel) {

        try {
            String resultString;
            /**
             * SubCategory Spring Boot API Call
             */
            response = restTemplate.exchange(brandurl2, HttpMethod.GET, null, String.class);
            resultString = response.getBody();
            try {
                subCategorySingleModel = mapper.readValue(resultString, SubCategorySingleModel.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return subCategorySingleModel;
        } catch (final HttpClientErrorException httpClientErrorException) {
            return subCategorySingleModel;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return subCategorySingleModel;
        } catch (final Exception exception) {
            return subCategorySingleModel;
        }
    }

    private SubCategoryModel getSubCategoryModel(String brandurl, SubCategoryModel subCategoryModel) {
        try {
            String resultString;
            /**
             * SubCategory NodeJS API Call
             */
            response = restTemplate.exchange(brandurl, HttpMethod.GET, null, String.class);
            resultString = response.getBody();
            try {
                subCategoryModel = mapper.readValue(resultString, SubCategoryModel.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return subCategoryModel;
        } catch (final HttpClientErrorException httpClientErrorException) {
            return subCategoryModel;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return subCategoryModel;
        } catch (final Exception exception) {
            return subCategoryModel;
        }
    }

    @Override
    public SubCategoryModel searchnodeAPI(String s) {
        return null;
    }

    @Override
    public boolean deleteSpringAPI(String s) {
        return false;
    }

    @Override
    public boolean deleteNodeAPI(String s) {
        return false;
    }

    @Override
    public SubCategoryModel postAPI(SubCategoryModel subCategoryModel) {
        return null;
    }

    @Override
    public SubCategoryModel updateAPI(String s, SubCategoryModel subCategoryModel) {
        return null;
    }
}
