import java.net.*;
import java.io.*;
import java.util.*;

public class client {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        Data data;

        Socket socket1 = new Socket("localhost",12345);
        System.out.println("Connected with Server");

        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        data = new Data(name,socket1.getLocalAddress(), socket1.getPort());


        inputStreamReader = new InputStreamReader(socket1.getInputStream());
        outputStreamWriter = new OutputStreamWriter(socket1.getOutputStream());



        //Message Stream
        bufferedWriter= new BufferedWriter(outputStreamWriter);
        bufferedReader = new BufferedReader(inputStreamReader);


        //Information Stream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());
        objectOutputStream.writeObject(data);

        while (true){

            System.out.print(data.getName()+":");
            String message = scan.nextLine();

            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            if (message.equalsIgnoreCase("bye")){
                socket1.close();
                break;
            }

            System.out.println("Server:" + bufferedReader.readLine());
        }

    }
}
