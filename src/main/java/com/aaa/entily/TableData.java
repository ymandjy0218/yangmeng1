package com.aaa.entily;

import java.util.List;

public class TableData {

    private Integer code;   // 0是成功，非0失败
    private String msg;     // 提示信息
    private Integer count;  // 总条数
    private List data;      // 显示的数据

    // 不分页查询
    public TableData(List data) {
        if (data.size() > 0) {// 有数据
            this.code = 0;
            this.msg = "";
            this.count = data.size();
        } else {
            this.code = 1;
            this.msg = "没有查询到数据";
            this.count = 0;
        }
        this.data = data;
    }

    // 分页查询
    public TableData(List data, Integer count) { // data当前页的数据，count总条数计算总页数
        if (data.size() > 0) {// 有数据
            this.code = 0;
            this.msg = "";
        } else {
            this.code = 1;
            this.msg = "没有查询到数据";
        }
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TableData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
