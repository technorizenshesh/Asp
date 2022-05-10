package com.my.asp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailsModels {

    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Result {

        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("Parts_detail")
        @Expose
        private List<PartsDetail> partsDetail = null;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<PartsDetail> getPartsDetail() {
            return partsDetail;
        }

        public void setPartsDetail(List<PartsDetail> partsDetail) {
            this.partsDetail = partsDetail;
        }

        public class PartsDetail {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("product_id")
            @Expose
            private String productId;
            @SerializedName("parts_name")
            @Expose
            private String partsName;

            boolean isView=false;

            public boolean isView() {
                return isView;
            }

            public void setView(boolean view) {
                isView = view;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getPartsName() {
                return partsName;
            }

            public void setPartsName(String partsName) {
                this.partsName = partsName;
            }

        }

    }

}





