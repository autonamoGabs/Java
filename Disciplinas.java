import java.io.*;
import java.util.*;

public class Disciplinas implements Comparable<Disciplinas>{
    String codigo;
    String nome;
    Float cr;

    public Disciplinas(String codigo, String nome, Float cr) {
        this.codigo = codigo;
        this.nome = nome;
        this.cr = cr;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Float getCr() {
        return cr;
    }

    public int compareTo(Disciplinas outro){
        return this.getCr().compareTo(outro.getCr());
    }

    public static void main(String[] args){
        ArrayList<ArrayList<Disciplinas>> todas = new ArrayList<>();
        String linha;
        String nomearq = "Nome do Arquivo";
        try(BufferedReader ln = new BufferedReader(new FileReader(nomearq))){
            while((linha = ln.readLine()) != null){
                String dados[] = linha.split("/");
                String cod = dados[0];
                String n = dados[1];
                Float cr = Float.parseFloat(dados[2]);
                int k = 0;
                Disciplinas nova = new Disciplinas(cod,n,cr);;
                Boolean est = false;
                for(int i = 0; i < todas.size(); i++){
                    if(todas.get(i).get(0).getCodigo().equals(cod)){
                        est = true;
                        k = i;
                        break;
                    }
                }
                if(est){
                    todas.get(k).add(nova);
                }
                else{
                    ArrayList<Disciplinas> nl = new ArrayList<>();
                    nl.add(nova);
                    todas.add(nl);

                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        for(int j = 0; j < todas.size(); j++){
            Collections.sort(todas.get(j));
        }

        String nomes = "Nome da Saída do Arquivo";
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomes))){
            for(ArrayList<Disciplinas> p : todas){
                for(Disciplinas da : p){
                    escritor.write(da.getCodigo() + "/" + da.getNome() + "/" + da.getCr());
                    escritor.newLine();
                }
                
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    
}
