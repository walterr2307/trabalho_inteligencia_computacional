package com.inteligencia.computacional;

public class Excecoes extends Exception {

    public void medidaException() {
        System.out.println("\nInsira um valor inteiro três e dez!\n");
    }

    public void numeroObjetosExpection(){
        System.out.println("\nInsira um valor entre um e metade da quantidade de casas!\n");
    }

    public void indiceObjetoException() {
        System.out.println("\nInsira um valor entre um e quatro!\n");
    }

    public void posicaoException(){
        System.out.println("\nInsira um valor entre zero e a medida correspondente menos um!\n");
    }
}
