package com.qgj.juan_05.netwok.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HouseModel {

    @SerializedName("total")
    private int total;
    @SerializedName("rows")
    private List<RowsDTO> rows;
    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
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

    public static class RowsDTO {
        @SerializedName("searchValue")
        private Object searchValue;
        @SerializedName("createBy")
        private Object createBy;
        @SerializedName("createTime")
        private Object createTime;
        @SerializedName("updateBy")
        private Object updateBy;
        @SerializedName("updateTime")
        private Object updateTime;
        @SerializedName("remark")
        private Object remark;
        @SerializedName("params")
        private ParamsDTO params;
        @SerializedName("id")
        private int id;
        @SerializedName("sourceName")
        private String sourceName;
        @SerializedName("address")
        private String address;
        @SerializedName("areaSize")
        private int areaSize;
        @SerializedName("tel")
        private String tel;
        @SerializedName("price")
        private String price;
        @SerializedName("houseType")
        private String houseType;
        @SerializedName("pic")
        private String pic;
        @SerializedName("description")
        private String description;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsDTO getParams() {
            return params;
        }

        public void setParams(ParamsDTO params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAreaSize() {
            return areaSize;
        }

        public void setAreaSize(int areaSize) {
            this.areaSize = areaSize;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public static class ParamsDTO {
        }
    }
}
