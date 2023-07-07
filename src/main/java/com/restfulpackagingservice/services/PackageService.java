package com.restfulpackagingservice.services;

import com.restfulpackagingservice.model.Item;
import com.restfulpackagingservice.request.PackageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageService {

        public List<Integer> findOptimalItems(List<Item> items, double maxWeight) {
            int n = items.size();
            int[][] dp = new int[n + 1][(int) (maxWeight + 1)];

            for (int i = 1; i <= n; i++) {
                Item currentItem = items.get(i - 1);
                for (int j = 0; j <= maxWeight; j++) {
                    if (currentItem.getWeight() <= j) {
                        dp[i][j] = Math.max(dp[i - 1][j], (int) (currentItem.getPrice() + dp[i - 1][(int) (j - currentItem.getWeight())]));
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return getSelectedItems(dp, items, n, (int) maxWeight);
        }

        public List<Integer> getSelectedItems(int[][] dp, List<Item> items, int n, int maxWeight) {
            List<Integer> selectedItems = new ArrayList<>();
            int i = n;
            int j = maxWeight;

            while (i > 0 && j > 0) {
                if (dp[i][j] != dp[i - 1][j]) {
                    selectedItems.add(items.get(i - 1).getId());
                    j -= items.get(i - 1).getWeight();
                }
                i--;
            }

            return selectedItems;
        }


    public void validateRequest(PackageRequest packageRequest) {
        if (packageRequest.getItems().size() > 15) {
            throw new IllegalArgumentException("Maximum number of items exceeded (15).");
        }

        for (Item item : packageRequest.getItems()) {
            if (item.getWeight() > 100 || item.getPrice() > 100) {
                throw new IllegalArgumentException("Maximum weight or price exceeded (100 kg/€).");
            }
        }

        if (packageRequest.getMaxWeight() > 100) {
            throw new IllegalArgumentException("Maximum package weight exceeded (100 kg).");
        }
    }
    }

