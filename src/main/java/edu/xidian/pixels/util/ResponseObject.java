package edu.xidian.pixels.util;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.ToString;

/**
 * ResponseObject
 */
@ToString
@Data
public class ResponseObject {

    private String code;
    private String message;
    private JSONObject object;

    public static ResponseObject getSuccessResponse() {
        ResponseObject o = new ResponseObject();
        o.code = "200";
        o.message = "sucess";
        o.object = new JSONObject();
        return o;
    }

    public static ResponseObject getSuccessResponse(String message) {
        ResponseObject o = new ResponseObject();
        o.code = "200";
        o.message = message;
        o.object = new JSONObject();
        return o;
    }

    public static ResponseObject getFailResponse() {
        ResponseObject o = new ResponseObject();
        o.code = "500";
        o.message = "服务器错误";
        o.object = new JSONObject();
        return o;
    }

    public static ResponseObject getFailResponse(String message) {
        ResponseObject o = new ResponseObject();
        o.code = "500";
        o.message = message;
        o.object = new JSONObject();
        return o;
    }

    public ResponseObject setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseObject putValue(String key, Object value) {
        this.object.put(key, value);
        return this;
    }
}