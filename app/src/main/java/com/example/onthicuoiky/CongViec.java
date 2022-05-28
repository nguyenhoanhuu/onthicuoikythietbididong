package com.example.onthicuoiky;

import java.util.Date;

public class CongViec {
    private int _id;
    private String tenCongViec;
    private String mucDo;
    private Date ngay;

    public CongViec(int _id, String tenCongViec, String mucDo, Date ngay) {
        this._id = _id;
        this.tenCongViec = tenCongViec;
        this.mucDo = mucDo;
        this.ngay = ngay;
    }

    public CongViec(int _id) {
        this._id = _id;
    }

    public CongViec(String tenCongViec, String mucDo) {
        this.tenCongViec = tenCongViec;
        this.mucDo = mucDo;
    }

    public CongViec(String tenCongViec, String mucDo, Date ngay) {
        this.tenCongViec = tenCongViec;
        this.mucDo = mucDo;
        this.ngay = ngay;
    }

    public CongViec() {
    }

    public int get_id() {
        return _id;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public String getMucDo() {
        return mucDo;
    }

    public Date getNgay() {
        return ngay;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public void setMucDo(String mucDo) {
        this.mucDo = mucDo;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    @Override
    public String toString() {
        return "CongViec{" +
                "_id=" + _id +
                ", tenCongViec='" + tenCongViec + '\'' +
                ", mucDo='" + mucDo + '\'' +
                ", ngay=" + ngay +
                '}';
    }
}
