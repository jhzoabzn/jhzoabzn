package org.cleverframe.fastdfs.testbase;

import org.cleverframe.fastdfs.conn.Connection;
import org.cleverframe.fastdfs.conn.DefaultConnection;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 作者：LiZW <br/>
 * 创建时间：2016/11/20 12:53 <br/>
 */
public final class GetConnection {
    private static final InetSocketAddress address = new InetSocketAddress("192.168.56.139", 22122);
    private static final int soTimeout = 1500;
    private static final int connectTimeout = 600;
    private static final Charset charset = Charset.forName("UTF-8");

    public static Connection getDefaultConnection() {
        return new DefaultConnection(address, soTimeout, connectTimeout, charset);
    }
}
