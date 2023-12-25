When running both the client and server on the same device, ensure that they are connected to the same Wi-Fi network. For socket communication, employ "localhost" as the IP address in the client part.
However, if using different devices, follow these steps:

First:
Add a Data class for the client as well:
----client.java----
import java.net.*;
import java.io.*;
import java.util.*;

    class Data implements Serializable {
        private String name;
        private InetAddress ip;
        private int port;
    
        public Data(String name, InetAddress ip, int port) {
            this.name = name;
            this.ip = ip;
            this.port = port;
        }
    
        public String getName() {
            return name;
        }
    
        public InetAddress getIp() {
            return ip;
        }
    
        public int getPort() {
            return port;
        }
    }
...

Second:
In the client class, modify the local IP section with the server's IP address. The server's IP address corresponds to the device running the server part.
