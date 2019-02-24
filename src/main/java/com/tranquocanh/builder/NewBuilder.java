package com.tranquocanh.builder;

public class NewBuilder {
    private String title;
    private String code;

    public NewBuilder(Builder builder){
        this.title = builder.title;
        this.code = builder.code;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public static class Builder {
        private String title;
        private String code;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public NewBuilder build() {
            return new NewBuilder(this);
        }
    }
}
