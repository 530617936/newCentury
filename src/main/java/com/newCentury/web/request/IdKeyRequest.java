package com.newCentury.web.request;

import lombok.Data;

import java.util.List;

/**
 * @ClassName IdKeyRequest
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/27
 */
@Data
public class IdKeyRequest {
    private Long id;
    private List<Long> ids;
}
