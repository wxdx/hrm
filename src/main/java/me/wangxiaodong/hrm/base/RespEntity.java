package me.wangxiaodong.hrm.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "统一响应实体")
public class RespEntity {
    @ApiModelProperty(value = "响应码")
    private int code;
    @ApiModelProperty(value = "响应信息")
    private String msg;
    @ApiModelProperty(value = "数据")
    private Object data;

    public RespEntity(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespEntity(RespCode respCode, Object data) {
        this(respCode);
        this.data = data;
    }

    public static RespEntity success(Object data){
        RespEntity respEntity = new RespEntity(RespCode.SUCCESS);
        respEntity.setData(data);
        return respEntity;
    }
    public static RespEntity fail(Object data){
        RespEntity respEntity = new RespEntity(RespCode.FAIL);
        respEntity.setData(data);
        return respEntity;
    }
    public static RespEntity unAuthorized(Object data){
        RespEntity respEntity = new RespEntity(RespCode.UNAUTHORIZED);
        respEntity.setData(data);
        return respEntity;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
