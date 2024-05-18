package br.com.ifsp.vcRiquinho;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TesteEnv {
	public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(".env");
            prop.load(input);

            String dbHost = prop.getProperty("LOCAL_POSTGRES_URL");
            String dbUser = prop.getProperty("LOCAL_POSTGRES_USER");
            String dbPass = prop.getProperty("LOCAL_POSTGRES_PASSWORD");

            System.out.println("DB_HOST = " + dbHost);
            System.out.println("DB_USER = " + dbUser);
            System.out.println("DB_PASS = " + dbPass);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
