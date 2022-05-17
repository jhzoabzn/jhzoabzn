package org.cleverframe.fastdfs.pool;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.cleverframe.fastdfs.conn.Connection;
import org.cleverframe.fastdfs.conn.SocketConnection;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 定义了被池化的对象的创建，初始化，激活，钝化以及销毁功能<br/>
 * 作者：LiZW <br/>
 * 创建时间：2016/11/20 19:33 <br/>
 */
public class PooledConnectionFactory extends BaseKeyedPooledObjectFactory<InetSocketAddress, Connection> {
    /**
     * 读取时间
     */
    private int soTimeout;

    /**
     * 连接超时时间
     */
    private int connectTimeout;

    /**
     * 字符集
     */
    private Charset charset;

    /**
     * 默认字符集
     */
    private static final String DEFAULT_CHARSET_NAME = "UTF-8";

    /**
     * 设置默认字符集
     */
    private String charsetName = DEFAULT_CHARSET_NAME;

    /**
     * 创建连接
     */
    @Override
    public Connection create(InetSocketAddress address) throws Exception {
        // 初始化字符集
        if (null == charset) {
            charset = Charset.forName(charsetName);
        }
        return new SocketConnection(address, soTimeout, connectTimeout, charset);
    }

    /**
     * 将对象池化pooledObject
     */
    @Override
    public PooledObject<Connection> wrap(Connection conn) {
        return new DefaultPooledObject<Connection>(conn);
    }

    @Override
    public void destroyObject(InetSocketAddress key, PooledObject<Connection> p) throws Exception {
        p.getObject().close();
    }

    @Override
    public boolean validateObject(InetSocketAddress key, PooledObject<Connection> p) {
        return p.getObject().isValid();
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }
}