package com.thomaskjh.protobuf;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;

public class Main {

    public static void main(String[] args) {

        // 在Any中存储消息
        ErrorStatus.A a = ErrorStatus.A.newBuilder().build();
        ErrorStatus.B b = ErrorStatus.B.newBuilder().build();
        ErrorStatus.Error error = ErrorStatus.Error.newBuilder()
                .addDetails(Any.pack(a))
                .addDetails(Any.pack(b)).build();

        // 从Any中取出消息
        for(Any detail : error.getDetailsList()) {
            if(detail.is(ErrorStatus.A.class)) {
                try {
                    ErrorStatus.A aa = detail.unpack(ErrorStatus.A.class);
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            } else if(detail.is(ErrorStatus.B.class)) {
                try {
                    ErrorStatus.B bb = detail.unpack(ErrorStatus.B.class);
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
