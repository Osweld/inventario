package com.oswelddev.inventario.utils;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class ControllerUtils {


    public static Map<String,Object> paginationUtils(Page<?> page){
        Map<String,Object> body = new HashMap<>();
        body.put("totalPages",page.getTotalPages());
        body.put("totalElements",page.getTotalElements());
        body.put("data",page.getContent());
        body.put("page",page.getNumber());
        return body;
    }

    public static Map<String,Object> genericDeleteResponse(Long idDeleted){
        Map<String,Object> body = new HashMap<>();
        body.put("delete",true);
        body.put("id",idDeleted);
        return body;
    }

}
