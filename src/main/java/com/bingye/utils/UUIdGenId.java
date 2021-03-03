package com.bingye.utils;

import cn.hutool.core.lang.UUID;
import tk.mybatis.mapper.genid.GenId;

public class UUIdGenId implements GenId<String> {

    @Override
    public String genId(String s, String s1) {
        return UUID.randomUUID().toString(true);
    }
}
