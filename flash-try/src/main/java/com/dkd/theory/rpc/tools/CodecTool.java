package com.dkd.theory.rpc.tools;

/**
 * 编解码工具
 * @author dkd
 */
public final class CodecTool {
    private CodecTool() {
    }

    public static byte[] encode(Object obj) {
        return JsonTool.toJson(obj).getBytes();
    }

    public static <T> T decode(byte[] data, Class<T> clazz) {
        return JsonTool.fromJson(new String(data), clazz);
    }
}
