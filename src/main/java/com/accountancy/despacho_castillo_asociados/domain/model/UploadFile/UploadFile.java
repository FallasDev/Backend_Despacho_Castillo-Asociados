package com.accountancy.despacho_castillo_asociados.domain.model.UploadFile;

public class UploadFile {

        private String id;
        private String url;
        private String secureUrl;
        private String format;
        private long size;

        public UploadFile() {
        }

        public UploadFile(String id, String url, String secureUrl, String format, long size) {
            this.id = id;
            this.url = url;
            this.secureUrl = secureUrl;
            this.format = format;
            this.size = size;
        }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSecureUrl() {
        return secureUrl;
    }

    public void setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
