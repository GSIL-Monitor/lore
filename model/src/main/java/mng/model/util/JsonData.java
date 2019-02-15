package mng.model.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author oac
 */
public class JsonData<T> {
    /**
     * 成功
     */
    private final static String SUCCESS_CODE = "0";
    /**
     * 失败
     */
    private final static String FAILURE_CODE = "1";
    private String code;
    private String msg;
    private T data;
    public JsonData(){}
    public JsonData(String code,T data){
        this.code = code;
        this.data = data;
    }

    public static <T> JsonData<T> success(T data){
        return new JsonData<T>(SUCCESS_CODE ,data);
    }

    public static <T> JsonData<T> error(T data){
        return new JsonData<T>(FAILURE_CODE ,data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public JSONObject toMap() {
        JSONObject json = new JSONObject();
        if(this.code != null){
            json.fluentPut("code", this.code);
        }
        if(this.msg != null){
            json.fluentPut("msg", this.msg);
        }
        if(this.data != null){
            json.fluentPut("data", this.data);
        }
        return json;
    }
}
