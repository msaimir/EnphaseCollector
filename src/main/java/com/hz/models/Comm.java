package com.hz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by David on 22-Oct-17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comm {
    private int num;
    private int level;
    private PCU pcu;
    private ACB acb;
}
