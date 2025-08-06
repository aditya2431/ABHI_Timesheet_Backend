package com.example.timesheet.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.io.*;

import java.nio.charset.StandardCharsets;

public class DecryptedRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] decryptedBody;

    public DecryptedRequestWrapper(HttpServletRequest request, String decryptedBody) {
        super(request);
        this.decryptedBody = decryptedBody.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decryptedBody);

        return new ServletInputStream() {
            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }
            @Override
            public boolean isReady() {
                return true;
            }
            @Override
            public void setReadListener(ReadListener listener) {
                // Not implemented
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public int getContentLength() {
        return decryptedBody.length;
    }

    @Override
    public long getContentLengthLong() {
        return decryptedBody.length;
    }
}
