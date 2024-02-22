package com.amazon.dummy.service;

import com.amazon.dummy.entity.Store;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class StoreService {

    private static final String API_URL = "https://fakestoreapi.com/products";

    public Store[] getProductsByKeyword(String keyword) {
        String apiurl = API_URL;
        RestTemplate restTemplate = new RestTemplate();

        Store[] allProducts = restTemplate.getForObject(apiurl, Store[].class);

        if (allProducts != null) {
            return Arrays.stream(allProducts)
                    .filter(product -> product.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                    .toArray(Store[]::new);
        }

        return null;
    }

    public Store getProductById(int productId) {
        String apiurl = API_URL;
        RestTemplate restTemplate = new RestTemplate();

        Store[] allProducts = restTemplate.getForObject(apiurl, Store[].class);

        if (allProducts != null) {
            for(Store s: allProducts){
                if(s.getId()==productId){
                    return  s;
                }
            }
        }

        return null;
    }
}