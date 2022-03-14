package com.chenning.common.netty.nettyServer.util;


public class SslInfoMation {
    private boolean open = false;
    private String certFilePath = null;
    private String keyFilePath = null;

    public SslInfoMation() {
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getCertFilePath() {
        return certFilePath;
    }

    public void setCertFilePath(String certFilePath) {
        this.certFilePath = certFilePath;
    }

    public String getKeyFilePath() {
        return keyFilePath;
    }

    public void setKeyFilePath(String keyFilePath) {
        this.keyFilePath = keyFilePath;
    }
}
