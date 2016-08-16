package com.tobiascode.marvelclient.rest.util;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.tobiascode.marvelclient.util.Configuration;
import com.tobiascode.marvelclient.rest.constants.Parameter;
import com.tobiascode.marvelclient.service.constants.Property;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CommonParametersTest {
    private final int LIMIT = 50;
    private final int OFFSET = 10;
    private final String ORDERBY = "somefield";
    private int maximumLimit;

    @Before
    public void initi(){
        maximumLimit = Integer.valueOf(Configuration.getProperty(Property.MAXIMUM_LIMIT).orElse("100"));
    }

    @Test
    public void getCommonThreeParameters_with_all_fields_constructor() {
        Map<String, Object> expectedParameters = buildParameters();
        expectedParameters.put(Parameter.OFFSET, OFFSET);
        expectedParameters.put(Parameter.LIMIT, LIMIT);
        expectedParameters.put(Parameter.ORDER_BY, ORDERBY);

        Map<String, Object> parameters = new CommonParameters(OFFSET, LIMIT, ORDERBY).getCommonThreeParameters();

        assertEquals(expectedParameters, parameters);
    }

    @Test
    public void getCommonThreeParameters_with_limit_larger_than_max_should_return_max() {
        Map<String, Object> expectedParameters = buildParameters();
        expectedParameters.put(Parameter.OFFSET, OFFSET);
        expectedParameters.put(Parameter.LIMIT, maximumLimit);
        expectedParameters.put(Parameter.ORDER_BY, ORDERBY);

        Map<String, Object> parameters = new CommonParameters(OFFSET, maximumLimit + maximumLimit, ORDERBY).getCommonThreeParameters();

        assertEquals(expectedParameters, parameters);
    }

    @Test
    public void getCommonThreeParameters_with_no_fields_constructor() {
        Map<String, Object> expectedParameters = buildParameters();

        Map<String, Object> parameters = new CommonParameters().getCommonThreeParameters();

        assertEquals(expectedParameters, parameters);
    }

    @Test
    public void getCommonThreeParameters_with_no_limit_should_have_default_limit() {
        Map<String, Object> expectedParameters = buildParameters();
        expectedParameters.put(Parameter.OFFSET, OFFSET);
        expectedParameters.put(Parameter.ORDER_BY, ORDERBY);

        Map<String, Object> parameters = new CommonParameters(OFFSET, ORDERBY).getCommonThreeParameters();

        assertEquals(expectedParameters, parameters);
    }

    @Test
    public void getCommonThreeParameters_with_only_offset_should_have_default_limit_and_orderby() {
        Map<String, Object> expectedParameters = buildParameters();
        expectedParameters.put(Parameter.OFFSET, OFFSET);

        Map<String, Object> parameters = new CommonParameters(OFFSET).getCommonThreeParameters();

        assertEquals(expectedParameters, parameters);
    }

    @Test
    public void getCommonThreeParameters_with_limit_bigger_than_max_limit_should_return_max_limit() {
        Map<String, Object> expectedParameters = buildParameters();
        expectedParameters.put(Parameter.OFFSET, OFFSET);
        expectedParameters.put(Parameter.ORDER_BY, ORDERBY);

        Map<String, Object> parameters = new CommonParameters(OFFSET, 10000, ORDERBY).getCommonThreeParameters();

        assertEquals(expectedParameters, parameters);
    }

    @Test
    public void getSingleLimitParameter_should_only_return_limit_parameter_of_one(){
        Map<String, Object> expectedParameters = new HashMap<>();
        expectedParameters.put(Parameter.LIMIT, 1);

        Map<String, Object> parameters = new CommonParameters().getSingleLimitParameter();

        assertEquals(expectedParameters, parameters);
    }

    @Test
    public void equals_and_hash_are_valid() {
        EqualsVerifier.forClass(CommonParameters.class).verify();
    }

    private Map<String, Object> buildParameters() {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put(Parameter.OFFSET, 0);
        parameters.put(Parameter.LIMIT, maximumLimit);
        parameters.put(Parameter.ORDER_BY, Parameter.DEFAULT_ORDER_BY);

        return parameters;
    }
}