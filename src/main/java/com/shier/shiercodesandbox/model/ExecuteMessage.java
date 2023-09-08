package com.shier.shiercodesandbox.model;

import lombok.Data;
import org.apache.catalina.valves.LoadBalancerDrainingValve;

/**
 * 进程执行信息
 *
 * @author Shier 2023/9/1 14:39
 */
@Data
public class ExecuteMessage {

    private Integer exitValue;

    private String message;

    private String errorMessage;

    private Long time;

    private Long memory;

}
