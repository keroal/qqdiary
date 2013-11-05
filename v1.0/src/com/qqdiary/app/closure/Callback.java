package com.qqdiary.app.closure;

/**
 * 回调函数超类定义
 * @author Administrator
 *
 */
abstract public class Callback implements ICallback {
    protected ICallback parent;

    public Object callback(Object param) {
        Object result = callbackFunc(param);
        if(parent != null){
            parent.callback(param);
        }
        return result;
    }

    public Callback(){
        parent = null;
    }

    public Callback(ICallback parent){
        this.parent = parent;
    }

    abstract protected Object callbackFunc(Object param);

}