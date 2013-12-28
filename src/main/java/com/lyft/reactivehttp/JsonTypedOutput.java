package com.lyft.reactivehttp;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by zakharov on 12/15/13.
 */
class JsonTypedOutput implements TypedOutput {
    private final Object data;
    private Gson gson;
    private byte[] jsonBytes;

    public JsonTypedOutput(Object data, Gson gson) {
        this.data = data;
        this.gson = gson;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String mimeType() {
        return "application/json; charset=UTF-8";
    }

    @Override
    public long length() {
        try {
            return getJsonBytes().length;
        } catch (UnsupportedEncodingException e) {
            return -1;
        }
    }

    @Override
    public void writeTo(OutputStream out) throws IOException {
        out.write(getJsonBytes());
    }

    private byte[] getJsonBytes() throws UnsupportedEncodingException {
        if (jsonBytes == null) {
            jsonBytes = gson.toJson(data).getBytes("UTF-8");
        }
        return jsonBytes;
    }
}