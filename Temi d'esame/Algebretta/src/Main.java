/*

Copyright 2022 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/


import java.util.Scanner;

public class Main {

  public static MatriceInterface valueOf(final char tipo, final int[][] arr) {
    switch (tipo) {
      case ' ':
        return new MatriceDensa(arr);
      case 'Z':
        return new MatriceNulla(arr[0][0]);
      case 'I':
        return new MatriceIdentita(arr[0][0]);
      case 'D':
        return new MatriceDiagonale(arr[0]);
    }
    throw new IllegalArgumentException("Tipo non riconosciuto.");
  }

  public static void main(String[] args) {
    try (final Scanner s = new Scanner(System.in)) {
      while (s.hasNextLine()) {
        final String[] lor = Parser.partiOperazione(s.nextLine());
        final char op = lor[1].charAt(0);
        final String left = lor[0], right = lor[2];
        if (op == '+') {
          if (Parser.isVettore(left) && Parser.isVettore(right)) {
            Vettore u = new VettoreDenso(Parser.valoriVettore(left));
            Vettore v = new VettoreDenso(Parser.valoriVettore(right));
            System.out.println(u.piu(v));
          } else if (Parser.isMatrice(left) && Parser.isMatrice(right)) {
            MatriceInterface M = valueOf(Parser.tipoMatrice(left), Parser.valoriMatrice(left));
            MatriceInterface N = valueOf(Parser.tipoMatrice(right), Parser.valoriMatrice(right));
            System.out.println(M.somma(N));
          }
        } else { // op == '*', altrimenti partiOperazione solleva eccezione
          if (Parser.isScalare(left)) {
            int alpha = Parser.valoreScalare(left);
            if (Parser.isVettore(right)) {
              Vettore v = new VettoreDenso(Parser.valoriVettore(right));
              System.out.println(v.per(alpha));
            } else if (Parser.isMatrice(right)) {
              MatriceInterface M = valueOf(Parser.tipoMatrice(right), Parser.valoriMatrice(right));
              System.out.println(M.prodottoScalare(alpha));
            }
          } else if (Parser.isMatrice(left)) {
            MatriceInterface M = valueOf(Parser.tipoMatrice(left), Parser.valoriMatrice(left));
            if (Parser.isMatrice(right)) {
              MatriceInterface N = valueOf(Parser.tipoMatrice(right), Parser.valoriMatrice(right));
              System.out.println(M.prodottoMatriciale(N));
            } else if (Parser.isVettore(right)) {
              Vettore v = new VettoreDenso(Parser.valoriVettore(right));
              System.out.println("unsupported");
            }
          }
        }
      }
    }
  }
}
