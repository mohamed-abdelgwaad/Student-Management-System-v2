package com.sms.studentSystem.util;

import org.modelmapper.ModelMapper;


public class ModelMapperUtil {

    /**
     * the single instance of ModelMapper
     */
    private static final ModelMapper modelMapper = new ModelMapper();


    private ModelMapperUtil() {

        /**
         * private constructor
         */
    }

    /**
     * Retrieves the shared instance of ModelMapper
     *
     * @return instance of ModelMapper
     */


    public static ModelMapper MAPPER() {
        return modelMapper;
    }
}
