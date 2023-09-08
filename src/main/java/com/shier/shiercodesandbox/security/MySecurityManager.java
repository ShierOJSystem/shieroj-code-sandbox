package com.shier.shiercodesandbox.security;

import java.security.Permission;

/**
 * 细粒度的权限检查
 *
 * @author Shier 2023/9/1 19:31
 */
public class MySecurityManager extends SecurityManager {


    /**
     * 检查所有权限
     *
     * @param perm
     */
    @Override
    public void checkPermission(Permission perm) {
        System.out.println("存在请求权限：" + perm.toString());
        // super.checkPermission(perm);
    }

    /**
     * 检查程序是否允许执行权限
     *
     * @param cmd the specified system command.
     */
    @Override
    public void checkExec(String cmd) {
        throw new SecurityException("checkExec 权限异常：" + cmd);
    }

    /**
     * 检查程序是否允许读文件
     *
     * @param file the system-dependent file name.
     */
    @Override
    public void checkRead(String file) {
        //throw new SecurityException("checkRead 权限异常：" + file);
    }

    /**
     * 检查程序是否可以写文件
     *
     * @param file the system-dependent filename.
     */
    @Override
    public void checkWrite(String file) {
        //throw new SecurityException("checkWrite 权限异常：" + file.toString());
    }

    /**
     * 检查程序是否可以连接网络
     *
     * @param host the host name port to connect to.
     * @param port the protocol port to connect to.
     */
    @Override
    public void checkConnect(String host, int port) {
        //throw new SecurityException("checkConnect 权限异常：" + host + port);
    }

    /**
     * 检查程序是否可以删除文件
     *
     * @param file the system-dependent filename.
     */
    @Override
    public void checkDelete(String file) {
        //throw new SecurityException("checkDelete 权限异常：" + file);
    }
}
