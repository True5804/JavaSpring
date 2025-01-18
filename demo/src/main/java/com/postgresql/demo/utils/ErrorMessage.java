package com.postgresql.demo.utils;

public class ErrorMessage {
    private int r_code;
    private String r_msg;

    public static ErrorMessage SUCCESS = new ErrorMessage(0, "success");
    public static ErrorMessage ERROR_IN_INPUT_PARAMETERS = new ErrorMessage(10, "Error in input parameters");
    public static ErrorMessage MISSING_PARAMETER = new ErrorMessage(20, "Missing necessary parameter");
    public static ErrorMessage DATA_NOT_FOUND = new ErrorMessage(30, "No data exists for the input ID");
    public static ErrorMessage SERVER_ERROR = new ErrorMessage(40, "Server error");

    public ErrorMessage(){

    }

    public ErrorMessage(int r_code, String r_msg){
        this.r_code = r_code;
        this.r_msg = r_msg;
    }

    public int getCode(){
        return r_code;
    }
    public void setCode(int r_code){
        this.r_code = r_code;
    }

    public String getMsg(){
        return r_msg;
    }
    public void setMsg(String r_msg){
        this.r_msg = r_msg;
    }
}

