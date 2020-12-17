package by.gsu.pms;

public class Materials {
    private int material_id;
    private String material_name;
    private int material_cost;

    public Materials(int material_id, String material_name, int material_cost) {
        this.material_id = this.material_id;
        this.material_name = material_name;
        this.material_cost = material_cost;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public int getMaterial_cost() {
        return material_cost;
    }

    public void setMaterial_cost(int material_cost) {
        this.material_cost = material_cost;
    }
}
