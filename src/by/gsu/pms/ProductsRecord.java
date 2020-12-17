package by.gsu.pms;

public class ProductsRecord {
    private int product_id;
    private int material_id;
    private int worker_id;
    private int making_time;

    public ProductsRecord(int product_id, int material_id, int worker_id, int making_time) {
        this.product_id = product_id;
        this.material_id = material_id;
        this.worker_id = worker_id;
        this.making_time = making_time;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }

    public int getMaking_time() {
        return making_time;
    }

    public void setMaking_time(int making_time) {
        this.making_time = making_time;
    }
}
