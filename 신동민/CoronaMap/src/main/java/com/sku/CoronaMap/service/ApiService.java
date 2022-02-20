package com.sku.CoronaMap.service;

import java.util.List;

public interface ApiService {
    public List<String> CallCoronaApi(String start_date, String end_date);
}
