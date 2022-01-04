package com.dollarbarberhouse.backend.models;

import java.util.List;
import java.util.Map;

public abstract class BaseModel {
    public abstract List<String> getHeader();
    public abstract Map<String, Object> getMap();
}
