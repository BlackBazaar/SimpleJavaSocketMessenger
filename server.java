import java.net.*;
import java.io.*;
import java.util.Scanner;

class Data implements Serializable{
    private String name;
    private InetAddress ip;
    private int port;
    public Data(String name, InetAddress ip, int port){
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
public class server {

    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        InputStreamReader inputStreamReader= null;
        OutputStreamWriter outputStreamWriter= null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        ServerSocket socket = new ServerSocket(12345);



        while (true){
            System.out.println("waiting for connection...");
            Socket ss = socket.accept();


            inputStreamReader = new InputStreamReader(ss.getInputStream());
            outputStreamWriter = new OutputStreamWriter(ss.getOutputStream());

            //Message Stream
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedReader = new BufferedReader(inputStreamReader);

            //Information Stream
            ObjectInputStream objectInputStream = new ObjectInputStream(ss.getInputStream());
            Data data = (Data)objectInputStream.readObject();

            System.out.println(data.getName()+" has connected");

            while (true){

                String clientmessage = bufferedReader.readLine();
                System.out.println(data.getName()+":"+clientmessage);

                if (clientmessage.equalsIgnoreCase("bye")){
                    System.out.println(data.getName()+" has disconnected");
                    break;
                }

                System.out.print("Server:");
                String servermessage = scan.nextLine();

                bufferedWriter.write(servermessage);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
    }
}
