package com.sdajava.OtoczkaWypukla;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    List<Punkt> lista = new ArrayList<Punkt>();
    Punkt a;

    public void dodajPunkty(Punkt pkt) {
        this.lista.add(pkt);
    }

    public void szukanieA() {
        Punkt min = new Punkt(1000, 1000);
        int pozMin = 0;
        for (int i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).getY() < min.getY()) {
                min.y = this.lista.get(i).getY();
                min.x = this.lista.get(i).getX();
                pozMin = i;
            } else if (this.lista.get(i).getY() == min.getY() && this.lista.get(i).getX() < min.getX()) {
                min.y = this.lista.get(i).getY();
                min.x = this.lista.get(i).getX();
                pozMin = i;
            }
        }
        this.a = lista.get(pozMin);
        this.lista.remove(pozMin);
    }

    public void sortowaniePoKacie() {
        Punkt a; double kat;
        for (int j = 0; j < this.lista.size(); j++) {
            for (int i = 0; i < this.lista.size() - 1; i++) {
                double p1  = (double) this.lista.get(i).y / (this.lista.get(i).x + this.lista.get(i).y);
                double p2 = (double) this.lista.get(i+1).y / (this.lista.get(i+1).x + this.lista.get(i+1).y);

                if ( p1 > p2 ) {
                    a = this.lista.get(i);
                    this.lista.remove(i);
                    this.lista.add(i+1, a);
                }
            }
        }
    }
    public void wierzcholki() {
        Stack<Punkt> stos = new Stack<Punkt>();
        stos.push(this.a);
        stos.push(this.lista.get(0));
        Punkt a, b;

        for (int i = 2; i < this.lista.size(); i++){
            int det;
            b = null; a = null;
            b = stos.pop();
            a = stos.pop();

            det = a.x * b.y + b.x * this.lista.get(i).y + this.lista.get(i).x * a.y
                    - b.y * this.lista.get(i).x - this.lista.get(i).y * a.x - b.x * a.y;

            if ( det > 0) {
                stos.push(a);
                stos.push(b);
                stos.push(this.lista.get(i));
            }
            else if ( det < 0 ) {
                stos.push(a);
                i--;
            }
        }
        System.out.println("Wierzchołki otoczki");
        while (!stos.isEmpty()) {
            Punkt d;
            d = stos.pop();
            System.out.println(d.x + " " + d.y);
        }
    }

    public static void main(String[] args) {
        Main test = new Main();
        test.dodajPunkty(new Punkt(5,4));
        test.dodajPunkty(new Punkt(4,5));
        test.dodajPunkty(new Punkt(2,2));
        test.dodajPunkty(new Punkt(4,6));
        test.dodajPunkty(new Punkt(4,9));
        test.dodajPunkty(new Punkt(6,7));
        test.dodajPunkty(new Punkt(5,10));
        test.dodajPunkty(new Punkt(4,3));
        test.dodajPunkty(new Punkt(9,3));
        test.dodajPunkty(new Punkt(7,5));
        test.szukanieA();
        test.sortowaniePoKacie();
        test.wierzcholki();

    }
}
