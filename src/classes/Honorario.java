/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.time.LocalDate;

/**
 *
 * @author Julio Cesar Nomelini
 */
public class Honorario {

    private int id;
    private int idCliente;
    private LocalDate data;
    private String desc_1;
    private String desc_2;
    private String desc_3;
    private String desc_4;
    private Double val_1;
    private Double val_2;
    private Double val_3;
    private Double val_4;

    public Honorario() {
        this.data = java.time.LocalDate.now();
    }

    public String periodoFormatado() {
        String dataFormatada = "";

        switch (this.data.getMonthValue()) {
            case 1:
                dataFormatada += "JAN/";
                break;
            case 2:
                dataFormatada += "FEV/";
                break;
            case 3:
                dataFormatada += "MAR/";
                break;
            case 4:
                dataFormatada += "ABR/";
                break;
            case 5:
                dataFormatada += "MAI/";
                break;
            case 6:
                dataFormatada += "JUN/";
                break;
            case 7:
                dataFormatada += "JUL/";
                break;
            case 8:
                dataFormatada += "AGO/";
                break;
            case 9:
                dataFormatada += "SET/";
                break;
            case 10:
                dataFormatada += "OUT/";
                break;
            case 11:
                dataFormatada += "NOV/";
                break;
            case 12:
                dataFormatada += "DEZ/";
                break;
            default:
                dataFormatada += "NUL/";
        }
        dataFormatada += this.data.getYear();
        return dataFormatada;
    }
    
    public String getMes(){
        return this.data.getMonthValue()+"";
    }
    
    public String getAno(){
        return this.data.getYear()+"";
    }

    public Double getTotal() {
        return this.val_1 + this.val_2 + this.val_3 + this.val_4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDesc_1() {
        return desc_1;
    }

    public void setDesc_1(String desc_1) {
        this.desc_1 = desc_1;
    }

    public String getDesc_2() {
        return desc_2;
    }

    public void setDesc_2(String desc_2) {
        this.desc_2 = desc_2;
    }

    public String getDesc_3() {
        return desc_3;
    }

    public void setDesc_3(String desc_3) {
        this.desc_3 = desc_3;
    }

    public String getDesc_4() {
        return desc_4;
    }

    public void setDesc_4(String desc_4) {
        this.desc_4 = desc_4;
    }

    public Double getVal_1() {
        return val_1;
    }

    public void setVal_1(Double val_1) {
        this.val_1 = val_1;
    }

    public Double getVal_2() {
        return val_2;
    }

    public void setVal_2(Double val_2) {
        this.val_2 = val_2;
    }

    public Double getVal_3() {
        return val_3;
    }

    public void setVal_3(Double val_3) {
        this.val_3 = val_3;
    }

    public Double getVal_4() {
        return val_4;
    }

    public void setVal_4(Double val_4) {
        this.val_4 = val_4;
    }

    @Override
    public String toString() {
        return "Honorario{" + "id=" + id + ", idCliente=" + idCliente + ", data=" + data + ", desc_1=" + desc_1 + ", desc_2=" + desc_2 + ", desc_3=" + desc_3 + ", desc_4=" + desc_4 + ", val_1=" + val_1 + ", val_2=" + val_2 + ", val_3=" + val_3 + ", val_4=" + val_4 + '}';
    }

}
