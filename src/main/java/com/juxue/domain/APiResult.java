package com.juxue.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class APiResult {

    @ApiModelProperty(value = "状态码",notes = "状态码")
    private Integer code;

    @ApiModelProperty(notes = "返回具体的数据")
    private Object data;

    public APiResult() { }

    public APiResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }
    public  static APiResult error( Object json){
        return new APiResult(500,json);
    }
    public  static APiResult error(){
        return new APiResult(500,"");
    }
    public  static APiResult ok(Object json){
        return new APiResult(200,json);
    }
    public  static APiResult ok(){
        return new APiResult(200,"");
    }



    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }
    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }

}
