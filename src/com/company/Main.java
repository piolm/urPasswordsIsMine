package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.zip.CRC32;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        File fileManager = new File("src/res/passwords.txt");
        String passwordIn =
        scanner.next().replace("0x", "").replace("L", "");
        CRC32 crc32 = new CRC32();
        Long passwordToLong = Long.parseLong(passwordIn, 16);
        Scanner sc = new Scanner(fileManager);
        while (sc.hasNext()) {
            boolean flag = true;
            String currentPass = sc.next().replace("\n", "");
            for (int i = 1; i < 10000; i++) {
                String tmp = currentPass+i;
                crc32.update(tmp.getBytes(StandardCharsets.UTF_8));
                Long currentPassButLong = crc32.getValue();
//                System.out.println(tmp);
                if (currentPassButLong.equals(passwordToLong)) {
                    flag = false;
                    System.out.println(tmp);
                    break;
                }
                crc32.reset();
            }
            if (!flag) {
                break;
            }
        }
    }
}
