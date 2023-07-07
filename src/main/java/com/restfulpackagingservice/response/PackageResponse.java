package com.restfulpackagingservice.response;

import java.util.List;

public class PackageResponse {

        private List<Integer> selectedItems;
        private String message;

        public PackageResponse(List<Integer> selectedItems) {
            this.selectedItems = selectedItems;
        }

        public PackageResponse(String message) {
            this.message = message;
        }

        public List<Integer> getSelectedItems() {
            return selectedItems;
        }

        public void setSelectedItems(List<Integer> selectedItems) {
            this.selectedItems = selectedItems;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


