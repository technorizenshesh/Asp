package com.my.asp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModels {

    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
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

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("news_image")
        @Expose
        private String newsImage;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("data_time")
        @Expose
        private String dataTime;
        @SerializedName("news_link")
        @Expose
        private String newsLink;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNewsImage() {
            return newsImage;
        }

        public void setNewsImage(String newsImage) {
            this.newsImage = newsImage;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDataTime() {
            return dataTime;
        }

        public void setDataTime(String dataTime) {
            this.dataTime = dataTime;
        }

        public String getNewsLink() {
            return newsLink;
        }

        public void setNewsLink(String newsLink) {
            this.newsLink = newsLink;
        }

    }

}

