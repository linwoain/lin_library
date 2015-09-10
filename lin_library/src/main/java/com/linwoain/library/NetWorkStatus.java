/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/22 16:34
 */
package com.linwoain.library;

/**
 * @author linwoain
 * @version 2014/10/22 16:34
 */
public enum NetWorkStatus {
    /**
     * 当前网络未连接
     */
    ALL_CLOSED("所有网络连接都已关闭"),
    /**
     * 当前wifi打开
     */
    WIFI_CONNECTED("wifi已连接"),
    /**
     * 当前手机3g流量打开
     */
    G3_CONNECTED("3g流量已打开"),
    /**
     * 当前手机2g流量打开
     */
    G2_CONNECTED("2g流量已打开"),
    /**
     * 当前手机3g流量打开
     */
    G4_CONNECTED("4g流量已打开"),
    /**
     * 当前手机通过蓝牙联网
     */
    BLUETOOTH_CONNECTED("蓝牙流量已打开");
    
    private final String value;

    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
    NetWorkStatus(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
