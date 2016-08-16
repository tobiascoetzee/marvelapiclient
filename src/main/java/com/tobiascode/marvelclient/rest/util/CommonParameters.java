package com.tobiascode.marvelclient.rest.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.tobiascode.marvelclient.util.Configuration;
import com.tobiascode.marvelclient.rest.constants.Parameter;
import com.tobiascode.marvelclient.service.constants.Property;

public final class CommonParameters {
    private final int offset;
    private final int limit;
    private final String orderBy;

    public CommonParameters() {
        this(0, -1, Parameter.DEFAULT_ORDER_BY);
    }

    public CommonParameters(int offset) {
        this(offset, -1, Parameter.DEFAULT_ORDER_BY);
    }

    public CommonParameters(int offset, int limit) {
        this(offset, limit, Parameter.DEFAULT_ORDER_BY);
    }

    public CommonParameters(int offset, String orderBy) {
        this(offset, -1, orderBy);
    }

    public CommonParameters(int offset, int limit, String orderBy) {
        this.offset = offset;
        this.limit = limit == -1 ? Integer.valueOf(Configuration.getProperty(Property.MAXIMUM_LIMIT).orElse("100")) : limit;
        this.orderBy = orderBy;
    }

    public Map<String, Object> getCommonThreeParameters() {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put(Parameter.ORDER_BY, orderBy);
        parameters.put(Parameter.LIMIT, maxLimit(limit));
        parameters.put(Parameter.OFFSET, offset);

        return parameters;
    }

    public Map<String, Object> getSingleLimitParameter() {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put(Parameter.LIMIT, 1);

        return parameters;
    }

    private static int maxLimit(int totalItems) {
        int maximumLimit = Integer.parseInt(Configuration.getProperty(Property.MAXIMUM_LIMIT).orElse("100"));
        return totalItems > maximumLimit ? maximumLimit : totalItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonParameters that = (CommonParameters) o;
        return offset == that.offset &&
                limit == that.limit &&
                Objects.equals(orderBy, that.orderBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offset, limit, orderBy);
    }
}
