package com.example.finalyearproject.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class SignData {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("key")
        @Expose
        private String key;
        @SerializedName("sign_images")
        @Expose
        private String signImages;
        @SerializedName("comments")
        @Expose
        private Object comments;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        /**
         * No args constructor for use in serialization
         *
         */
        public SignData() {
        }

        /**
         *
         * @param signImages
         * @param createdAt
         * @param comments
         * @param id
         * @param key
         * @param status
         * @param updatedAt
         */
        public SignData(Integer id, String key, String signImages, Object comments, String status, String createdAt, String updatedAt) {
            super();
            this.id = id;
            this.key = key;
            this.signImages = signImages;
            this.comments = comments;
            this.status = status;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getSignImages() {
            return signImages;
        }

        public void setSignImages(String signImages) {
            this.signImages = signImages;
        }

        public Object getComments() {
            return comments;
        }

        public void setComments(Object comments) {
            this.comments = comments;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
