package it.fabrick.meteo.classEnum;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true, description = "Error codes: \n" +
        "* `INTERNAL_ERROR` - Something went wrong\n" +
        "* `DATA_NOT_FOUND` - No data found\n" +
        "* `DATA_NOT_VALID` - Invalid data provided\n" +
        "")
public enum ErrorCode {

    INTERNAL_ERROR, DATA_NOT_FOUND, DATA_NOT_VALID
}
