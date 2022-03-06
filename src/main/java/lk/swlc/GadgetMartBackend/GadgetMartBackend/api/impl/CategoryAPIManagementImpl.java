

package lk.swlc.GadgetMartBackend.GadgetMartBackend.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.api.CategoryAPIManagement;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.exception.DownstreamApi;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.exception.MyRestTemplateException;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.category.*;
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
public class CategoryAPIManagementImpl implements CategoryAPIManagement {
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
     * Return get all Category data from Singer & Abans API
     *
     * @return
     */

    @Override
    public List<CommonCategoryModel> getalldataAPI() {
        String brandurl = singerurl + "category";
        String brandurl2 = abnasurl + "category/get_all";
        List<CategoryModel> categoryModel = null;
        CategoryNodeModel categoryNodeModelreturn = null;

        /**
         * Brand NodeJS API Call
         */

        response = restTemplate.exchange(brandurl2, HttpMethod.GET, null, String.class);
        String resultBrandNodeModelString = response.getBody();

        /**
         * Brand Spring Boot API Call
         */

        ResponseEntity<List<CategoryModel>> responseEntity = restTemplate.exchange(
                brandurl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryModel>>() {
                });
        categoryModel = responseEntity.getBody();

        try {
            categoryNodeModelreturn = mapper.readValue(resultBrandNodeModelString, CategoryNodeModel.class);
            return convertCategorys(categoryNodeModelreturn, categoryModel);
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
    public CategoryModel searchspringAPI(String s) {
        return null;
    }

    /**
     * @param s
     * @return
     */

    @Override
    public CommonCategoryModel searchspringnodeAPI(String s) {
        String brandurl = singerurl + "category/" + s;
        String brandurl2 = abnasurl + "category/get/" + s;
        CategoryModel categoryModel = null;
        CategorySingleModel category = null;

        categoryModel = getCategoryModel(brandurl, categoryModel);
        if (categoryModel==null){
            category = getCategory(brandurl2, category);
        }



        if (categoryModel != null) {
            return new CommonCategoryModel(categoryModel.getId() + "", categoryModel.getName());
        } else if (category != null) {
            return new CommonCategoryModel(category.getCategory().getId() + "", category.getCategory().getName());
        } else {
            throw new MyRestTemplateException(DownstreamApi.MY_API_1, HttpStatus.NOT_FOUND, "Data Not Found");
        }
    }

    private CategoryModel getCategoryModel(String brandurl, CategoryModel categoryModel) {
        try {
            String resultString;
            /**
             * Brand Spring Boot API Call
             */
            response = restTemplate.exchange(brandurl, HttpMethod.GET, null, String.class);
            resultString = response.getBody();
            try {
                categoryModel = mapper.readValue(resultString, CategoryModel.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return categoryModel;
        } catch (final HttpClientErrorException httpClientErrorException) {
            return categoryModel;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return categoryModel;
        } catch (final Exception exception) {
            return categoryModel;
        }

    }

    private CategorySingleModel getCategory(String brandurl2, CategorySingleModel category) {

        String resultString;
        try {
            /**
             * Brand NodeJS API Call
             */
            response = restTemplate.exchange(brandurl2, HttpMethod.GET, null, String.class);
            resultString = response.getBody();
            try {
                category = mapper.readValue(resultString, CategorySingleModel.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return category;

        } catch (final HttpClientErrorException httpClientErrorException) {
            return category;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return category;
        } catch (final Exception exception) {
            return category;
        }
    }

    /**
     * @param s
     * @return
     */

    @Override
    public CategoryModel searchnodeAPI(String s) {
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
     * @param categoryModel
     * @return
     */

    @Override
    public CategoryModel postAPI(CategoryModel categoryModel) {
        return null;
    }

    /**
     * @param s
     * @param categoryModel
     * @return
     */

    @Override
    public CategoryModel updateAPI(String s, CategoryModel categoryModel) {
        return null;
    }

    /**
     * Convert Category Spring and Node model to one ArrayList
     *
     * @param categoryNodeModelreturn
     * @param categoryModel
     * @return
     */

    private List<CommonCategoryModel> convertCategorys(CategoryNodeModel categoryNodeModelreturn, List<CategoryModel> categoryModel) {
        List<CommonCategoryModel> commonCategoryModel = new ArrayList<>();
        List<Category> categories = categoryNodeModelreturn.getCategories();
        for (Category category : categories) {
            commonCategoryModel.add(new CommonCategoryModel(category.getId(), category.getName()));
        }
        for (CategoryModel categoryModel1 : categoryModel) {
            commonCategoryModel.add(new CommonCategoryModel(categoryModel1.getId() + "", categoryModel1.getName()));
        }
        return commonCategoryModel;
    }
}
