package com.sep.carsharingbusiness.mediator;

public class RequestReply {
    public String Action;
    public String ObjType;
    public String ObjJson;

    public RequestReply(String action, String objType, String objJson) {
        Action = action;
        ObjType = objType;
        ObjJson = objJson;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getObjType() {
        return ObjType;
    }

    public void setObjType(String objType) {
        ObjType = objType;
    }

    public String getObjJson() {
        return ObjJson;
    }

    public void setObjJson(String objJson) {
        ObjJson = objJson;
    }
}
