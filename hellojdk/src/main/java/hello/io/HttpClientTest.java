package hello.io;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by min on 17-3-11.
 * TODO
 */
public class HttpClientTest {
    private String host;
    private String requestBody;

    @Before
    public void init() {
        this.host = "www.baidu.com";// www.baidu.com will not work "java.net.SocketException: Connection reset"

        StringBuffer request = new StringBuffer();
        request.append("GET " + "/" + " HTTP/1.1\r\n");
        request.append("Host: " + host + " \r\n");
        request.append("User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:51.0) Gecko/20100101 Firefox/51.0");
        request.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        request.append("Accept-Language: zh-CN,en-US;q=0.7,en;q=0.3");
        request.append("Accept-Encoding: gzip, deflate");
        request.append("Cookie: userid=1480592626460_up2nn26193; vjuids=a4c53b2c4.158f744782f.0.c4234314d1c228; vjlast=1481617209.1486884390.12; selCityName=%E5%B9%BF%E5%B7%9E; prov=cn020; city=020; weather_city=gd_gz; region_ip=120.85.69.x; region_ver=1.2; games_source_3962747416=3; ifengRotator_Ap4132=0; ifengRotator_AP794=2; ifengRotator_ArpAdPro_4080=0; ifengRotator_AP795=0; ifengRotator_Ap4081=1; ifengRotator_AP796=0; ifengRotator_AP4082=0; ifengRotator_AP797=0; ifengRotator_AP4083=0; ifengRotator_AP798=0; ifengRotator_AP4084=0; ifengRotator_AP4085=0; ifengRotator_AP4079=0; ifengRotator_AP4086=0");
//        request.append("Connection: keep-alive");
        request.append("Upgrade-Insecure-Requests: 1");
        request.append("Cache-Control: max-age=0");
        request.append("\r\n");
        this.requestBody = request.toString();
    }

    @Test
    public void httpClientWithSocket() throws Exception {
        Socket socket= new Socket();
        socket.connect(new InetSocketAddress(this.host, 80));
        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());

        osw.write(this.requestBody);
        osw.flush();
        socket.shutdownOutput();

        BufferedReader isw = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
        String line = null;

        System.out.println("Read to reading response...");
        while ((line = isw.readLine()) != null) {
            System.out.println(line);
        }
        osw.close();
        socket.close();
    }

    @Test
    public void httpClientWithNioSync() throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress(this.host, 80));
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(this.requestBody.getBytes());

        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }

        channel.shutdownOutput();

        System.out.println("before read");

        buffer.clear();

        int read = channel.read(buffer);
        System.out.println("after read: " + read);

        while (read != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array(), "utf-8"));
            buffer.clear();
            System.out.println("before read2");
            read = channel.read(buffer);
            System.out.println("after read: " + read);
        }
        channel.close();
    }

    @Test
    public void httpClientWithNioAsync() throws Exception {
        String host = "www.baidu.com";
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(host, 80));
        // connect　异步模式下立即返回，需要轮循是否链接成功
        while (!channel.finishConnect()) {
            System.out.print(".");
        }
        System.out.println("\n连接成功");
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(this.requestBody.getBytes());

        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }

        channel.shutdownOutput();

        System.out.println("before read");

        buffer.clear();

        int read = channel.read(buffer);
        System.out.println("after read: " + read);

        while (read != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array(), "utf-8"));
            buffer.clear();
            System.out.println("before read2");
            read = channel.read(buffer);
            System.out.println("after read: " + read);
        }
        channel.close();
    }

    @Test
    public void httpClientWithSelector() throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        final Selector selector = Selector.open();
        channel.connect(new InetSocketAddress("www.ifeng.com", 80));
        channel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {
            System.out.println("loop start");
            selector.select(); // 这里会阻塞，实际情况下单独一个线程用来做这件事
            System.out.println("selected");

            Iterator iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = (SelectionKey) iter.next();
                iter.remove();
                System.out.println("selecte one");

                if (key.isConnectable()) {
                    System.out.println("connectable");

                    SocketChannel client = (SocketChannel) key.channel();
                    if (client.isConnectionPending()) {
                        client.finishConnect();
                    }
                    client.configureBlocking(false);
//                    client.write(ByteBuffer.wrap("向服务端发送了一条信息".getBytes()));
                    System.out.println("connected register op_read");

                    client.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // read
                    System.out.println("readable");

                    // TODO
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(100);
                    int c = 0;
                    while ((c = client.read(buffer)) != -1) {
                        buffer.flip();
                        System.out.println(Arrays.toString(buffer.array()));
                        buffer.clear();
                    }
                    return;
                }
            }
        }
    }
}
