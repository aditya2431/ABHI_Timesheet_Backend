package com.example.timesheet.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import java.io.*;

public class CachedBodyHttpServletResponse extends HttpServletResponseWrapper {

    private ByteArrayOutputStream cachedBytes = new ByteArrayOutputStream();
    private ServletOutputStream outputStream;
    private PrintWriter writer;

    public CachedBodyHttpServletResponse(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() {
        if (outputStream == null) {
            outputStream = new ServletOutputStream() {
                @Override
                public boolean isReady() {
                    return true;
                }
                @Override
                public void setWriteListener(WriteListener listener) {
                    // Not implemented
                }
                @Override
                public void write(int b) {
                    cachedBytes.write(b);
                }
            };
        }
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() {
        if (writer == null) {
            writer = new PrintWriter(new OutputStreamWriter(cachedBytes));
        }
        return writer;
    }

    public byte[] getCapturedBody() {
        if (writer != null) {
            writer.flush();
        }
        return cachedBytes.toByteArray();
    }
}
