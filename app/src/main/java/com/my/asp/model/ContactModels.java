package com.my.asp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactModels {

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

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("country_id")
        @Expose
        private String countryId;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("lon")
        @Expose
        private String lon;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("dapartment_of_interests")
        @Expose
        private String dapartmentOfInterests;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("subject_matter")
        @Expose
        private String subjectMatter;
        @SerializedName("message")
        @Expose
        private String message;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDapartmentOfInterests() {
            return dapartmentOfInterests;
        }

        public void setDapartmentOfInterests(String dapartmentOfInterests) {
            this.dapartmentOfInterests = dapartmentOfInterests;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSubjectMatter() {
            return subjectMatter;
        }

        public void setSubjectMatter(String subjectMatter) {
            this.subjectMatter = subjectMatter;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}

