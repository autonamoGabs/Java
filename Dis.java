import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Dis implements Comparable<Dis>{
    String codigo;
    String nome;
    Float cr;

    Dis(String c, String n, Float crr){
        this.codigo = c;
        this.nome = n;
        this.cr = crr;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getCr() {
        return cr;
    }

    public void setCr(Float cr) {
        this.cr = cr;
    }

     @Override
    public int compareTo(Dis outro) {
        return this.getCr().compareTo(outro.getCr());
    }

    public static void main(String[]args){
        ArrayList<Dis> d = new ArrayList<>();
        String nomearq = "Nome Arquivo";
        try(BufferedReader leitor = new BufferedReader(new FileReader(nomearq))){
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String dados[] = linha.split("/");
                String cod = dados[0];
                String n = dados[1];
                Float cr = Float.parseFloat(dados[2]);
                Boolean est = false;
                Dis di = null;
                int k = 0;
                for(int c = 0; c < d.size(); c++){
                    if(d.get(c).getCodigo().equals(cod)){
                        di = d.get(c);
                        est = true;
                        k = c;
                        break;
                    }
                }
                if(est){
                    if(cr > di.getCr()){
                     Dis novo = new Dis(cod,n,cr);
                     d.set(k,novo);
                    }
                }
                else{
                    d.add(new Dis(cod,n,cr));
                }
            }

        } catch(IOException e){
            e.printStackTrace();
        }
        Collections.sort(d, Collections.reverseOrder());

        try(BufferedWriter escritor = new BufferedWriter(new FileWriter("as.txt"))){
            for(Dis l : d){
                escritor.write(l.getCodigo() + "/"+ l.getNome() + "/" + l.getCr());
                escritor.newLine();
            }

        }
        catch(IOException e){
            e.printStackTrace();
            }
    }
}
