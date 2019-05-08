/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.temp.impl.f;

import java.io.*;

/**
 * 模本服务基类
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class FtemplateService {

    /**
     * file方式读取模本文件转换成string
     * @param filePath
     * @throws IOException
     */
    protected String loadFile(String filePath) throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(filePath);
        if (is == null) {
            File file = new File(filePath);
            is = new FileInputStream(file);
        }

        StringBuilder sb = new StringBuilder();
        BufferedReader output = new BufferedReader(new InputStreamReader(is));
        String line = output.readLine();
        while (line != null) {
            sb.append(line);
            line = output.readLine();
            //必须 groovy}后面不能直接跟代码
            sb.append("\n");
        }
        output.close();
        return sb.toString();
    }
}
