package com.tranquocanh.builder;

import javax.enterprise.inject.New;

public class NewBuilder {
    private String title;
    private String categoryCode;

    public NewBuilder(Builder builder){
        this.title = builder.title;
        this.categoryCode = builder.categoryCode;
    }

    public static class Builder {
        private String title;
        private String categoryCode;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCategoryCode(String categoryCode) {
            this.categoryCode = categoryCode;
            return this;
        }
        public NewBuilder build() {
            return new NewBuilder(this);
        }
    }
}
