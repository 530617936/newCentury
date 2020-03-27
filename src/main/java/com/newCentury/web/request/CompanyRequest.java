package com.newCentury.web.request;

import lombok.Data;

/**
 * @ClassName CompanyRequest
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/27
 */
@Data
public class CompanyRequest {

    private Long id;

    private String name;

    private String address;

    private String type;

}
