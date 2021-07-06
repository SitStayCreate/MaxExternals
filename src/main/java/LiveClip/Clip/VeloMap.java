package LiveClip.Clip;

import java.util.ArrayList;
import java.util.List;

public class VeloMap {
    //Velocities associated with each step - never should be more than 16
    private final List<Integer> mappedVelos;

    public VeloMap() {
        mappedVelos = new ArrayList<>(16);
        //100 is default velo
        for (int i = 0; i < 16; i++){
            mappedVelos.add(100);
        }
    }

    public List<Integer> getMappedVelos() {
        return mappedVelos;
    }

    public int getVelo(int index){
        return mappedVelos.get(index);
    }

    public void setVelo(int index, int velo){
        if(index >= mappedVelos.size()){
            return;
        }
        mappedVelos.set(index, velo);
    }

    public void setMappedVelos(List<Integer> velos){
        if(velos.size() > mappedVelos.size()){
            return;
        }
        for(int i = 0; i < velos.size(); i++){
            mappedVelos.set(i, velos.get(i));
        }
    }

    @Override
    public String toString() {
        return "VeloMap{" +
                "mappedVelos=" + mappedVelos +
                '}';
    }
}
