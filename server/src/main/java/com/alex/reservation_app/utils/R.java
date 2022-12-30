package com.alex.reservation_app.utils;

import com.alex.reservation_app.exception.ErrorObject;

public class R {
    private boolean flag;
    private Object data;
    private ErrorObject error;

    public R() {
    }

    public R(Object data) {
        this.flag = true;
        this.data = data;
    }

    public R(boolean flag) {
        this.flag = flag;
    }

    public R(ErrorObject error) {
        this.flag = false;
        this.error = error;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ErrorObject getError() {
        return error;
    }

    public void setError(ErrorObject error) {
        this.error = error;
    }
}
