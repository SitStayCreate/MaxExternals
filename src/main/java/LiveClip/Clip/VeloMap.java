package LiveClip.Clip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VeloMap {
    //Velocities associated with each step - never should be more than 16
    private final Map<Integer, Integer> mappedVelos;

    public VeloMap() {
        mappedVelos = new HashMap<>(16);
        //100 is default velo
        for (int i = 0; i < 16; i++){
            mappedVelos.put(i, 100);
        }
    }

    public List<Integer> getMappedVelos() {
        return (List<Integer>) mappedVelos.values();
    }

    public int getVelo(int index){
        return mappedVelos.get(index);
    }

    public void setVelo(int index, int velo){
        if(index >= mappedVelos.size()){
            return;
        }
        mappedVelos.put(index, velo);
    }

    public void reset(){
        //100 is default velo
        for (int i = 0; i < 16; i++){
            mappedVelos.put(i, 100);
        }
    }

    public void setMappedVelos(List<Integer> velos){
        if(velos.size() > mappedVelos.size()){
            return;
        }
        for(int i = 0; i < velos.size(); i++){
            mappedVelos.put(i, velos.get(i));
        }
    }

    @Override
    public String toString() {
        return "VeloMap{" +
                "mappedVelos=" + mappedVelos +
                '}';
    }
}
