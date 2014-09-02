/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep4;

import grafo.Arco;
import grafo.ComparatorVertice;
import grafo.Grafo;
import grafo.Vertice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.LerGrafoTXT;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class EP4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LerGrafoTXT leGrafo = new LerGrafoTXT();
        Grafo grafo = leGrafo.LerArquivo(args[0]);
        System.out.println("\nMatriz de distâncias");
        for (int i = 0; i < grafo.getVertices().size(); i++) {
            dijkstra(grafo, grafo.getVertices().get(i));
            imprimeMatrizDistancias(grafo);
        }
        System.out.println();
    }

    public static void dijkstra(Grafo grafo, Vertice verticeInicial) {
        for (Vertice vertice : grafo.getVertices()) {
            vertice.setDistancia(Double.POSITIVE_INFINITY);
            vertice.setPai("NIL");
        }
        verticeInicial.setDistancia(0);
        List<Vertice> filaPrioridade = new ArrayList<>();
        filaPrioridade.addAll(grafo.getVertices());
        
        while (!filaPrioridade.isEmpty()) {
            Vertice u = Collections.min(filaPrioridade, new ComparatorVertice());
            filaPrioridade.remove(u);
            for (Arco arco : u.getArcos()) {
                Vertice v = arco.getVerticeDestino();
                if ((u.getDistancia() + arco.getPeso()) < v.getDistancia()) {
                    v.setDistancia(u.getDistancia() + arco.getPeso());
                    v.setPai(u.getNome());
                }
            }
        }
    }

    public static void imprimeMatrizDistancias(Grafo grafo) {
        System.out.println();
        for (Vertice vertice : grafo.getVertices()) {
            System.out.print(vertice.getDistancia() + "\t");
        }
    }
}
