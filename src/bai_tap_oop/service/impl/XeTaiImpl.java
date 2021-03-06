package bai_tap_oop.service.impl;

import bai_tap_oop.models.OTo;
import bai_tap_oop.models.XeMay;
import bai_tap_oop.models.XeTai;
import bai_tap_oop.service.IXeTaiService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class XeTaiImpl implements IXeTaiService {
    static List<XeTai> xeTaiList = new ArrayList<>();
    public void write() {

        try {
            FileWriter fileWriter = new FileWriter("src/bai_tap_oop/data/XeTaiCsv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (XeTai xeTai:xeTaiList) {
                bufferedWriter.write(xeTai.getBienKiemSoat() + "," + xeTai.getTenHangSanXuat() + "," + xeTai.getNamSanXuat() + "," + xeTai.getChuSoHuu() + "," + xeTai.getTaiTrong());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void read(){
        try {
            FileReader fileReader=new FileReader("src/bai_tap_oop/data/XeTaiCsv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String[]    data;
            String line;
            XeTai xeTai;
            while ((line =bufferedReader.readLine())!=null){
                data =line.split(",");
                xeTai=new XeTai(data[0],data[1],Integer.parseInt(data[2]),data[3],Integer.parseInt(data[4]));
                xeTaiList.add(xeTai);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void themMoiXeTai() {


        Scanner scanner = new Scanner(System.in);
        System.out.println("hay nhap bien kiem soat");
        String bienKiemSoat = scanner.nextLine();
        while(!(bienKiemSoat.matches("[0-9]{2}[A-Z]{1}[-][0-9]{3}[.][0-9]{2}"))) {
            System.out.println("Sai dinh dang,hay nhap lai");
            bienKiemSoat = scanner.nextLine();
        }

        String hangSanXuat ;
        while (true) {
            System.out.println("hay nhap hang san xuat");
            hangSanXuat = scanner.nextLine();
            if (hangSanXuat.trim().equals("")) {
                System.out.println("nhap lai");
            } else {
                break;
            }
        }

        System.out.println("hay nhap nam san xuat");
        int namSanXuat = Integer.parseInt(scanner.nextLine());
        System.out.println("hay nhap chu so huu");
        String chuSoHuu = scanner.nextLine();
        System.out.println("hay nhap tai trong");
        int taiTrong = Integer.parseInt(scanner.nextLine());
        while (taiTrong<0){
            System.out.println("tai trong khong the am, hay nhap lai");
            taiTrong=Integer.parseInt(scanner.nextLine());
        }
        XeTai xeTai = new XeTai(bienKiemSoat, hangSanXuat, namSanXuat, chuSoHuu, taiTrong);
        xeTaiList.add(xeTai);
    }

    @Override
    public void hienThiXeTai() {
        for (XeTai xeTai : xeTaiList) {
            System.out.println(xeTai);
        }
    }

    @Override
    public void xoaXeTai() {
        Scanner scanner = new Scanner(System.in);
        String xoaBienKiemSoat = scanner.nextLine();
        for (int i = 0; i < xeTaiList.size(); i++) {
            if (xeTaiList.get(i).getBienKiemSoat().equals(xoaBienKiemSoat)) {
                xeTaiList.remove(i);
            }
        }

    }
}
