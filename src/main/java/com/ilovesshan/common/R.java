package com.ilovesshan.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/5
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class R {

    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE_CLIENT = -200;
    public static final int ERROR_CODE_SERVER = 500;

    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final String SUCCESS_MESSAGE_SELECT = "查询成功";
    public static final String SUCCESS_MESSAGE_INSERT = "新增成功";
    public static final String SUCCESS_MESSAGE_DELETE = "删除成功";
    public static final String SUCCESS_MESSAGE_UPDATE = "更新成功";

    public static final String ERROR_MESSAGE = "操作失败";
    public static final String ERROR_MESSAGE_SELECT = "查询失败";
    public static final String ERROR_MESSAGE_INSERT = "新增失败";
    public static final String ERROR_MESSAGE_DELETE = "删除失败";
    public static final String ERROR_MESSAGE_UPDATE = "更新失败";

    private Integer code;
    private String message;
    private Object data;


    public static R success() {
        return R.builder().code(R.SUCCESS_CODE).message(R.SUCCESS_MESSAGE).build();
    }


    public static R success(Object data) {
        return R.builder().code(R.SUCCESS_CODE).message(R.SUCCESS_MESSAGE).data(data).build();
    }

    public static R success(String message, Object data) {
        return R.builder().code(R.SUCCESS_CODE).message(message).data(data).build();
    }

    public static R fail() {
        return R.builder().code(R.ERROR_CODE_CLIENT).message(R.ERROR_MESSAGE).build();
    }

    public static R fail(Object data) {
        return R.builder().code(R.ERROR_CODE_CLIENT).message(R.ERROR_MESSAGE).data(data).build();
    }

    public static R fail(String message, Object data) {
        return R.builder().code(R.ERROR_CODE_CLIENT).message(message).data(data).build();
    }

    public static R error() {
        return R.builder().code(R.ERROR_CODE_SERVER).message(R.ERROR_MESSAGE).build();
    }

    public static R error(Object data) {
        return R.builder().code(R.ERROR_CODE_SERVER).message(R.SUCCESS_MESSAGE).data(data).build();
    }

    public static R error(String message, Object data) {
        return R.builder().code(R.ERROR_CODE_SERVER).message(message).data(data).build();
    }

}
