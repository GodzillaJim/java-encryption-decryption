package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String mode = "enc";
        String message = null;
        int key = 0;
        String result = "";
        String pathIn = null;
        String pathOut = null;
        int parameter = 0;


        for(int a=0;a<args.length;a++){
            if(args[a].equals("-mode")){
                mode = args[a+1];
            }else if(args[a].equals("-key")){
                key = Integer.parseInt(args[a+1]);
            }else if(args[a].equals("-in")){
                pathIn = args[a+1];
            }else if(args[a].equals("-data")){
                message = args[a+1];
                parameter++;
            }else if(args[a].equals("-out")){
                pathOut = args[a+1];
            }
        }
        if(parameter == 0){
            if(pathIn != null){
                File file = new File(pathIn);
                Scanner scanner = new Scanner(file);
                message = "";
                while(scanner.hasNext()){
                    message += scanner.next();
                }
            }else {
                message = "";
            }
        }

        if(mode.equals("enc")){
            for(int a=0;a<message.length();a++){
                int temp = message.codePointAt(a)+key;
                char newChar = (char)temp;
                result = result+newChar;
            }
        }else if(mode.equals("dec")){
            for(int a=0;a<message.length();a++){
                int temp =message.codePointAt(a)-key;
                char newChar = (char)temp;
                result = result + newChar;
            }
        }
        if(pathOut == null){
            System.out.println(result);
        }else{
            File file = new File(pathOut);
            PrintWriter writer = new PrintWriter(file);
            writer.print(result);
            writer.close();
        }
    }
}

