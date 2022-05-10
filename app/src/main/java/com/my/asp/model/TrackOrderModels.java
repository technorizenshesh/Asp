package com.my.asp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackOrderModels {

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
        @SerializedName("po")
        @Expose
        private String po;
        @SerializedName("oc")
        @Expose
        private String oc;
        @SerializedName("readiness")
        @Expose
        private String readiness;
        @SerializedName("expedition")
        @Expose
        private String expedition;
        @SerializedName("order_number")
        @Expose
        private String orderNumber;
        @SerializedName("order_notes")
        @Expose
        private String orderNotes;
        @SerializedName("arrival")
        @Expose
        private String arrival;
        @SerializedName("invoicing")
        @Expose
        private String invoicing;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPo() {
            return po;
        }

        public void setPo(String po) {
            this.po = po;
        }

        public String getOc() {
            return oc;
        }

        public void setOc(String oc) {
            this.oc = oc;
        }

        public String getReadiness() {
            return readiness;
        }

        public void setReadiness(String readiness) {
            this.readiness = readiness;
        }

        public String getExpedition() {
            return expedition;
        }

        public void setExpedition(String expedition) {
            this.expedition = expedition;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getOrderNotes() {
            return orderNotes;
        }

        public void setOrderNotes(String orderNotes) {
            this.orderNotes = orderNotes;
        }

        public String getArrival() {
            return arrival;
        }

        public void setArrival(String arrival) {
            this.arrival = arrival;
        }

        public String getInvoicing() {
            return invoicing;
        }

        public void setInvoicing(String invoicing) {
            this.invoicing = invoicing;
        }
    }

}